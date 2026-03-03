package tdse.servidor.web.ComponentsExamples;

import java.io.IOException;
import java.lang.Math;

import tdse.servidor.web.ServidorWebBrowser.HttpServer;

public class HelloWebApp {

  public static void main(String args[]) throws IOException {

    HttpServer.staticFiles("/webroot");

    HttpServer.get("/pi", (req, res) -> {
      return String.valueOf(Math.PI);
    });
    HttpServer.get("/hello", (req, res) -> "Hello " + req.getValues("name"));
    HttpServer.get("/frommethod", (req, res) -> {
      return String.valueOf(Math.E);
    });

    HttpServer.main(args);

  }

}
