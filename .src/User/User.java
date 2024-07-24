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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Clob;
public class User {
    static Scanner sc=new Scanner(System.in);
    public Stack<String> skillAssessmentResultLog=new Stack<>();
    public Stack<String>  personalityAssessmentResultLog=new Stack<>();
    public ArrayList1<String> resumes=new ArrayList1<>();
    public Queue<Skill> skills=new Queue<>();
    public static HashMap<String,User> users=new HashMap<>();
    
    private int id;
    private String name;
    private String email;
    private String password;
    private String location;
    private String education;
    private String personalityTraits;
    private long phonenumber;
    private String experience;
    int pt=1;

     public int getId() {
         return id;
     }
     public String getEducation() {
        return education;
    }

    public long getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
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
                 user.setExperience(resultSet.getString("experience"));
                 user.setPhonenumber(resultSet.getLong("phonenumber"));
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
        System.out.println("Contact Number: "+this.getPhonenumber());
        System.out.println("Location: "+this.getLocation());
        System.out.println("Education: "+this.getEducation());
        System.out.println("Experience: "+this.getExperience());
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
        int i=1;
        Node<Skill> current = this.skills.list.head;
        while (current != null) {
            if(i%3==0){
                System.out.println();
            }
            System.out.print(i+". "+current.data.toString() + "  ");
           
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
    public void buildResume(){
       boolean exit=true;
       while(exit){
        System.out.println("----------------------------------------- Resumes -------------------------------------");
        System.out.println("1. Building for Yourself");
        System.out.println("2. Building for Someone");
        System.out.println("3. search Resume");
        System.out.println("4. Return");
        int c=sc.nextInt();
        switch (c) {
            case 1:
            System.out.println("---------------------------------------- Build Resume --------------------------------------");
            this.resumeBuidingForSelf();
                break;
            case 2:System.out.println("---------------------------------------- Build Resume --------------------------------------");
            this.resumeBuildingForSomeone();
            break;
            case 3:
            System.out.println("-------------------------------------- Search Resume -----------------------------------");
           this.searchResume();
            break;
            case 4:
            exit=false;
            break;
            default:System.out.println("Invalid Input !");
                break;
        }
       }
    }
    public void resumeBuidingForSelf(){
        sc.nextLine();
        System.out.print("Enter File Name You want Save Your Resume as: ");
        String fn=sc.nextLine();
        this.resumes.add(fn);
        try {
            FileWriter fw=new FileWriter("D://shivam//Resumes.txt",true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write("========================================== Resume =======================================");
            bw.newLine();
            bw.newLine();
            System.out.println("Enter Career Summary: ");
            String co=sc.nextLine();
            bw.write("------------------------------------------ Personal Details ------------------------------------");
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("Name: "+this.getName());
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("Location: "+this.getLocation());
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("Email: "+this.getEmail());
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("Contact Number: "+this.getPhonenumber());
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("-------------------------------------------- Career Summary ------------------------------------");
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write(co);
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("------------------------------------------- Educational Details ---------------------------------");
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write(this.getEducation());
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write("-------------------------------------------- Experience --------------------------------------");
            bw.flush();
            bw.newLine();bw.newLine();
            bw.write(this.getExperience());
            bw.flush();
            bw.newLine();;bw.newLine();
            bw.write("---------------------------------------------- Skills -------------------------------------");
            bw.flush();
            bw.newLine();bw.newLine();
            Node<Skill> curr=this.skills.list.head;
            int i=1;
            while(curr!=null){
                bw.write(i+". "+curr.data.getSkill());
                bw.flush();
                bw.newLine();
                i++;
                curr=curr.next;
            }
            bw.flush();
            bw.close();
            File f=new File("D://shivam//Resumes.txt");
            FileReader fr=new FileReader(f);
            DatabaseUtil.uploadingResume(this, fr,fn);
            fw=new FileWriter("D://shivam//Resumes.txt",false);
            fw.write("");
            fw.flush();
            fw.close();
            fr.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void resumeBuildingForSomeone(){
        sc.nextLine();
        System.out.print("Enter Resume Name: ");
        String fn=sc.nextLine();
        String sql="SELECT * FROM user_resume WHERE resume_name = ? AND user_id = ?";
        try{
            PreparedStatement ps=DatabaseUtil.getConnection().prepareStatement(sql);
            ps.setString(1, fn);
            ps.setInt(2, this.getId());
            ResultSet rs1=ps.executeQuery();
            while(rs1.next()){
                System.out.println("Resume Name already exists !");
                System.out.print("Re-enter Name: ");
                fn=sc.nextLine();
                ps.setString(1, fn);
                ps.setInt(2, this.getId());
                rs1=ps.executeQuery();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        this.resumes.add(fn);

        System.out.print("Enter name: ");
        String name = sc.nextLine();
        
        System.out.print("Enter location: ");
        String location = sc.nextLine();
        
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        
        System.out.print("Enter contact number: ");
        String contactNumber = sc.nextLine();
        
        System.out.print("Enter career summary: ");
        String careerSummary = sc.nextLine();
        
        System.out.print("Enter education details: ");
        String education = sc.nextLine();
        
        System.out.print("Enter experience details: ");
        String experience = sc.nextLine();
        System.out.println("Set Skills");
       try{
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
       HashSet<Integer> hs=new HashSet<>();
       System.out.print("Enter Number of skills you want Insert: ");
       int n=sc.nextInt();
       for(int j=0;j<n;j++){
        int n1=sc.nextInt();
        while(n1<7 || n1>31){
            System.out.println("Enter Valid Id: ");
            n1=sc.nextInt();
        }
        hs.add(n1);
       }
       FileWriter fw=new FileWriter("D://shivam//Resumes.txt",true);
       BufferedWriter bw=new BufferedWriter(fw);
       bw.write("========================================== Resume =======================================");
       bw.newLine();
       bw.newLine();
       bw.write("------------------------------------------ Personal Details ------------------------------------");
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("Name: "+name);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("Location: "+location);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("Email: "+email);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("Contact Number: "+contactNumber);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("-------------------------------------------- Career Summary ------------------------------------");
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write(careerSummary);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("------------------------------------------- Educational Details ---------------------------------");
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write(education);
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write("-------------------------------------------- Experience --------------------------------------");
       bw.flush();
       bw.newLine();bw.newLine();
       bw.write(experience);
       bw.flush();
       bw.newLine();;bw.newLine();
       bw.write("---------------------------------------------- Skills -------------------------------------");
       bw.flush();
       bw.newLine();bw.newLine();
       String querry1="SELECT skill_name FROM skills WHERE id = ?";
       PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry1);
       int k=1;
       for(int x:hs){
        pst.setInt(1, x);
        ResultSet rs1=pst.executeQuery();
        if(rs1.next()){
        bw.write(k+". "+rs1.getString("skill_name"));
        bw.flush();
        bw.newLine();
        k++;
     }
       }
       bw.flush();
       bw.close();
       File f=new File("D://shivam//Resumes.txt");
       FileReader fr=new FileReader(f);
       DatabaseUtil.uploadingResume(this, fr,fn);
       fw=new FileWriter("D://shivam//Resumes.txt",false);
       fw.write("");
       fw.flush();
       fw.close();
       fr.close();

       }catch(Exception e){
        e.printStackTrace();
       }
    }
    public void searchResume(){
        sc.nextLine();
        System.out.print("Enter Resume Name: ");
        String name=sc.nextLine();
        String querry="SELECT * FROM user_resume WHERE user_id = ? AND resume_name = ?";
        try {
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            pst.setInt(1, this.getId());
            pst.setString(2, name);
            ResultSet rs=pst.executeQuery();
            if(!rs.next()){
                System.out.println("No Resume Found !");
                return;
            }
                System.out.println("Resume Id: "+rs.getInt("resume_id")+"   Name: "+name);
            
           boolean exit=true;
           while(exit){
            System.out.println("==================================== Options =================================");
            System.out.println("1. Preview");
            System.out.println("2. Download");
            System.out.println("3. Return");
            int n=sc.nextInt();
            switch (n) {

                case 1:
                System.out.println("***********************************************************************************************************************************************");
                this.previewResume(rs);
                System.out.println("************************************************************************************************************************************************");
                
                    break;
                 case 2:
                 this.downloadResume(rs);
                    break;

                 case 3:exit=false;
                    break;

                default:
                    break;
            }
           }
        
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    public void previewResume(ResultSet rs){
        System.out.println();
        try {
            Clob c=rs.getClob("resume_text");
            Reader r=c.getCharacterStream();
            BufferedReader br=new BufferedReader(r);
            String s=br.readLine();
            while(s!=null){
                
                System.out.println(s);
                s=br.readLine();
            }
            
         }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void downloadResume(ResultSet rs){
       try{
        Clob c=rs.getClob("resume_text");
            Reader r=c.getCharacterStream();
       String filename=rs.getString("resume_name");
                FileWriter fw=new FileWriter("D://shivam//"+filename+".txt");
                BufferedWriter bw=new BufferedWriter(fw);
                BufferedReader br=new BufferedReader(r);
                String line=br.readLine();
                while(line!=null){
                    bw.write(line);;
                    bw.newLine();
                   line=br.readLine();
                   
                }
                fw.flush();
                bw.flush();
                bw.close();
                fw.close();
                br.close();
                r.close();
        System.out.println("Downloading Complete");
       }
       catch(Exception e){

       }
    }
    public void searchUser(){
        boolean exit=true;
        while(exit){
            System.out.println("---------------------------------------- Search User ---------------------------------------");
            System.out.println("1. Search User By Name");
            System.out.println("2. Search User By Email");
            System.out.println("3. Return");
            int n=sc.nextInt();
            switch (n) {
                case 1:
                this.searchUserByName();
                    break;

                case 2:
                System.out.print("Enter Email To Search: ");
                String email=sc.next();
                    try {
                       User u= getUserByEmail(email);
                       System.out.println("----------------------------- Personal Details -------------------------------");
               System.out.println("User Id: "+u.getId());
               System.out.println("Name: "+u.getName());
               System.out.println("Email: "+u.getEmail());
               System.out.println("Contact Number: "+u.getPhonenumber());
               System.out.println("Location: "+u.getLocation());
               System.out.println("Education: "+u.getEducation());
               System.out.println("Experience: "+u.getExperience());
               System.out.println("Personality Trait: "+u.getPersonalityTraits());
               System.out.println("---------------------------------- Skills --------------------------------------");
               String querry2="SELECT skill_id FROM user_skills WHERE user_id = ?";
               PreparedStatement pst2=DatabaseUtil.getConnection().prepareStatement(querry2);
               pst2.setInt(1, u.getId());
               ResultSet rs2=pst2.executeQuery();
               int i=1;
               boolean b1=true;
               while(rs2.next()){
                b1=false;
                String querry3="SELECT skill_name FROM skills WHERE id = ?";
                PreparedStatement pst3=DatabaseUtil.getConnection().prepareStatement(querry3);
                pst3.setInt(1, rs2.getInt("skill_id"));
                ResultSet rs3=pst3.executeQuery();
                if(i%3==0){
                    System.out.println();
                }
                if(rs3.next()){
                    System.out.print(i+". "+rs3.getString("skill_name")+"  ");
                }
                i++;
                
               }
               if(b1){
                System.out.println("No Skills Addedd !");
                return;
               }
            }
                     catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }System.out.println();
                    break;
                case 3:
                exit=false;
                break;
                default:
                    break;
            }
        }
    }
    public void searchUserByName(){
        sc.nextLine();
        System.out.print("Enter Name To Search: ");
        String name1=sc.nextLine();
        String querry="SELECT * FROM users WHERE name = ?";
        try {
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            pst.setString(1, name1);
            ResultSet rs=pst.executeQuery();
            boolean b=true;
            while(rs.next()){
                System.out.println("User Id: "+rs.getInt("id")+"  Name: "+rs.getString("name"));
                b=false;
            }if(b){
                System.out.println("No User Found !");
                return;
            }
            System.out.print("Enter User Id to View Profile: ");
            int n=sc.nextInt();
            String querry1="SELECT email FROM users WHERE id = ?";
            PreparedStatement pst1=DatabaseUtil.getConnection().prepareStatement(querry1);
            pst1.setInt(1, n);
            ResultSet rs1=pst1.executeQuery();
            if(rs1.next()){
               User u= getUserByEmail(rs1.getString("email"));
               System.out.println("----------------------------- Personal Details -------------------------------");
               System.out.println("User Id: "+u.getId());
               System.out.println("Name: "+u.getName());
               System.out.println("Email: "+u.getEmail());
               System.out.println("Contact Number: "+u.getPhonenumber());
               System.out.println("Location: "+u.getLocation());
               System.out.println("Education: "+u.getEducation());
               System.out.println("Experience: "+u.getExperience());
               System.out.println("Personality Trait: "+u.getPersonalityTraits());
               System.out.println("---------------------------------- Skills --------------------------------------");
               String querry2="SELECT skill_id FROM user_skills WHERE user_id = ?";
               PreparedStatement pst2=DatabaseUtil.getConnection().prepareStatement(querry2);
               pst2.setInt(1, u.getId());
               ResultSet rs2=pst2.executeQuery();
               int i=1;
               boolean b1=true;
               while(rs2.next()){
                b1=false;
                String querry3="SELECT skill_name FROM skills WHERE id = ?";
                PreparedStatement pst3=DatabaseUtil.getConnection().prepareStatement(querry3);
                pst3.setInt(1, rs2.getInt("skill_id"));
                ResultSet rs3=pst3.executeQuery();
                if(i%3==0){
                    System.out.println();
                }
                if(rs3.next()){
                    System.out.print(i+". "+rs3.getString("skill_name")+"  ");
                }
                i++;
                
               }if(b){
                System.out.println("No Skills Addedd !");
                return;
               }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }System.out.println();
    }
 }
