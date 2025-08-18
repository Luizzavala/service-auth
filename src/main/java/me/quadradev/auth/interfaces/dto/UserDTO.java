package me.quadradev.auth.interfaces.dto;

import java.time.LocalDateTime;

public record UserDTO(Long id, String email, Integer status, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
