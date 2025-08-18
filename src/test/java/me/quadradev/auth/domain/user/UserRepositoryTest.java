package me.quadradev.auth.domain.user;

import me.quadradev.auth.config.AuditingConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(AuditingConfig.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmailWorks() {
        User user = User.builder().email("repo@example.com").password("pwd").status(1).build();
        userRepository.save(user);
        assertTrue(userRepository.findByEmail("repo@example.com").isPresent());
    }

    @Test
    void uniqueEmailConstraint() {
        User user1 = User.builder().email("dup@example.com").password("pwd").status(1).build();
        userRepository.save(user1);
        User user2 = User.builder().email("dup@example.com").password("pwd").status(1).build();
        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user2);
        });
    }
}
