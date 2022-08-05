package com.craft.shorturl.exception;

public class ErrorWhileSavingToDB extends RuntimeException {
    public ErrorWhileSavingToDB(String message) {
        super(message);
    }
}
