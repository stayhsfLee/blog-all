package com.thenorthw.blog.common;

/**
 * Created by theNorthW on 06/04/2017.
 * blog: thenorthw.com
 *
 * @autuor : theNorthW
 */
public enum ResponseCode {
    OK(200,"ok"),



    BAD_REQUEST(400,"bad request"),
    UNAUTHORIZED(401,"unauthorized"),
    FORBIDDEN(403,"forbidden"),
    NOT_FOUND(404,"not found"),

    INTERNAL_SERVER_ERROR(500,"internal server error"),



    //user & account
    EXISTED_USER(1000,"The account has registered."),


    //login
    NO_SUCH_ACCOUNT(1001,"No such account exists"),
    LOGIN_FAIL(1002,"Login error."),

    //update user info
    OSS_UPLOAD_ERROR(1011,"upload error"),

    //article group
    NO_SUCH_PARENT_ARTICLE_GROUP(1031,"no such parent article group or group level out of 2."),
    PARENT_LEVEL_ILLEGAL(1032,"only level 1&2 group is allowed."),
    NO_SUCH_ARTICLE_GROUP(1033,"group not exists."),
    GROUP_DELETE_ERROR(1034,"group delete error."),

    //tag
    TAG_ADD_ERROR(1041,"tag add error."),
    TAG_DELETE_ERROR(1042,"tag delete error."),
    NO_SUCH_TAG(1043,"no such tag."),



	//notification
	NO_SUCH_NOTIFICATION(1061,"no such notification");




    ResponseCode(int code,String m){
        this.code = code;
        this.message = m;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\""+code+"\"";
    }


}
