package edu.miu.cse.librarymanag.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cse.librarymanag.dto.request.AuthorRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import edu.miu.cse.librarymanag.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorRequestDTO authorRequestDTO;
    private AuthorResponseDTO authorResponseDTO;
    private List<AuthorResponseDTO> authorResponseDTOList;

    @BeforeEach
    void setUp() {
        // Set up test data
        authorRequestDTO = new AuthorRequestDTO("Test Author", Set.of(1L, 2L));

        authorResponseDTO = new AuthorResponseDTO("Test Author");

        authorResponseDTOList = Arrays.asList(authorResponseDTO);
    }


}
