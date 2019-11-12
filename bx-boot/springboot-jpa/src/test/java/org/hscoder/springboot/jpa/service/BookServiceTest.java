package org.hscoder.springboot.jpa.service;

import org.hscoder.springboot.jpa.BootJpa;
import org.hscoder.springboot.jpa.domain.Author;
import org.hscoder.springboot.jpa.domain.Book;
import org.hscoder.springboot.jpa.repository.AuthorRepository;
import org.hscoder.springboot.jpa.repository.BookRepository;
import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.hscoder.springboot.simplebuild.util.PageResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Tuple;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJpa.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author currentAuthor;

    @Before
    public void initData() {
        currentAuthor = bookService.createAuthor("李平", "江西省");
    }

    @After
    public void disposeData() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void testCreateBook() {

        Book book = bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        assertNotNull(book);

        book = bookService.getBook(book.getId());
        assertNotNull(book);
    }

    @Test
    public void testDeleteBook() {

        Book book = bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        assertNotNull(book);

        assertTrue(bookService.deleteBook(book.getId()));
        assertFalse(bookService.deleteBook(book.getId()));

    }

    @Test
    public void testUpdateBook() {

        Book book = bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        assertNotNull(book);

        boolean flag = bookService.updateBook(book.getId(), book.getType(), "隋唐传", "新书上市");
        assertTrue(flag);

        Book newbook = bookService.getBook(book.getId());
        assertNotNull(newbook);
        assertTrue(book.getType().equals(newbook.getType()));
        assertNotEquals(book.getTitle(), newbook.getTitle());
        assertNotEquals(book.getDescription(), newbook.getDescription());
    }

    @Test
    public void testListTopFav() {

        //初始化类别书籍
        bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        bookService.createBook(currentAuthor, "小说", "奇迹校园", "中学");
        bookService.createBook(currentAuthor, "小说", "三重门", "经典");


        List<Book> books;

        //分类查询
        books = bookService.listTopFav("文学", 10);
        assertNotNull(books);
        assertTrue(books.size() == 1);

        books = bookService.listTopFav("小说", 10);
        assertNotNull(books);
        assertTrue(books.size() == 2);

        //收藏变化
        Book lastBook = books.get(1);
        assertTrue(bookService.incrFav(lastBook.getId(), 1));

        //排名发生变化
        books = bookService.listTopFav("小说", 10);
        assertNotNull(books);
        assertEquals(books.get(0).getId(), lastBook.getId());
    }


    @Test
    public void testSearch() {

        //初始化类别书籍
        Book book1 = bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        Book book2 = bookService.createBook(currentAuthor, "小说", "奇迹校园", "中学");
        Book book3 = bookService.createBook(currentAuthor, "小说", "三重门", "经典");

        // 按投票数倒序排序
        Sort sort = new Sort(Sort.Direction.DESC, "favCount");
        PageRequest request = new PageRequest(0, 5, sort);

        PageResult<Book> result = bookService.search("文学1", "", false, request);
        assertTrue(result.isEmpty());

        result = bookService.search("小说", "", false, request);
        assertFalse(result.isEmpty());
        assertTrue(result.getList().size() == 2);


        result = bookService.search("小说", "奇迹", false, request);
        assertFalse(result.isEmpty());
        assertTrue(result.getList().size() == 1);

        result = bookService.search("小说", "大学校园", false, request);
        assertTrue(result.isEmpty());

        result = bookService.search("小说", "奇迹", true, request);
        assertTrue(result.isEmpty());


        //投票
        bookService.incrFav(book2.getId(), 2);
        result = bookService.search("小说", "奇迹", true, request);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGroupCount() {

        //初始化类别书籍
        Book book1 = bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        Book book2 = bookService.createBook(currentAuthor, "小说", "奇迹校园", "中学");
        Book book3 = bookService.createBook(currentAuthor, "小说", "三重门", "经典");
        //投票
        bookService.incrFav(book2.getId(), 2);
        bookService.incrFav(book1.getId(), 15);

        List<Tuple> tuples = bookRepository.groupCount();
        assertNotNull(tuples);
        assertTrue(tuples.size() == 2);

        System.out.println(JsonUtil.toPrettyJson(tuples));
    }
}
