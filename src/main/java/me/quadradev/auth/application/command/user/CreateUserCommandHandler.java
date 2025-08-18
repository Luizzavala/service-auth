package me.quadradev.auth.application.command.user;

import lombok.RequiredArgsConstructor;
import me.quadradev.auth.common.error.DuplicateResourceException;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import me.quadradev.auth.domain.user.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserCommandHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User handle(CreateUserCommand command) {
        userRepository.findByEmail(command.email()).ifPresent(u -> {
            throw new DuplicateResourceException("Email already exists");
        });
        User user = User.builder()
                .email(command.email())
                .password(passwordEncoder.encode(command.password()))
                .status(UserStatus.ENABLED.getCode())
                .build();
        return userRepository.save(user);
    }
}
