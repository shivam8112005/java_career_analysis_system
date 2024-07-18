
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CareerService careerService = new CareerService();
    public static void main(String[] args) throws Exception {
        if(DatabaseUtil.getConnection()!=null){
            System.out.println("success");
        }else{
            System.out.println("fail");
        }
        while (true) {
            System.out.println("Career Analysis System");
            System.out.println("1. Sign In");
            System.out.println("2. Add Career");
            System.out.println("3. Get User by Email");
            System.out.println("4. Get Career by ID");
            System.out.println("5. Exit");
            System.out.println("6. interest profiling");
            System.out.println("7. skilled assessment");
            System.out.println("8. Result Log");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    addCareer();
                    break;
                case 3:
                    getUserByEmail();
                    break;
                case 4:
                    getCareerById();
                    break;
                case 5:
                    System.exit(0);
                    break;
                case 6:
                userService.interestProfiling();
                break;
                case 7:
                userService.skillAssessment();
                break;
                case 8:
                userService.printResultLog();
                break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    private static void addCareer() throws Exception{
        System.out.print("Enter career name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter required skills: ");
        String requiredSkills = scanner.nextLine();
        System.out.print("Enter educational requirements: ");
        String educationalRequirements = scanner.nextLine();
        System.out.print("Enter industry insights: ");
        String industryInsights = scanner.nextLine();
        Jobs career = new Jobs();
        career.setName(name);
        career.setDescription(description);
        career.setRequiredSkills(requiredSkills);
        career.setEducationalRequirements(educationalRequirements);
        career.setIndustryInsights(industryInsights);
        careerService.addCareer(career);
        System.out.println("Career added successfully.");
    }
//old
    private static void getUserById() throws Exception{
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        User user = userService.getUserById(id);
        if (user != null) {
            System.out.println("User Details:");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Skills: " + user.getSkills());
            System.out.println("Interests: " + user.getInterests());
            System.out.println("Personality Traits: " + user.getPersonalityTraits());
        } else {
            System.out.println("User not found.");
        }
    }
    private static void getCareerById() throws Exception{
        System.out.print("Enter career ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Jobs career = careerService.getCareerById(id);
        if (career != null) {
            System.out.println("Career Details:");
            System.out.println("ID: " + career.getId());
            System.out.println("Name: " + career.getName());
            System.out.println("Description: " + career.getDescription());
            System.out.println("Required Skills: " + career.getRequiredSkills());
            System.out.println("Educational Requirements: " + career.getEducationalRequirements());
            System.out.println("Industry Insights: " + career.getIndustryInsights());
        } else {
            System.out.println("Career not found.");
        }
    }
    private static void signIn() throws Exception{
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            System.out.println("User signed in successfully.");
            System.out.println("Welcome back, " + user.getName());
        } else {
            createUser(email);
        }
    }
    private static void createUser(String email) throws Exception{
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter skills: ");
        String skills = scanner.nextLine();
        System.out.print("Enter interests: ");
        String interests = scanner.nextLine();
        System.out.print("Enter personality traits: ");
        String personalityTraits = scanner.nextLine();
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setSkills(skills);
        newUser.setInterests(interests);
        newUser.setPersonalityTraits(personalityTraits);
        userService.addUser(newUser);
        System.out.println("User created and signed in successfully.");
    }
    private static void getUserByEmail() throws Exception {
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            System.out.println("User Details:");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Skills: " + user.getSkills());
            System.out.println("Interests: " + user.getInterests());
            System.out.println("Personality Traits: " + user.getPersonalityTraits());
        } else {
            System.out.println("User not found.");
        }
    }
}