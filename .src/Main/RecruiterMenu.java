import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class RecruiterMenu extends Recruiter{
     static User u=new User();
    static Scanner sc=new Scanner(System.in);
    Recruiter r;
    int choice;
    DatabaseUtil du=new DatabaseUtil();
    InputValidator ip=new InputValidator();
    public  void signUpMenu() throws Exception{
        boolean exit = true;
        
        while (exit) {
            System.out.println("================================== Recruiter Menu ==================================");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Return");

            System.out.print("Enter your choice: ");
             choice = sc.nextInt();
            switch (choice) {
                case 1:
                System.out.println("================================== Sign Up =============================");
                   customerLogIn();
                    break;
                case 2:
                System.out.println("============================== Log In ===============================");
                   customerLogIn();
                    break;
                case 3:
                    exit = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void customerLogIn() throws Exception{
        while (true) {
            System.out.print("Enter email: ");
            //sc.next();
            String input = sc.next();
            if (isValidEmail(input)) {
                findUserByEmail(input);
                break;
            }
            System.out.println("Invalid email. Please re-enter the email.");
        }
    }
  
    private void findUserByEmail(String input) throws Exception{
        boolean b=false;
        if(Recruiter.getRecruiterByEmail(input)!=null){
            b=true;
            if(choice==1){
                System.out.println("Email already exists!");
            }else{
            while(true){
            System.out.print("enter password: ");
            String password=sc.next();
            String n1=ip.encryptPassword(password);
            if(n1.equals(Recruiter.getRecruiterByEmail(input).getPassword())){
                r=Recruiter.getRecruiterByEmail(input);
                System.out.println("---------------------------------- Welcome Again! ---------------------------------------");
                recruiterMenu(u);
                break;
            }else{
               System.out.println("pasword incorrect!");
               System.out.println("Reset password ?(y/n): ");
               String choice =sc.next();
               if(choice.equalsIgnoreCase("y")){
                r=Recruiter.getRecruiterByEmail(input);
                System.out.println("Enter Recruiter Id: ");
                int userid=sc.nextInt();
                if(userid==Recruiter.getRecruiterByEmail(input).getId()){
                    r.setPassword();
                    String n=ip.encryptPassword(r.getPassword());
                    String querry="UPDATE recruiters SET password = ? WHERE email = ?";
                    PreparedStatement cst=DatabaseUtil.getConnection().prepareStatement(querry);
                    cst.setString(1, n);
                    cst.setString(2,r.getEmail());
                    int b1=cst.executeUpdate();
                    if(b1>0){
                        System.out.println("Password Updated Successfully.");
                    }else{
                        System.out.println("Password Not Updated !");
                    }
                   break;
                }else{
                    System.out.println("incorrect user id! login failed");
                    break;
                }
               }
            }
        }}
        }
       if(!b){
        if(choice==1){
            createUser(input);
            recruiterMenu(u);
        }
        else{
            System.out.println("User Not found!");
        System.out.println("Don't Have an Account? want to sign up ? (y/n):");
        String ch=sc.next();
        if(ch.equalsIgnoreCase("y")){
           createUser(input);
           recruiterMenu(u);
        }
        }
       }
    }

    private  void createUser(String email) throws Exception{
        this.r = new Recruiter();
        r.setPassword();
        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Phonenumber: ");
        long pn=sc.nextLong();
        while(pn<6000000000l || pn>9999999999l){
            System.out.println("Enter Valid Phonenumber !");
            pn=sc.nextLong();
        }sc.nextLine();
        System.out.print("Enter Location: ");
        String location=sc.nextLine();
        System.out.print("Enter Company name: ");
        String companyname=sc.nextLine();
        String querry="SELECT id FROM recruiters WHERE companyname = ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        pst.setString(1, companyname);
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            System.out.println("Company's Recruiter Already Exist !");
            System.out.print("Enter company name: ");
            companyname=sc.nextLine();
            rs=pst.executeQuery();
            pst.setString(1, companyname);
        }
        r.setCompanyname(companyname);
        System.out.println();
        r.setPhonenumber(pn);
        r.setName(name);
        r.setEmail(email);
        r.setLocation(location);
        du.addRecruiter(r,email);
        System.out.println("User created and signed in successfully.");
    }
    public void recruiterMenu(User u) throws Exception{
       
        boolean exit=true;
        while (exit) {
            System.out.println("============================== Career Analysis System ===================================");
           System.out.println("1. Profile");
           System.out.println("2. Add Job Listings");
           System.out.println("3. Search User");
           System.out.println("4. view applications");
           System.out.println("5. View Posted Job Listings");
           System.out.println("6. Log out");
            System.out.print("Choose an option: ");
          int choice=sc.nextInt();
            switch (choice) {
                case 1: 
                r.profile();
                    break;
                case 2:
                r.addJobListings();
                 //  assessments();
                    break;
                case 3:
                User u1=new User(); 
                u1.searchUser();
               // userService.interestProfiling();
                    break;
                case 4:
               r.viewApplications();
                    break;

                case 5:
                r.postedJobs();
                    break;
                    case 6:
                    System.out.println("Logged out successfully.");
                    exit=false;
                    break;
               
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
