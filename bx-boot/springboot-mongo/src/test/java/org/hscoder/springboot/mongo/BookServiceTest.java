package org.hscoder.springboot.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hscoder.springboot.mongo.domain.Book;
import org.hscoder.springboot.mongo.repository.BookRepository;
import org.hscoder.springboot.mongo.service.BookService;
import org.hscoder.springboot.simplebuild.util.DateUtil;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class BookServiceTest extends BaseFongoTest{

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    private final String category = "novel";
    private final String title = "the three musketeers";
    private final String author = "Alexandre Dumas";

    private List<Book> preInsertedBooks;

    @Autowired
    Environment environment;
 
    public void getActiveProfiles() {
        
        System.out.println("-------------");
        for (final String profileName : environment.getActiveProfiles()) {
            System.out.println("Currently active profile - " + profileName);
        }   
    }
    
    @Before
    public void initData() {
        
        getActiveProfiles();
        preInsertedBooks = new ArrayList<Book>();

        Book book1 = new Book();

        book1.setAuthor(author);
        book1.setTitle(title);
        book1.setCategory(category);
        book1.setPrice(60);
        book1.setPublishDate(DateUtil.toDate("2016-11-09"));

        book1.setVoteCount(10);
        book1.setCreateTime(new Date());
        book1.setUpdateTime(book1.getCreateTime());

        Book book2 = new Book();

        book2.setAuthor(author);
        book2.setTitle("The New World");
        book2.setCategory(category);
        book2.setPrice(30);
        book2.setPublishDate(DateUtil.toDate("2016-12-09"));

        book2.setVoteCount(8);
        book2.setCreateTime(new Date());
        book2.setUpdateTime(book1.getCreateTime());

        preInsertedBooks.add(bookRepository.save(book1));
        preInsertedBooks.add(bookRepository.save(book2));
    }

    @After
    public void disposeData() {
        bookRepository.delete(preInsertedBooks);
    }

    @Test
    public void testCreate() {

        Book newBook = bookService.createBook(category, "New Book", author, 30, new Date());
        assertNotNull(newBook);
        assertNotNull(newBook.getId());
        assertEquals(newBook.getCategory(), category);

        bookRepository.delete(newBook);
    }

    @Test
    public void testUpdatePrice() {
        int newPrice = 100;
        Book book = preInsertedBooks.get(0);

        boolean result = bookService.updatePrice(book.getId(), 100);
        assertTrue(result);

        book = bookRepository.findOne(book.getId());
        assertTrue(book.getPrice() == newPrice);
    }

    @Test
    public void testDelete() {
        Book book = preInsertedBooks.get(0);
        boolean result = bookService.deleteBook(book.getId());
        assertTrue(result);

        book = bookRepository.findOne(book.getId());
        assertNull(book);
    }

    @Test
    public void testGet() {
        Book book = bookService.getBookByTitle(title);
        assertNotNull(book);
    }

    @Test
    public void testListTopVoted() {

        Book top = preInsertedBooks.get(0);

        List<Book> books = bookService.listTopVoted(category, 10);
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(books.get(0).getId(), top.getId());
    }

    @Test
    public void testVote() {
        Book toVote = preInsertedBooks.get(1);

        boolean result = bookService.addVote(toVote.getId(), 10);
        assertTrue(result);

        // must change the rank
        List<Book> books = bookService.listTopVoted(category, 10);
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals(books.get(0).getId(), toVote.getId());
    }

    @Test
    public void testSearch() {

        PageResult<Book> result = bookService.search(category, null, author, DateUtil.toDate("2016-12-01"),
                DateUtil.toDate("2016-12-30"), 0, 20);

        assertNotNull(result);
        assertTrue(result.getTotalCount() == 1);
        assertNotNull(result.getList());
        assertTrue(result.getList().size() > 0);
        assertEquals(result.getList().get(0).getId(), preInsertedBooks.get(1).getId());

    }
}
