package com.odaw2a.orkdate.exceptions;

public class InvalidUserDataException extends Exception {
    public InvalidUserDataException(String msg){
        super(msg);
    }
    public InvalidUserDataException(){
        super("Los datos de usuario introducidos no son válidos");
    }
}
