package me.quadradev.auth.common.api;

public record ApiResponse<T>(boolean success, String message, T data) {
}
