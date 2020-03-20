package com.elearning.interceptor.loginInterceptor;

import com.alibaba.fastjson.JSON;
import com.elearning.common.ResponseCode;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.WebUtilsPro;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/1 13:46
 */
public class CheckUserLoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IsCheckUserLogin auth = handlerMethod.getMethodAnnotation(IsCheckUserLogin.class);

        /**
         * 如果在controller中的方法没有使用IsCheckUserLogin注解或者check=false,
         * 就不需要判断在请求时用户是否已经登录.
         */
        if (auth == null || !auth.check()) {
            return true;
        }

        HttpSession session = request.getSession();
        EosOperator eosOperator = (EosOperator) session.getAttribute(PropertiesUtil.getProperty("USERINFO_KEY"));
        //User user = (User) session.getAttribute("user");     //判断用户是否登录,如果user==null,则没有登录
        if (eosOperator != null ) {
            return true;
        } else {
            if(WebUtilsPro.isAjaxRequest(request)){
                Map<String,Object> map = new HashMap<>();
                map.put("status", ResponseCode.NEED_LOGIN.getCode());
                map.put("msg", ResponseCode.NEED_LOGIN.getDesc());
                writeJson(map, response);
            }else{
                String bath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
                response.sendRedirect(bath);
            }
            return false;
        }
    }
    private void writeJson(Map<String,Object> map, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            out = response.getWriter();
            out.write(JSON.toJSONString(map));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
