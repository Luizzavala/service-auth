package me.quadradev.auth.interfaces.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.quadradev.auth.AbstractIntegrationTest;
import me.quadradev.auth.interfaces.dto.AuthRequestDTO;
import me.quadradev.auth.interfaces.dto.CreateUserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registerAndLogin() throws Exception {
        CreateUserDTO register = new CreateUserDTO("john@example.com", "secret");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value("john@example.com"));

        AuthRequestDTO login = new AuthRequestDTO("john@example.com", "secret");
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.token").exists());
    }

    @Test
    void loginInvalidCredentials() throws Exception {
        CreateUserDTO register = new CreateUserDTO("jane@example.com", "secret");
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk());

        AuthRequestDTO login = new AuthRequestDTO("jane@example.com", "wrong");
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());
    }
}
