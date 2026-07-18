package com.workintech.library.exceptions;

public class BookLimitExceededException extends RuntimeException {
    public BookLimitExceededException(String message) {
        super(message);
    }
}
