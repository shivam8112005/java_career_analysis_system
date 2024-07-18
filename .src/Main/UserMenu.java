import java.util.Scanner;

public class UserMenu extends User {
    private static final UserService userService = new UserService();
    Scanner sc=new Scanner(System.in);
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

    public void customerLogIn() throws Exception{
        while (true) {
            System.out.print("Enter email: ");
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
                userMenu();
                break;
            }else{
               System.out.println("pasword incorrect!");
               System.out.println("Reset password ?(y/n): ");
               String choice =sc.next();
               if(choice.equalsIgnoreCase("y")){
                System.out.println("Enter UserId: ");
                int userid=sc.nextInt();
                if(userid==User.getUserByEmail(input).getId()){
                   setPassword();
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
            userMenu();
        }
        else{
            System.out.println("User Not found!");
        System.out.println("Don't Have an Account? want to sign up ? (y/n):");
        String ch=sc.next();
        if(ch.equalsIgnoreCase("y")){
           createUser(input);
           userMenu();
        }
        }
       }
    }
    //************************************************** also take educational details of user */
    private  void createUser(String email) throws Exception{
        setPassword();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter skills: ");
        String skills = sc.nextLine();
        System.out.print("Enter interests: ");
        String interests = sc.nextLine();
        System.out.print("Enter personality traits: ");
        String personalityTraits = sc.nextLine();
         this.u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setSkills(skills);
        u.setInterests(interests);
        u.setPersonalityTraits(personalityTraits);
        userService.addUser(u,email);
        System.out.println("User created and signed in successfully.");
    }
    public void userMenu(){

    }
}
