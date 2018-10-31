package cr.ac.tec.vis_u.Parcelables;

import android.os.Parcel;
import android.os.Parcelable;

public class CareerParcelable implements Parcelable {
  public static final Creator<CareerParcelable> CREATOR = new Creator<CareerParcelable>() {
    @Override
    public CareerParcelable createFromParcel(Parcel in) {
      return new CareerParcelable(in);
    }
    
    @Override
    public CareerParcelable[] newArray(int size) {
      return new CareerParcelable[size];
    }
  };
  private long idCareer;
  private String name;
  private String school;
  private String image;
  
  protected CareerParcelable(Parcel in) {
    idCareer = in.readLong();
    name = in.readString();
    school = in.readString();
    image = in.readString();
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeLong(idCareer);
    parcel.writeString(name);
    parcel.writeString(school);
    parcel.writeString(image);
  }
  
  public long getIdCareer() {
    return idCareer;
  }
  
  public String getName() {
    return name;
  }
  
  public String getSchool() {
    return school;
  }
  
  public String getImage() {
    return image;
  }
}
