package org.hscoder.springboot.interceptor.modules.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 * 
 * @author atp
 *
 */
public class CustomerFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CustomerFilter.class);

    private String name;

    // 获取filterConfig内容
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        name = filterConfig.getInitParameter("name");
    }

    //
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        logger.info("Filter {} handle before", name);
        chain.doFilter(request, response);
        logger.info("Filter {} handle after", name);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
