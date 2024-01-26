package com.FCarrena.demoparkapi.exception;

public class UsernameUniqueViolationExcption extends RuntimeException {

    public UsernameUniqueViolationExcption(String message){
        super(message);

    }
}
