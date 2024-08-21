
import java.io.File;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CareerService careerService = new CareerService();
    public static void main(String[] args) throws Exception {
        String sql="Insert into users(resume) values(?)";
   
    // String sql1="update users set resume=? where id=?";
    // PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(sql1);
    // File f=new File("D:/shivam/temp.txt");
    // FileReader fr=new FileReader(f);
    // pst.setCharacterStream(1, fr);
    // pst.setInt(2, 1);
    // pst.executeUpdate();
        while (true) {
            System.out.println("=============================== Choose Account Type ==============================");
            System.out.println("1. Jobseeker");
            System.out.println("2. Recruiter");
            System.out.println("3. Exit");
            int ch=scanner.nextInt();
            switch (ch) {
                case 1:
                UserMenu um=new UserMenu();
                um.signUpMenu();
                    break;
                    case 2:
                    RecruiterMenu r=new RecruiterMenu();
                   r.signUpMenu();
                   break;
                   case 3:
                   System.exit(0);
                   break;
                default:
                System.out.println("Enter Valid Input!");
                    break;
            }
            }
       
    }
    
    
//     private static void addCareer() throws Exception{
//         System.out.print("Enter career name: ");
//         String name = scanner.nextLine();
//         System.out.print("Enter description: ");
//         String description = scanner.nextLine();
//         System.out.print("Enter required skills: ");
//         String requiredSkills = scanner.nextLine();
//         System.out.print("Enter educational requirements: ");
//         String educationalRequirements = scanner.nextLine();
//         System.out.print("Enter industry insights: ");
//         String industryInsights = scanner.nextLine();
//         Jobs career = new Jobs();
//         career.setName(name);
//         career.setDescription(description);
//         //career.setRequiredSkills(requiredSkills);
//         career.setEducationalRequirements(educationalRequirements);
//         career.setIndustryInsights(industryInsights);
//         careerService.addCareer(career);
//         System.out.println("Career added successfully.");
//     }
// //old
//     private static void getUserById() throws Exception{
//         System.out.print("Enter user ID: ");
//         int id = scanner.nextInt();
//         scanner.nextLine();
//         User user = userService.getUserById(id);
//         if (user != null) {
//             System.out.println("User Details:");
//             System.out.println("ID: " + user.getId());
//             System.out.println("Name: " + user.getName());
//             System.out.println("Email: " + user.getEmail());
//             System.out.println("Skills: " + user.getSkills());
//            // System.out.println("Interests: " + user.getInterests());
//             System.out.println("Personality Traits: " + user.getPersonalityTraits());
//         } else {
//             System.out.println("User not found.");
//         }
//     }
//     private static void getCareerById() throws Exception{
//         System.out.print("Enter career ID: ");
//         int id = scanner.nextInt();
//         scanner.nextLine();
//         Jobs career = careerService.getCareerById(id);
//         if (career != null) {
//             System.out.println("Career Details:");
//             System.out.println("ID: " + career.getId());
//             System.out.println("Name: " + career.getName());
//             System.out.println("Description: " + career.getDescription());
//            // System.out.println("Required Skills: " + career.getRequiredSkills());
//             System.out.println("Educational Requirements: " + career.getEducationalRequirements());
//             System.out.println("Industry Insights: " + career.getIndustryInsights());
//         } else {
//             System.out.println("Career not found.");
//         }
//     }
    
//     private static void getUserByEmail() throws Exception {
//         System.out.print("Enter user email: ");
//         String email = scanner.nextLine();
//         User user = userService.getUserByEmail(email);
//         if (user != null) {
//             System.out.println("User Details:");
//             System.out.println("ID: " + user.getId());
//             System.out.println("Name: " + user.getName());
//             System.out.println("Email: " + user.getEmail());
//             System.out.println("Skills: " + user.getSkills());
//            // System.out.println("Interests: " + user.getInterests());
//             System.out.println("Personality Traits: " + user.getPersonalityTraits());
//         } else {
//             System.out.println("User not found.");
//         }
//     }
}
