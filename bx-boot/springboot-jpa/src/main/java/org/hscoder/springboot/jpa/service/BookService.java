package org.hscoder.springboot.jpa.service;

import org.hscoder.springboot.jpa.domain.Author;
import org.hscoder.springboot.jpa.domain.Book;
import org.hscoder.springboot.jpa.repository.AuthorRepository;
import org.hscoder.springboot.jpa.repository.BookRepository;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    /**
     * 创建作者信息
     *
     * @param name
     * @param hometown
     * @return
     */
    public Author createAuthor(String name, String hometown) {

        if (StringUtils.isEmpty(name)) {
            return null;
        }

        Author author = new Author();
        author.setName(name);
        author.setHometown(hometown);

        return authorRepository.save(author);
    }

    /**
     * 创建书籍信息
     *
     * @param author
     * @param type
     * @param title
     * @param description
     * @return
     */
    public Book createBook(Author author, String type, String title, String description) {

        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(title) || author == null) {
            return null;
        }

        Book book = new Book();
        book.setType(type);
        book.setTitle(title);
        book.setDescription(description);

        book.setAuthor(author);
        return bookRepository.save(book);
    }


    /**
     * 更新书籍信息
     *
     * @param bookId
     * @param type
     * @param title
     * @param description
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = Exception.class)
    public boolean updateBook(Long bookId, String type, String title, String description) {
        if (bookId == null || StringUtils.isEmpty(title)) {
            return false;
        }

        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return false;
        }

        book.setType(type);
        book.setTitle(title);
        book.setDescription(description);
        return bookRepository.save(book) != null;
    }

    /**
     * 删除书籍信息
     *
     * @param bookId
     * @return
     */
    public boolean deleteBook(Long bookId) {
        if (bookId == null) {
            return false;
        }


        Book book = bookRepository.findOne(bookId);
        if (book == null) {
            return false;
        }
        bookRepository.delete(book);
        return true;
    }

    /**
     * 根据编号查询
     *
     * @param bookId
     * @return
     */
    public Book getBook(Long bookId) {
        if (bookId == null) {
            return null;
        }
        return bookRepository.findOne(bookId);
    }

    /**
     * 增加收藏数
     *
     * @return
     */
    public boolean incrFav(Long bookId, int fav) {

        if (bookId == null || fav <= 0) {
            return false;
        }
        return bookRepository.incrFavCount(bookId, fav) > 0;
    }

    /**
     * 获取分类下书籍，按收藏数排序
     *
     * @param type
     * @return
     */
    public List<Book> listTopFav(String type, int max) {

        if (StringUtils.isEmpty(type) || max <= 0) {
            return Collections.emptyList();
        }

        // 按投票数倒序排序
        Sort sort = new Sort(Sort.Direction.DESC, "favCount");
        PageRequest request = new PageRequest(0, max, sort);

        return bookRepository.findByType(type, request);
    }

    /**
     * 搜索
     *
     * @param type
     * @param title
     * @param hasFav
     * @param request
     * @return
     */
    public PageResult<Book> search(String type, String title, boolean hasFav, PageRequest request) {

        return bookRepository.search(type, title, hasFav, request);
    }
}
