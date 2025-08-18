package me.quadradev.auth.interfaces.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(@Email @NotBlank String email, @NotBlank String password) {
}
