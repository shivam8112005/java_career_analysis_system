import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
   
}
