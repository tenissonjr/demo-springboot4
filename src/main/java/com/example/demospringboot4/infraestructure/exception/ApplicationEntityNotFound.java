package com.example.demospringboot4.infraestructure.exception;

public class ApplicationEntityNotFound extends ApplicationException {
    public ApplicationEntityNotFound(String message) {
        super(message);
    }

}
