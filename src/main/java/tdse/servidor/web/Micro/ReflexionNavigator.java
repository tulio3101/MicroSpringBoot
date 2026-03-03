package tdse.servidor.web.Micro;

import java.lang.reflect.Method;
import java.lang.reflect.Member;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ReflexionNavigator {

  public static void main(String[] args) throws FileNotFoundException {

    Class c = "Hola".getClass();

    printMembers(c.getFields(), "Fields");
  }

  private static void printMembers(Member[] mbrs, String s) throws FileNotFoundException {
    PrintWriter out = new PrintWriter(s);
    out.format("%s:%n", s);
    for (Member mbr : mbrs) {
      if (mbr instanceof Field)
        out.format(" %s%n", ((Field) mbr).toGenericString());
      else if (mbr instanceof Constructor)
        out.format(" %s%n", ((Constructor) mbr).toGenericString());
      else if (mbr instanceof Method)
        out.format(" %s%n", ((Method) mbr).toGenericString());
    }
    if (mbrs.length == 0)
      out.format(" -- No %s --%n", s);
    out.format("%n");
  }

}
