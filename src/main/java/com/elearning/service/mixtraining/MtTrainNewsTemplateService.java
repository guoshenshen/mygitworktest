package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.dao.mixtraining.MtTrainNewsTemplateMapper;
import com.elearning.pojo.mixtraining.MtTrainNewsTemplate;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.vo.mixtraining.MtTrainNewsTemplateForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("mtTrainNewsTemplateService")
public class MtTrainNewsTemplateService implements IMtTrainNewsTemplateService{


    @Autowired
    private MtTrainNewsTemplateMapper mtTrainNewsTemplateMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IMtTrainNewsService mtTrainNewsServices;


    @Override
    public MtTrainNewsTemplate selectByPrimaryKey(Integer newsTemplateId){

        return this.mtTrainNewsTemplateMapper.selectByPrimaryKey(newsTemplateId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void queryNewsTemplate(MtTrainNewsTemplateForm mtMixTrainNewsTemplateForm, HttpServletRequest request){

        int trainId = (Integer)request.getSession().getAttribute("trainId");//从seesion中获得当前培训ID号

        if(String.valueOf(trainId) != null){
            TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
            request.setAttribute("train", train);
        }

        Map<String,Object> map = new HashMap<>();
        //Pager pager =  mtMixTrainNewsTemplateService.listNewsTemplate(map, mtMixTrainNewsTemplateForm.getPageSize(), mtMixTrainNewsTemplateForm.getPageNo());
        //List<MtMixTrainnnewstemplate> newsTemplateList = pager.getResultList();

        List<MtTrainNewsTemplate> newsTemplateList = this.mtTrainNewsTemplateMapper.listNewsTemplate(map);

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int login_user_id = 0;
        if(eosoperator != null){
            login_user_id = eosoperator.getOperatorId();
        }
        String newsName = request.getParameter("newsName");


        /*Map<String,Object> map1 = new HashMap<>();
        map1.put("operatorId", login_user_id);
        map1.put("newsName", newsName);
        map1.put("trainId", trainId);
        Pager pager1 = mtMixTrainNewsService.listNews(map1, pageForm.getPageSize(), pageForm.getPageNo());
        List<MtMixTrainnews> newsList = pager1.getResultList();*/
        //request.setAttribute("newsList", newsList);

        List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsServices.findByTrainId(trainId);//默认当前trainId为0

        request.setAttribute("trainNewsList", trainNewsList);
        request.setAttribute("newsTemplateList", newsTemplateList);
    }




}
