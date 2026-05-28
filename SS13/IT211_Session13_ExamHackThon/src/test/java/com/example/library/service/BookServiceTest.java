package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.BookServiceImpl;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void getAllBooks_returnList() {

        when(bookRepository.findAll())
                .thenReturn(
                        List.of(
                                new Book(1L,"A","AA","Java",10),
                                new Book(2L,"B","BB","Spring",20)
                        )
                );

        List<Book> books =
                bookService.getAllBooks();

        assertEquals(2, books.size());
    }

    @Test
    void getBookById_found() {

        Book book =
                new Book(1L,"A","AA","Java",10);

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        Book result =
                bookService.getBookById(1L);

        assertEquals("A", result.getTitle());
    }

    @Test
    void getBookById_notFound() {

        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookById(1L)
        );
    }

    @Test
    void createBook_success() {

        Book book =
                new Book(null,"A","AA","Java",10);

        Book savedBook =
                new Book(1L,"A","AA","Java",10);

        when(bookRepository.save(book))
                .thenReturn(savedBook);

        Book result =
                bookService.createBook(book);

        assertNotNull(result.getId());

        verify(bookRepository, times(1))
                .save(book);
    }

    @Test
    void deleteBook_notFound() {

        when(bookRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                BookNotFoundException.class,
                () -> bookService.deleteBook(1L)
        );

        verify(bookRepository, never())
                .delete(any(Book.class));
    }
}