package me.quadradev.auth.application.command.user;

import lombok.RequiredArgsConstructor;
import me.quadradev.auth.common.error.ResourceNotFoundException;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserStatusCommandHandler {

    private final UserRepository userRepository;

    public User handle(UpdateUserStatusCommand command) {
        User user = userRepository.findById(command.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setStatus(command.status());
        return userRepository.save(user);
    }
}
