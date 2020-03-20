package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.systemManage.PictureBase;
import com.elearning.service.mixtraining.IMtMixTrainTopbandService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.systemManage.IPictureBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mtMixTrainTopband/")
public class MtMixTrainTopbandController {

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IPictureBaseService pictureBaseService;

    @Autowired
    private IMtMixTrainTopbandService mtMixTrainTopbandService;


    @IsCheckUserLogin(check = true)
    @RequestMapping("execute.do")
    public String execute(HttpServletRequest request){

        String jspName = "index";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        Integer trainId = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        request.setAttribute("train", train);
        Integer topbandId = train.getTopbandId();
        PictureBase pictureBase =  this.pictureBaseService.findById(topbandId);

        if(topbandId!=null && pictureBase!=null && pictureBase.getPictureUrl()!=null) {
            request.setAttribute("basePic", pictureBase);
        }

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("picType",1602);
        parmMap.put("operatorId",operator.getOperatorId());

        List<PictureBase> myList = this.pictureBaseService.getListByParmMap(parmMap);

        parmMap.put("operatorId",1);
        List<PictureBase> shareList = this.pictureBaseService.getListByParmMap(parmMap);

        //--------------------去除--picturebase表中--pictureUrl字段--./image/picturebase/2.jpg中的点---------------------------
        for (PictureBase pictureBase1:shareList) {
            if(pictureBase1.getPictureUrl().toString().startsWith(".")){
                pictureBase1.setPictureUrl(pictureBase1.getPictureUrl().toString().substring(1));
            }
        }
        //-----------------------------------------------

        request.setAttribute("myList", myList);
        request.setAttribute("shareList", shareList);

        jspName = "mixtraining/mixTrainTopbandList";

        return jspName;
    }

    /**
     *  使用某张背景图片
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("selectTopband.do")
    public String selectTopband(HttpServletRequest request){

        String jspName = "index";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        this.mtMixTrainTopbandService.selectTopband(request);

        jspName = "redirect:../mtMixTrainTopband/execute.do";

        return jspName;
    }

    /**
     *  删除当前培训topband
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteTrainTopband.do")
    public String deleteTrainTopband(HttpServletRequest request){

        String jspName = "index";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        this.mtMixTrainTopbandService.deleteTrainTopband(request);

        jspName = "redirect:../mtMixTrainTopband/execute.do";

        return jspName;
    }

    /**
     *  删除自己上传的图标
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteSelfTopband.do")
    public String deleteSelfTopband(HttpServletRequest request){

        String jspName = "index";

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return jspName;
        }

        this.mtMixTrainTopbandService.deleteSelfTopband(request);

        jspName = "redirect:../mtMixTrainTopband/execute.do";

        return jspName;
    }

}
