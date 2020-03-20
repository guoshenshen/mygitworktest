package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IAddrBookService;
import com.elearning.service.mixtraining.IMtMixTrainUserArrangeService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.vo.BasicUserForm;
import com.elearning.vo.mixtraining.MtMixTrainUserArrangeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/addrBook/")
public class AddrBookController {

    @Autowired
    private IAddrBookService addrBookService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;


    /**
     * 生成通讯录预览界面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("showPrevAddressBook.do")
    public String showPrevAddressBook(HttpServletRequest request){

        String jspName = "index";

        String addrbookDate = new SimpleDateFormat("yyyy-MM-dd:hh:mm").format(new Date());
        request.setAttribute("addrbookDate", addrbookDate);

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Map<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("operatorId", eosoperator.getOperatorId());
        if(request.getParameter("trainId")!=null){
            conditionMap.put("trainId",Integer.parseInt(request.getParameter("trainId")));
        }

        List<MtMixTrainUserArrangeForm> addrBookList = this.addrBookService.listAddrBook(conditionMap);
        request.setAttribute("addrBooks",addrBookList);

        return "mixtraining/forAddAddrBook";
    }

    /**
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getAddressBookListInfo.do")
    @ResponseBody
    public ServiceResponse getAddressBookListInfo(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.getAddressBookListInfo(request);
    }

    /**
     * 显示某通讯录或者若干通讯录的人员信息表（可能包含重复信息）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("showStaffOfAddrBook.do")
    @ResponseBody
    public ServiceResponse showStaffOfAddrBook(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.showStaffOfAddrBook(request);

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("getUserInfoByMixTrainUserInfo.do")
    @ResponseBody
    public ServiceResponse getUserInfoByMixTrainUserInfo(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.getUserInfoByMixTrainUserInfo(request);

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("generateTrainBook.do")
    @ResponseBody
    public ServiceResponse generateTrainBook(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.generateTrainBook(request);
    }

    /**
     * 显示通讯录，当trainId=-1:除当前培训的通讯录外，该人创建的其他通讯录全部显示
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("queryTrainBook.do")
    public String queryTrainBook(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        Integer trainId = null;
        //从session中获得当前培训ID号
        Integer currentTrainId = (Integer)request.getSession().getAttribute("trainId");
        String trainIdStr = request.getParameter("trainId");
        if(trainIdStr==null || trainIdStr.trim().length()==0){

        } else{
            trainId = Integer.parseInt(trainIdStr.trim());
        }
        if(trainId == null){
            trainId = currentTrainId;
            TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
            request.setAttribute("train", train);
        }
        request.setAttribute("trainId", trainId);
        int login_user_id = eosoperator.getOperatorId();
        String addrbookName = request.getParameter("bookName");
        Map<String,Object> conditionMap = new HashMap();
        if(addrbookName != null){
            conditionMap.put("addrbookName", addrbookName );
        }
        // 获取查询条件（可为空）
        String keyWords = request.getParameter("keyWords");
        if (keyWords != null) {
            conditionMap.put("keyWords", keyWords);
        }
        List<MtMixTrainUserArrangeForm> trainAddrBookList;
        if(trainId == -1){
            conditionMap.put("operatorId", login_user_id);
            trainAddrBookList = addrBookService.listAllAddrBook(conditionMap);
        } else{
            conditionMap.put("trainId", trainId);
            trainAddrBookList = addrBookService.listAddrBook(conditionMap);
        }
        if(trainId == -1){
            //获得此人其他培训的通讯录
            request.setAttribute("otherAddrBookList", trainAddrBookList);
        } else{
            //获得此人当前培训的通讯录
            request.setAttribute("trainAddrBookList", trainAddrBookList);
        }
        jspName = "mixtraining/mixTrainingPersonnelHome";

        return jspName;
    }

    /**
     *通讯录--点击--通讯录名称
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("showAddrBook.do")
    public String showAddrBook(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        this.addrBookService.showAddrBook(request);

        jspName = "mixtraining/addrBookDetail";
        return jspName;
    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("forUpdateAddrBook.do")
    public String forUpdateAddrBook(HttpServletRequest request){

        String jspName = "index";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        this.addrBookService.forUpdateAddrBook(request);

        jspName = "mixtraining/editAddressBook";
        return jspName;
    }

    /**
     * 通讯录--操作--删除
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("removeAddrBook.do")
    @ResponseBody
    public ServiceResponse removeAddrBook(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.removeAddrBook(request);
    }

    /**
     * 通讯录--操作--复制
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("quoteAddrBook.do")
    @ResponseBody
    public ServiceResponse quoteAddrBook(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.quoteAddrBook(request);
    }

    /**
     * 更新某通讯录（通讯录--操作--编辑）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateTrainBook.do")
    @ResponseBody
    public ServiceResponse updateTrainBook(HttpServletRequest request){

        EosOperator operator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(operator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.addrBookService.updateTrainBook(request);
    }


}
