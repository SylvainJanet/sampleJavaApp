package fr.sylvainjanet.test.backend;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test the app.
 * 
 * @author Sylvain
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class AppTest {

  /**
   * Autowired mockMvc.
   */
  @Autowired
  private MockMvc mockMvc;

  /**
   * Checks the /hello.
   *
   * @throws Exception if error occurs.
   */
  @Test
  void testHello() throws Exception {
    this.mockMvc.perform(get("/hello")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World !")));
  }
}
