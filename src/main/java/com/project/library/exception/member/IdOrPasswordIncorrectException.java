package com.project.library.exception.member;

public class IdOrPasswordIncorrectException extends RuntimeException{

    public IdOrPasswordIncorrectException(String msg, Throwable t){
        super(msg,t);
    }

    public IdOrPasswordIncorrectException(String msg){
        super(msg);
    }

    public IdOrPasswordIncorrectException(){
        super();
    }
}
