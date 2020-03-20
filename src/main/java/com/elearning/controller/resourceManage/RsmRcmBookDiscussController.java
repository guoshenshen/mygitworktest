package com.elearning.controller.resourceManage;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.service.resourceManage.IRsmRcmbookdiscussService;
import com.elearning.util.PropertiesUtil;
import com.google.common.base.Strings;
import org.hamcrest.core.Is;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/16 14:55
 */
@Controller
@RequestMapping("/rsmRcmBookDiscuss/")
public class RsmRcmBookDiscussController {

    @Autowired
    private IRsmRcmbookdiscussService rsmRcmbookdiscussService;


    /**
     * 课程评价
     * @param bookId
     * @param discussType
     * @return
     */
    @RequestMapping("getScoreDistribute.do")
    @ResponseBody
    public ServiceResponse getScoreDistribute(Long bookId,Integer discussType){
        if(bookId == null || discussType == null){
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("bookId",bookId);
        map.put("isReply",0);
        map.put("discussType",discussType);
        return rsmRcmbookdiscussService.getScoreDistribute(map);
    }


    /**
     * 课程评论
     * @param num
     * @param size
     * @param request
     * @return
     */
    @RequestMapping("getDiscussListInFixedNum.do")
    @ResponseBody
    public ServiceResponse getDiscussListInFixedNum(@RequestParam(value = "num",defaultValue = "1")Integer num,
                                                    @RequestParam(value = "size",defaultValue = "10")Integer size,
                                                    HttpServletRequest request){
        Long bookId=-1L;
        if(request.getParameter("bookId")!=null&&!request.getParameter("bookId").trim().equals("")){
            bookId=Long.valueOf(request.getParameter("bookId").toString());
        }
        HashMap map = new HashMap();
        map.put("bookId", bookId);
        if(request.getParameter("keyword")!=null&&!request.getParameter("keyword").equals("")){
            String keyword = request.getParameter("keyword");
            map.put("keyword", keyword);
        }
        if(request.getParameter("operatorName")!=null&&!request.getParameter("operatorName").equals("")){
            String operatorName = request.getParameter("operatorName");
            map.put("operatorName", operatorName);
        }
        if(request.getParameter("discussType")!=null&&!request.getParameter("discussType").equals("")){
            Integer discussType = Integer.valueOf(request.getParameter("discussType").toString());
            map.put("discussType", discussType);
        }
        map.put("num",num);
        map.put("size",size);
        return rsmRcmbookdiscussService.listRsmRcmbookdiscuss(map);
    }

    /**
     * 添加评论
     * @param bookId
     * @param score
     * @param isReply
     * @param discussType
     * @param content
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("addComment.do")
    @ResponseBody
    public ServiceResponse addComment(Long bookId,Integer score ,Integer isReply ,Integer discussType ,String content ,
                                      HttpServletRequest request,Integer discussId){
        if(content == null || content.length() == 0 ){
            return ServiceResponse.createByErrorMessage("评论不能为空");
        }
        EosOperator operator = (EosOperator) request.getSession().getAttribute(Constants.USERINFO_KEY);
        RsmRcmBookDiscuss rsmRcmBookDiscuss = new RsmRcmBookDiscuss();
        rsmRcmBookDiscuss.setOperatorId(operator.getOperatorId());
        rsmRcmBookDiscuss.setOperatorName(operator.getOperatorName());
        rsmRcmBookDiscuss.setBookId(bookId);
        rsmRcmBookDiscuss.setBookName("");
        rsmRcmBookDiscuss.setContent(content);
        if(isReply != null){
            rsmRcmBookDiscuss.setIsReply(isReply);
        }
        if(score != null ){
            rsmRcmBookDiscuss.setScore(score);
        }else if(isReply == 0 ){
            rsmRcmBookDiscuss.setScore(3);
        }
        rsmRcmBookDiscuss.setDiscussTime(new Date());
        rsmRcmBookDiscuss.setDiscussType(discussType);
        if(discussId != null){
            rsmRcmBookDiscuss.setIsReply(1);
            rsmRcmBookDiscuss.setParentDiscussId(discussId);
            rsmRcmBookDiscuss.setParentDiscuss(this.rsmRcmbookdiscussService.findById(discussId));
        }
        int success = rsmRcmbookdiscussService.addRsmRcmBookDiscuss(rsmRcmBookDiscuss).getStatusCode();
        if(success == 0 ){
            return ServiceResponse.createBySuccess(rsmRcmBookDiscuss);
        }
        return ServiceResponse.createByError();
    }








}
