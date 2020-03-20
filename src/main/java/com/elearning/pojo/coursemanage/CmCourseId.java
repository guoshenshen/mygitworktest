package com.elearning.pojo.coursemanage;

import java.io.Serializable;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/16 17:19
 */
public class CmCourseId implements Serializable {

    // Fields
    private static final long serialVersionUID = 9004570074787771780L;

    private Long courseId;

    private Long orgId;

    // Constructors

    /** default constructor */
    public CmCourseId() {
    }

    /** full constructor */
    public CmCourseId(Long courseId, Long orgId) {
        this.courseId = courseId;
        this.orgId = orgId;
    }

    // Property accessors

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CmCourseId))
            return false;
        CmCourseId castOther = (CmCourseId) other;

        return ((this.getCourseId() == castOther.getCourseId()) || (this
                .getCourseId() != null
                && castOther.getCourseId() != null && this.getCourseId()
                .equals(castOther.getCourseId())))
                && ((this.getOrgId() == castOther.getOrgId()) || (this
                .getOrgId() != null
                && castOther.getOrgId() != null && this.getOrgId()
                .equals(castOther.getOrgId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result
                + (getCourseId() == null ? 0 : this.getCourseId().hashCode());
        result = 37 * result
                + (getOrgId() == null ? 0 : this.getOrgId().hashCode());
        return result;
    }


}
