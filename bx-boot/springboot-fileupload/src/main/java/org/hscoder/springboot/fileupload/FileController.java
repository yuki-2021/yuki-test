package org.hscoder.springboot.fileupload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传样例
 * 
 * @author atp
 *
 */
@Controller
@RequestMapping(value = "/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    // 后缀名
    private static final String[] IMAGE_SUFFIXES = { "jpg", "png", "jpeg", "bmp" };
    // 大小
    private static final long IMAGE_MAX_SIZE = 2 * 1024 * 1024;

    // 存放根目录
    private static final File ROOT = new File("D:/temp");

    /**
     * 文件上传
     * 
     * @param file
     * @return
     */
    @PostMapping(value = "/single", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<String> singleUpload(@RequestParam("file") MultipartFile file) {

        logger.info("file receive {}", file.getOriginalFilename());

        // 检查文件内容是否为空
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("no file input");
        }

        // 原始文件名
        String fileName = file.getOriginalFilename();

        // 检查后缀名
        if (!checkImageSuffix(fileName)) {
            return ResponseEntity.badRequest().body("the file is not image");
        }

        // 检查大小
        if (!checkSize(file.getSize())) {
            return ResponseEntity.badRequest().body("the file is too large");
        }

        String name = save(file);

        URI getUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/get").queryParam("name", name)
                .build(true).toUri();

        return ResponseEntity.ok(getUri.toString());

    }

    /**
     * 多文件上传
     * 
     * @param files
     * @return
     */
    @PostMapping(value = "/multi", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<String>> multiUpload(@RequestParam("file") MultipartFile[] files) {

        logger.info("file receive count {}", files.length);

        List<String> uris = new ArrayList<String>();
        for (MultipartFile file : files) {
            // 检查文件内容是否为空
            if (file.isEmpty()) {
                continue;
            }

            // 原始文件名
            String fileName = file.getOriginalFilename();

            // 检查后缀名
            if (!checkImageSuffix(fileName)) {
                logger.warn("the file {} is not image", fileName);
                continue;
            }

            // 检查大小
            if (!checkSize(file.getSize())) {
                logger.warn("the file {} is too large", fileName);
                continue;
            }

            String name = save(file);

            URI getUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/get").queryParam("name", name)
                    .build(true).toUri();
            uris.add(getUri.toString());
        }

        return ResponseEntity.ok(uris);

    }

    /**
     * 检查文件后缀
     * 
     * @param fileName
     * @return
     */
    private boolean checkImageSuffix(String fileName) {
        String testFileName = fileName.toLowerCase();
        for (String suffix : IMAGE_SUFFIXES) {
            if (testFileName.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查大小
     * 
     * @param size
     * @return
     */
    private boolean checkSize(long size) {
        if (size > IMAGE_MAX_SIZE) {
            return false;
        }
        return true;
    }

    /**
     * 保存文件
     * 
     * @param file
     * @return
     */
    private String save(MultipartFile file) {

        if (!ROOT.isDirectory()) {
            ROOT.mkdirs();
        }
        try {
            String path = UUID.randomUUID().toString() + getSuffix(file.getOriginalFilename());
            File storeFile = new File(ROOT, path);
            file.transferTo(storeFile);
            return path;

        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSuffix(String originalFilename) {
        int idx = originalFilename.lastIndexOf('.');
        if (idx >= 0) {
            return originalFilename.substring(idx);
        }
        return "";
    }

    /**
     * 文件访问
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(path = "/get")
    public ResponseEntity<Object> get(@RequestParam("name") String name) throws IOException {

        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.badRequest().body("name is empty");
        }

        if (!checkName(name)) {
            return ResponseEntity.badRequest().body("name is illegal");
        }

        File file = new File(ROOT, name);
        if (!file.isFile()) {
            return ResponseEntity.notFound().build();
        }

        if (!file.canRead()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("no allow to access");
        }
        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        //.contentType(MediaType.IMAGE_JPEG).
        return ResponseEntity.ok().contentLength(file.length()).body(resource);
    }

    /**
     * 文件下载
     * 
     * @param name
     * @return
     * @throws IOException
     */
    @GetMapping(path = "/download")
    public ResponseEntity<Object> download(@RequestParam("name") String name) throws IOException {

        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.badRequest().body("name is empty");
        }

        if (!checkName(name)) {
            return ResponseEntity.badRequest().body("name is illegal");
        }

        File file = new File(ROOT, name);
        if (!file.isFile()) {
            return ResponseEntity.notFound().build();
        }

        if (!file.canRead()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("no allow to access");
        }

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().header("Content-Disposition", "attachment;fileName=" + name)
                .contentLength(file.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    /**
     * 检查文件名是否合法
     * 
     * @param name
     * @return
     */
    private boolean checkName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        if (name.contains("..") || name.contains("/") || name.contains("\\")) {
            return false;
        }
        return true;
    }

    /**
     * 上传异常处理
     * 
     * @author atp
     *
     */
    @ControllerAdvice(assignableTypes = FileController.class)
    public class MultipartExceptionHandler {
        @ExceptionHandler(MultipartException.class)
        public ResponseEntity<String> handleUploadError(MultipartException e) {
            return ResponseEntity.badRequest().body("上传失败:" + e.getCause().getMessage());
        }
    }

    @Configuration
    public static class FileConfig {

        /**
         * 文件上传大小配置
         * 
         * @return
         */
        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            factory.setMaxFileSize("10MB");
            factory.setMaxRequestSize("50MB");
            return factory.createMultipartConfig();

        }
    }
}
