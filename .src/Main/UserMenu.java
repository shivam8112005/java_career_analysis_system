import java.util.*;
import java.sql.ResultSet;
import java.sql.Statement;
public class UserMenu extends User {
    private static final UserService userService = new UserService();
    static Scanner sc=new Scanner(System.in);
    User u;
    int choice;
    public  void signUpMenu() throws Exception{
        boolean exit = true;
        
        while (exit) {
            System.out.println();
            System.out.println("User Menu:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Return");

            System.out.print("Enter your choice: ");
             choice = sc.nextInt();
            

            switch (choice) {
                case 1:
                System.out.println("Sign Up: ");
                   customerLogIn();
                    break;
                case 2:
                System.out.println("Log In: ");
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
    public static void main(String[] args) throws Exception{
        
        // String querry="SELECT * FROM users";
        // Statement st=DatabaseUtil.getConnection().createStatement();
        // ResultSet rs=st.executeQuery(querry);
        // while(rs.next()){

        // }
        UserMenu um=new UserMenu();
        um.signUpMenu();
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
        if(User.getUserByEmail(input)!=null){
            b=true;
            if(choice==1){
                System.out.println("Email already exists!");
            }else{
            while(true){
            System.out.println("enter password: ");
            String password=sc.next();
            if(password.equals(User.getUserByEmail(input).getPassword())){
                u=User.getUserByEmail(input);
                userMenu(u);
                break;
            }else{
               System.out.println("pasword incorrect!");
               System.out.println("Reset password ?(y/n): ");
               String choice =sc.next();
               if(choice.equalsIgnoreCase("y")){
                System.out.println("Enter UserId: ");
                int userid=sc.nextInt();
                if(userid==User.getUserByEmail(input).getId()){
                   setPassword(u);
                   System.out.println("Password Updated successfully");
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
            userMenu(u);
        }
        else{
            System.out.println("User Not found!");
        System.out.println("Don't Have an Account? want to sign up ? (y/n):");
        String ch=sc.next();
        if(ch.equalsIgnoreCase("y")){
           createUser(input);
           userMenu(u);
        }
        }
       }
    }
    
    private  void createUser(String email) throws Exception{
        this.u = new User();
        setPassword(u);
        System.out.print("Enter name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("Enter Education: ");
        String edu=sc.nextLine();
        System.out.print("Enter Location: ");
        String location=sc.nextLine();
        userService.personalityAssessment(u.pt,  this.u);
        u.pt++;
        u.setName(name);
        u.setEmail(email);
        u.setSkill(u);
        u.setLocation(location);
       u.setEducation(edu);
        userService.addUser(u,email);
        System.out.println("User created and signed in successfully.");
    }
    public void userMenu(User u) throws Exception{
       
        boolean exit=true;
        while (exit) {
            System.out.println("Career Analysis System");
           System.out.println("1. Profile");
           System.out.println("2. Assessments");
           System.out.println("3. Career Matching");
           System.out.println("4. Search User");
           System.out.println("5. Log out");
            System.out.print("Choose an option: ");
          int choice=sc.nextInt();
            
           
            switch (choice) {
                case 1:
                    profile(u);
                    break;
                case 2:
                   assessments();
                    break;
                case 3:
                userService.interestProfiling();
                    break;
                case 4:
                   
                    break;

                case 5:
                System.out.println("Logged out successfully.");
                exit=false;
                    break;
               
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public void assessments(){
        System.out.println("1. Skilled Assessment");
        System.out.println("2. Personality Assessment");
        System.out.println("3. ResultLog");
        int choice=sc.nextInt();
        switch (choice) {
            case 1:
            userService.skillAssessment();
                break;
            case 2:
            userService.personalityAssessment(pt,u);
            pt++;
                break;    
            case 3:
            System.out.println("1. skilled Assessment resultlog");
            System.out.println("2. Personality Assessment Resultlog");
            int c=sc.nextInt();
            if(c==1){
                userService.printResultLog();
            }else if(c==2){
                userService.personalityAssessmentResultLog();
            }else{
                System.out.println("Invalid option. Try again.");
            }
                break;
        }
    }
   
}
