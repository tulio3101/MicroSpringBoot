package tdse.servidor.web.ServidorWebBrowser;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class HttpServer {

  static Map<String, WebMethod> endPoints = new HashMap<>();
  private static String staticPath = "target/classes";

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(35000);
    } catch (IOException e) {
      System.err.println("Could not listen on port: 35000.");
      System.exit(1);
    }
    Socket clientSocket = null;

    boolean running = true;
    while (running) {

      try {
        System.out.println("Listo para recibir ...");
        clientSocket = serverSocket.accept();
      } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
      }

      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
          new InputStreamReader(
              clientSocket.getInputStream()));
      String inputLine, outputLine;

      boolean isFirstLine = true;

      String reqPath = "";

      String queryPath = "";

      while ((inputLine = in.readLine()) != null) {
        System.out.println("Received: " + inputLine);
        if (isFirstLine) {
          String[] firstLineTokens = inputLine.split(" ");
          String method = firstLineTokens[0];
          String uriStr = firstLineTokens[1];
          String protocolVersion = firstLineTokens[2];
          try {
            URI requestedURI = new URI(uriStr);

            reqPath = requestedURI.getPath();
            System.out.println("Path: " + reqPath);

            queryPath = requestedURI.getQuery();
            if (queryPath != null) {
              System.out.println("Query: " + queryPath);
            }

            isFirstLine = false;

          } catch (URISyntaxException e) {
            e.printStackTrace();
          }
        }

        if (!in.ready()) {
          break;
        }
      }
      WebMethod wm = endPoints.get(reqPath);
      if (wm != null) {

        outputLine = "HTTP/1.1 200 OK\n\r"
            + "Content-Type: text/html\n\r"
            + "\n\r"
            + "<!DOCTYPE html>"
            + "<html>"
            + "<head>"
            + "<meta charset=\"UTF-8\">"
            + "<title>Title of the document</title>\n"
            + "</head>"
            + "<body>"
            + wm.execute(new Request(queryPath), null)
            + "</body>"
            + "</html>";
        out.println(outputLine);

      } else {
        outputLine = handleStaticFile(reqPath);
      }
      out.close();
      in.close();
      clientSocket.close();
    }
    serverSocket.close();
  }

  public static void get(String path, WebMethod wm) {
    endPoints.put(path, wm);
  }

  public static void staticFiles(String path) {
    staticPath = "target/classes" + path;
    System.out.println("Archivos en: " + staticPath);
  }

  private static String handleStaticFile(String path) {
    try {
      File file = new File(staticPath + path);

      if (!file.exists()) {
        return "HTTP/1.1 404 Not Found\r\n\r\nFile Not Found";
      }

      BufferedReader br = new BufferedReader(new FileReader(file));
      StringBuilder content = new StringBuilder();
      String line;

      while ((line = br.readLine()) != null) {
        content.append(line);
      }

      br.close();

      return "HTTP/1.1 200 OK\r\n\r\n" + content;

    } catch (IOException e) {
      return "HTTP/1.1 500 Internal Server Error\r\n\r\nError";
    }
  }

}
