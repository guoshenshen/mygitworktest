package com.elearning.pojo.coursemanage;

public class CourseFilter {
    private Integer id;

    private String filterName;

    public CourseFilter(Integer id, String filterName) {
        this.id = id;
        this.filterName = filterName;
    }

    public CourseFilter() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName == null ? null : filterName.trim();
    }
}