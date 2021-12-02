package com.project.library.exception.book;

public class TheNumberOfBookNotFoundException extends RuntimeException{

    public TheNumberOfBookNotFoundException(String msg, Throwable t){
        super(msg,t);
    }

    public TheNumberOfBookNotFoundException(String msg){
        super(msg);
    }

    public TheNumberOfBookNotFoundException(){
        super();
    }
}
