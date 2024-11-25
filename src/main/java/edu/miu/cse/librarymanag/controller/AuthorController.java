package edu.miu.cse.librarymanag.controller;


import edu.miu.cse.librarymanag.dto.request.AuthorRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import edu.miu.cse.librarymanag.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO author = authorService.getAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(author);
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED.value()) // 201
                .body(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(
            @PathVariable Long id,
            @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO updatedAuthor = authorService.updateAuthor(id, authorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK.value()) // 200
                .body(updatedAuthor);
        // Note: 404 is handled by GlobalExceptionHandler when ResourceNotFoundException is thrown
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT.value()) // 204
                .build();
        // Note: 404 is handled by GlobalExceptionHandler when ResourceNotFoundException is thrown
    }
}
