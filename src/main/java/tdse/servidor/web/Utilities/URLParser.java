package tdse.servidor.web.Utilities;

import java.io.*;
import java.net.*;

public class URLParser {

  public static void main(String args[]) {
    try {
      URL url = new URL("http://ldbn.is.escuelaing.edu.co:8084/respuestasexamenarep.txt");

      System.out.println("Protocol: " + url.getProtocol());
      System.out.println("Authority " + url.getAuthority());
      System.out.println("Host " + url.getHost());
      System.out.println("Puerto: " + url.getPort());
      System.out.println("Path: " + url.getPath());
      System.out.println("Query: " + url.getQuery());
      System.out.println("File: " + url.getFile());
      System.out.println("Ref" + url.getRef());

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

}
