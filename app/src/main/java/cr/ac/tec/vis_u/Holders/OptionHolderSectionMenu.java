package cr.ac.tec.vis_u.Holders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import cr.ac.tec.vis_u.Activities.SectionImageActivity;
import cr.ac.tec.vis_u.Activities.SectionVideoActivity;
import cr.ac.tec.vis_u.Activities.SubsectionActivity;
import cr.ac.tec.vis_u.Parcelables.SectionImageParcelable;
import cr.ac.tec.vis_u.Parcelables.SectionVideoParcelable;
import cr.ac.tec.vis_u.Parcelables.SubsectionParcelable;
import cr.ac.tec.vis_u.R;
import cr.ac.tec.vis_u.Utility.ConnectionDetector;
import cr.ac.tec.vis_u.Utility.URLBuilder;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

;

/**
 * Created by elang on 9/25/2016.
 */
public class OptionHolderSectionMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
  private final static String SECTION_IMAGES = "SECTION_IMAGES";
  private final static String SECTION_VIDEOS = "SECTION_VIDEOS";
  private final static String SECTION_SUBSECTIONS = "SECTION_SUBSECTIONS";
  private final static String SECTION_NAME = "SECTION_NAME";
  public long optionId;
  public long idSection;
  public String sectionName;
  public TextView optionName;
  private ArrayList<SectionImageParcelable> sectionImages;
  private ArrayList<SectionVideoParcelable> sectionVideos;
  private ArrayList<SubsectionParcelable> sectionSubsections;
  
  public OptionHolderSectionMenu(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    optionName = (TextView) itemView.findViewById(R.id.option_name);
  }
  
  @Override
  public void onClick(View view) {
    if (ConnectionDetector.getConnectionStatus(view.getContext()) != 0) {
      if (optionId == 1) {
        makeImageRequest(view);
      } else if (optionId == 2) {
        makeVideoRequest(view);
      } else {
        makeSubsectionRequest(view);
      }
    } else {
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
          (view.getContext(), R.style.AppDialogStyle);
      alertDialogBuilder.setTitle(R.string.alert_connection_error_title);
      alertDialogBuilder.setMessage(R.string.alert_connection_error);
      alertDialogBuilder.setCancelable(false);
      alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          
        }
      })
          .create()
          .show();
    }
  }
  
  /*get Request for images of a section, occurs when user clicks Images option in the
  section activity recycler view*/
  private void makeImageRequest(final View view) {
    String url = URLBuilder.requestGET("sectionImages.php?id= " + idSection);
    OkHttpClient client = new OkHttpClient();
    final Intent intent = new Intent(view.getContext(), SectionImageActivity.class);
    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
        (view.getContext(), R.style.AppDialogStyle);
    final Bundle bundle = new Bundle();
    final Gson gson = new Gson();
    Request request = new Request.Builder()
        .url(url)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(okhttp3.Call call, IOException e) {
        alertDialogBuilder.setTitle(R.string.alert_connection_error_title);
        alertDialogBuilder.setMessage(R.string.alert_connection_error);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            
          }
        })
            .create()
            .show();
        e.printStackTrace();
      }
      
      @Override
      public void onResponse(okhttp3.Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected code " + response);
        } else {
          sectionImages = gson.fromJson(response.body().charStream(),
              new TypeToken<ArrayList<SectionImageParcelable>>() {
              }.getType());
          bundle.putParcelableArrayList(SECTION_IMAGES, sectionImages);
          intent.putExtra(SECTION_NAME, sectionName);
          intent.putExtras(bundle);
          view.getContext().startActivity(intent);
        }
      }
    });
  }
  
  /*get request for videos of a sectioon, this occurs when the user clicks the video option
  in the section recycler view in section activity*/
  private void makeVideoRequest(final View view) {
    String url = URLBuilder.requestGET("sectionVideos.php?id= " + idSection);
    OkHttpClient client = new OkHttpClient();
    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
        (view.getContext(), R.style.AppDialogStyle);
    final Intent intent = new Intent(view.getContext(), SectionVideoActivity.class);
    final Bundle bundle = new Bundle();
    final Gson gson = new Gson();
    Request request = new Request.Builder()
        .url(url)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(okhttp3.Call call, IOException e) {
        alertDialogBuilder.setTitle(R.string.alert_connection_error_title);
        alertDialogBuilder.setMessage(R.string.alert_connection_error);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            
          }
        })
            .create()
            .show();
        e.printStackTrace();
      }
      
      @Override
      public void onResponse(okhttp3.Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected code " + response);
        } else {
          sectionVideos = gson.fromJson(response.body().charStream(),
              new TypeToken<ArrayList<SectionVideoParcelable>>() {
              }.getType());
          bundle.putParcelableArrayList(SECTION_VIDEOS, sectionVideos);
          intent.putExtras(bundle);
          intent.putExtra(SECTION_NAME, sectionName);
          view.getContext().startActivity(intent);
        }
      }
    });
    
  }
  
  private void makeSubsectionRequest(final View view) {
    String url = URLBuilder.requestGET("sectionSubsections.php?id= " + idSection);
    OkHttpClient client = new OkHttpClient();
    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
        (view.getContext(), R.style.AppDialogStyle);
    final Intent intent = new Intent(view.getContext(), SubsectionActivity.class);
    final Bundle bundle = new Bundle();
    final Gson gson = new Gson();
    Request request = new Request.Builder()
        .url(url)
        .build();
    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(okhttp3.Call call, IOException e) {
        alertDialogBuilder.setTitle(R.string.alert_connection_error_title);
        alertDialogBuilder.setMessage(R.string.alert_connection_error);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            
          }
        })
            .create()
            .show();
        e.printStackTrace();
      }
      
      @Override
      public void onResponse(okhttp3.Call call, Response response) throws IOException {
        if (!response.isSuccessful()) {
          throw new IOException("Unexpected code " + response);
        } else {
          sectionSubsections = gson.fromJson(response.body().charStream(),
              new TypeToken<ArrayList<SubsectionParcelable>>() {
              }.getType());
          bundle.putParcelableArrayList(SECTION_SUBSECTIONS, sectionSubsections);
          intent.putExtras(bundle);
          intent.putExtra(SECTION_NAME, sectionName);
          view.getContext().startActivity(intent);
        }
      }
    });
    
  }
}
