package cr.ac.tec.vis_u.Holders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import cr.ac.tec.vis_u.Activities.SectionMenuActivity;
import cr.ac.tec.vis_u.Parcelables.SectionParcelable;
import cr.ac.tec.vis_u.Utility.ConnectionDetector;
import cr.ac.tec.vis_u.Utility.URLBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by elang on 9/11/2016.
 */
public class ImageHolderCareerMenu extends RecyclerView.ViewHolder implements View.OnClickListener {
  
  private static final String CONNECTION_ERROR = "CONNECTION_ERROR";
  private static final String SERVER_ERROR = "SERVER_ERROR";
  private static final String SECTION_LIST = "SECTION_LIST";
  private static final String CAREER_NAME = "CAREER_NAME";
  private static final String CAREER_SCHOOL = "CAREER_SCHOOL";
  public TextView name;
  public long id;
  public ImageView image;
  public long idCareer;
  public String careerName;
  public String school;
  private OkHttpClient client;
  private Request request;
  private String url;
  private ArrayList<SectionParcelable> sections = new ArrayList<>();
  
  public ImageHolderCareerMenu(View itemView) {
    super(itemView);
    itemView.setOnClickListener(this);
    name = (TextView) itemView.findViewById(cr.ac.tec.vis_u.R.id.career_name);
    image = (ImageView) itemView.findViewById(cr.ac.tec.vis_u.R.id.career_image);
  }
  
  //make request to get section data
  @Override
  public void onClick(View view) {
    url = URLBuilder.requestGET("section.php?id= " + idCareer);
    client = new OkHttpClient();
    final Intent intent = new Intent(view.getContext(), SectionMenuActivity.class);
    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
        (view.getContext(), cr.ac.tec.vis_u.R.style.AppDialogStyle);
    final Bundle bundle = new Bundle();
    final Gson gson = new Gson();
    final View sectionView = view;
    request = new Request.Builder()
        .url(url)
        .build();
    
    //if request is succesful send section data to section activity, else send error message
    if (ConnectionDetector.getConnectionStatus(view.getContext()) != 0) {
      client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
          e.printStackTrace();
          alertDialogBuilder.setTitle(cr.ac.tec.vis_u.R.string.alert_connection_error_title);
          alertDialogBuilder.setMessage(cr.ac.tec.vis_u.R.string.alert_connection_error);
          alertDialogBuilder.setCancelable(false);
          alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              
            }
          });
          alertDialogBuilder.create();
          alertDialogBuilder.show();
        }
        
        @Override
        public void onResponse(Call call, Response response) throws IOException {
          if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
          } else {
            sections = gson.fromJson(response.body().charStream(),
                new TypeToken<ArrayList<SectionParcelable>>() {
                }.getType());
            bundle.putParcelableArrayList(SECTION_LIST, sections);
            intent.putExtras(bundle);
            intent.putExtra(CAREER_NAME, careerName);
            intent.putExtra(CAREER_SCHOOL, school);
            sectionView.getContext().startActivity(intent);
          }
        }
      });
    } else {
      alertDialogBuilder.setTitle(cr.ac.tec.vis_u.R.string.alert_connection_error_title);
      alertDialogBuilder.setMessage(cr.ac.tec.vis_u.R.string.alert_connection_error);
      alertDialogBuilder.setCancelable(false);
      alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          
        }
      });
      alertDialogBuilder.create();
      alertDialogBuilder.show();
    }
  }
}
