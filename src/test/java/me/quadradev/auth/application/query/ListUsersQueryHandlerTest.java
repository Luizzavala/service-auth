package me.quadradev.auth.application.query;

import me.quadradev.auth.application.query.user.ListUsersQuery;
import me.quadradev.auth.application.query.user.ListUsersQueryHandler;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ListUsersQueryHandlerTest {

    @Autowired
    private ListUsersQueryHandler handler;
    @Autowired
    private UserRepository userRepository;

    @Test
    void handleReturnsPageOfUsers() {
        for (int i = 0; i < 3; i++) {
            userRepository.save(User.builder().email("user" + i + "@example.com").password("pwd").status(1).build());
        }
        Page<User> page = handler.handle(new ListUsersQuery(0, 2));
        assertEquals(2, page.getContent().size());
        assertEquals(3, page.getTotalElements());
    }
}
