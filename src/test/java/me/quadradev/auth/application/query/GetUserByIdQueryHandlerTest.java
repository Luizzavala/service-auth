package me.quadradev.auth.application.query;

import me.quadradev.auth.AbstractIntegrationTest;
import me.quadradev.auth.application.query.user.GetUserByIdQuery;
import me.quadradev.auth.application.query.user.GetUserByIdQueryHandler;
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
class GetUserByIdQueryHandlerTest extends AbstractIntegrationTest {

    @Autowired
    private GetUserByIdQueryHandler handler;
    @Autowired
    private UserRepository userRepository;

    @Test
    void handleReturnsUser() {
        User saved = userRepository.save(User.builder().email("byid@example.com").password("pwd").status(1).build());
        User found = handler.handle(new GetUserByIdQuery(saved.getId()));
        assertEquals("byid@example.com", found.getEmail());
    }

    @Test
    void handleThrowsForMissingUser() {
        assertThrows(ResourceNotFoundException.class, () -> handler.handle(new GetUserByIdQuery(999L)));
    }
}
