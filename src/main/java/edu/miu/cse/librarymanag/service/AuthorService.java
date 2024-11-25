package edu.miu.cse.librarymanag.service;

import edu.miu.cse.librarymanag.dto.request.AuthorRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<AuthorResponseDTO> getAllAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteAuthor(Long id);
}
