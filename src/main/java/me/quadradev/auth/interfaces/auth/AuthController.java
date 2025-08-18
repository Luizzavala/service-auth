package me.quadradev.auth.interfaces.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.quadradev.auth.application.command.user.CreateUserCommand;
import me.quadradev.auth.application.command.user.CreateUserCommandHandler;
import me.quadradev.auth.application.query.user.GetUserByEmailQuery;
import me.quadradev.auth.application.query.user.GetUserByEmailQueryHandler;
import me.quadradev.auth.common.api.ApiResponse;
import me.quadradev.auth.domain.user.User;
import me.quadradev.auth.infrastructure.security.JwtProvider;
import me.quadradev.auth.interfaces.dto.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CreateUserCommandHandler createUserCommandHandler;
    private final GetUserByEmailQueryHandler getUserByEmailQueryHandler;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@Valid @RequestBody CreateUserDTO dto) {
        User user = createUserCommandHandler.handle(new CreateUserCommand(dto.email(), dto.password()));
        return new ApiResponse<>(true, "User registered", UserMapper.toDto(user));
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO dto) {
        User user = getUserByEmailQueryHandler.handle(new GetUserByEmailQuery(dto.email()));
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtProvider.generateToken(user.getEmail(), user.getId(), List.of("ROLE_USER"));
        AuthResponseDTO response = new AuthResponseDTO(token, jwtProvider.getExpiration(), UserMapper.toDto(user));
        return new ApiResponse<>(true, "Login successful", response);
    }
}
