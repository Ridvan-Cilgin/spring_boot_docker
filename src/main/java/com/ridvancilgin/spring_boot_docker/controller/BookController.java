package com.ridvancilgin.spring_boot_docker.controller;


import com.ridvancilgin.spring_boot_docker.dto.BookDto;
import com.ridvancilgin.spring_boot_docker.entity.Book;
import com.ridvancilgin.spring_boot_docker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public List<BookDto> findAll() {
        return bookService.findAllBook();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping("/{id}")
    public BookDto findOneBook(@PathVariable Long id) {
        BookDto bookDto = null;
        try {
            bookDto = bookService.findOneBook(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookDto;
    }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        return bookService.updateBook(book, id);

    }

    @PatchMapping("/{id}")
    public Book patchAuthor(@RequestBody Map<String, String> update, @PathVariable Long id) {
        return bookService.patchAuthor(update, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

    }

}
