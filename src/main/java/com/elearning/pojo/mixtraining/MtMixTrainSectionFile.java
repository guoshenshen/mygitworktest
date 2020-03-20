package com.elearning.pojo.mixtraining;

public class MtMixTrainSectionFile {
    private Integer ID;

    private Integer section_id;

    private String name;

    private String url;

    public MtMixTrainSectionFile(Integer ID, Integer section_id, String name, String url) {
        this.ID = ID;
        this.section_id = section_id;
        this.name = name;
        this.url = url;
    }

    public MtMixTrainSectionFile() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getSection_id() {
        return section_id;
    }

    public void setSection_id(Integer section_id) {
        this.section_id = section_id;
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