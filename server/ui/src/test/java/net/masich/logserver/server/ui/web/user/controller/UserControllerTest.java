package net.masich.logserver.server.ui.web.user.controller;

import net.masich.logserver.server.ui.test.BaseIntegrationTest;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends BaseIntegrationTest {

    @Test
    public void signedInUserInfo() throws Exception {
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

    @Test
    public void signedInUserInfoForUnauthorized() throws Exception {
        mockMvc.perform(get("/user/signed-in/info")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
               .andExpect(status().isUnauthorized());
    }

    @Test
    public void allUsers() throws Exception {
        mockMvc.perform(get("/user/all")
                        .with(SIGNED_IN_USER)
                        .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
               .andExpect(status().isOk())
               .andExpect(content().contentType("application/json;charset=UTF-8"))
               .andExpect(jsonPath("$.users").exists())
               .andExpect(jsonPath("$.users").isArray())
               .andExpect(jsonPath("$.users").value(hasSize(1)))
               .andExpect(jsonPath("$.users[0].id").value(1))
               .andExpect(jsonPath("$.users[0].email").value("w3cvalid@gmail.com"))
               .andExpect(jsonPath("$.users[0].active").value(true))
               .andExpect(jsonPath("$.users[0].name").exists())
        ;
    }

}