package cr.ac.tec.vis_u.Models;

/**
 * Created by elang on 10/5/2016.
 */
public class Subsection {
  private String name;
  private String description;
  
  public Subsection(String name, String description) {
    this.name = name;
    this.description = description;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
}
