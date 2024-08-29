
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;



public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/career_analysis_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws Exception {
       // Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public static void updatingPersonalityTraits(String pt,int id){
       try {
        String querry="UPDATE users SET personality_traits = ? WHERE id = ?";
        PreparedStatement pst=getConnection().prepareStatement(querry);
        pst.setString(1, pt);
        pst.setInt(2, id);
        int r=pst.executeUpdate();
        if(r>0){
            System.out.println("Personality Traits updated.");
        }else{
            System.out.println("Persoality Traits Not Update !");
        }
       } catch (Exception e) {
        // TODO: handle exception
        System.out.println(e.getMessage());
       }
        
    }
    //personalityresultlog
    public static void updatingPersonalityTraitsResultLog(User a){
        String querry="INSERT INTO personalityresultlog(user_id, date, personality_traits) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst=getConnection().prepareStatement(querry);
            pst.setInt(1, a.getId());
            Timestamp eventTime = Timestamp.from(Instant.now());
            pst.setTimestamp(2,eventTime );
            pst.setString(3, a.getPersonalityTraits());
            pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void updatingSkillAssessmentResultLog(User a,int ps,int sn,int sd,int hd){
        String querry="INSERT INTO skillresultlog(user_id, date, programming_skills_out_of_5, systems_and_networking_out_of_5, software_development_out_of_5, hardware_and_embedded_systems_out_of_5) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst=getConnection().prepareStatement(querry);
            pst.setInt(1, a.getId());
            Timestamp eventTime = Timestamp.from(Instant.now());
            pst.setTimestamp(2,eventTime );
            pst.setInt(3, ps);
            pst.setInt(4, sn);
            pst.setInt(5, sd);
            pst.setInt(6, hd);
            pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    // public static void getPersonalityTraitsResultLog(User a){
    //     String querry="SELECT * FROM personalityresultlog WHERE user_id = ?";
    //    try{
    //     PreparedStatement pst=getConnection().prepareStatement(querry);
    //     pst.setInt(1, a.getId());
    //     ResultSet rs=pst.executeQuery();
    //     int i=1;
    //     while(rs.next()){
    //         System.out.println(i+".  Date/Time: "+rs.getTimestamp("date")+"  Personality Trait: "+rs.getString("personality_traits"));
    //         i++;
    //     }
    //    }catch(Exception e){
    //     System.out.println(e.getMessage());
    //    }
    // }
    // public static void getSkillResultLog(User a){
    //     String querry="SELECT * FROM skillresultlog WHERE user_id = ?";
    //     try{
    //      PreparedStatement pst=getConnection().prepareStatement(querry);
    //      pst.setInt(1, a.getId());
    //      ResultSet rs=pst.executeQuery();
    //      int i=1;
    //      if(!rs.next()){
    //         System.out.println("No Skills Assessment Attempted.");
    //         return;
    //      }
    //      while(rs.next()){
    //          System.out.println(i+".  Date/Time: "+rs.getTimestamp("date")+"  Programming Skills: "+rs.getInt("programming_skills_out_of_5")+"/5  Systems and Networking: "+
    //          rs.getInt("systems_and_networking_out_of_5")+"/5  Software Devlopment: "+rs.getInt("software_development_out_of_5")+"/5  Hardware and Embedded System: "+
    //          rs.getInt("hardware_and_embedded_systems_out_of_5")+"/5");
    //          i++;
    //      }
    //     }catch(Exception e){
    //      System.out.println(e.getMessage());
    //     }
    // }
    public static void copySkills(User a){
        String querry="SELECT skill_id FROM  user_skills WHERE user_id = ?";
        try{
            PreparedStatement pst=getConnection().prepareStatement(querry);
            pst.setInt(1, a.getId());
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                String querry1="SELECT skill_name FROM skills WHERE id = ?";
                PreparedStatement pst1=getConnection().prepareStatement(querry1);
                pst1.setInt(1, rs.getInt("skill_id"));
                ResultSet rs1=pst1.executeQuery();
                if(rs1.next()){
                    a.skills.enqueue(new Skill(rs.getInt("skill_id"), rs1.getString("skill_name")));
                }
            }
        }catch(Exception e){
           // System.out.println(e.getMessage());
        }
    }
    public static void copyPtResultLog(User a){
        String querry="SELECT * FROM personalityresultlog WHERE user_id = ?";
       try{
        PreparedStatement pst=getConnection().prepareStatement(querry);
        pst.setInt(1, a.getId());
        ResultSet rs=pst.executeQuery();
        
        while(rs.next()){
            a.personalityAssessmentResultLog.push("Date/Time: "+rs.getTimestamp("date")+"  Personality Trait: "+rs.getString("personality_traits"));
            
        }
       }catch(Exception e){
        //System.out.println(e.getMessage());
       }

    }
    public static void copySkillResultLog(User a){
        String querry="SELECT * FROM skillresultlog WHERE user_id = ?";
       try{
        PreparedStatement pst=getConnection().prepareStatement(querry);
        pst.setInt(1, a.getId());
        ResultSet rs=pst.executeQuery();
        
        while(rs.next()){
            a.skillAssessmentResultLog.push(" Date/Time: "+rs.getTimestamp("date")+"  Programming Skills: "+rs.getInt("programming_skills_out_of_5")+"/5  Systems and Networking: "+
             rs.getInt("systems_and_networking_out_of_5")+"/5  Software Devlopment: "+rs.getInt("software_development_out_of_5")+"/5  Hardware and Embedded System: "+
             rs.getInt("hardware_and_embedded_systems_out_of_5")+"/5");
            
        }
       }catch(Exception e){
       // System.out.println(e.getMessage());
       }

    }
    public static void uploadingResume(User a, FileReader fr,String fileName){
        String querry="INSERT INTO user_resume(user_id, resume_name, resume_text) VALUES(?, ?, ?) ";
        try {
            PreparedStatement pst=getConnection().prepareStatement(querry);
            pst.setInt(1, a.getId());
            pst.setString(2, fileName);
            pst.setCharacterStream(3, fr);
            pst.executeUpdate();
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }InputValidator ip=new InputValidator();

    public void addRecruiter(Recruiter user, String email) throws Exception{
       // System.out.println("asfewi dij1111111111111111111111");
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO recruiters (name, email, phonenumber, password, location, companyname) VALUES (?, ?, ?, ?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            String n=ip.encryptPassword(user.getPassword());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setLong(3, user.getPhonenumber());
            preparedStatement.setString(4, n);
            preparedStatement.setString(5, user.getLocation());
            //preparedStatement.setString(6, user.getEducation());
           // preparedStatement.setString(7, user.getExperience());
            preparedStatement.setString(6, user.getCompanyname());
            preparedStatement.executeUpdate();
            String query1="SELECT id FROM recruiters WHERE email= ?";
            PreparedStatement pst=connection.prepareStatement(query1);
            pst.setString(1, user.getEmail());
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                int id=rs.getInt("id");
                user.setId(id);
            }
            //users.put(email, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
   
}
