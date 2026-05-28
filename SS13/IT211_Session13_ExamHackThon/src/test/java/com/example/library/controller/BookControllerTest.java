package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.service.BookService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllBooks_return200()
            throws Exception {

        when(bookService.getAllBooks())
                .thenReturn(
                        List.of(
                                new Book(
                                        1L,
                                        "Java",
                                        "A",
                                        "Backend",
                                        10
                                )
                        )
                );

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].title")
                                .value("Java")
                );
    }

    @Test
    void getBookById_found()
            throws Exception {

        when(bookService.getBookById(1L))
                .thenReturn(
                        new Book(
                                1L,
                                "Java",
                                "A",
                                "Backend",
                                10
                        )
                );

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.title")
                                .value("Java")
                );
    }

    @Test
    void getBookById_notFound()
            throws Exception {

        when(bookService.getBookById(1L))
                .thenThrow(
                        new BookNotFoundException(
                                "Book not found"
                        )
                );

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createBook_success()
            throws Exception {

        Book requestBook =
                new Book(
                        null,
                        "Java",
                        "A",
                        "Backend",
                        10
                );

        Book responseBook =
                new Book(
                        1L,
                        "Java",
                        "A",
                        "Backend",
                        10
                );

        when(bookService.createBook(requestBook))
                .thenReturn(responseBook);

        mockMvc.perform(
                        post("/api/books")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(
                                        objectMapper
                                                .writeValueAsString(
                                                        requestBook
                                                )
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(
                        jsonPath("$.id")
                                .value(1L)
                );
    }
}