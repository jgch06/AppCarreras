package cr.ac.tec.vis_u.Activities;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

public class LogoSplashActivity extends AppCompatActivity {
  private final static String SERVER_ERROR = "SERVER_ERROR";
  private final static String CONNECTION_ERROR = "CONNECTION_ERROR";
  private final static String CAREER_LIST = "CAREER_LIST";
  private OkHttpClient client;
  private ArrayList<CareerParcelable> careers;
  private String url = URLBuilder.requestGET("career.php");
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_logo_splash_activity);
    
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .build();
    
    if (ConnectionDetector.getConnectionStatus(LogoSplashActivity.this) != 0) {
      //make get request to web service
      client.newCall(request).enqueue(new Callback() {
        final Bundle bundle = new Bundle();
        final Intent intent = new Intent(LogoSplashActivity.this, MainActivity.class);
        final Gson gson = new Gson();
        
        @Override
        public void onFailure(Call call, IOException e) {
          e.printStackTrace();
          final Intent intent = new Intent(LogoSplashActivity.this, InternetConnectionActivity.class);
          startActivity(intent);
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
      final Intent intent = new Intent(LogoSplashActivity.this, InternetConnectionActivity.class);
      startActivity(intent);
    }
  }
}