package cr.ac.tec.vis_u.Models;

/**
 * Created by elang on 9/14/2016.
 */
public class Section {
  private long idSection;
  private String name;
  private String image;
  private String description;
  private String titleColor;
  
  public Section(long idSection, String name, String image, String description, String titleColor) {
    this.idSection = idSection;
    this.name = name;
    this.description = description;
    this.image = image;
    this.titleColor = titleColor;
  }
  
  public long getIdSection() {
    return idSection;
  }
  
  public String getName() {
    return name;
  }
  
  public String getImage() {
    return image;
  }
  
  public String getDescription() {
    return description;
  }
  
  public String getTitleColor() {
    return titleColor;
  }
}
