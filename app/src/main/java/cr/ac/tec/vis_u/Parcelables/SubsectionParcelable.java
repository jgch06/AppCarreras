package cr.ac.tec.vis_u.Parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elang on 10/5/2016.
 */
public class SubsectionParcelable implements Parcelable {
  public static final Creator<SubsectionParcelable> CREATOR = new Creator<SubsectionParcelable>() {
    @Override
    public SubsectionParcelable createFromParcel(Parcel in) {
      return new SubsectionParcelable(in);
    }
    
    @Override
    public SubsectionParcelable[] newArray(int size) {
      return new SubsectionParcelable[size];
    }
  };
  private String name;
  private String description;
  
  protected SubsectionParcelable(Parcel in) {
    name = in.readString();
    description = in.readString();
  }
  
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(name);
    dest.writeString(description);
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
}
