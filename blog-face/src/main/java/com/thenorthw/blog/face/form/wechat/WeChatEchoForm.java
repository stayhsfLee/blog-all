package com.thenorthw.blog.face.form.wechat;

/**
 * @author theNorthW
 * @date 21/07/2017
 * blog: thenorthw.com
 */
public class WeChatEchoForm {
    String signature;
    String timestamp;
    String nonce;
    String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}
