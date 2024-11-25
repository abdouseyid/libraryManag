package edu.miu.cse.librarymanag.repository;

import edu.miu.cse.librarymanag.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
