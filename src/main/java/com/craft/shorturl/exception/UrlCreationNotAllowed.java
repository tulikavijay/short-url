package com.craft.shorturl.exception;

public class UrlCreationNotAllowed extends RuntimeException {
    public UrlCreationNotAllowed(String message) {
        super(message);
    }
}
