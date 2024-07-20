import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.crypto.Data;

import java.sql.Statement;
import java.sql.CallableStatement;
public class User {
    static Scanner sc=new Scanner(System.in);
    int pt=1;
    private int id;
    private String name;
    private String email;
    private String password;
    private String location;
    private String education;
    private String personalityTraits;
    public static HashMap<String,User> users=new HashMap<>();
    public ArrayList<Skill> skills=new ArrayList<>();
     public int getId() {
         return id;
     }
     public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }
     public String getPassword() {
        return password;
    }

   public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

public void setPassword(User u) {
        while (true) {
            System.out.print("Enter password (minimum 8 characters, must include letters and digits): ");
            String input = sc.nextLine();
            if (isValidPassword(input)) {
                u.password = input;
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

     public ArrayList<Skill> getSkills() {
         return skills;
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
                // user.setSkills(resultSet.getString("skills"));
               //  user.setInterests(resultSet.getString("interests"));
                 user.setPersonalityTraits(resultSet.getString("personality_traits"));
                 user.setPassword(resultSet.getString("password"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return user;
     }
     public void setPassword(String password){
        this.password=password;
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
    public void profile(){
       boolean exit=true;
       while(exit){
        System.out.println("1. View Details");
        System.out.println("2. Edit Name");
        System.out.println("3. Edit password");
        System.out.println("4. Edit email");
        System.out.println("5. Edit Interests");
        System.out.println("6. Edit skills");
        int choice=sc.nextInt();
        switch(choice){

        }
       }
    }
    public void editName() throws Exception{
        System.out.print("Enter new Name: ");
        this.name=sc.nextLine();
        String querry="{call nameupdation(?,?)}";
        CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry);
        cst.setString(1, this.name);
        cst.setString(2, this.email);
        System.out.println("Name Updated Successfully.");
    }
    public void editPassword() throws Exception{
        System.out.print("Enter email: ");
        String email=sc.next();
        System.out.println();
        if(isValidEmail(email)){
            if(email.equals(this.email)){
                System.out.print("Enter New Password: ");
                setPassword(this);
                String querry="{call passupdation(?,?)}";
                CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry);
                cst.setString(1, this.password);
                cst.setString(2, this.email);
            }else{
                System.out.println("Email Not Match !");
            }
        }else{
            System.out.println("Enter valid Email !");
        }
    }
    public void editEmail() throws Exception{
        System.out.print("Enter your Id: ");
        int id=sc.nextInt();
        System.out.println();
        if(id==this.id){
            System.out.print("Enter New Email: ");
            String email=sc.next();
           if(isValidEmail(email)){
            String querry="SELECT * FROM users WHERE email=?";
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            pst.setString(1, email);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("Email already exists ! ");
            }else{
                this.email=email;
                String querry1="{call emailupdation(?,?)}";
                CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry);
                cst.setInt(1, this.id);
                cst.setString(2, email);
            }
           }else{
            System.out.println("Email Invalid ! ");
           }
        }
    }public void setSkill() throws Exception{
        String querry="SELECT * FROM skills";
        Statement st=DatabaseUtil.getConnection().createStatement();
        ResultSet rs=st.executeQuery(querry);
        int i=0;
        while(rs.next()){
            System.out.print(rs.getInt("id")+". "+rs.getString("skill_name")+"  ");
            if(i%5==0){
                System.out.println();
            }i++;
        }
        System.out.print("Enter Number of Skill you want to add: ");
        int num=sc.nextInt();
        int[] arr=new int[num];
        for(int j=0;j<num;j++){
            int num1=sc.nextInt();
           while(num1<7 || num1>31){
            System.out.println("Enter valid Id !");
            num1=sc.nextInt();
           }
           arr[j]=num1;
        }   
        String querry1="SELECT skill_name FROM skills WHERE id = ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry1);
        for(int k=0;k<arr.length;k++){
            pst.setInt(1, arr[k]);
            ResultSet rs1=pst.executeQuery();
            skills.add(new Skill(arr[k], rs1.getString("skill_name")));
        } 
    }
    //***********************************************remove skills and interest from user table and create interest table which 
    //is link with user like skills table take every input by id like (menu type) */
    public void editSkills(){
        System.out.println("1. Add Skill");
        System.out.println("2. Remove Skill");
        
    }
 }
