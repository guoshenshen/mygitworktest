package com.elearning.service.testPaper;

import com.elearning.vo.testPaper.TpaPaperStrategyQuesTypeForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IPaperManageService {

    List<TpaPaperStrategyQuesTypeForm> previewTestPaper(HttpServletRequest request);


}
