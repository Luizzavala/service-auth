package me.quadradev.auth.application.query.user;

import lombok.RequiredArgsConstructor;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.domain.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListUsersQueryHandler {

    private final UserRepository userRepository;

    public Page<User> handle(ListUsersQuery query) {
        return userRepository.findAll(PageRequest.of(query.page(), query.size()));
    }
}
