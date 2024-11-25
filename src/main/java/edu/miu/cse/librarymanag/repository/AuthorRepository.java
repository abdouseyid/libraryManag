package edu.miu.cse.librarymanag.repository;

import edu.miu.cse.librarymanag.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
