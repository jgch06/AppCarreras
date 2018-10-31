package cr.ac.tec.vis_u.Models;

/**
 * Created by elang on 9/24/2016.
 */
public class Career {
  private long idCareer;
  private String name;
  private String image;
  private String school;
  
  public Career(long idCareer, String name, String image, String school) {
    this.idCareer = idCareer;
    this.name = name;
    this.image = image;
    this.school = school;
  }
  
  public long getIdCareer() {
    return idCareer;
  }
  
  public String getName() {
    return name;
  }
  
  public String getImage() {
    return image;
  }
  
  public String getSchool() {
    return school;
  }
}
