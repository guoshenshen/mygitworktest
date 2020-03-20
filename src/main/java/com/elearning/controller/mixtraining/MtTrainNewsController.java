package com.elearning.controller.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ResponseCode;
import com.elearning.common.ServiceResponse;
import com.elearning.interceptor.loginInterceptor.IsCheckUserLogin;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.mixtraining.IMtTrainNewsService;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.util.DateTimeUtil;
import com.elearning.vo.mixtraining.MtTrainNewsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/mtTrainNews/")
public class MtTrainNewsController {

    @Autowired
    private IMtTrainNewsService mtTrainNewsService;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosOperatorService eosOperatorService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    /**
     * 点击培训新闻图标时查询出的主页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("queryNews.do")
    public String queryNews(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        Integer trainId = null;
        Object trainID = request.getSession().getAttribute("trainId");
        if(trainID!=null){
            trainId = (Integer)trainID;
            TrainWithBLOBs trainWithBLOBs = this.onlineTrainingService.findById(trainId);
            request.setAttribute("train", trainWithBLOBs);
        }
        String newsFlag = request.getParameter("newsFlag");

        int login_user_id=0;
        if(eosoperator != null){
            login_user_id = eosoperator.getOperatorId();
        }
        String newsName = request.getParameter("newsName");
        request.setAttribute("newsName", newsName);
        Map<String,Object> map1 = new HashMap();
        map1.put("operatorId", login_user_id);
        map1.put("trainId", trainId);
        map1.put("newsName", newsName);
        map1.put("newsFlag", newsFlag);
        map1.put("tenantId", Constants.tenantId);

        List<MtTrainNewsWithBLOBs> newsList = this.mtTrainNewsService.listNews(map1);
        request.setAttribute("newsList", newsList);

        //科协和继续教育网中的注释
        //--------------------------------------------------------------------------------------------------------------
        //HashMap map2 = new HashMap();
        //map2.put("operatorId", login_user_id);
        //map2.put("trainId", 0);//前期开发阶段trainId是0,以后此id将从培训列表中得到。
        //Pager pager2 = mtMixTrainNewsService.listNews(map2, pageForm.getPageSize(), pageForm.getPageNo());
        //List<MtMixTrainnews> trainNewsList = pager2.getResultList();
        //--------------------------------------------------------------------------------------------------------------

        if(trainId!=null){
            List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsService.findListByTrainIdOrderByCreateTime(trainId);//默认当前trainId为0
            request.setAttribute("trainNewsList", trainNewsList);
        }

        request.setAttribute("tenantId", Constants.tenantId);

        if(newsFlag!=null){
            jspName = "mixtraining/newsList";
        } else{
            jspName = "mixtraining/mixTrainingNewsHome";
        }
        return jspName;
    }

    /**
     * 保存新增新闻后，重定向到新闻主页面
     * @param mtTrainNewsForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("toAddNews.do")
    public String toAddNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String newsFlag= request.getParameter("newsFlag");

        this.mtTrainNewsService.toAddNews(mtTrainNewsForm,request);

        if(newsFlag!=null){
            return "redirect:../mtTrainNews/queryNews.do?newsFlag=1";
        } else{
            return "redirect:../mtTrainNews/queryNews.do";
        }

    }

    /**
     * 更改新闻
     * @param mtTrainNewsForm
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("updateNews.do")
    public String updateNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String newsFlag= request.getParameter("newsFlag");

        this.mtTrainNewsService.updateNews(mtTrainNewsForm,request);

        if(newsFlag!=null){
            return "redirect:../mtTrainNews/queryNews.do?newsFlag=1";
        }else{
            return "redirect:../mtTrainNews/queryNews.do";
        }

    }

    /**
     * 修改发布状态（已发布，未发布）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("changeStatus.do")
    @ResponseBody
    public ServiceResponse changeStatus(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtTrainNewsService.changeStatus(request);

    }

    /**
     * 修改推送状态（已推送，未推送）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("recommendNewsAjax.do")
    @ResponseBody
    public ServiceResponse recommendNewsAjax(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtTrainNewsService.recommendNewsAjax(request);

    }

    /**
     * 删除
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("deleteNews.do")
    public String deleteNews(HttpServletRequest request){

        String newsFlag = request.getParameter("newsFlag");

        this.mtTrainNewsService.deleteNews(request);

        if(newsFlag!=null){
            //<forward name="newsDelete" path="/mtMixTrainNewsAction.do?method=queryNews&amp;newsFlag=1" redirect="true"></forward>
            return "redirect:../mtTrainNews/queryNews.do?newsFlag=1";
        } else{
            //<forward name="deleteNews" path="/mtMixTrainNewsAction.do?method=queryNews" redirect="true"></forward>
            return "redirect:../mtTrainNews/queryNews.do";
        }
    }

    /**
     * 预览
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("viewNews.do")
    @ResponseBody
    public ServiceResponse viewNews(HttpServletRequest request){

        EosOperator eosoperator1 = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator1 == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        int newsId = Integer.valueOf(request.getParameter("newsId"));
        MtTrainNewsWithBLOBs news= this.mtTrainNewsService.findById(newsId);
        EosOperator eosoperator2 = this.eosOperatorService.findById(news.getOperatorId());
        String tmp="";

        tmp+="<div id='_newsTitle'>"+news.getNewsTitle()+"</div><div id='_operatorName'>"+eosoperator2.getOperatorName()+"</div><div id='_newsDate'>"
                + DateTimeUtil.dateToStr(news.getCreateTime(),"yyyy-MM-dd HH:mm:ss")+"</div><div id='_newContent'>"+news.getNewsContent()+"</div>";

        return ServiceResponse.createBySuccess(tmp);
    }

    /**
     * 去编辑新闻页面
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("forUpdateNews.do")
    public String forUpdateNews(HttpServletRequest request){
        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }
        String newsFlag = request.getParameter("newsFlag");

        this.mtTrainNewsService.forUpdateNews(request);

        if(newsFlag != null){
            return jspName = "mixtraining/newsUpdate";
        } else{
            return jspName = "mixtraining/mixTrainingNewsEdit";
        }
    }

    /**
     * 在使用内置模板时，通过连接修改推送状态（已推送，未推送）
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("recommendNews.do")
    public String recommendNews(MtTrainNewsForm mtTrainNewsForm,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtTrainNewsService.recommendNews(mtTrainNewsForm,request);

        return "redirect:../mtTrainNews/queryNews.do";

    }

    @IsCheckUserLogin(check = true)
    @RequestMapping("queryForNews.do")
    public String queryForNews(HttpServletRequest request){

        String jspName = "test/error";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String trainId = request.getParameter("trainId");
        TrainWithBLOBs train = this.onlineTrainingService.findById(Integer.parseInt(trainId));
        List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsService.findByTrainId(Integer.parseInt(trainId));//默认当前trainId为0
        request.setAttribute("trainNewsList", trainNewsList);
        request.setAttribute("train", train);
        jspName = "mixtraining/newsForTrain";

        return jspName;

    }

    /**
     * 显示培训新闻内容
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getNewsDetailInfo.do")
    @ResponseBody
    public ServiceResponse getNewsDetailInfo(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtTrainNewsService.getNewsDetailInfo(request);
    }

    /**
     * 用于线上培训班查询新闻
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("findTrainNews.do")
    @ResponseBody
    public ServiceResponse findTrainNews(HttpServletRequest request){
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return ServiceResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }

        return this.mtTrainNewsService.findTrainNews(request);
    }

    /**
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("getNewsDetailFromMainPage.do")
    public String getNewsDetailFromMainPage(HttpServletRequest request){
        String jspName = "test/error";
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        this.mtTrainNewsService.getNewsDetailFromMainPage(request);
        String newsType = request.getParameter("newsType");

        if(eosoperator!=null || newsType.equals("0")){
            jspName = "mixtraining/messageDetail";
        } else{
            jspName = "logon_cast.jsp";
        }
        return jspName;
    }

    /**
     *
     * @param request
     * @return
     */
    @IsCheckUserLogin(check = true)
    @RequestMapping("foraddNews.do")
    public String foraddNews(HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        request.setAttribute("tenantId", Constants.tenantId);
        //获得工作流程子分类
        List<DDictionary> subNewsType = this.dictionaryService.getByParentCode("5100");
        request.setAttribute("subNewsType", subNewsType);

        return "mixtraining/newsAdd";

    }


    @IsCheckUserLogin(check = true)
    @RequestMapping("preview.do")
    public String preview(MtTrainNewsForm mtTrainNewsForm,HttpServletRequest request){

        String jspName = "index";

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        if(eosoperator == null) {
            return jspName;
        }

        String mtMixTrainnewsInfo = mtTrainNewsForm.getNewsContent();
        if(mtMixTrainnewsInfo != null){
            mtMixTrainnewsInfo=mtMixTrainnewsInfo.replaceAll("&nbsp;", "").replaceAll("  ", "").replaceAll("   ","").replaceAll("　　", "");
        }
        mtTrainNewsForm.setNewsContent(mtMixTrainnewsInfo);
        String orgName = "";
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);
        if(org!=null){
            orgName = org.getOrgName();
        }
        mtTrainNewsForm.setCreateTime(new Date());
        mtTrainNewsForm.setOrgName(orgName);
        request.setAttribute("newsType", 0);
        request.setAttribute("mtMixTrainnews", mtTrainNewsForm);

        return "mixtraining/mixTrainingNewsDetail";

    }


}
