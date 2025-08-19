package me.quadradev.auth.application.query;

import me.quadradev.auth.application.query.user.GetUserByEmailQuery;
import me.quadradev.auth.application.query.user.GetUserByEmailQueryHandler;
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
class GetUserByEmailQueryHandlerTest {

    @Autowired
    private GetUserByEmailQueryHandler handler;
    @Autowired
    private UserRepository userRepository;

    @Test
    void handleReturnsUser() {
        userRepository.save(User.builder().email("byemail@example.com").password("pwd").status(1).build());
        User found = handler.handle(new GetUserByEmailQuery("byemail@example.com"));
        assertEquals("byemail@example.com", found.getEmail());
    }

    @Test
    void handleThrowsForMissingEmail() {
        assertThrows(ResourceNotFoundException.class, () -> handler.handle(new GetUserByEmailQuery("missing@example.com")));
    }
}
