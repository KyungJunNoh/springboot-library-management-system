package com.project.library.exception.rental;

public class BookLoanHistoryNotFoundException extends RuntimeException{

    public BookLoanHistoryNotFoundException(String msg, Throwable t){
        super(msg,t);
    }

    public BookLoanHistoryNotFoundException(String msg){
        super(msg);
    }

    public BookLoanHistoryNotFoundException(){
        super();
    }
}
