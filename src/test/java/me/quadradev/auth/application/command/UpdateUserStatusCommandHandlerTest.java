package me.quadradev.auth.application.command;

import me.quadradev.auth.AbstractIntegrationTest;
import me.quadradev.auth.application.command.user.UpdateUserStatusCommand;
import me.quadradev.auth.application.command.user.UpdateUserStatusCommandHandler;
import me.quadradev.auth.common.error.ResourceNotFoundException;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UpdateUserStatusCommandHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private UpdateUserStatusCommandHandler handler;
    @Autowired
    private UserRepository userRepository;

    @Test
    void handleUpdatesStatus() {
        User user = userRepository.save(User.builder().email("status@example.com").password("pwd").status(0).build());
        UpdateUserStatusCommand cmd = new UpdateUserStatusCommand(user.getId(), 1);
        User updated = handler.handle(cmd);
        assertEquals(1, updated.getStatus());
        assertEquals(1, userRepository.findById(user.getId()).orElseThrow().getStatus());
    }

    @Test
    void handleThrowsForMissingUser() {
        UpdateUserStatusCommand cmd = new UpdateUserStatusCommand(999L, 1);
        assertThrows(ResourceNotFoundException.class, () -> handler.handle(cmd));
    }
}
