package edu.miu.cse.librarymanag.service.impl;

import edu.miu.cse.librarymanag.dto.request.BookRequestDTO;
import edu.miu.cse.librarymanag.dto.response.BookResponseDTO;
import edu.miu.cse.librarymanag.entities.Author;
import edu.miu.cse.librarymanag.entities.Book;
import edu.miu.cse.librarymanag.mapper.BookMapper;
import edu.miu.cse.librarymanag.repository.AuthorRepository;
import edu.miu.cse.librarymanag.repository.BookRepository;
import edu.miu.cse.librarymanag.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookResponseDTO).toList();
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return bookMapper.bookToBookResponseDTO(book);
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book savedBook = bookRepository.save(bookMapper.bookRequestDTOToBook(bookRequestDTO));
        return bookMapper.bookToBookResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book book = bookRepository.findById(id)
                .orElse(null);
        book.setTitle(bookRequestDTO.title());
        book.setIsbn(bookRequestDTO.isbn());

        Set<Author> authors = bookRequestDTO.authorIds().stream().map(
                authorId -> authorRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("Author not found with id: " + authorId)))
                .collect(Collectors.toSet());
        book.setAuthors(authors);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.bookToBookResponseDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
