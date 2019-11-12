package org.hscoder.springboot.interceptor.modules.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 基于注解的Filter
 * 
 * @author atp
 *
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/intercept/*", filterName = "annotateFilter")
public class AnnotateFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AnnotateFilter.class);

    private final String name = "annotateFilter";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("Filter {} handle before", name);
        chain.doFilter(request, response);
        logger.info("Filter {} handle after", name);
    }

    @Override
    public void destroy() {

    }
}
