package cr.ac.tec.vis_u.Parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elang on 9/24/2016.
 */
public class SectionParcelable implements Parcelable {
  public static final Creator<SectionParcelable> CREATOR = new Creator<SectionParcelable>() {
    @Override
    public SectionParcelable createFromParcel(Parcel in) {
      return new SectionParcelable(in);
    }
    
    @Override
    public SectionParcelable[] newArray(int size) {
      return new SectionParcelable[size];
    }
  };
  private long idSection;
  private String name;
  private String description;
  private String image;
  private String titleColor;
  
  protected SectionParcelable(Parcel in) {
    idSection = in.readLong();
    name = in.readString();
    description = in.readString();
    image = in.readString();
    titleColor = in.readString();
  }
  
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(idSection);
    dest.writeString(name);
    dest.writeString(description);
    dest.writeString(image);
    dest.writeString(titleColor);
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  public long getIdSection() {
    return idSection;
  }
  
  public String getImage() {
    return image;
  }
  
  public String getDescription() {
    return description;
  }
  
  public String getName() {
    return name;
  }
  
  public String getTitleColor() {
    return titleColor;
  }
}
