import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;



public class Jobs extends Recruiter{
    static Scanner sc=new Scanner(System.in);
    private int id;
    private String name;
    private String description;
    private String educationalRequirements;
    private String industryInsights;
    public static ArrayList1<Jobs> jobs=new ArrayList1<>();
    public ArrayList1<Skill> jobSkills=new ArrayList1<>();
    
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
