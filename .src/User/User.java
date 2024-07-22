import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
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
    Stack<String> skillAssessmentResultLog=new Stack<>();
    Stack<String>  personalityAssessmentResultLog=new Stack<>();
    public static HashMap<String,User> users=new HashMap<>();
    public Queue<Skill> skills=new Queue<>();
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

public void setPassword() {
        while (true) {
            System.out.print("Enter password (minimum 8 characters, must include letters and digits): ");
            String input = sc.nextLine();
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

     public Queue<Skill> getSkills() {
         return skills;
     }

     
     public String getPersonalityTraits() {
         return personalityTraits;
     }

     public void setPersonalityTraits(String personalityTraits) {
         this.personalityTraits = personalityTraits;
     }
     @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", location="
                + location + ", education=" + education + ", personalityTraits=" + personalityTraits + "]";
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
                 user.setLocation(resultSet.getString("location"));
                 user.setEducation(resultSet.getString("education"));
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
    public void profile( ) throws Exception{
       boolean exit=true;
       while(exit){
        System.out.println("=================================== Profile =================================");
        System.out.println("1. View Details");
        System.out.println("2. Edit Name");
        System.out.println("3. Edit password");
        System.out.println("4. Edit email");
        System.out.println("5. Edit skills");
        System.out.println("6. Edit Location");
        System.out.println("7. Edit Education");
        System.out.println("8. Return");
        int choice=sc.nextInt();
        switch(choice){
            case 1:viewDetails();
            break;

            case 2:editName();
            break;

            case 3:editPassword();
            break;

            case 4:editEmail();
            break;

            case 5:editSkills();
            break;

            case 6:editLocation();
            break;

            case 7:editEducation();
            break;

            case 8:exit=false;
            break;

            default:System.out.println("Invalid Input !");
            break;
        }
       }
    }
    public void viewDetails( ){
        System.out.println("----------------------------- Personal Details -------------------------------");
        System.out.println("User Id: "+this.getId());
        System.out.println("Name: "+this.getName());
        System.out.println("Email: "+this.getEmail());
        System.out.println("Location: "+this.getLocation());
        System.out.println("Education: "+this.getEducation());
        System.out.println("Personality Trait: "+this.getPersonalityTraits());
        System.out.println("---------------------------------- Skills --------------------------------------");
        viewSkills();
    }
    public void editName() throws Exception{
        System.out.println("---------------------------------- Edit Name -------------------------------");
        System.out.print("Enter new Name: ");
        sc.nextLine();
        this.name=sc.nextLine();
        String querry="{call nameupdation(?,?)}";
        CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry);
        cst.setString(1, this.name);
        cst.setString(2, this.email);
       boolean b= cst.execute();
        if(!b){
            System.out.println("Name Updated Successfully.");
        }else{
            System.out.println("Name not updated !");
        }
    }
    public void editPassword() throws Exception{
        System.out.println("---------------------------------- Edit Password ----------------------------------");
        System.out.print("Enter email: ");
        String email=sc.next();
        System.out.println();
        if(isValidEmail(email)){
            if(email.equals(this.email)){
                System.out.print("Enter New Password: ");
                setPassword();
                String querry="{call passupdation(?,?)}";
                CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry);
                cst.setString(1, this.password);
                cst.setString(2, this.email);
                boolean b=cst.execute();
                if(!b){
                    System.out.println("Password Updated Successfully.");
                }else{
                    System.out.println("Password Not Updated !");
                }
            }else{
                System.out.println("Email Not Match !");
            }
        }else{
            System.out.println("Enter valid Email !");
        }
    }
    public void editEmail() throws Exception{
        System.out.println("-------------------------------------- Edit Email ----------------------------------");
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
                CallableStatement cst=DatabaseUtil.getConnection().prepareCall(querry1);
                cst.setInt(1, this.id);
                cst.setString(2, email);
                boolean b=cst.execute();
                if(!b){
                    System.out.println("Email Updated Successfully.");
                }else{
                    System.out.println("Email not Updated Successfully !");
                }
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
            System.out.print(rs.getInt("id")+". "+rs.getString("skill_name")+"   ");
            if(i%4==0 && i!=0){
                System.out.println();
            }i++;
        }
        System.out.print("Enter Number of Skill you want to add: ");
        int num=sc.nextInt();
        int[] arr=new int[num];
        System.out.print("Enter Id of Skills You want Add: ");
        String querry2="SELECT user_id  FROM user_skills WHERE user_id = ? AND skill_id= ?";
        PreparedStatement pst1=DatabaseUtil.getConnection().prepareStatement(querry2);
        pst1.setInt(1, this.getId());
        for(int j=0;j<num;j++){
            int num1=sc.nextInt();
           while(num1<7 || num1>31){
            System.out.println("Enter valid Id !");
            num1=sc.nextInt();
           }pst1.setInt(2, num1);
           ResultSet rs1=pst1.executeQuery();
           if(!rs1.next()){
            UserService.insertUserSkill(this.getId(),num1);
            arr[j]=num1;
           }
           
        }   
        String querry1="SELECT skill_name FROM skills WHERE id = ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry1);
        for(int k=0;k<arr.length;k++){
            pst.setInt(1, arr[k]);
            ResultSet rs1=pst.executeQuery();
            if(rs1.next()){
                this.skills.enqueue(new Skill(arr[k], rs1.getString("skill_name")));
            }
        } 
        
    }
    //***********************************************remove skills and interest from user table and create interest table which 
    //is link with user like skills table take every input by id like (menu type) */
    public void editSkills( ) throws Exception{
        boolean exit=true;
        while(exit){
            System.out.println("-------------------------------------- Edit Skills ------------------------------------");
        System.out.println("1. Add Skills");
        System.out.println("2. Remove Skills");
        System.out.println("3. View Skills");
        System.out.println("4. Return");
        int c=sc.nextInt();
        switch (c) {
            case 1:
            System.out.println("----------------------------------- Add Skills --------------------------------");
            setSkill();
                break;
        
            case 2:
            System.out.println("------------------------------------- Remove Skills ---------------------------------");
            removeSkills();
                break;

            case 3:
            System.out.println("------------------------------- Skills ----------------------------------");
            viewSkills();
            break;
            
            case 4:exit=false;
            break;

            default:  
            System.out.println("Input Invalid !");
            break;  
        }
    }
        
    }
    public void viewSkills(){
        //System.out.println("jdsfbhewuhfu 11111");
        int i=1;
        Node<Skill> current = this.skills.list.head;
        while (current != null) {
           // System.out.println("dfjioerhfioe 222222");
            if(i%3==0){
                System.out.println();
               // System.out.println("dsjfhiuewhioewh 3333333");
            }//System.out.println("jdbsfuewhjnrkjnf 44444");
            System.out.print(i+". "+current.data.toString() + "  ");
           // System.out.println("liefjioewjfew fjejf 5555555");
            current = current.next;
            i++;
        }
        System.out.println();
      
    }
    public void removeSkills() throws Exception{
        System.out.println("------------------------------------ Your Skills ----------------------------------");
       viewSkills();
        System.out.print("Enter Number of skills you want to delete: ");
        int num=sc.nextInt();
        String querry="DELETE FROM user_skills WHERE user_id= ? AND skill_id= ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        System.out.print("Enter Id of Skills You want Delete: ");
        for(int j=0;j<num;j++){
            int num1=sc.nextInt();
           while(num1<7 || num1>31){
            System.out.println("Enter valid Id !");
            num1=sc.nextInt();
           }
           Node<Skill> current = this.skills.list.head;

         while (current != null) {
            if(current.data.skillId==num1){
                this.skills.list.remove(current.data);
            }
            current=current.next;
         }
          pst.setInt(1, this.id);
          pst.setInt(2, num1);
           pst.addBatch();
        }
        int[] arr=pst.executeBatch() ;
        int sum=0;
        for(int x:arr){
            sum+=x;
        }if(sum==0){
            System.out.println("You already dont have this skills ! ");
        }
        else if(sum==arr.length){
            System.out.println("Skills Removed successfully. ");
        }else{
            System.out.println("Removed available skills.");
        }
        
    }
    public void editLocation( ) throws Exception{
        System.out.println("---------------------------------- Edit Location ---------------------------------");
        System.out.print("Enter new Location: ");
        sc.nextLine();
        String loc=sc.nextLine();
        setLocation(loc);
        String querry="UPDATE users SET location = ? WHERE email= ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        pst.setString(1, this.location);
        pst.setString(2, this.email);
        pst.executeUpdate();
        System.out.println();
    }
    public void editEducation( ) throws Exception{
        System.out.println("------------------------------------- Edit Education ----------------------------------");
        System.out.print("Enter new Education: ");
        sc.nextLine();
        String edu=sc.nextLine();
        setEducation(edu);
        String querry="UPDATE users SET education = ? WHERE email= ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        pst.setString(1, this.education);
        pst.setString(2, this.email);
        pst.executeUpdate();
        System.out.println();
    }
 }
