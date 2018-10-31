package cr.ac.tec.vis_u.YouTubeEventListieners;

import com.google.android.youtube.player.YouTubePlayer;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by elang on 9/26/2016.
 */
public class CustomPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
  private Context context;
  
  public CustomPlaybackEventListener(Context context) {
    this.context = context;
  }
  
  @Override
  public void onPlaying() {
    //Call when video starts playing
    showMessage("Mostrando video");
  }
  
  @Override
  public void onPaused() {
    //Call when video is paused
    showMessage("Video pausado");
  }
  
  @Override
  public void onStopped() {
    //Call when video is stopped
    showMessage("Video detenido");
  }
  
  @Override
  public void onBuffering(boolean b) {
    
  }
  
  @Override
  public void onSeekTo(int i) {
    
  }
  
  private void showMessage(String message) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
  }
}
