package com.web.startspring.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message) {super(message);}
}
