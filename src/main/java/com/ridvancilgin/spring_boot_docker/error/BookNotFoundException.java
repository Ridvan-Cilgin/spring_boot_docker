package com.ridvancilgin.spring_boot_docker.error;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id){

        super("Book id not found : " + id);
    }
}
