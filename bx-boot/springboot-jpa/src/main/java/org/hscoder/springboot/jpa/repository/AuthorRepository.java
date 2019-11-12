package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
