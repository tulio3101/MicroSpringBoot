package tdse.servidor.web.Micro;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class FinalSpringBoot {

  public static Map<String, Method> controllerFinalMethods = new HashMap<>();

  public static void main(String[] args)
      throws IOException, ClassNotFoundException, InvocationTargetException,
      IllegalAccessException {

    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    String packageName = args[0];
    String path = packageName.replace(".", "/");
    Enumeration<URL> resources = classLoader.getResources(path);

    List<Class<?>> classes = new ArrayList<>();

    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      File directory = new File(resource.getFile());
      File[] files = directory.listFiles((dir, name) -> name.endsWith(".class"));
      for (File file : files) {
        String fileName = file.getName();
        String className = fileName.substring(0, fileName.length() - 6);
        Class<?> claz = Class.forName(packageName + "." + className);
        classes.add(claz);
      }
    }

    for (Class<?> c : classes) {
      if (c.isAnnotationPresent(RestController.class)) {
        for (Method m : c.getDeclaredMethods()) {
          if (m.isAnnotationPresent(GetMapping.class)) {
            GetMapping a = m.getAnnotation(GetMapping.class);
            System.out.println("Adding controller method for Path: " + a.value());
            controllerFinalMethods.put(a.value(), m);
          }
        }
      }
    }

    String executionPath = args[1];

    Method m = controllerFinalMethods.get(executionPath);

    System.out.println("Invoking: " + m.getName());

    System.out.println(m.invoke(null));

  }

}
