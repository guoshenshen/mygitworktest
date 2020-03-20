package com.elearning.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/1 15:21
 */
public class WebUtilsPro {

    /**
     * 是否为ajax请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
