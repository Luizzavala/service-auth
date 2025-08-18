package me.quadradev.auth.interfaces.dto;

import me.quadradev.auth.domain.user.User;

public final class UserMapper {
    private UserMapper() {}

    public static UserDTO toDto(User user) {
        return new UserDTO(user.getId(), user.getEmail(), user.getStatus(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
