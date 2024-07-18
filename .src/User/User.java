import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String skills;
    private String interests;
    private String personalityTraits;
    public static HashMap<String,User> users=new HashMap<>();
     public int getId() {
         return id;
     }

     public String getPassword() {
        return password;
    }

   public void setPassword() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter password (minimum 8 characters, must include letters and digits): ");
            String input = scanner.nextLine();
            if (isValidPassword(input)) {
                this.password = input;
                break;
            }
            System.out.println("Invalid password. Please re-enter the password.");
        }
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
     public boolean isValidEmail(String email) {
        // Simple validation to check the email format contains "@" and "."
        int atPosition = email.indexOf('@');
        int dotPosition = email.lastIndexOf('.');
        return atPosition > 0 && dotPosition > atPosition;
    }
    
     public static User getUserByEmail(String email) throws Exception {
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
     private  boolean isValidPassword(String password) {
        // Password must be at least 8 characters long and contain both letters and digits
        if (password.length() < 8) {
            return false;
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            if (hasLetter && hasDigit) {
                return true;
            }
        }
        return false;
    }
 }
