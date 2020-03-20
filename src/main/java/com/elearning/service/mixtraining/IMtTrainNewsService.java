package com.elearning.service.mixtraining;

import com.elearning.common.ServiceResponse;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.vo.mixtraining.MtTrainNewsForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface IMtTrainNewsService {

    MtTrainNewsWithBLOBs selectByPrimaryKey(Integer newsId);

    MtTrainNewsWithBLOBs findById(Integer newsId);

    List<MtTrainNewsWithBLOBs> findByTrainId(Integer trainId);

    List<MtTrainNewsWithBLOBs> listNews(Map<String,Object> map);

    List<MtTrainNewsWithBLOBs> findListByTrainIdOrderByCreateTime(Integer trainId);

    void toAddNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request);

    void updateNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request);

    void recommendNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request);

    ServiceResponse changeStatus(HttpServletRequest request);

    ServiceResponse recommendNewsAjax(HttpServletRequest request);

    void deleteNews(HttpServletRequest request);

    void forUpdateNews(HttpServletRequest request);

    ServiceResponse getNewsDetailInfo(HttpServletRequest request);

    ServiceResponse findTrainNews(HttpServletRequest request);

    void getNewsDetailFromMainPage(HttpServletRequest request);






}
