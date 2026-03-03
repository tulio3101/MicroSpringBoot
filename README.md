# ServidorWeb

A lightweight web framework developed in Java that allows you to create web applications with REST services and static file handling. The framework provides a simple way to define GET routes using lambda expressions and extract query parameters from incoming requests.

## Features

- **REST Services**: Define GET endpoints using lambda expressions
- **Query Parameters**: Easily access query string parameters
- **Static Files**: Serve HTML, CSS, JavaScript, and images
- **Simple API**: Intuitive fluent interface

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.6** or higher

```bash
java -version
mvn -version
```

### Installing

1. Clone the repository:

```bash
git clone https://github.com/tulio3101/ServidorWeb.git
cd ServidorWeb
```

2. Compile the project:

```bash
mvn clean install
```

3. Run the example application:

```bash
mvn exec:java -Dexec.mainClass="tdse.servidor.web.ComponentsExamples.HelloWebApp"
```

## Usage

### Defining REST Services with GET

```java
HttpServer.get("/hello", (req, res) -> "Hello " + req.getValues("name"));
HttpServer.get("/pi", (req, res) -> String.valueOf(Math.PI));
```

### Extracting Query Parameters

```java
HttpServer.get("/greet", (req, res) -> {
    String name = req.getValues("name");
    return "Hello, " + (name != null ? name : "World") + "!";
});
```

### Serving Static Files

```java
HttpServer.staticFiles("webroot");
```

Place your static files (HTML, CSS, JS, images) in `src/main/resources/webroot/`.

### Complete Example

```java
public class MyApp {
    public static void main(String[] args) throws IOException {
        HttpServer.staticFiles("webroot");
        
        HttpServer.get("/hello", (req, res) -> "Hello " + req.getValues("name"));
        HttpServer.get("/pi", (req, res) -> String.valueOf(Math.PI));
        
        HttpServer.main(args);
    }
}
```

## Testing

Run the application and test these endpoints:

- **REST Services:**
  - http://localhost:35000/hello?name=Pedro
  - http://localhost:35000/pi

- **Static Files:**
  - http://localhost:35000/index.html

## Built With

- [Java](https://www.java.com/) - Programming language
- [Maven](https://maven.apache.org/) - Dependency management

## Authors

- **Tulio Riaño Sánchez** - [tulio3101](https://github.com/tulio3101)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
