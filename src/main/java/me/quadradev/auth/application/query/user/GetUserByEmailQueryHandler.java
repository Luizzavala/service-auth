package me.quadradev.auth.application.query.user;

import lombok.RequiredArgsConstructor;
import me.quadradev.auth.common.error.ResourceNotFoundException;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserByEmailQueryHandler {

    private final UserRepository userRepository;

    public User handle(GetUserByEmailQuery query) {
        return userRepository.findByEmail(query.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
