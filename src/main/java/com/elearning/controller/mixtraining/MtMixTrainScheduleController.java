package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.mixtraining.MtMixTrainSchedule;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleItemInfo;
import com.elearning.pojo.mixtraining.MtMixTrainScheduleTrain;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IMtMixTrainScheduleItemInfoService;
import com.elearning.service.mixtraining.IMtMixTrainScheduleService;
import com.elearning.service.mixtraining.IMtMixTrainScheduleTrainService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.vo.mixtraining.MtMixTrainScheduleItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/mtMixTrainSchedule/")
public class MtMixTrainScheduleController {

    @Autowired
    private IMtMixTrainScheduleService mtMixTrainScheduleService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IMtMixTrainScheduleTrainService mtMixTrainScheduleTrainService;

    @Autowired
    private IMtMixTrainScheduleItemInfoService mtMixTrainScheduleItemInfoService;

    @Autowired
    private ICourseService courseService;

    @IsCheckUserLogin(check = true)
    @RequestMapping("getMtMixTrainScheduleById.do")
    @ResponseBody
    public ServiceResponse getMtMixTrainScheduleById(Integer ID, Model model){

        MtMixTrainSchedule mtMixTrainSchedule = this.mtMixTrainScheduleService.selectByPrimaryKey(ID);

        return ServiceResponse.createBySuccess(mtMixTrainSchedule);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listScheduleItem.do")
    public String listScheduleItem(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        return mtMixTrainScheduleService.listScheduleItem(request);
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listSchedule.do")
    public String listSchedule(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        int trainId = Integer.parseInt(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train=onlineTrainingService.findById(trainId);
        int scheduleId = 0;
        List<MtMixTrainScheduleTrain> mtScheduleTrainList=this.mtMixTrainScheduleTrainService.findScheduleTrainList(trainId);

        if(mtScheduleTrainList!=null && mtScheduleTrainList.size()>0){
            scheduleId=mtScheduleTrainList.get(0).getScheduleId();
        }

        List<MtMixTrainScheduleItemInfo> mtScheduleItemList = this.mtMixTrainScheduleItemInfoService.findByScheduleId(scheduleId);
        List<MtMixTrainScheduleItemInfo> mtScheduleOnlineItemList = this.mtMixTrainScheduleItemInfoService.getScheduleOnlineItemList(mtScheduleItemList);
        List<MtMixTrainScheduleItemInfo> mtScheduleOfflineItemList = this.mtMixTrainScheduleItemInfoService.getScheduleOfflineItemList(mtScheduleItemList);

        if(mtScheduleOnlineItemList!=null && mtScheduleOnlineItemList.size()>0){
            List<List<MtMixTrainScheduleItemInfo>> reviseMtScheduleOnlineItemList=new ArrayList<>();
            //request.setAttribute("onlineItemList", mtScheduleOnlineItemList);
            request.setAttribute("reviseOnlineItemList", reviseMtScheduleOnlineItemList);
            String oldScheduleDateStr=null;
            List<MtMixTrainScheduleItemInfo> itemList=null;
            for(MtMixTrainScheduleItemInfo schedule:mtScheduleOnlineItemList){
                Date scheduleDate=schedule.getScheduleDate();
                String scheduleDateStr="";
                if(scheduleDate!=null){
                    scheduleDateStr=scheduleDate.getTime()+"";
                }
                if(!scheduleDateStr.equals(oldScheduleDateStr)){
                    oldScheduleDateStr=scheduleDateStr;
                    itemList=new ArrayList<>();
                    reviseMtScheduleOnlineItemList.add(itemList);
                }
                itemList.add(schedule);
            }
        }

        if(mtScheduleOfflineItemList!=null && mtScheduleOfflineItemList.size()>0){
            List<List<MtMixTrainScheduleItemInfo>> reviseMtScheduleOfflineItemList=new ArrayList<>();
            request.setAttribute("reviseOfflineItemList", reviseMtScheduleOfflineItemList);
            String oldScheduleDateStr=null;
            List<MtMixTrainScheduleItemInfo> itemList=null;
            for(MtMixTrainScheduleItemInfo schedule:mtScheduleOfflineItemList){
                Date scheduleDate=schedule.getScheduleDate();
                String scheduleDateStr="";
                if(scheduleDate!=null){
                    scheduleDateStr=scheduleDate.getTime()+"";
                }
                if(!scheduleDateStr.equals(oldScheduleDateStr)){
                    oldScheduleDateStr=scheduleDateStr;
                    itemList=new ArrayList<>();
                    reviseMtScheduleOfflineItemList.add(itemList);
                }
                itemList.add(schedule);
            }
            for(MtMixTrainScheduleItemInfo itemInfo:mtScheduleOfflineItemList){
                if(itemInfo.getHoster()!=null && !"".equals(itemInfo.getHoster())){
                    request.setAttribute("scheduleHasHoster", true);
                    break;
                }
            }
        }
        String recentOnlineScheduleDate="";
        String recentOfflineScheduleDate="";
        if(request.getSession().getAttribute("recentOnlineScheduleDate")!=null){
            recentOnlineScheduleDate=request.getSession().getAttribute("recentOnlineScheduleDate").toString();
        }
        if(request.getSession().getAttribute("recentOfflineScheduleDate")!=null){
            recentOfflineScheduleDate=request.getSession().getAttribute("recentOfflineScheduleDate").toString();
        }
        request.setAttribute("recentOnlineScheduleDate", recentOnlineScheduleDate);
        request.setAttribute("recentOfflineScheduleDate", recentOfflineScheduleDate);
        request.setAttribute("scheduleId", scheduleId);
        request.setAttribute("train", train);

        return "mixtraining/mixTrainingSchedule";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "forAddOnLineScheduleItemInfo.do")
    public String forAddOnLineScheduleItemInfo(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainScheduleService.forAddOnLineScheduleItemInfo(request);

        return "forward:../mtMixTrainSchedule/listSchedule.do";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "forAddOffLineScheduleItemInfo.do")
    public String forAddOffLineScheduleItemInfo(MtMixTrainScheduleItemForm mtMixTrainScheduleItemForm, HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainScheduleService.forAddOffLineScheduleItemInfo(mtMixTrainScheduleItemForm,request);

        return "redirect:../mtMixTrainSchedule/listSchedule.do";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "resetScheduleForm.do")
    public String resetScheduleForm(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        if(request.getSession().getAttribute("recentOfflineScheduleDate")!=null){
            request.getSession().removeAttribute("recentOfflineScheduleDate");
        }
        if(request.getSession().getAttribute("recentOnlineScheduleDate")!=null){
            request.getSession().removeAttribute("recentOnlineScheduleDate");
        }

        return "redirect:../mtMixTrainSchedule/listSchedule.do";

    }

    @RequestMapping("deleteAjax.do")
    @ResponseBody
    public ServiceResponse deleteAjax(HttpServletRequest request){

        int i = this.mtMixTrainScheduleService.deleteAjax(request);

        if(i > 0){
            return ServiceResponse.createBySuccess();
        }else{
            return ServiceResponse.createByError();
        }

    }

    @RequestMapping("editScheduleItem.do")
    @ResponseBody
    public ServiceResponse editScheduleItem(HttpServletRequest request){

        return this.mtMixTrainScheduleService.editScheduleItem(request);

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "updateOnLineScheduleItem.do")
    public String updateOnLineScheduleItem(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainScheduleService.updateOnLineScheduleItem(request);

        return "redirect:../mtMixTrainSchedule/listSchedule.do";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "updateOffLineScheduleItem.do")
    public String updateOffLineScheduleItem(MtMixTrainScheduleItemForm mtMixTrainScheduleItemForm,HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainScheduleService.updateOffLineScheduleItem(mtMixTrainScheduleItemForm,request);

        return "redirect:../mtMixTrainSchedule/listSchedule.do";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listScheduleTeacher.do")
    public String listScheduleTeacher(HttpServletRequest request){

        this.mtMixTrainScheduleService.listScheduleTeacher(request);

        return "mixtraining/mixTrainingScheduleTeacherInfo";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping(value = "listScheduleCourse.do")
    public String listScheduleCourse(HttpServletRequest request){

        this.mtMixTrainScheduleService.listScheduleCourse(request);

        return "mixtraining/mixTrainingScheduleCourseInfo";

    }

    /*@IsCheckUserLogin(check = true)
    @RequestMapping(value = "listScheduleItem.do")
    public String listScheduleItem(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtMixTrainScheduleService.listScheduleItem(request);

        return "redirect:../mtMixTrainSchedule/listSchedule.do";

    }*/

    /**
     * 预览 -- 培训课程
     * =======================此方法未完成====================
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("loadOnlineTrainScheduleItem.do")
    @ResponseBody
    public ServiceResponse loadOnlineTrainScheduleItem(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtMixTrainScheduleService.loadOnlineTrainScheduleItem(request);
    }



}
