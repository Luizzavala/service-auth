package me.quadradev.auth.common.api;

import java.time.Instant;

public record ErrorResponse(String error, String message, int status, String path, Instant timestamp) {
}
