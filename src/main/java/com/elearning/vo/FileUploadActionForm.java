package com.elearning.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/14 14:49
 */
public class FileUploadActionForm {
    private String coureName;

    /** coureId property */
    private String coureId;

    /** hidFileID property */
    private String hidFileID;

    private MultipartFile coursezipfile;

    private MultipartFile lectrueFile;

    private MultipartFile pictureFile;

    private String lectrueName;

    private String courseUrl;

    private String courseTypeId;

    private MultipartFile captionsChinese;

    private MultipartFile captionsEnglish;


    public String getCoureName() {
        return coureName;
    }

    public void setCoureName(String coureName) {
        this.coureName = coureName;
    }

    public String getCoureId() {
        return coureId;
    }

    public void setCoureId(String coureId) {
        this.coureId = coureId;
    }

    public String getHidFileID() {
        return hidFileID;
    }

    public void setHidFileID(String hidFileID) {
        this.hidFileID = hidFileID;
    }

    public MultipartFile getCoursezipfile() {
        return coursezipfile;
    }

    public void setCoursezipfile(MultipartFile coursezipfile) {
        this.coursezipfile = coursezipfile;
    }

    public MultipartFile getLectrueFile() {
        return lectrueFile;
    }

    public void setLectrueFile(MultipartFile lectrueFile) {
        this.lectrueFile = lectrueFile;
    }

    public MultipartFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getLectrueName() {
        return lectrueName;
    }

    public void setLectrueName(String lectrueName) {
        this.lectrueName = lectrueName;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public MultipartFile getCaptionsChinese() {
        return captionsChinese;
    }

    public void setCaptionsChinese(MultipartFile captionsChinese) {
        this.captionsChinese = captionsChinese;
    }

    public MultipartFile getCaptionsEnglish() {
        return captionsEnglish;
    }

    public void setCaptionsEnglish(MultipartFile captionsEnglish) {
        this.captionsEnglish = captionsEnglish;
    }
}
