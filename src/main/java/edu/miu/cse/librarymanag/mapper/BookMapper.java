package edu.miu.cse.librarymanag.mapper;

import edu.miu.cse.librarymanag.dto.request.BookRequestDTO;
import edu.miu.cse.librarymanag.dto.response.BookResponseDTO;
import edu.miu.cse.librarymanag.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book bookRequestDTOToBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO bookToBookResponseDTO(Book book);
}
