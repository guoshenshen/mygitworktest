package com.elearning.service.systemManage;

import javax.servlet.http.HttpSession;

/**
 * @author  zq
 * @createDate 2019.08.01
 */
public interface ISessionCountService {

    public HttpSession getSession(String userid);

    public  void sessionInvalidate(String userid);

    public  void addSession(HttpSession se);

    public  void deleteSessions(String[] userids);

}
