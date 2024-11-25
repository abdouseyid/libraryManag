package edu.miu.cse.librarymanag.service.impl;

import edu.miu.cse.librarymanag.dto.request.AuthorRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import edu.miu.cse.librarymanag.entities.Author;
import edu.miu.cse.librarymanag.entities.Book;
import edu.miu.cse.librarymanag.mapper.AuthorMapper;
import edu.miu.cse.librarymanag.repository.AuthorRepository;
import edu.miu.cse.librarymanag.repository.BookRepository;
import edu.miu.cse.librarymanag.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorToAuthorResponseDTO).toList();
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        return authorMapper.authorToAuthorResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        Author savedAuthor = authorRepository.save(authorMapper.authorRequestDTOToAuthor(authorRequestDTO));
        return authorMapper.authorToAuthorResponseDTO(savedAuthor);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + id));
        author.setName(authorRequestDTO.name());
        if (authorRequestDTO.bookIds() != null) {
            Set<Book> books = authorRequestDTO.bookIds().stream()
                    .map(bookId -> bookRepository.findById(bookId)
                            .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bookId)))
                    .collect(Collectors.toSet());
            author.setBooks(books);
        }
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.authorToAuthorResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new NoSuchElementException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

}
