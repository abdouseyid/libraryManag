package edu.miu.cse.librarymanag.controller;


import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cse.librarymanag.dto.request.BookRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import edu.miu.cse.librarymanag.dto.response.BookResponseDTO;
import edu.miu.cse.librarymanag.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private ObjectMapper objectMapper;

    private BookRequestDTO bookRequestDTO;
    private BookResponseDTO bookResponseDTO;
    private List<BookResponseDTO> bookResponseDTOList;

    @BeforeEach
    void setUp() {
        // Set up test data
        bookRequestDTO = new BookRequestDTO("Test Book", "1234567890", Set.of(1L, 2L));

        Set<AuthorResponseDTO> authors = new HashSet<>();
        AuthorResponseDTO author = new AuthorResponseDTO("Test Author");
        authors.add(author);

        bookResponseDTO = new BookResponseDTO("Test Book", "1234567890", authors);

        bookResponseDTOList = Arrays.asList(bookResponseDTO);
    }
//
//    @Test
//    void getAllBooks_ShouldReturnListOfBooks() throws Exception {
//        when(bookService.getAllBooks()).thenReturn(bookResponseDTOList);
//
//        mockMvc.perform(get("/api/v1/books"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].title").value("Test Book"))
//                .andExpect(jsonPath("$[0].isbn").value("1234567890"));
//    }
//
//    @Test
//    void getBookById_WithValidId_ShouldReturnBook() throws Exception {
//        when(bookService.getBookById(1L)).thenReturn(bookResponseDTO);
//
//        mockMvc.perform(get("/api/v1/books/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Test Book"))
//                .andExpect(jsonPath("$.isbn").value("1234567890"));
//    }
//
//    @Test
//    void createBook_WithValidData_ShouldReturnCreatedBook() throws Exception {
//        when(bookService.createBook(any(BookRequestDTO.class))).thenReturn(bookResponseDTO);
//
//        mockMvc.perform(post("/api/v1/books")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(bookRequestDTO)))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Test Book"))
//                .andExpect(jsonPath("$.isbn").value("1234567890"));
//    }
//
//    @Test
//    void updateBook_WithValidData_ShouldReturnUpdatedBook() throws Exception {
//        when(bookService.updateBook(eq(1L), any(BookRequestDTO.class))).thenReturn(bookResponseDTO);
//
//        mockMvc.perform(put("/api/v1/books/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(bookRequestDTO)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.title").value("Test Book"))
//                .andExpect(jsonPath("$.isbn").value("1234567890"));
//    }
//
//    @Test
//    void deleteBook_WithValidId_ShouldReturnNoContent() throws Exception {
//        doNothing().when(bookService).deleteBook(1L);
//
//        mockMvc.perform(delete("/api/v1/books/1"))
//                .andExpect(status().isNoContent());
//    }

    @Test
    public void getAllBooks_success_returnsBookList() {
        //bookResponseDTOList;
        Mockito.when(bookService.getAllBooks()).thenReturn(bookResponseDTOList);
        ResponseEntity<List<BookResponseDTO>> listResponseEntity = bookController.getAllBooks();
        Assertions.assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
        Assertions.assertEquals(bookResponseDTOList, listResponseEntity.getBody());
    }

    @Test
    public void createBook_validInput_returnsCreatedBook() {
        Mockito.when(bookService.createBook(bookRequestDTO)).thenReturn(bookResponseDTO);
        ResponseEntity<BookResponseDTO> responseEntity = bookController.createBook(bookRequestDTO);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(bookResponseDTO, responseEntity.getBody());
    }

    @Test
    public void updateBook_validInput_updatesBook() {
        Long bookId = 1L;
        Mockito.when(bookService.updateBook(bookId, bookRequestDTO)).thenReturn(bookResponseDTO);
        ResponseEntity<BookResponseDTO> responseEntity = bookController.updateBook(bookId, bookRequestDTO);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(bookResponseDTO, responseEntity.getBody());
    }

    @Test
    public void deleteBoookById_validId_deleteBook() {
        Long bookId = 1L;
        ResponseEntity<BookResponseDTO> responseEntity = bookController.deleteBook(bookId);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        Mockito.verify(bookService, Mockito.times(1)).deleteBook(bookId);
    }

}
