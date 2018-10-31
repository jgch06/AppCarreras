package cr.ac.tec.vis_u.Utility;

/**
 * Created by elang on 9/10/2016.
 * Edited by jorgeG on 30/10/2018
 */
public class URLBuilder {
  private static final String WEB_SERVICE_URL = "https://carrerastec-cr.herokuapp.com/web_services/";
  
  private URLBuilder() {
  }
  
  public static String requestGET(String parameter) {
    return String.format("%s%s", WEB_SERVICE_URL, parameter);
  }
}
