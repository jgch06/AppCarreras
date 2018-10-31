package cr.ac.tec.vis_u.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by elang on 10/8/2016.
 */
public class ConnectionDetector {
  
  private final static ConnectionDetector connectionDetector = new ConnectionDetector();
  private static int TYPE_WIFI = 1;
  private static int TYPE_MOBILE = 2;
  private static int TYPE_NOT_CONNECTED = 0;
  
  public static int getConnectionStatus(Context context) {
    ConnectivityManager connectivityManager = (ConnectivityManager)
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
    
    if (activeNetwork != null) {
      if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
        return TYPE_WIFI;
      
      if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
        return TYPE_MOBILE;
    }
    return TYPE_NOT_CONNECTED;
  }
  
  public static ConnectionDetector getConnectionDetector() {
    return connectionDetector;
  }
}

