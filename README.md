# Java CodeRunner API

This is a small Spring Boot app that exposes a REST API which can take Java code as request payload and execute it.

Start the app by running `Application` class.

Send a POST request to `http://localhost:8080/execute` with payload:

```java
public class TempCode {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

<img width="1079" alt="image" src="https://github.com/user-attachments/assets/cb284afc-1bcd-4385-83a2-dc1f5422ece2">

<br/>
<br/>

Your code will be executed and output will be printed to your console:

![image](https://github.com/user-attachments/assets/d04b52c1-a378-450c-a240-cc1f0000fff7)

---

You can even execute fairly complex code like:

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TempCode {
  public static void main(String[] args) {
    try {
      String city = "Pune";
      String urlString = "https://wttr.in/" + city + "?format=%l+weather+:+%C+%c+|+Temperature+%t+|+Feels+like+%f+|+Humidity+%h+|+Moon+phase+%m+|+Sunrise+%S+|+Sunset+%s\n";

      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuilder content = new StringBuilder();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      connection.disconnect();

      // Print the weather data
      System.out.println("Weather Data: " + content.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
```

![image](https://github.com/user-attachments/assets/c5df0e4a-28e1-4c62-9675-fd402069e732)

The possibilities are endless! 😉

### Disclaimer

> This application is intended for fun and educational purposes only.
> It should never be used in real production environments.
> Executing arbitrary code can lead to malicious results, including security vulnerabilities and data breaches.
> Use this application at your own risk and ensure that it is run in a controlled and isolated environment.
> The developer is not responsible for any misuse or damage caused by the application.


---
<p align="center">
  ⭐ Star this repository if it helped you!
</p>
