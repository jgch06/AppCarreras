package cr.ac.tec.vis_u.Parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elang on 9/26/2016.
 */
public class SectionImageParcelable implements Parcelable {
  public static final Creator<SectionImageParcelable> CREATOR = new Creator<SectionImageParcelable>() {
    @Override
    public SectionImageParcelable createFromParcel(Parcel in) {
      return new SectionImageParcelable(in);
    }
    
    @Override
    public SectionImageParcelable[] newArray(int size) {
      return new SectionImageParcelable[size];
    }
  };
  private String image_file_path;
  
  protected SectionImageParcelable(Parcel in) {
    image_file_path = in.readString();
  }
  
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(image_file_path);
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  public String getImage_file_path() {
    return image_file_path;
  }
}
