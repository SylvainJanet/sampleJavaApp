package fr.sylvainjanet.test.backend;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
   * profile.
   */
  @Value("${app.environment}")
  private String environment;

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
        .andExpect(content().string(equalTo("Hello World ! - " + environment)));
  }

  /**
   * Checks the /add-message.
   *
   * @throws Exception if error occurs.
   */
  @Test
  void testAddMessage() throws Exception {
    String content = "Test de l'application";
    this.mockMvc.perform(put("/add-message?content=" + content).with(csrf()))
        .andDo(print()).andExpect(status().isOk()).andExpect(
            content().string(equalTo("message \"" + content + "\" added.")));
  }
}
