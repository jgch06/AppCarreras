package cr.ac.tec.vis_u.GlideLoggingListeners;

import android.util.Log;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Locale;

/**
 * Created by elang on 9/26/2016.
 */
public class LoggingListener<T, R> implements RequestListener<T, R> {
  public static final String GLIDE_TAG = "GLIDE";
  
  @Override
  public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
    
    String strFirstResource = String.valueOf(isFirstResource);
    String msg = String.format(Locale.ROOT, "onException(%s, %s, %s, %s)", e, model, target,
        strFirstResource);
    
    Log.d(LoggingListener.GLIDE_TAG, msg, e);
    return false;
  }
  
  @Override
  public boolean onResourceReady(Object resource, Object model, Target target,
                                 boolean isFromMemoryCache, boolean isFirstResource) {
    String strIsFromMemCache = String.valueOf(isFromMemoryCache);
    String strIsFirstResource = String.valueOf(isFirstResource);
    String msg = String.format(Locale.ROOT, "onResourceReady(%s, %s, %s, %s, %s)", resource,
        model, target, strIsFromMemCache, strIsFirstResource);
    
    Log.d(LoggingListener.GLIDE_TAG, msg);
    return false;
  }
}