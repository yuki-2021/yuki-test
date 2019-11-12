package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

    List<Book> findByType(String type, Pageable request);

    @Transactional
    @Modifying
    @Query("update Book b set b.favCount = b.favCount + ?2 where b.id = ?1")
    int incrFavCount(Long id, int fav);
}
