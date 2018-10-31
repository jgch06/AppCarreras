package cr.ac.tec.vis_u.Models;

/**
 * Created by elang on 9/25/2016.
 */
public class Option {
  private long idSection;
  private long optionId;
  private String optionName;
  private String sectionName;
  
  public Option(int optionId, long idSection, String optionName, String sectionName) {
    this.optionId = optionId;
    this.idSection = idSection;
    this.optionName = optionName;
    this.sectionName = sectionName;
  }
  
  public String getSectionName() {
    return sectionName;
  }
  
  public long getIdSection() {
    return idSection;
  }
  
  public long getOptionId() {
    return optionId;
  }
  
  public String getOptionName() {
    return optionName;
  }
}
