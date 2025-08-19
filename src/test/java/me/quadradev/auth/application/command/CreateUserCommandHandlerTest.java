package me.quadradev.auth.application.command;

import me.quadradev.auth.AbstractIntegrationTest;
import me.quadradev.auth.application.command.user.CreateUserCommand;
import me.quadradev.auth.application.command.user.CreateUserCommandHandler;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CreateUserCommandHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private CreateUserCommandHandler handler;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void registerHashesPassword() {
        CreateUserCommand cmd = new CreateUserCommand("test@example.com", "password");
        User user = handler.handle(cmd);
        assertEquals(1, userRepository.count());
        assertNotEquals("password", user.getPassword());
        assertTrue(passwordEncoder.matches("password", user.getPassword()));
    }
}
