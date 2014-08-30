package net.masich.logserver.server.ui.web.user.controller;

import net.masich.logserver.server.ui.test.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends BaseIntegrationTest {

    @Test
    public void someTest() throws Exception {
        mockMvc.perform(get("/user/signed-in/info")
                        .with(SIGNED_IN_USER)
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json;charset=UTF-8"))
               .andExpect(jsonPath("$.email").value("w3cvalid@gmail.com"))
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.active").value(true))
               .andExpect(jsonPath("$.name").exists());
    }

}