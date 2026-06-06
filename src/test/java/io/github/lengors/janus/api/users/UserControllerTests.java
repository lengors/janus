package io.github.lengors.janus.api.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.github.lengors.janus.testing.TestSuite;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

@TestSuite.Defaults
record UserControllerTests(
  @Autowired MockMvc mockMvc
) {
  @Test
  void givenUserWhenDoesNotExistThenNotFound() throws Exception {
    mockMvc
      .perform(get("/users/12345678-1234-5678-1234-567812345677/avatar"))
      .andExpect(status().isNotFound());
  }

  @Test
  void givenValidUserWhenLinkAvatarThenUserAvatar() throws Exception {
    mockMvc
      .perform(get("/users/12345678-1234-5678-1234-567812345678/avatar"))
      .andExpect(status().isFound())
      .andExpect(header().string("Location", "https://example.com/avatar.png"));
  }

  @Test
  void givenValidUserWhenBlobAvatarThenUserAvatar() throws Exception {
    mockMvc
      .perform(get("/users/12345678-1234-5678-1234-567812345679/avatar"))
      .andExpect(status().isOk())
      .andExpect(content().string("data:image/svg+xml;base64,abc"));
  }
}
