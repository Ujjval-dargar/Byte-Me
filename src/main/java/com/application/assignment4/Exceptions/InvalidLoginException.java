package com.application.assignment4.Exceptions;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String errorMessage) {
        super(errorMessage);
    }
}
