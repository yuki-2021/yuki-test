package com.yuki.file.upload.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    @GetMapping
    public String index(){
        return "index";
    }

    @PostMapping("/upload1")
    @ResponseBody
    public Map<String,String> upload1(MultipartFile file) throws IOException {

        System.out.println("这是Jrebel");
        HashMap<String,String> map = new HashMap<>(8);
        map.put("1","a");
        if(map.size() == 1){
            return map;
        }

        file.transferTo(new File("D:/" + file.getOriginalFilename()));

        HashMap<String,String> result = new HashMap<>(8);
        result.put("contentType",file.getContentType());
        result.put("name",file.getName());
        result.put("originalFilename",file.getOriginalFilename());
        result.put("size",String.valueOf(file.getSize()));
        return result;
    }

    @PostMapping("/upload2")
    @ResponseBody
    public List<Map<String, String>> upload2(MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            // TODO Spring Mvc 提供的写入方式
            file.transferTo(new File("D:\\" + file.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(8);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            map.put("name",file.getName());
            results.add(map);
        }
        return results;
    }

    @PostMapping("/upload3")
    @ResponseBody
    public void upload2(String base64) throws IOException {
        // TODO BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
        final File tempFile = new File("D:\\");
        // TODO 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, tempFile);

    }
}
