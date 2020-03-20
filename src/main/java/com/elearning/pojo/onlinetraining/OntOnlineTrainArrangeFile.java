package com.elearning.pojo.onlinetraining;

public class OntOnlineTrainArrangeFile {
    private Integer ID;

    private Integer arrangeID;

    private String name;

    private String url;

    public OntOnlineTrainArrangeFile(Integer ID, Integer arrangeID, String name, String url) {
        this.ID = ID;
        this.arrangeID = arrangeID;
        this.name = name;
        this.url = url;
    }

    public OntOnlineTrainArrangeFile() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getArrangeID() {
        return arrangeID;
    }

    public void setArrangeID(Integer arrangeID) {
        this.arrangeID = arrangeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}