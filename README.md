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
<p align="center">
  ⭐ Star this repository if it helped you!
</p>
