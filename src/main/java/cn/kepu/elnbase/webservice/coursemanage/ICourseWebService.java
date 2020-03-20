package cn.kepu.elnbase.webservice.coursemanage;

import com.elearning.pojo.coursemanage.CmCourse;
import com.elearning.pojo.coursemanage.CmCourseId;

import javax.jws.WebService;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/10/16 17:22
 */
@WebService
public interface ICourseWebService {

    /**
     * 通过文件大小获得上传服务器
     * @param fileSize 上传文件大小
     * @return ftp服务器信息
     */
    //SmServer getUploadServer(final int fileSize, long orgId);
    /**
     * 通过课程主键，获取访问共享课件的Url
     */
    String getSharedCourseVisitUrl(CmCourseId cmCourseId);

    /**
     * 通过课程主键，获取访问课件的Url
     */
    String getVisiteUrl(CmCourseId cmCourseId);

    /**
     * 保存或者更新课程信息
     */
    boolean saveOrUpdateCourse(CmCourse course);
    /**
     * 删除课程
     */
    boolean deleteCourse(CmCourseId cmCourseId);
    /**
     * 通过id访问，设置共享课程
     */
    void setSharedCourse(CmCourseId cmCourseId, int sharedCourseTypeId);
    /**
     * 通过id访问，设置课程的courseUrl
     */
    void setEnterUrl(CmCourseId cmCourseId, String enterUrl);

    /**
     * 作用:通过id访问，查找cmCourse
     * @author wxy wxy@cnic.cn
     * @version Nov 2, 2011 11:19:44 AM
     */
    CmCourse findCmCourseById(CmCourseId cmCourseId);
}
