package com.project.library.exception.book;

public class BookListAtBookNotFoundException extends RuntimeException{

    public BookListAtBookNotFoundException(String msg, Throwable t){
        super(msg,t);
    }

    public BookListAtBookNotFoundException(String msg){
        super(msg);
    }

    public BookListAtBookNotFoundException(){
        super();
    }
}
