

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


 class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/career_analysis_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Shuklas@#303";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


 class User {
    private int id;
    private String name;
    private String email;
    private String skills;
    private String interests;
    private String personalityTraits;



     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getSkills() {
         return skills;
     }

     public void setSkills(String skills) {
         this.skills = skills;
     }

     public String getInterests() {
         return interests;
     }

     public void setInterests(String interests) {
         this.interests = interests;
     }

     public String getPersonalityTraits() {
         return personalityTraits;
     }

     public void setPersonalityTraits(String personalityTraits) {
         this.personalityTraits = personalityTraits;
     }
 }


 class Career {
    private int id;
    private String name;
    private String description;
    private String requiredSkills;
    private String educationalRequirements;
    private String industryInsights;



     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public String getRequiredSkills() {
         return requiredSkills;
     }

     public void setRequiredSkills(String requiredSkills) {
         this.requiredSkills = requiredSkills;
     }

     public String getEducationalRequirements() {
         return educationalRequirements;
     }

     public void setEducationalRequirements(String educationalRequirements) {
         this.educationalRequirements = educationalRequirements;
     }

     public String getIndustryInsights() {
         return industryInsights;
     }

     public void setIndustryInsights(String industryInsights) {
         this.industryInsights = industryInsights;
     }
 }




 class CareerService {
    public void addCareer(Career career) throws Exception{
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO careers (name, description, required_skills, educational_requirements, industry_insights) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, career.getName());
            preparedStatement.setString(2, career.getDescription());
            preparedStatement.setString(3, career.getRequiredSkills());
            preparedStatement.setString(4, career.getEducationalRequirements());
            preparedStatement.setString(5, career.getIndustryInsights());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Career getCareerById(int id) throws Exception{
        Career career = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM careers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                career = new Career();
                career.setId(resultSet.getInt("id"));
                career.setName(resultSet.getString("name"));
                career.setDescription(resultSet.getString("description"));
                career.setRequiredSkills(resultSet.getString("required_skills"));
                career.setEducationalRequirements(resultSet.getString("educational_requirements"));
                career.setIndustryInsights(resultSet.getString("industry_insights"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return career;
    }

    // Other methods for career(jobs) matching, industry insights, etc.
}

 class UserService {
    public void addUser(User user) throws Exception{
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO users (name, email, skills, interests, personality_traits) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getSkills());
            preparedStatement.setString(4, user.getInterests());
            preparedStatement.setString(5, user.getPersonalityTraits());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) throws Exception{
        User user = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setSkills(resultSet.getString("skills"));
                user.setInterests(resultSet.getString("interests"));
                user.setPersonalityTraits(resultSet.getString("personality_traits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
     public User getUserByEmail(String email) throws Exception {
         User user = null;
         try (Connection connection = DatabaseUtil.getConnection()) {
             String query = "SELECT * FROM users WHERE email = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1, email);
             ResultSet resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                 user = new User();
                 user.setId(resultSet.getInt("id"));
                 user.setName(resultSet.getString("name"));
                 user.setEmail(resultSet.getString("email"));
                 user.setSkills(resultSet.getString("skills"));
                 user.setInterests(resultSet.getString("interests"));
                 user.setPersonalityTraits(resultSet.getString("personality_traits"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return user;
     }
//     public void getCareerById() {
//         System.out.print("Enter career ID: ");
//         int id = scanner.nextInt();
//         scanner.nextLine();
//
//         Career career = careerService.getCareerById(id);
//         if (career != null) {
//             System.out.println("Career Details:");
//             System.out.println("ID: " + career.getId());
//             System.out.println("Name: " + career.getName());
//             System.out.println("Description: " + career.getDescription());
//             System.out.println("Required Skills: " + career.getRequiredSkills());
//             System.out.println("Educational Requirements: " + career.getEducationalRequirements());
//             System.out.println("Industry Insights: " + career.getIndustryInsights());
//         } else {
//             System.out.println("Career not found.");
//         }
//     }

    // Other methods for skill assessment, interest profiling, etc.
}








public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final CareerService careerService = new CareerService();

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("Career Analysis System");
            System.out.println("1. Sign In");
            System.out.println("2. Add Career");
            System.out.println("3. Get User by Email");
            System.out.println("4. Get Career by ID");
            System.out.println("5. Exit");
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
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    //old
    private static void addUser() throws Exception{
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter skills: ");
        String skills = scanner.nextLine();
        System.out.print("Enter interests: ");
        String interests = scanner.nextLine();
        System.out.print("Enter personality traits: ");
        String personalityTraits = scanner.nextLine();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setSkills(skills);
        user.setInterests(interests);
        user.setPersonalityTraits(personalityTraits);
        userService.addUser(user);
        System.out.println("User added successfully.");
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
        Career career = new Career();
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
        Career career = careerService.getCareerById(id);
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





