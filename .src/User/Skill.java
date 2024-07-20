import java.util.LinkedList;

public class Skill {
    String skill;
    int skillId;
   static LinkedList<Skill> skills=new LinkedList<>();
   public Skill(int id, String skill ){
    this.skill=skill;
    this.skillId=id;
   }
    public String getSkill() {
        return skill;
    }
    public void setSkill(String skill) {
        this.skill = skill;
    }
    public int getSkillId() {
        return skillId;
    }
    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }
    @Override
    public String toString() {
        return "skills [skill=" + skill + ", skillId=" + skillId + "]";
    }
    
}
