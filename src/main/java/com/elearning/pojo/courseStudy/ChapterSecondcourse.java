package com.elearning.pojo.courseStudy;

public class ChapterSecondcourse {
    private Long id;

    private Long chapterId;

    private Long secondCourseId;

    private Integer type;

    private Integer sortOrder;

    public ChapterSecondcourse(Long id, Long chapterId, Long secondCourseId, Integer type, Integer sortOrder) {
        this.id = id;
        this.chapterId = chapterId;
        this.secondCourseId = secondCourseId;
        this.type = type;
        this.sortOrder = sortOrder;
    }

    public ChapterSecondcourse() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Long getSecondCourseId() {
        return secondCourseId;
    }

    public void setSecondCourseId(Long secondCourseId) {
        this.secondCourseId = secondCourseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder == null ? null : sortOrder;
    }
}