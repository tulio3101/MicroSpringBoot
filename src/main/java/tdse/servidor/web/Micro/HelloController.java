package tdse.servidor.web.Micro;

@RestController
public class HelloController {

  @GetMapping("/")
  public static String index() {
    return "Greetings";
  }

  @GetMapping("/pi")
  public static String pi() {
    return "PI: " + Math.PI;
  }
}
