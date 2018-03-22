package com.thenorthw.blog.web.filter;

import com.thenorthw.blog.common.ResponseCode;
import com.thenorthw.blog.common.ResponseModel;
import com.thenorthw.blog.common.exception.BlogException;
import com.thenorthw.blog.common.utils.json.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by theNorthW on 17/07/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
public class ExceptionCatchFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(ExceptionCatchFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        try{
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Throwable t){
            if(t instanceof BlogException) {
                logger.error("exception happen, please locate the error and fix it.", t);
                ResponseModel<String> res = new ResponseModel<String>();
                res.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
                res.setMessage(t.getMessage());
                res.setData(null);
                OutputStream outputStream = servletResponse.getOutputStream();
                outputStream.write(JsonUtil.beanToJson(res).getBytes());
                outputStream.flush();
                outputStream.close();
            }else if(t instanceof SQLException){
                logger.error("exception happen, please locate the error and fix it.",t);
                ResponseModel<String> res = new ResponseModel<String>();
                res.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
                res.setMessage(t.getMessage());
                res.setData(null);
                OutputStream outputStream = servletResponse.getOutputStream();
                outputStream.write(JsonUtil.beanToJson(res).getBytes());
                outputStream.flush();
            }else {
                logger.error("exception happen, please locate the error and fix it.",t);
                ResponseModel<String> res = new ResponseModel<String>();
                res.setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR.getCode());
                res.setMessage(t.getMessage());
                res.setData(null);
                OutputStream outputStream = servletResponse.getOutputStream();
                outputStream.write(JsonUtil.beanToJson(res).getBytes());
                outputStream.flush();
            }
        }
    }

    public void destroy() {

    }
}