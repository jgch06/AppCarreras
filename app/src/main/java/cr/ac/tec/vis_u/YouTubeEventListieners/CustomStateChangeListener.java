package cr.ac.tec.vis_u.YouTubeEventListieners;

import com.google.android.youtube.player.YouTubePlayer;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by elang on 9/26/2016.
 */
public class CustomStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
  private Context context;
  
  public CustomStateChangeListener(Context context) {
    this.context = context;
  }
  
  @Override
  public void onLoading() {
    
  }
  
  @Override
  public void onLoaded(String s) {
    
  }
  
  @Override
  public void onAdStarted() {
    
  }
  
  @Override
  public void onVideoStarted() {
    
  }
  
  @Override
  public void onVideoEnded() {
    
  }
  
  @Override
  public void onError(YouTubePlayer.ErrorReason errorReason) {
    
  }
  
  private void showMessage(String message) {
    Toast.makeText(this.context, message, Toast.LENGTH_LONG).show();
  }
}
