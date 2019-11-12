package org.hscoder.springboot.mail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SimpleMailSender implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SimpleMailSender.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送文本
     */
    public void sendText() {
        String from = environment.getProperty("spring.mail.from");
        String to = environment.getProperty("spring.mail.to");

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);

        msg.setSubject("first email from yourself");
        msg.setText("hello world!");

        this.mailSender.send(msg);
        logger.info("send text done");
    }

    /**
     * 发送附件
     * 
     * @throws MessagingException
     */
    public void sendAttachment() throws MessagingException {
        String from = environment.getProperty("spring.mail.from");
        String to = environment.getProperty("spring.mail.to");

        // 使用Mime消息体
        MimeMessage message = mailSender.createMimeMessage();

        // multipart参数为true，表示需要发送附件
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);

        helper.setSubject("first file from yourself");
        helper.setText("check the file");

        // 指定系统文件
        File file = new File("D:\\temp\\attachment.xlsx");
        FileSystemResource resource = new FileSystemResource(file);
        helper.addAttachment(file.getName(), resource);

        mailSender.send(message);

        logger.info("send attachment done");
    }

    /**
     * 发送模板邮件(html)
     * 
     * @throws MessagingException
     */
    public void sendTemplateMail() throws MessagingException {

        String from = environment.getProperty("spring.mail.from");
        String to = environment.getProperty("spring.mail.to");

        // 使用Mime消息体
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);

        helper.setSubject("first html report from yourself");

        // 根据模板、变量生成内容

        // 数据模型
        List<Pet> pets = new ArrayList<Pet>();
        pets.add(new Pet("Polly", "Bird", 2));
        pets.add(new Pet("Tom", "Cat", 5));
        pets.add(new Pet("Badboy", "Dog", 3));

        Context context = new Context();
        context.setVariable("customer", "LiLei");
        context.setVariable("pets", pets);

        String text = templateEngine.process("mail/template", context);
        helper.setText(text, true);


        //添加图片展示
        helper.addInline("soft", new FileSystemResource("D:/temp/soft.png"));
        
        mailSender.send(message);
    }

    @Override
    public void run(String... args) throws Exception {
        // sendText();

        // sendAttachment();

        sendTemplateMail();
    }

    /**
     * 宠物模型
     * 
     * @author hscoder
     *
     */
    public static class Pet {

        private String name;
        private String type;
        private int age;

        public Pet(String name, String type, int age) {
            super();
            this.name = name;
            this.type = type;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }
    
    
    public static void main(String[] args) {
        Arrays.asList(new File("C:\\Users\\atp\\Documents\\My Knowledge\\Data\\atphack@163.com\\N.1 技术笔记\\04. 数据库-中间件\\mongodb").listFiles()).stream().forEach( f -> {
            System.out.println(f.getName());
        });
    }
    
}
