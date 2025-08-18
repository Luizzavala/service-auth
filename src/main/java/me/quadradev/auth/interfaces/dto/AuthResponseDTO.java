package me.quadradev.auth.interfaces.dto;

public record AuthResponseDTO(String token, long expiresIn, UserDTO user) {
}
