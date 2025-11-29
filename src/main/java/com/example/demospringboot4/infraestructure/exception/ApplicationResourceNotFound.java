package com.example.demospringboot4.infraestructure.exception;

public class ApplicationResourceNotFound extends ApplicationException {
    public ApplicationResourceNotFound(String message) {
        super(message);
    }

}
