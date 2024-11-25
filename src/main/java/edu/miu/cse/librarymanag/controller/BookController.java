package edu.miu.cse.librarymanag.controller;

import edu.miu.cse.librarymanag.dto.request.BookRequestDTO;
import edu.miu.cse.librarymanag.dto.response.BookResponseDTO;
import edu.miu.cse.librarymanag.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(book);
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()) // 201
                .body(createdBook);
    }
    // Note: 404 is handled by GlobalExceptionHandler when ResourceNotFoundException is thrown

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()) // 204
                .build();
    }
}
