package com.thenorthw.blog.web.aop;

import com.thenorthw.blog.common.ResponseCode;
import com.thenorthw.blog.common.ResponseModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

/**
 * Created by theNorthW on $date.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
@Component
@Aspect
public class HttpParamValidator {
    private static Logger logger = LoggerFactory.getLogger("httpValidLogger");

    @Around("execution(* com.thenorthw.blog.web.controller..*(..)) && args(..,bindingResult))")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object retVal;
        if (bindingResult.hasErrors()) {
            errorHandler(bindingResult);

            ResponseModel responseModel = new ResponseModel();
            responseModel.setResponseCode(ResponseCode.BAD_REQUEST.getCode());
            responseModel.setMessage(ResponseCode.BAD_REQUEST.getMessage());
            return responseModel;
        } else {
            retVal = pjp.proceed();
        }
        return retVal;
    }

    private void errorHandler(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        for(FieldError e : bindingResult.getFieldErrors()){
            sb.append(e.getObjectName() + " -> " +e.getField() + " -> " + e.getRejectedValue().toString()+"\n");
        }
        logger.error("Found invalid http parameters request, \n result: {}",sb.toString());
    }
}
