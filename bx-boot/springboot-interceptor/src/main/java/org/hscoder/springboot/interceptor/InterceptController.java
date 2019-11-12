package org.hscoder.springboot.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intercept")
public class InterceptController {

    @PostMapping(value = "/body", consumes = { MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
    public String body(@RequestBody MsgBody msg) {
        return msg == null ? "<EMPTY>" : msg.getContent();
    }

    public static class MsgBody {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }

    @GetMapping
    public String get() {
        return "OK";
    }

    @GetMapping("/error")
    public String error(@RequestParam("msg") String msg) {
        throw new RuntimeException("just for test");
    }
}
