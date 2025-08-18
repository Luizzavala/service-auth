package me.quadradev.auth.domain.user;

public enum UserStatus {
    DISABLED(0),
    ENABLED(1);

    private final int code;

    UserStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
