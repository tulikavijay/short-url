package com.craft.shorturl.exception;

public class URLNotFound extends RuntimeException {
    public URLNotFound(String message) {
        super(message);
    }
}
