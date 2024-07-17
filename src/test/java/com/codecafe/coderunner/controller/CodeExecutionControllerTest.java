package com.codecafe.coderunner.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CodeExecutionController.class)
public class CodeExecutionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testExecuteCode_successful() throws Exception {
    String code = "public class TempCode { public static void main(String[] args) { System.out.println(\"Hello, world!\"); } }";

    mockMvc.perform(MockMvcRequestBuilders.post("/execute")
                                          .contentType("application/json")
                                          .content(code))
           .andExpect(status().isOk())
           .andExpect(content().string("Execution Successful"));
  }

  @Test
  public void testExecuteCode_compilationFailure() throws Exception {
    String code = "public class TempCode { public static void main(String[] args) { System.out.println(\"Hello, world!\" } }";

    mockMvc.perform(MockMvcRequestBuilders.post("/execute")
                                          .contentType("application/json")
                                          .content(code))
           .andExpect(status().isOk())
           .andExpect(content().string("Compilation Failed"));
  }

  @Test
  public void testExecuteCode_executionFailure() throws Exception {
    String code = "public class TempCode { public static void main(String[] args) { throw new RuntimeException(\"Test exception\"); } }";

    mockMvc.perform(MockMvcRequestBuilders.post("/execute")
                                          .contentType("application/json")
                                          .content(code))
           .andExpect(status().isOk())
           .andExpect(content().string(org.hamcrest.Matchers.startsWith("Execution Failed: ")));
  }

}