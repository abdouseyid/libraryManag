package edu.miu.cse.librarymanag.dto.request;

import java.util.Set;

public record AuthorRequestDTO(
        String name,
        Set<Long> bookIds
) {
}
