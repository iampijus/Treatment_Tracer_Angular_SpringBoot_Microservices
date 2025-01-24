package com.healthcare.bookmark_service.exception;

public class BookmarkAlreadyExistsException extends RuntimeException{
    public BookmarkAlreadyExistsException(String message){
        super(message);
    }
}
