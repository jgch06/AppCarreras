package cr.ac.tec.vis_u.Activities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import cr.ac.tec.vis_u.Parcelables.CareerParcelable;
import cr.ac.tec.vis_u.R;
import cr.ac.tec.vis_u.Utility.ConnectionDetector;
import cr.ac.tec.vis_u.Utility.URLBuilder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InternetConnectionActivity extends AppCompatActivity {
  
  private final static String SERVER_ERROR = "SERVER_ERROR";
  private final static String CONNECTION_ERROR = "CONNECTION_ERROR";
  private final static String CAREER_LIST = "CAREER_LIST";
  private OkHttpClient client;
  private Toolbar mainToolbar;
  private TextView primaryTitle;
  private TextView secondaryTitle;
  private TextView connectionError;
  private Button button;
  private ArrayList<CareerParcelable> careers;
  private String url = URLBuilder.requestGET("career.php");
  private ProgressBar progressBar;
  private Toolbar toolbar;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_internet_connection_error);
    mainToolbar = (Toolbar) findViewById(R.id.toolbar);
    progressBar = (ProgressBar) findViewById(R.id.adprogress_progressBar);
    progressBar.setVisibility(View.GONE);
    setSupportActionBar(mainToolbar);
    
    button = (Button) findViewById(R.id.buttonRetry);
    connectionError = (TextView) findViewById(R.id.textViewInternetConnectionError);
    
    primaryTitle = (TextView) findViewById(R.id.txt_title);
    secondaryTitle = (TextView) findViewById(R.id.secondary_txt_title);
    
    primaryTitle.setText(R.string.connection_error);
    secondaryTitle.setText("");
    
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(InternetConnectionActivity.this, MainActivity.class);
        final Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        
        connectionError.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        
        if (ConnectionDetector.getConnectionStatus(InternetConnectionActivity.this) != 0) {
          client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
              e.printStackTrace();
            }
            
            //response actions, if response is successful modify UI to represent changes
            @Override
            public void onResponse(Call call, Response response) throws IOException {
              if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
              } else {
                careers = gson.fromJson(response.body().charStream(),
                    new TypeToken<ArrayList<CareerParcelable>>() {
                    }.getType());
                bundle.putParcelableArrayList(CAREER_LIST, careers);
                intent.putExtras(bundle);
                startActivity(intent);
              }
            }
          });
        } else {
          progressBar.setVisibility(View.GONE);
          connectionError.setVisibility(View.VISIBLE);
          button.setVisibility(View.VISIBLE);
          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder
              (InternetConnectionActivity.this, R.style.AppDialogStyle);
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
    });
  }
  
  private void makeRequest() {
    
  }
}
