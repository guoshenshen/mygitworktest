package com.elearning.service.systemManage;

import com.elearning.common.Constants;
import com.elearning.pojo.pub.EosOperator;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.Serializable;
import java.util.Vector;

@Service("sessionCountService")
public class SessionCountServiceImpl implements  ISessionCountService,HttpSessionListener,Serializable {

    private static Vector<HttpSession> _totalSessions = new Vector<HttpSession>();

    @Override
    public HttpSession getSession(String userid) {
        try{
            HttpSession ssR = null;
            for(HttpSession _httpSession:_totalSessions)
            {
                EosOperator eosoperator = null;
                try
                {
                    eosoperator = (EosOperator)_httpSession.getAttribute(Constants.USERINFO_KEY);
                }
                catch(Exception ex1)
                {
                    continue;
                }
                if(eosoperator == null || !eosoperator.getUserId().equals(userid))
                    continue;
                ssR = _httpSession;
                break;
            }
            return ssR;
        }catch(Exception ex){
            System.out.println("getSession\n" + ex.getMessage());
            return null;
        }
    }

    @Override
    public void sessionInvalidate(String userid) {
        try
        {
            for(HttpSession _httpSession:_totalSessions)
            {
                try
                {
                    Object eosoperator = _httpSession.getAttribute(Constants.USERINFO_KEY);
                    if(eosoperator != null && ((EosOperator)eosoperator).getUserId().equals(userid))
                    {
                        _totalSessions.remove(_httpSession);
                        _totalSessions.trimToSize();
                        _httpSession.removeAttribute(Constants.USERINFO_KEY);
                        break;
                    }
                }
                catch(Exception exception) { }
            }
        }
        catch(Exception ex)
        {
            System.out.println("sessionInvalidate\n" + ex.getMessage());
        }
    }

    @Override
    public void addSession(HttpSession se) {
        try
        {
//        	for(HttpSession _httpSession:_totalSessions){
//    	        if(_httpSession.getId().equals((se.getId()))){
//    	        	_totalSessions.remove(se.getId());
//    	            return;
//    	        }
//        	}
            _totalSessions.add(se);
        }
        catch(Exception ex)
        {
            System.out.println("addSession\n" + ex.getMessage());
        }
    }

    @Override
    public void deleteSessions(String[] userids) {
        if(userids!=null && userids.length!=0){
            for(String userid:userids){
                this.sessionInvalidate(userid);
            }
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //_totalSessions.add(httpsessionevent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        for(HttpSession _httpSession:_totalSessions){
            if(_httpSession.getId().equals((se.getSession().getId()))){
                _totalSessions.remove(_httpSession);
                _totalSessions.trimToSize();
                return;
            }
        }
    }
}
