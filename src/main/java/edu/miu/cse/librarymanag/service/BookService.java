package edu.miu.cse.librarymanag.service;

import edu.miu.cse.librarymanag.dto.request.BookRequestDTO;
import edu.miu.cse.librarymanag.dto.response.BookResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookResponseDTO> getAllBooks();
    BookResponseDTO getBookById(Long id);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);
}
