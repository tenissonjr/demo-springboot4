package com.example.demospringboot4.infraestructure.exception;

public class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }   

}
