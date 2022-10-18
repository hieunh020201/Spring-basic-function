package com.hieunh.restfulAPI.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
