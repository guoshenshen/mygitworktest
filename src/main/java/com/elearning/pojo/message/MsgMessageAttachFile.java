package com.elearning.pojo.message;

public class MsgMessageAttachFile {
    private Integer ID;

    private Integer msgID;

    private Integer attachFileNO;

    private String attachFileName;

    private String url;

    public MsgMessageAttachFile(Integer ID, Integer msgID, Integer attachFileNO, String attachFileName, String url) {
        this.ID = ID;
        this.msgID = msgID;
        this.attachFileNO = attachFileNO;
        this.attachFileName = attachFileName;
        this.url = url;
    }

    public MsgMessageAttachFile() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMsgID() {
        return msgID;
    }

    public void setMsgID(Integer msgID) {
        this.msgID = msgID;
    }

    public Integer getAttachFileNO() {
        return attachFileNO;
    }

    public void setAttachFileNO(Integer attachFileNO) {
        this.attachFileNO = attachFileNO;
    }

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName == null ? null : attachFileName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}