package com.elearning.service.message;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IMsgMessageArrangeListService {

    ServiceResponse listArrangeUser(Map<String,Object> map, HttpServletRequest request);


}
