package com.ridvancilgin.spring_boot_docker.service;


import com.ridvancilgin.spring_boot_docker.dto.BookDto;
import com.ridvancilgin.spring_boot_docker.entity.Book;
import com.ridvancilgin.spring_boot_docker.error.BookNotFoundException;
import com.ridvancilgin.spring_boot_docker.error.BookUnSupportedFieldPatchException;
import com.ridvancilgin.spring_boot_docker.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public List<BookDto> findAllBook() {
        List<Book> bookDtoList = bookRepository.findAll();
        return bookDtoList.stream().map(book ->
                dozerBeanMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto createBook(BookDto bookDto) {

        Book book = bookRepository.save(dozerBeanMapper.map(bookDto, Book.class));
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public BookDto findOneBook(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(Exception::new);
        return dozerBeanMapper.map(book, BookDto.class);
    }

    public Book updateBook(Book book, Long id) {
        return bookRepository.findById(id).map(x -> {
            x.setName(book.getName());
            x.setAuthor(book.getAuthor());
            x.setPrice(book.getPrice());
            return bookRepository.save(x);
        }).orElseGet(() -> {
            book.setId(id);
            return bookRepository.save(book);
        });
    }

    public Book patchAuthor(Map<String, String> update, Long id) {
        return bookRepository.findById(id)
                .map(x -> {
                    String author = update.get("author");
                    if (!isBlank(author)) {
                        x.setAuthor(author);
                        return bookRepository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException(update.keySet());
                    }
                }).orElseThrow(() -> new BookNotFoundException(id));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}