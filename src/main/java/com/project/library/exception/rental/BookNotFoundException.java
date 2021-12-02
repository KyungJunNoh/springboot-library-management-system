package com.project.library.exception.rental;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String msg, Throwable t){
        super(msg,t);
    }

    public BookNotFoundException(String msg){
        super(msg);
    }

    public BookNotFoundException(){
        super();
    }
}
