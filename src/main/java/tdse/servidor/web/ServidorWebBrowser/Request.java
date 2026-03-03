package tdse.servidor.web.ServidorWebBrowser;

import java.util.HashMap;
import java.util.Map;

public class Request {

  private Map<String, String> queryParams;

  public Request(String queryString) {

    queryParams = new HashMap<>();
    if (queryString != null) {
      for (String param : queryString.split("&")) {
        String[] querys = param.split("=");
        queryParams.put(querys[0], querys.length > 1 ? querys[1] : "");
      }
    }
  }

  public String getValues(String key) {
    return queryParams.get(key);
  }

}
