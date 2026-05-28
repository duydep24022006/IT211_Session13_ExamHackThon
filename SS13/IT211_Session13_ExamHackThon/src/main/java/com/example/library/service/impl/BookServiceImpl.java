package com.example.library.service.impl;

import com.example.library.entity.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl
        implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {

        log.debug("Get all books");

        List<Book> books =
                bookRepository.findAll();

        log.info("Found {} books", books.size());

        return books;
    }

    @Override
    public Book getBookById(Long id) {

        log.debug("Get book by id {}", id);

        return bookRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("Book not found with id {}", id);

                    return new BookNotFoundException(
                            "Book not found with id: " + id
                    );
                });
    }

    @Override
    public Book createBook(Book book) {

        log.debug("Create book {}", book);

        Book savedBook =
                bookRepository.save(book);

        log.info("Book created successfully");

        return savedBook;
    }

    @Override
    public Book updateBook(Long id, Book book) {

        Book existingBook =
                getBookById(id);

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setCategory(book.getCategory());
        existingBook.setQuantity(book.getQuantity());

        return bookRepository.save(existingBook);
    }

    @Override
    public Book patchBook(Long id, Book book) {

        Book existingBook =
                getBookById(id);

        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }

        if (book.getAuthor() != null) {
            existingBook.setAuthor(book.getAuthor());
        }

        if (book.getCategory() != null) {
            existingBook.setCategory(book.getCategory());
        }

        if (book.getQuantity() != null) {
            existingBook.setQuantity(book.getQuantity());
        }

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {

        Book existingBook =
                getBookById(id);

        bookRepository.delete(existingBook);

        log.info("Deleted book {}", id);
    }
}