package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.domain.Book;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;

public interface BookRepositoryCustom {
    public PageResult<Book> search(String type, String title, boolean hasFav, Pageable pageable);

    public List<Tuple> groupCount();
}
