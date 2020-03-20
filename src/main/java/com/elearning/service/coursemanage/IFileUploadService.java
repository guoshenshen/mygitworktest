package com.elearning.service.coursemanage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/8/14 8:47
 */

public interface IFileUploadService{
    boolean uploadCourse(HttpServletRequest request, MultipartFile coursezipfile);
}
