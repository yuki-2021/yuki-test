package org.hscoder.springboot.mongo.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hscoder.springboot.mongo.domain.Book;
import org.hscoder.springboot.mongo.repository.BookRepository;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 书籍样例
 * 
 * @author atp
 *
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    /**
     * 创建 book
     * 
     * @param category
     * @param title
     * @param author
     * @param price
     * @param publishDate
     * @return
     */
    public Book createBook(String category, String title, String author, int price, Date publishDate) {
        if (StringUtils.isEmpty(category) || StringUtils.isEmpty(title) || StringUtils.isEmpty(author)) {
            return null;
        }

        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setCategory(category);
        book.setPrice(price);
        book.setPublishDate(publishDate);

        book.setVoteCount(0);
        book.setCreateTime(new Date());
        book.setUpdateTime(book.getCreateTime());

        return bookRepository.save(book);
    }

    /**
     * 更新价格
     * 
     * @param id
     * @param price
     * @return
     */
    public boolean updatePrice(String id, int price) {
        if (StringUtils.isEmpty(id)) {
            return false;
        }

        Book book = bookRepository.findOne(id);
        if (book == null) {

            logger.info("the book '{}' is not exist", id);
            return false;
        }

        book.setPrice(price);
        book.setUpdateTime(new Date());
        if (bookRepository.save(book) != null) {
            return true;
        }
        return false;
    }

    /**
     * 根据获取book
     * 
     * @param title
     * @return
     */
    public Book getBookByTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return bookRepository.findOneByTitle(title);
    }

    /**
     * 获取投票排行列表
     * 
     * @param category
     * @param max
     * @return
     */
    public List<Book> listTopVoted(String category, int max) {

        if (StringUtils.isEmpty(category) || max <= 0) {
            return Collections.emptyList();
        }

        // 按投票数倒序排序
        Sort sort = new Sort(Direction.DESC, Book.COL_VOTE_COUNT);
        PageRequest request = new PageRequest(0, max, sort);

        return bookRepository.findByCategory(category, request);

    }

    /**
     * 删除书籍
     * 
     * @param id
     * @return
     */
    public boolean deleteBook(String id) {
        Book book = bookRepository.findOne(id);
        if (book == null) {

            logger.info("the book '{}' is not exist", id);
            return false;
        }

        bookRepository.delete(book);
        return true;
    }

    /**
     * 添加投票
     * 
     * @param id
     * @return
     */
    public boolean addVote(String id, int count) {
        Book book = bookRepository.findOne(id);
        if (book == null) {

            logger.info("the book '{}' is not exist", id);
            return false;
        }

        return bookRepository.incrVoteCount(id, count);
    }

    /**
     * 搜索
     * 
     * @param id
     * @return
     */
    public PageResult<Book> search(String category, String title, String author, Date publishDataStart,
            Date publishDataEnd, int page, int size) {

        Sort sort = new Sort(Direction.DESC, Book.COL_CREATE_TIME);
        PageRequest pageable = new PageRequest(page, size, sort);

        return bookRepository.search(category, title, author, publishDataStart, publishDataEnd, pageable);
    }

}
