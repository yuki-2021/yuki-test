package org.hscoder.springboot.mongo.repository;

import java.util.Date;

import org.hscoder.springboot.mongo.domain.Book;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.springframework.data.domain.Pageable;

public interface BookRepositoryCustom {

    public PageResult<Book> search(String category, String title, String author, Date publishDataStart,
            Date publishDataEnd, Pageable pageable);

    public boolean incrVoteCount(String id, int voteIncr);
}
