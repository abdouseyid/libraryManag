package edu.miu.cse.librarymanag.mapper;

import edu.miu.cse.librarymanag.dto.request.AuthorRequestDTO;
import edu.miu.cse.librarymanag.dto.response.AuthorResponseDTO;
import edu.miu.cse.librarymanag.entities.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author authorRequestDTOToAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO authorToAuthorResponseDTO(Author author);
}
