package me.quadradev.auth.interfaces.user;

import lombok.RequiredArgsConstructor;
import me.quadradev.auth.application.query.user.GetUserByEmailQuery;
import me.quadradev.auth.application.query.user.GetUserByEmailQueryHandler;
import me.quadradev.auth.application.query.user.ListUsersQuery;
import me.quadradev.auth.application.query.user.ListUsersQueryHandler;
import me.quadradev.auth.common.api.ApiResponse;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.interfaces.dto.UserDTO;
import me.quadradev.auth.interfaces.dto.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserByEmailQueryHandler getUserByEmailQueryHandler;
    private final ListUsersQueryHandler listUsersQueryHandler;

    @GetMapping("/me")
    public ApiResponse<UserDTO> me(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User user = getUserByEmailQueryHandler.handle(new GetUserByEmailQuery(email));
        return new ApiResponse<>(true, "User fetched", UserMapper.toDto(user));
    }

    @GetMapping
    public ApiResponse<Page<UserDTO>> list(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        Page<User> users = listUsersQueryHandler.handle(new ListUsersQuery(page, size));
        Page<UserDTO> dtos = users.map(UserMapper::toDto);
        return new ApiResponse<>(true, "Users list", dtos);
    }
}
