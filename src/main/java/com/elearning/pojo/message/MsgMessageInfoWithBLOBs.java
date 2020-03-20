package com.elearning.pojo.message;

import java.util.Date;

public class MsgMessageInfoWithBLOBs extends MsgMessageInfo {
    private String content;

    private String url;

    private String result;

    public MsgMessageInfoWithBLOBs(Integer ID, String title, Integer status, Date validDate, Integer inSystem, Integer isEmail, Integer editorID, Integer isPublic, Date invalidDate, Integer hasSend, String attachmentPath, Integer backflag, Integer trainID, Integer isSMS, Integer tenantId, String smsContent, Integer recommendTag, Integer confirmType, String ewm, Integer ewmType, String code, String content, String url, String result) {
        super(ID, title, status, validDate, inSystem, isEmail, editorID, isPublic, invalidDate, hasSend, attachmentPath, backflag, trainID, isSMS, tenantId, smsContent, recommendTag, confirmType, ewm, ewmType, code);
        this.content = content;
        this.url = url;
        this.result = result;
    }

    public MsgMessageInfoWithBLOBs() {
        super();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }
}