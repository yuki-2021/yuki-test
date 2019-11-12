package org.hscoder.springboot.jpa.repository;

import org.hscoder.springboot.jpa.BootJpa;
import org.hscoder.springboot.jpa.domain.Author;
import org.hscoder.springboot.jpa.domain.AuthorBookView;
import org.hscoder.springboot.jpa.service.BookService;
import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootJpa.class)
public class AuthorBookViewRepositoryTest {

    @Autowired
    private AuthorBookViewRepository authorBookViewRepository;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAll(){

        Author currentAuthor = bookService.createAuthor("张三", "台北市");
        //初始化类别书籍
        bookService.createBook(currentAuthor, "文学", "现代文学基础讲义", "");
        bookService.createBook(currentAuthor, "小说", "奇迹校园", "中学");

        List<AuthorBookView> views = authorBookViewRepository.findAll();
        System.out.println(JsonUtil.toPrettyJson(views));
    }

}
