package com.elearning.service.pub;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/23 15:57
 */
public interface IExportService {
    ServiceResponse exportCourseInfo(String[] selectbox, HttpServletRequest request) throws IOException;
}
