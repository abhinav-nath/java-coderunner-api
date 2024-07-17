package com.codecafe.coderunner.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@RestController
public class CodeExecutionController {

  @PostMapping("/execute")
  public String executeCode(@RequestBody String code) {
    try {
      // Create the directory if it does not exist
      File directory = new File("generated-code");
      if (!directory.exists()) {
        directory.mkdir();
      }

      // Save the code to a .java file
      File sourceFile = new File(directory, "TempCode.java");
      try (PrintWriter out = new PrintWriter(new FileWriter(sourceFile))) {
        out.println(code);
      }

      // Compile the code
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      int compilationResult = compiler.run(null, null, null, sourceFile.getPath());
      if (compilationResult != 0) {
        return "Compilation Failed";
      }

      // Load and execute the compiled class
      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{directory.toURI().toURL()});
      Class<?> cls = Class.forName("TempCode", true, classLoader);
      Method method = cls.getDeclaredMethod("main", String[].class);
      method.invoke(null, (Object) new String[]{});

      return "Execution Successful";
    } catch (Exception e) {
      e.printStackTrace();
      return "Execution Failed: " + e.getMessage();
    }
  }

}