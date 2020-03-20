package com.elearning.pojo.courseStudy;

import java.util.Date;

public class Captions {
    private Long captionsId;

    private Long courseId;

    private String captionsChinese;

    private String captionsEnglish;

    private Date createDate;

    public Captions(Long captionsId, Long courseId, String captionsChinese, String captionsEnglish, Date createDate) {
        this.captionsId = captionsId;
        this.courseId = courseId;
        this.captionsChinese = captionsChinese;
        this.captionsEnglish = captionsEnglish;
        this.createDate = createDate;
    }

    public Captions() {
        super();
    }

    public Long getCaptionsId() {
        return captionsId;
    }

    public void setCaptionsId(Long captionsId) {
        this.captionsId = captionsId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCaptionsChinese() {
        return captionsChinese;
    }

    public void setCaptionsChinese(String captionsChinese) {
        this.captionsChinese = captionsChinese == null ? null : captionsChinese.trim();
    }

    public String getCaptionsEnglish() {
        return captionsEnglish;
    }

    public void setCaptionsEnglish(String captionsEnglish) {
        this.captionsEnglish = captionsEnglish == null ? null : captionsEnglish.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}