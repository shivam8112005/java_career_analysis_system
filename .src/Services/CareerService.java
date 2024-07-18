import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
public class CareerService {
    public void addCareer(Jobs job) throws Exception{
        
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO careers (name, description, required_skills, educational_requirements, industry_insights) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, job.getName());
            preparedStatement.setString(2, job.getDescription());
            preparedStatement.setString(3, job.getRequiredSkills());
            preparedStatement.setString(4, job.getEducationalRequirements());
            preparedStatement.setString(5, job.getIndustryInsights());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Jobs getCareerById(int id) throws Exception{
        Jobs career = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM careers WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                career = new Jobs();
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
