package io.aanagtalon.backend.entity.exception;

public class WishlistException extends RuntimeException {
    public WishlistException(String message) {
        super(message);
    }

    public WishlistException() {
        super("An error occurred");
    }
}
