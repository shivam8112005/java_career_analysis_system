import java.io.File;
import java.io.FileReader;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class UserService extends User{
    InputValidator ip=new InputValidator();
    static HashMap<String, String> recommendations = new HashMap<>();
    
    
    int Prog_skills_marks=0;
    int Sys_and_Net_marks=0;
    int sw_dev_marks=0;
    int hw_embeded_sys_marks=0;
    Scanner sc=new Scanner(System.in);
   
    public void addUser(User user, String email) throws Exception{
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO users (name, email, phonenumber, password, location, education, experience, personality_traits, resume) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            String n=ip.encryptPassword(user.getPassword());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setLong(3, user.getPhonenumber());
            preparedStatement.setString(4, n);
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setString(6, user.getEducation());
            preparedStatement.setString(7, user.getExperience());
            preparedStatement.setString(8, user.getPersonalityTraits());
            File f=new File("D://shivam//Resumes.txt");
                  FileReader fr=new FileReader(f);
            preparedStatement.setCharacterStream(9, fr);
            preparedStatement.executeUpdate();
            String query1="SELECT id FROM users WHERE email= ?";
            PreparedStatement pst=connection.prepareStatement(query1);
            pst.setString(1, user.getEmail());
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                int id=rs.getInt("id");
                user.setId(id);
            }
            users.put(email, user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void insertUserSkill(int userId, int skillId) throws Exception {
        String sql = "INSERT INTO user_skills (user_id, skill_id) VALUES (" + userId + ", '" + skillId + "')";

        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        }
    }

    public User getUserById(int id) throws Exception{
        User user = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
              //  user.setSkills(resultSet.getString("skills"));
                //user.setInterests(resultSet.getString("interests"));
                user.setPersonalityTraits(resultSet.getString("personality_traits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    


    
    public void skillAssessment(User a){
        System.out.println("============================== Skilled Assessment ===============================");
                 String[][] questions = {
                         {"1. Which of the following is a programming language?",
                                 "A. HTML",
                                 "B. CSS",
                                 "C. Java",
                                 "D. SQL"
                         },
                         {"2. What is the output of the following code snippet?\nint a = 5;\nint b = 10;\nSystem.out.println(a + b);",
                                 "A. 15",
                                 "B. 510",
                                 "C. 50",
                                 "D. 105"
                         },
                         {"3. Which data structure allows you to store elements in a First-In-First-Out (FIFO) manner?",
                                 "A. Stack",
                                 "B. Queue",
                                 "C. Array",
                                 "D. Tree"
                         },
                         {"4. What is the time complexity of a binary search algorithm?",
                                 "A. O(n)",
                                 "B. O(n^2)",
                                 "C. O(log n)",
                                 "D. O(1)"
                         },
                         {"5. Which keyword is used to create a subclass in Java?",
                                 "A. extend",
                                 "B. extends",
                                 "C. implement",
                                 "D. inherits"
                         },

                         
                         {"6. Which of the following is NOT a function of an operating system?",
                                 "A. Memory management",
                                 "B. File management",
                                 "C. Compiler design",
                                 "D. Process management"
                         },
                         {"7. What does TCP stand for?",
                                 "A. Transmission Control Protocol",
                                 "B. Transport Control Protocol",
                                 "C. Transfer Control Protocol",
                                 "D. Transmission Communication Protocol"
                         },
                         {"8. In networking, what is the primary purpose of DNS?",
                                 "A. To translate domain names to IP addresses",
                                 "B. To translate IP addresses to MAC addresses",
                                 "C. To provide encryption for data packets",
                                 "D. To monitor network traffic"
                         },
                         {"9. Which of the following is a common web application security vulnerability?",
                                 "A. Buffer overflow",
                                 "B. SQL injection",
                                 "C. Stack overflow",
                                 "D. Cross-threading"
                         },
                         {"10. What is context switching in an operating system?",
                                 "A. Switching between different programming languages",
                                 "B. Saving the state of a process and loading the state of another process",
                                 "C. Changing the network protocol",
                                 "D. Updating the user interface"
                         },

                        
                         {"11. Which of the following commands is used to create a new branch in Git?",
                                 "A. git branch new-branch",
                                 "B. git create new-branch",
                                 "C. git checkout new-branch",
                                 "D. git new-branch"
                         },
                         {"12. Which type of testing is performed to ensure that the code changes do not break the existing functionality?",
                                 "A. Unit testing",
                                 "B. Integration testing",
                                 "C. Regression testing",
                                 "D. System testing"
                         },
                         {"13. What does SOLID stand for in software engineering principles?",
                                 "A. Single responsibility, Open-closed, Liskov substitution, Interface segregation, Dependency inversion",
                                 "B. Simple, Organized, Logical, Integrated, Durable",
                                 "C. Structured, Object-oriented, Linked, Immutable, Dependable",
                                 "D. Secure, Optimized, Lively, Interactive, Dynamic"
                         },
                         {"14. Which Agile methodology involves sprints and daily stand-up meetings?",
                                 "A. Scrum",
                                 "B. Kanban",
                                 "C. Waterfall",
                                 "D. Spiral"
                         },
                         {"15. What is the purpose of a unit test?",
                                 "A. To test the overall system performance",
                                 "B. To test individual units or components of the software",
                                 "C. To test the interaction between integrated units",
                                 "D. To test the software in a production environment"
                         },

                         
                         {"16. Which of the following is a microcontroller?",
                                 "A. Intel Core i7",
                                 "B. Raspberry Pi",
                                 "C. Arduino Uno",
                                 "D. NVIDIA GeForce"
                         },
                         {"17. What is the primary difference between a microcontroller and a microprocessor?",
                                 "A. Microcontrollers have integrated memory and peripherals",
                                 "B. Microprocessors are used in embedded systems",
                                 "C. Microcontrollers are more powerful than microprocessors",
                                 "D. Microprocessors have integrated memory and peripherals"
                         },
                         {"18. Which type of logic circuit outputs a high signal only when all its inputs are high?",
                                 "A. OR gate",
                                 "B. AND gate",
                                 "C. XOR gate",
                                 "D. NOR gate"
                         },
                         {"19. What is the primary consideration when developing software for embedded systems?",
                                 "A. User interface design",
                                 "B. Power consumption",
                                 "C. Network speed",
                                 "D. Color scheme"
                         },
                         {"20. Which component is used to store data temporarily in a computer system?",
                                 "A. CPU",
                                 "B. Hard drive",
                                 "C. RAM",
                                 "D. GPU"
                         }
                 };
                 String[] ans={"C","A","B","C","B","C","A","A","B","B","A","C","A","A","B","C","A","B","B","C"};
                  int cnt=0;
                 for (String[] question : questions){
                     for (String line : question) {
                         System.out.println(line);
                     } System.out.print("Ans: ");
                     String ans1=sc.next();
                     if(cnt<5 && ans[cnt].equalsIgnoreCase(ans1)){
                         Prog_skills_marks++;
                     }else if(cnt>=5 && cnt<=9 && ans[cnt].equalsIgnoreCase(ans1)){
                        Sys_and_Net_marks++;
                     }else if(cnt>=10 && cnt<=14 && ans[cnt].equalsIgnoreCase(ans1)){
                        sw_dev_marks++;
                     }else if(cnt>=15 && cnt<=19 && ans[cnt].equalsIgnoreCase(ans1)){
                        hw_embeded_sys_marks++;
                     }
                     cnt++;
                     System.out.println();
                 }
                 System.out.println("------------------------------------- Result -----------------------------------");
                 System.out.println("Programming Skills: "+Prog_skills_marks+"/5  Systems and Networking: "+Sys_and_Net_marks
                 +"/5   Software Development Practices: "+sw_dev_marks+"/5  Hardware and Embedded Systems: "+hw_embeded_sys_marks
                 +"/5");
                 Timestamp eventTime = Timestamp.from(Instant.now());
                 a.skillAssessmentResultLog.push("Date/Time: "+eventTime+" Programming Skills: "+Prog_skills_marks+"/5  Systems and Networking: "+Sys_and_Net_marks
                 +"/5   Software Development Practices: "+sw_dev_marks+"/5  Hardware and Embedded Systems: "+hw_embeded_sys_marks
                 +"/5");
                 generateEvaluationStatements(Prog_skills_marks, Sys_and_Net_marks, sw_dev_marks, hw_embeded_sys_marks);
                 DatabaseUtil.updatingSkillAssessmentResultLog(a, Prog_skills_marks, Sys_and_Net_marks, sw_dev_marks, hw_embeded_sys_marks);
                 }
                 public void printResultLog(User a){
                if(a.skillAssessmentResultLog.isEmpty()){
                    System.out.println("No Skilled Assessment Attempted.");
                    return;
                }
                   Node<String> current=a.skillAssessmentResultLog.list.head;
                   int i=1;
                   while(current!=null){
                    System.out.println(i+". "+current.data);
                    current=current.next;
                    i++;
                   }
                 }
                 public static void generateEvaluationStatements(int prog, int sys, int swDev, int hw) {
                    StringBuilder strengths = new StringBuilder("Strengths: ");
                    StringBuilder weaknesses = new StringBuilder("Weaknesses: ");
                    StringBuilder improvements = new StringBuilder("Areas of Improvement: ");
                    int strengthThreshold = 4;
                    int weaknessThreshold = 2;
                    if (prog >= strengthThreshold) {
                        strengths.append("Programming Skills, ");
                    } else if (prog <= weaknessThreshold) {
                        weaknesses.append("Programming Skills, ");
                        improvements.append("Programming Skills, ");
                    }
                    if (sys >= strengthThreshold) {
                        strengths.append("Systems and Networking, ");
                    } else if (sys <= weaknessThreshold) {
                        weaknesses.append("Systems and Networking, ");
                        improvements.append("Systems and Networking, ");
                    }
                    if (swDev >= strengthThreshold) {
                        strengths.append("Software Development Practices, ");
                    } else if (swDev <= weaknessThreshold) {
                        weaknesses.append("Software Development Practices, ");
                        improvements.append("Software Development Practices, ");
                    }
                    if (hw >= strengthThreshold) {
                        strengths.append("Hardware and Embedded Systems, ");
                    } else if (hw <= weaknessThreshold) {
                        weaknesses.append("Hardware and Embedded Systems, ");
                        improvements.append("Hardware and Embedded Systems, ");
                    }
                    System.out.println();
                    System.out.println(strengths.toString());
                    System.out.println(weaknesses.toString());
                    System.out.println(improvements.toString());
                    System.out.println();
    }
    public void interestProfiling(){
        System.out.println("============================= Career Matching ==============================");
        int passion;
        int interest;
        System.out.println("choose any one Interest of the following:");
        System.out.println("1. Manipulating Data");
        System.out.println("2. Devloping Websites and Applications");
        System.out.println("3. Devloping Game Applications");
        System.out.println("4. Security and Ethical Hacking");
        System.out.println("5. Managing Stored Data on Cloud");
        System.out.println("6. Artificial Intelligence");
        System.out.println("7. Robots");
        while(true){
            interest = sc.nextInt();
           if(interest>=1 && interest<=7){
               break;
           }
           System.out.println("please enter valid input");
       }
        System.out.println("Choose any one Passion of the following:");
        System.out.println("1. Working with large datasets to find trends and insights");
        System.out.println("2. Building interactive and user-friendly web interfaces");
        System.out.println("3. Creating immersive and entertaining digital experiences");
        System.out.println("4. Protecting systems and networks from cyber threats");
        System.out.println("5. Managing and optimizing data storage in remote servers");
        System.out.println("6. Developing intelligent systems that can learn and adapt");
        System.out.println("7. Designing and programming autonomous machines");
        while(true){
             passion = sc.nextInt();
            if(passion>=1 && passion<=7){
                break;
            }
            System.out.println("please enter valid input");
        }
        giveRecommendations(interest, passion);
    }
    
    public static void giveRecommendations(int interest, int passion) {
        recommendations.put("1-1", "Data Science, Big Data Analytics");
        recommendations.put("1-6", "AI development, Machine Learning Engineering");
        recommendations.put("2-2", "Front-end or Back-end Development, Full Stack Development");
        recommendations.put("3-3", "Game Design, Game Programming");
        recommendations.put("4-4", "Penetration Testing, Security Analysis");
        recommendations.put("5-5", "Cloud Architecture, Cloud Security");
        recommendations.put("6-6", "AI development, Machine Learning Engineering");
        recommendations.put("7-7", "Robotics Engineering, Automation");
        String key = interest + "-" + passion;
        String recommendation = recommendations.get(key);
        if(recommendation==null){
            String recomendation1="";
            if(interest==1){
                recomendation1+="Data Science or ";
            }else if(interest==2){
                recomendation1+="Web Devloper or ";
            }else if(interest==3){
                recomendation1+="Game Devloper or ";
            }else if(interest==4){
                recomendation1+="Cyber Security or Ethical Hacking or ";
            }else if(interest==5){
                recomendation1+="Cloud Computing or ";
            }else if(interest==6){
                recomendation1+="AI development and Machine Learning Engineering";
            }else if(interest==7){
                recomendation1+="Robotics Engineering or ";
            }
            if(passion==1){
                recomendation1+="Big Data Analytics";
            }
            else if(passion==2){
                recomendation1+="Full Stack Development";
            }else if(passion==3){
                recomendation1+="Game Design";
            }else if(passion==4){
                recomendation1+="Security Analysis";
            }else if(passion==5){
                recomendation1+="Cloud Architecture";
            }else if(passion==7){
                recomendation1+="Automation";
            }
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("Based on your choices, here are some recommendations: ");
            System.out.println("Interest: " + getInterestDescription(interest));
            System.out.println("Passion: " + getPassionDescription(passion));
            System.out.println("Recommendation: " + recomendation1);
        }else{
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Based on your choices, here are some recommendations: ");
        System.out.println("Interest: " + getInterestDescription(interest));
        System.out.println("Passion: " + getPassionDescription(passion));
        System.out.println("Recommendation: " + recommendation);
        }
    }
    public static String getInterestDescription(int interest) {
        switch (interest) {
            case 1: return "Manipulating Data";
            case 2: return "Developing Websites and Applications";
            case 3: return "Developing Game Applications";
            case 4: return "Security and Ethical Hacking";
            case 5: return "Managing Stored Data on Cloud";
            case 6: return "Artificial Intelligence";
            case 7: return "Robots";
            default: return "Unknown Interest";
        }
 }
 public static String getPassionDescription(int passion) {
    switch (passion) {
        case 1: return "Working with large datasets to find trends and insights";
        case 2: return "Building interactive and user-friendly web interfaces";
        case 3: return "Creating immersive and entertaining digital experiences";
        case 4: return "Protecting systems and networks from cyber threats";
        case 5: return "Managing and optimizing data storage in remote servers";
        case 6: return "Developing intelligent systems that can learn and adapt";
        case 7: return "Designing and programming autonomous machines";
        default: return "Unknown Passion";
    }
}



public void personalityAssessment( User a) {
    if(a.pt!=1){
        System.out.println("This test will affect Your Profile's Personality Traits Section !");
        System.out.println("Want to continue ? (y/n): ");
        String s=sc.next();
        if(s.equalsIgnoreCase("n")){
            return;
        }
    }
   
    System.out.println("=================================== Personality Assessment ======================================");
   
   String[] QUESTIONS = {
    "I enjoy analyzing complex problems to find efficient solutions.",
    "I pay close attention to details when working on a project.",
    "I thrive on solving challenging problems.",
    "I often come up with innovative ideas for projects.",
    "I work well in collaborative team environments.",
    "I persist through difficulties until I find a solution."
};


   String[] TRAITS = {
    "Analytical Thinking",
    "Attention to Detail",
    "Problem Solving",
    "Innovation",
    "Teamwork",
    "Persistence"
};

  
   String[][] CAREERS = {
    {"Data Analyst", "Systems Analyst", "Software Developer"},
    {"Quality Assurance Engineer", "Technical Writer", "Database Administrator"},
    {"Software Engineer", "Cybersecurity Specialist", "Network Engineer"},
    {"Research Scientist", "Product Developer", "Startup Founder"},
    {"Project Manager", "Scrum Master", "DevOps Engineer"},
    {"Systems Administrator", "Site Reliability Engineer", "Technical Support Specialist"}
};
    Scanner scanner = new Scanner(System.in);
    int[] scores = new int[TRAITS.length];
    
   
    for (int i = 0; i < QUESTIONS.length; i++) {
        System.out.println(QUESTIONS[i]);
        System.out.print("Rate your agreement (1-5): ");
        int response = scanner.nextInt();
        while(response<1 || response>5){
            System.out.println("Enter Valid Input !");
            response=scanner.nextInt();
        }
        scores[i] = response;
    }

   
    int maxScoreIndex = 0;
    for (int i = 1; i < scores.length; i++) {
        if (scores[i] > scores[maxScoreIndex]) {
            maxScoreIndex = i;
        }
    }
    
    
    String s="";
    a.setPersonalityTraits(TRAITS[maxScoreIndex]);
   
    if(a.pt!=1){
        DatabaseUtil.updatingPersonalityTraits(TRAITS[maxScoreIndex], a.getId());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Your dominant trait is: " + TRAITS[maxScoreIndex]);
        System.out.println("Based on your personality, you might enjoy careers such as:");
        DatabaseUtil.updatingPersonalityTraitsResultLog(a);
        
    }
    for (String career : CAREERS[maxScoreIndex]) {
        System.out.println("- " + career);
        s+="- "+career;
    }
     Timestamp eventTime = Timestamp.from(Instant.now());
    a.personalityAssessmentResultLog.push("  Date/Time: "+eventTime+" "+TRAITS[maxScoreIndex]+" : - "+s);
//scanner.nextLine();
  //  scanner.close();
}
public void personalityAssessmentResultLog(User a){
    if(a.personalityAssessmentResultLog.isEmpty()){
        System.out.println("No personality Assessment Atempted.");
        return;
    }
                   Node<String> current=a.personalityAssessmentResultLog.list.head;
                   int i=1;
                   while(current!=null){
                    System.out.println(i+". "+current.data);
                    current=current.next;
                    i++;
                
                   }
}
 }


   
