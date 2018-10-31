package cr.ac.tec.vis_u.Activities;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import cr.ac.tec.vis_u.ConfigurationFiles.Config;
import cr.ac.tec.vis_u.Models.SectionVideo;
import cr.ac.tec.vis_u.Parcelables.SectionVideoParcelable;
import cr.ac.tec.vis_u.R;
import cr.ac.tec.vis_u.YouTubeEventListieners.CustomPlaybackEventListener;
import cr.ac.tec.vis_u.YouTubeEventListieners.CustomStateChangeListener;

public class SectionVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
  
  private static final int RECOVERY_REQUEST = 1;
  private Toolbar toolbar;
  private TextView toolbarTitle;
  private TextView toolbarSecondaryTitle;
  private TextView noContentText;
  private YouTubePlayerView youTubePlayerView;
  private ArrayList<SectionVideoParcelable> sectionVideoParcelables;
  private SectionVideo sectionVideo;
  private CustomPlaybackEventListener playbackEventListener;
  private CustomStateChangeListener stateChangeListener;
  private Bundle bundle;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_section_video);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbarTitle = (TextView) findViewById(R.id.txt_title);
    toolbarSecondaryTitle = (TextView) findViewById(R.id.secondary_txt_title);
    noContentText = (TextView) findViewById(R.id.textViewNoContent);
    bundle = getIntent().getExtras();
    sectionVideoParcelables = bundle.getParcelableArrayList("SECTION_VIDEOS");
    
    playbackEventListener = new CustomPlaybackEventListener(SectionVideoActivity.this);
    stateChangeListener = new CustomStateChangeListener(SectionVideoActivity.this);
    
    youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_view);
    
    toolbarTitle.setText(bundle.getString("SECTION_NAME"));
    toolbarSecondaryTitle.setText("Videos");
    
    //get player view from xml, configure with app API KEY
    if (!sectionVideoParcelables.isEmpty()) {
      sectionVideo = new SectionVideo(sectionVideoParcelables.get(0).getVideoFilePath());
      youTubePlayerView.initialize(Config.YOUTUBE_API_KEY, this);
    } else {
      youTubePlayerView.setVisibility(View.GONE);
      noContentText.setVisibility(View.VISIBLE);
    }
  }
  
  @Override
  public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                      YouTubePlayer youTubePlayer, boolean wasRestored) {
    if (!wasRestored) {
      youTubePlayer.cueVideo(sectionVideo.getVideoFilePath()); // play video associated to section
    }
  }
  
  @Override
  public void onInitializationFailure(YouTubePlayer.Provider provider,
                                      YouTubeInitializationResult errorReason) {
    if (errorReason.isUserRecoverableError()) {
      errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
    } else {
      getYoutubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
    }
  }
  
  private YouTubePlayer.Provider getYoutubePlayerProvider() {
    return youTubePlayerView;
  }
  
}
