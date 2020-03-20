package com.elearning.pojo.courseStudy;

import com.elearning.pojo.pub.Course;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.ToStringSerializer;

import java.util.Date;
import java.util.List;


public class Chapter{


    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private Long chapterId;

    private String chapterName;

    private Long firstCourseId;

    private Date createDate;

    private List<Course> subset;

    public Chapter(Long chapterId, String chapterName, Long firstCourseId, Date createDate) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.firstCourseId = firstCourseId;
        this.createDate = createDate;
    }

    public Chapter() {
        super();
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName == null ? null : chapterName.trim();
    }

    public Long getFirstCourseId() {
        return firstCourseId;
    }

    public void setFirstCourseId(Long firstCourseId) {
        this.firstCourseId = firstCourseId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Course> getSubset() {
        return subset;
    }

    public void setSubset(List<Course> subset) {
        this.subset = subset;
    }
}