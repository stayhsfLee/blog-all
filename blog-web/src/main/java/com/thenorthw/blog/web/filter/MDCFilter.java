package com.thenorthw.blog.web.filter;

import com.thenorthw.blog.common.constants.BlogConstant;
import com.thenorthw.blog.common.utils.ShortUUIDUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by theNorthW on 16/7/15.
 */
public class MDCFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //生成requestId，放入response中，客户端可以拿到这个requestId去查询日志
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String requestId = ShortUUIDUtil.generateRequestId();
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.addHeader(BlogConstant.HTTP_REQUEST_ID_HEADER, requestId);

            //将requestId 放到MDC中, 供日志打印
            MDC.put(BlogConstant.HTTP_REQUEST_ID_HEADER, requestId);
            String requestUrl = request.getRequestURI().toString();
            MDC.put(BlogConstant.HTTP_REQUEST_URL_HEADER, requestUrl);

            filterChain.doFilter(request, servletResponse);
        } finally {
            MDC.remove(BlogConstant.HTTP_REQUEST_ID_HEADER);
            MDC.remove(BlogConstant.HTTP_REQUEST_URL_HEADER);
        }
    }

    public void destroy() {

    }
}
