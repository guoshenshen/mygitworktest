package com.elearning.pojo.material;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Album {

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long id;

    private String albumName;

    private Date createDate;

    private String creatorName;

    private Boolean type;

    private Long resourceId;

    private String openOrgSeq;

    private String description;

    private String cover;

    public Album(Long id, String albumName, Date createDate, String creatorName, Boolean type, Long resourceId, String openOrgSeq, String description, String cover) {
        this.id = id;
        this.albumName = albumName;
        this.createDate = createDate;
        this.creatorName = creatorName;
        this.type = type;
        this.resourceId = resourceId;
        this.openOrgSeq = openOrgSeq;
        this.description = description;
        this.cover = cover;
    }

    public Album() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName == null ? null : albumName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getOpenOrgSeq() {
        return openOrgSeq;
    }

    public void setOpenOrgSeq(String openOrgSeq) {
        this.openOrgSeq = openOrgSeq == null ? null : openOrgSeq.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }
}