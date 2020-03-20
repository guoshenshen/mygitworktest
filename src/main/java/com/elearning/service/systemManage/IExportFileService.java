package com.elearning.service.systemManage;

import com.elearning.common.ServiceResponse;

import javax.servlet.http.HttpServletRequest;


public interface IExportFileService {

    ServiceResponse export(HttpServletRequest request);
}
