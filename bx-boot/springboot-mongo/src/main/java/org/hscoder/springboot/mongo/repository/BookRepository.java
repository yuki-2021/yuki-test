package org.hscoder.springboot.mongo.repository;

import java.util.Date;
import java.util.List;

import org.hscoder.springboot.mongo.domain.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom{

    public List<Book> findByCategory(String category, Pageable pageable);

    public Book findOneByTitle(String title);
    
    //按作者查询
    public List<Book> findByAuthorOrderByUpdateTimeDesc(String author);
    
    //按作者查询
    @Query(value = "{ 'publishDate' : { '$gte': ?0 } }", fields = "{'author': 1, 'title': 1}")
    public List<Book> listRecentPublished(Date dateAfter);
    
}
