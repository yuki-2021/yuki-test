package org.hscoder.springboot.mongo.repository;

import java.util.Date;
import java.util.List;

import org.hscoder.springboot.mongo.domain.Book;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import com.mongodb.WriteResult;

public class BookRepositoryImpl implements BookRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean incrVoteCount(String id, int voteIncr) {
        if (StringUtils.isEmpty(id)) {
            return false;
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Update update = new Update();

        update.inc(Book.COL_VOTE_COUNT, voteIncr);
        update.set(Book.COL_UPDATE_TIME, new Date());

        WriteResult result = mongoTemplate.updateFirst(query, update, Book.class);
        return result != null && result.getN() > 0;
    }

    @Override
    public PageResult<Book> search(String category, String title, String author, Date publishDataStart,
            Date publishDataEnd, Pageable pageable) {
        Query query = new Query();

        if (!StringUtils.isEmpty(category)) {
            query.addCriteria(Criteria.where(Book.COL_CATEGORY).is(category));
        }

        if (!StringUtils.isEmpty(author)) {
            query.addCriteria(Criteria.where(Book.COL_AUTHOR).is(author));
        }

        if (!StringUtils.isEmpty(title)) {
            query.addCriteria(Criteria.where(Book.COL_TITLE).regex(title));
        }

        if (publishDataStart != null || publishDataEnd != null) {
            Criteria publishDateCond = Criteria.where(Book.COL_PUBLISH_DATE);

            if (publishDataStart != null) {
                publishDateCond.gte(publishDataStart);
            }
            if (publishDataEnd != null) {
                publishDateCond.lt(publishDataEnd);
            }
            query.addCriteria(publishDateCond);
        }

        long totalCount = mongoTemplate.count(query, Book.class);
        if (totalCount <= 0) {
            return new PageResult<Book>();
        }

        if (pageable != null) {
            query.with(pageable);
        }

        List<Book> books = mongoTemplate.find(query, Book.class);
        return PageResult.of(totalCount, books);
    }

}
