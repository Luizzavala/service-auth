package me.quadradev.auth.application.command.user;

public record UpdateUserStatusCommand(Long userId, Integer status) {
}
