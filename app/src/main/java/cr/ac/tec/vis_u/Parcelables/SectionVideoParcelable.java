package cr.ac.tec.vis_u.Parcelables;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by elang on 9/26/2016.
 */
public class SectionVideoParcelable implements Parcelable {
  public static final Creator<SectionVideoParcelable> CREATOR = new Creator<SectionVideoParcelable>() {
    @Override
    public SectionVideoParcelable createFromParcel(Parcel in) {
      return new SectionVideoParcelable(in);
    }
    
    @Override
    public SectionVideoParcelable[] newArray(int size) {
      return new SectionVideoParcelable[size];
    }
  };
  private String video_file_path;
  
  protected SectionVideoParcelable(Parcel in) {
    video_file_path = in.readString();
  }
  
  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(video_file_path);
  }
  
  @Override
  public int describeContents() {
    return 0;
  }
  
  public String getVideoFilePath() {
    return video_file_path;
  }
}
