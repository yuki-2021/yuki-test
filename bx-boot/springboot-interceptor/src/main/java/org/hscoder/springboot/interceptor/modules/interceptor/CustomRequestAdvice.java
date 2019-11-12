package org.hscoder.springboot.interceptor.modules.interceptor;

import org.apache.commons.io.IOUtils;
import org.hscoder.springboot.interceptor.InterceptController;
import org.hscoder.springboot.simplebuild.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.hscoder.springboot.interceptor.InterceptController.MsgBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 请求内容解析拦截
 * 
 * @author atp
 *
 */
@ControllerAdvice(assignableTypes = InterceptController.class)
public class CustomRequestAdvice extends RequestBodyAdviceAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestAdvice.class);

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        // 返回true，表示启动拦截
        return MsgBody.class.getTypeName().equals(targetType.getTypeName());
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
            Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        logger.info("CustomRequestAdvice handleEmptyBody");

        // 对于空请求体，返回对象
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        logger.info("CustomRequestAdvice beforeBodyRead");

        // 可定制消息序列化
        return new BodyInputMessage(inputMessage);
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        logger.info("CustomRequestAdvice afterBodyRead");

        // 可针对读取后的对象做转换，此处不做处理
        return body;
    }

    public static class BodyInputMessage implements HttpInputMessage {
        private HttpHeaders headers;
        private InputStream body;

        public BodyInputMessage(HttpInputMessage inputMessage) throws IOException {
            this.headers = inputMessage.getHeaders();

            // 读取原字符串
            String content = IOUtils.toString(inputMessage.getBody(), "UTF-8");
            MsgBody msg = new MsgBody();
            msg.setContent(content);

            this.body = new ByteArrayInputStream(JsonUtil.toJson(msg).getBytes());
        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }
    }

    @ControllerAdvice(assignableTypes = InterceptController.class)
    public static class CustomResponseAdvice implements ResponseBodyAdvice<String> {

        private static final Logger logger = LoggerFactory.getLogger(CustomRequestAdvice.class);

        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            // 返回true，表示启动拦截
            return true;
        }

        @Override
        public String beforeBodyWrite(String body, MethodParameter returnType, MediaType selectedContentType,
                Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                ServerHttpResponse response) {

            logger.info("CustomResponseAdvice beforeBodyWrite");

            // 添加前缀
            String raw = String.valueOf(body);
            return "PREFIX:" + raw;
        }

    }

}
