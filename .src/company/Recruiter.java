import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

class Recruiter extends User{
    static Scanner sc=new Scanner(System.in);
    public int id;
    private String name;
    private String location;
    private String email;
    private String password;
    private long phonenumber;
    public String companyname;
    
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password=password;
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

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

public static Recruiter getRecruiterByEmail(String email) throws Exception {
         Recruiter user = null;
         try (Connection connection = DatabaseUtil.getConnection()) {
             String query = "SELECT * FROM recruiters WHERE email = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1, email);
             ResultSet resultSet = preparedStatement.executeQuery();
             if (resultSet.next()) {
                 user = new Recruiter();
                 user.setId(resultSet.getInt("id"));
                 user.setName(resultSet.getString("name"));
                 user.setEmail(resultSet.getString("email"));
                 user.setCompanyname(resultSet.getString("companyname"));
                 user.setPhonenumber(resultSet.getLong("phonenumber"));
                 user.setLocation(resultSet.getString("location"));
                // user.setEducation(resultSet.getString("education"));
               //  user.setPersonalityTraits(resultSet.getString("personality_traits"));
                 user.setPassword(resultSet.getString("password"));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return user;
     }
     public void profile( ) throws Exception{
        boolean exit=true;
        while(exit){
         System.out.println("=================================== Profile =================================");
         System.out.println("1. View Details");
         System.out.println("2. Edit Name");
         System.out.println("3. Edit password");
         System.out.println("4. Edit email");
         System.out.println("5. Edit Location");
         System.out.println("6. Edit Company");
         System.out.println("7. Return");
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
 
             case 5:editLocation();
             break;
 
             case 6:editCompany();
             break;

             case 7:exit=false;
             break;
 
             default:System.out.println("Invalid Input !");
             break;
         }
        }
     }
     @Override
     public void viewDetails( ){
        System.out.println("----------------------------- Personal Details -------------------------------");
        System.out.println("User Id: "+this.getId());
        System.out.println("Name: "+this.getName());
        System.out.println("Email: "+this.getEmail());
        System.out.println("Contact Number: "+this.getPhonenumber());
        System.out.println("Location: "+this.getLocation());
     System.out.println("Company Name: "+this.getCompanyname());
       
    } 
@Override
     public void editName() throws Exception{
        System.out.println("---------------------------------- Edit Name -------------------------------");
        System.out.print("Enter new Name: ");
        sc.nextLine();
        this.name=sc.nextLine();
        String querry="UPDATE recruiters SET name = ? WHERE email = ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        pst.setString(1, this.name);
        pst.setString(2, this.email);
       int b= pst.executeUpdate();
        if(b>0){
            System.out.println("Name Updated Successfully.");
        }else{
            System.out.println("Name not updated !");
        }
    }@Override
    public void editPassword() throws Exception{
        System.out.println("---------------------------------- Edit Password ----------------------------------");
        System.out.print("Enter email: ");
        String email=sc.next();
        System.out.println();
        if(isValidEmail(email)){
            if(email.equals(this.email)){
                System.out.print("Enter New Password: ");
                this.setPassword();
                String querry="UPDATE recruiters SET password = ? WHERE email = ?";
                PreparedStatement cst=DatabaseUtil.getConnection().prepareStatement(querry);
                cst.setString(1, this.password);
                cst.setString(2, this.email);
                int b=cst.executeUpdate();
                if(b>0){
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
    }@Override
    public void editEmail() throws Exception{
        System.out.println("-------------------------------------- Edit Email ----------------------------------");
        System.out.print("Enter your Id: ");
        int id=sc.nextInt();
        System.out.println();
        if(id==this.id){
            System.out.print("Enter New Email: ");
            String email=sc.next();
           if(isValidEmail(email)){
            String querry="SELECT * FROM recruiters WHERE email = ?";
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            pst.setString(1, email);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("Email already exists ! ");
            }else{
                this.email=email;
                String querry1="UPDATE recruiters SET email = ? WHERE id = ?";
                PreparedStatement cst=DatabaseUtil.getConnection().prepareStatement(querry1);
                cst.setString(1, this.email);
                cst.setInt(2, this.id);
                int b=cst.executeUpdate();
                if(b>0){
                    System.out.println("Email Updated Successfully.");
                }else{
                    System.out.println("Email not Updated Successfully !");
                }
            }
           }else{
            System.out.println("Email Invalid ! ");
           }
        }else{
            System.out.println("Invalid Id !");
        }
    }@Override
    public void editLocation( ) throws Exception{
        System.out.println("---------------------------------- Edit Location ---------------------------------");
        System.out.print("Enter new Location: ");
        sc.nextLine();
        String loc=sc.nextLine();
        this.setLocation(loc);
        String querry="UPDATE recruiters SET location = ? WHERE email= ?";
        PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
        pst.setString(1, this.location);
        pst.setString(2, this.email);
       int b= pst.executeUpdate();
       if(b>0){
        System.out.println("Location Updated Successfully.");
       }else{
        System.out.println("Location Not Updated !");
       }
        System.out.println();
    }
   
    public void editCompany() throws Exception{
        System.out.println("-------------------------------------- Edit Company ----------------------------------");
        System.out.print("Enter your Id: ");
        int id=sc.nextInt();
        System.out.println();
        if(id==this.id){
            System.out.print("Enter New Company Name: ");
            sc.nextLine();
            String cn=sc.nextLine();
            String querry="SELECT * FROM recruiters WHERE companyname = ?";
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            pst.setString(1,cn);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                System.out.println("Company's Recruiter already exists ! ");
            }else{
                this.companyname=cn;
                String querry1="UPDATE recruiters SET companyname = ? WHERE id = ?";
                PreparedStatement cst=DatabaseUtil.getConnection().prepareStatement(querry1);
                cst.setString(1, this.companyname);
                cst.setInt(2, this.id);
                int b=cst.executeUpdate();
                String querry2="UPDATE job_listings1 SET company_name = ? WHERE rec_id = ?";
                PreparedStatement ps1=DatabaseUtil.getConnection().prepareStatement(querry2);
                ps1.setString(1, this.getCompanyname());
                ps1.setInt(2, this.getId());
                int a=ps1.executeUpdate();
                if(b>0 && a>0){
                    System.out.println("Company Name Updated Successfully.");
                }else{
                    System.out.println("Company Name not Updated Successfully !");
                }
            }
        }else{
            System.out.println("Invalid Id !");
        }
    }
    public void addJobListings(){
        Jobs j=new Jobs();
        sc.nextLine();
        System.out.print("Enter Job Name: ");
       
        String name=sc.nextLine();
         String sql3="SELECT name FROM job_listings WHERE name = ?";
         try{
            PreparedStatement p=DatabaseUtil.getConnection().prepareStatement(sql3);
            p.setString(1, name);
            ResultSet rs=p.executeQuery();
            while(rs.next()){
                System.out.println("Job Name Already Exists !");
                System.out.print("Re-Enter Job Name: ");
                name=sc.nextLine();
                p.setString(1, name);
                rs=p.executeQuery();
            }
         }catch(Exception e){

         }
        System.out.print("Enter Job Description: ");
       String description=sc.nextLine();
        System.out.print("Enter Educational Requirements: ");
        String educationalRequirements=sc.nextLine();
        System.out.print("Enter Industry Insights: ");
        String industryInsights=sc.nextLine();
        String sql="INSERT INTO job_listings1(rec_id, company_name, name, description, educational_requirements, industry_insights) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps=DatabaseUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getId());
            ps.setString(2, this.getCompanyname());
            ps.setString(3, name);
            ps.setString(4, description);
            ps.setString(5, educationalRequirements);
            ps.setString(6, industryInsights);
            int r=ps.executeUpdate();
           
            if(r>0){
                
                String sql1="SELECT id FROM job_listings1 WHERE rec_id= ? AND name= ?";
                PreparedStatement ps1=DatabaseUtil.getConnection().prepareStatement(sql1);
                ps1.setInt(1, this.getId());
                ps1.setString(2, name);
                ResultSet rst=ps1.executeQuery();
                j.setDescription(description);
                j.setEducationalRequirements(educationalRequirements);
                j.setIndustryInsights(industryInsights);
                if(rst.next()){
                    int id=rst.getInt("id");
                    j.setId(id);
                }
            }else{
                System.out.println("Job Not Listed !");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Set Required Skills: ");
        String querry="SELECT * FROM skills";
        try {
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(querry);
            ResultSet rs=pst.executeQuery();
            int i=1;
            while(rs.next()){
                if(i%4==0){
                    System.out.println();
                }
                System.out.print(rs.getInt("id")+". "+rs.getString("skill_name")+"  ");
                i++;
            }
            System.out.println();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.print("Enter Number of Skills to Add: ");
        int n=sc.nextInt();
        int[] arr=new int[n];
        System.out.println("Enter SKill Id: ");
        String querry1="INSERT INTO job_skills1(job_id, skill_id) VALUES(?, ?)";
        //String querry2="SELECT job_id  FROM job_skills WHERE job_id = ? AND skill_id= ?";
        try {
            PreparedStatement pst1=DatabaseUtil.getConnection().prepareStatement(querry1);
            pst1.setInt(1, j.getId());
            for(int i=0;i<n;i++){
                int id=sc.nextInt();
                while(id<7 || id>31){
                    System.out.println("Enter valid Id !");
                    id=sc.nextInt();
                }pst1.setInt(2, id);
                pst1.executeUpdate();
                arr[i]=id;
            }
            String querry2="SELECT skill_name FROM skills WHERE ID = ?";
            PreparedStatement p=DatabaseUtil.getConnection().prepareStatement(querry2);
            for(int i=0;i<n;i++){
                p.setInt(1, arr[i]);
                ResultSet r1=p.executeQuery();
                if(r1.next()){
                    j.jobSkills.add(new Skill(arr[i], r1.getString("skill_name")));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
     }
     public void viewApplications(){
        System.out.println("---------------------------------- Job Applications ----------------------------------");
        String sql="SELECT ID FROM job_listings1 where rec_id=?";
        try {
            PreparedStatement pst=DatabaseUtil.getConnection().prepareStatement(sql);
            pst.setInt(1, this.getId());
            ResultSet rs=pst.executeQuery();
            String sql1="SELECT user_id FROM user_jobs1 where job_id=?";
            PreparedStatement pst1=DatabaseUtil.getConnection().prepareStatement(sql1);
            int i=1;
            ArrayList<Integer> uid=new ArrayList<>();
            
            while(rs.next()){
                pst1.setInt(1, rs.getInt("ID"));
                ResultSet rs1=pst1.executeQuery();
                String sql2="SELECT * FROM users WHERE id=?";
                PreparedStatement pst2=DatabaseUtil.getConnection().prepareStatement(sql2);
                while(rs1.next()){
                    pst2.setInt(1, rs1.getInt("user_id"));
                    ResultSet rs2=pst2.executeQuery();
                    if(rs2.next()){
                        System.out.println(i+".  Name: "+rs2.getString("name")+"   User ID: "+rs2.getInt("id")+"  Job ID: "+rs.getInt("ID")+"  Education: "+rs2.getString("education"));
                        uid.add(rs2.getInt("id"));
                       
                    }
                    i++;
                }
                
            }if(i==1){
                System.out.println("No Applications.");
                return;
            }
            System.out.print("Enter User ID to view full Details(or Enter 0 to Exit): ");
            int ch=sc.nextInt();
            if(ch==0){
                return;
            }
            System.out.print("Enter Job Id: ");
            int jid=sc.nextInt();
            String df="SELECT * from user_jobs1 where job_id=? AND user_id=?";
            PreparedStatement asd=DatabaseUtil.getConnection().prepareStatement(df);
            asd.setInt(1, jid);
            asd.setInt(2, ch);
            ResultSet ty=asd.executeQuery();
           if(ty.next()){
                String querry1="SELECT email FROM users WHERE id = ?";
                PreparedStatement pst3=DatabaseUtil.getConnection().prepareStatement(querry1);
                pst3.setInt(1, ch);
                ResultSet rs3=pst3.executeQuery();
                if(rs3.next()){
                   User u= getUserByEmail(rs3.getString("email"));
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
                   int i1=1;
                   boolean b1=true;
                   while(rs2.next()){
                    b1=false;
                    String querry3="SELECT skill_name FROM skills WHERE id = ?";
                    PreparedStatement pst4=DatabaseUtil.getConnection().prepareStatement(querry3);
                    pst4.setInt(1, rs2.getInt("skill_id"));
                    ResultSet rs4=pst4.executeQuery();
                    if(i1%3==0){
                        System.out.println();
                    }
                    if(rs4.next()){
                        System.out.print(i1+". "+rs4.getString("skill_name")+"  ");
                    }
                    i1++;
                    
                   }if(b1){
                    System.out.println("No Skills Addedd !");
                    return;
                   }System.out.println();
                   System.out.print("Recruit?(yes/no): ");
                   String ch1=sc.next();
                   if(ch1.equalsIgnoreCase("yes")){
                    this.recruit(jid);
                   }else{
                    viewApplications();
                   }
                }
            }else{
                System.out.println("No such Application found!");
                viewApplications();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public void recruit(int jid){
        try(Connection con= DatabaseUtil.getConnection();) {
           
            con.setAutoCommit(false);
            String s1="UPDATE job_listings1 SET is_active=? where id=?";
            PreparedStatement pst;
         
                pst = con.prepareStatement(s1);
                pst.setInt(1, 1);
                pst.setInt(2, jid);
                int r=pst.executeUpdate();
                System.out.print("Are you sure to Recruit this candidate?(yes/no): ");
                String ch=sc.next();
                if(ch.equalsIgnoreCase("yes")){
                    con.commit();
                    System.out.println("Recruited Successfully.");
                }else{
                    con.rollback();
                    System.out.println("Not Recruited.");
                }
                
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }
    
       
    }

