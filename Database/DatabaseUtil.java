import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/career_analysis_system";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
   
}
