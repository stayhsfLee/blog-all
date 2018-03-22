package com.thenorthw.blog.common.exception;

/**
 * Created by theNorthW on 12/07/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
public enum BlogError {
    LoginError("000001","login error",""),

    RequestParamError("100001","request param error","");

    BlogError(String code, String msg, String solution){
        this.code = code;
        this.message = msg;
        this.solution = solution;
    }

    private String code;
    private String message;
    private String solution;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getSolution() {
        return solution;
    }

}
