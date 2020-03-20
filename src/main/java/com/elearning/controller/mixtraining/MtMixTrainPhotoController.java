package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResourceType;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IMtMixTrainPhotoService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/mtMixTrainPhoto/")
public class MtMixTrainPhotoController {

    @Autowired
    private IMtMixTrainPhotoService mtMixTrainPhotoService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    /**
     * 培训班管理--管理--班级相册--（该链接是从数据库中获取到的）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("execute.do")
    public String execute(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        request.setAttribute("resourceId", trainId);
        request.setAttribute("type", ResourceType.TRAIN.getTypeCode());

        jspName = "mixtraining/mixTrainphotoManageList";

        return jspName;
    }

    /**
     * 培训班管理--预览--班级相册
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("readList.do")
    public String readList(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId=0;
        request.setAttribute("operator", eosoperator);
        if(request.getParameter("trainId") != null){
            trainId = Integer.parseInt(request.getParameter("trainId").toString());
        } else{
            trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        }
        request.getSession().setAttribute("trainId", trainId);
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        request.setAttribute("train", train);
        request.setAttribute("resourceId", trainId);
        request.setAttribute("type", ResourceType.TRAIN.getTypeCode());

        jspName = "mixtraining/mixTrainphotoList";

        return jspName;
    }


}
