package com.elearning.service.mixtraining;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.mixtraining.MtTrainNewsMapper;
import com.elearning.pojo.mixtraining.MtTrainNews;
import com.elearning.pojo.mixtraining.MtTrainNewsWithBLOBs;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.service.systemManage.ITenantService;
import com.elearning.vo.CourseVo;
import com.elearning.vo.mixtraining.MtTrainNewsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service("mtTrainNewsService")
public class MtTrainNewsService implements IMtTrainNewsService{

    @Autowired
    private MtTrainNewsMapper mtTrainNewsMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private ICourseService courseService;


    @Override
    public MtTrainNewsWithBLOBs selectByPrimaryKey(Integer newsId){

        return this.mtTrainNewsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public List<MtTrainNewsWithBLOBs> findByTrainId(Integer trainId){

        return this.mtTrainNewsMapper.findByTrainId(trainId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MtTrainNewsWithBLOBs> listNews(Map<String,Object> map){
        List<MtTrainNewsWithBLOBs> newList = this.mtTrainNewsMapper.listNews(map);
        List<MtTrainNewsWithBLOBs> newsList = new ArrayList<>();

        for(MtTrainNewsWithBLOBs  news : newList){
            if(news.getTrainId()!=null){
                int trainId = news.getTrainId();
                TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
                if(train!=null){
                    news.setTrainName(train.getTrainName());
                }
            }
            newsList.add(news);
        }
        return newsList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<MtTrainNewsWithBLOBs> findListByTrainIdOrderByCreateTime(Integer trainId){
        List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsMapper.findListByTrainIdOrderByCreateTime(trainId);
        if(trainNewsList == null || trainNewsList.size() == 0){
            trainNewsList = new ArrayList<>();
        }
        return trainNewsList;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void toAddNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request){

        String newsFlag= request.getParameter("newsFlag");
        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        Integer trainId = null;
        String orgName = "";
        Object trainID = request.getSession().getAttribute("trainId");
        if(trainID != null){
            trainId = (Integer)trainID;
        }
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        if(orgId != null){
            EosorgTOrganization org = this.eosorgTOrganizationService.findById(orgId);
            if(org!=null) {
                orgName = org.getOrgName();
            }
        }

        String newsContent = mtTrainNewsForm.getNewsContent();
        String newsTitle = mtTrainNewsForm.getNewsTitle();
        String newsAbstract = mtTrainNewsForm.getNewsAbstract();
        //String recommendTag = mtMixTrainNewsForm.getRecommendTag();
        String newsType = mtTrainNewsForm.getNewsType();
        String subNewsType= mtTrainNewsForm.getSubNewsType();

        Integer login_user_id=0;
        if(eosoperator != null){
            login_user_id = eosoperator.getOperatorId();
        }
        MtTrainNewsWithBLOBs news= new MtTrainNewsWithBLOBs();
        news.setCreateTime(new Date());
        news.setNewsContent(newsContent);
        news.setNewsTitle(newsTitle);
        news.setOperatorId(login_user_id);
        news.setNewsAbstract(newsAbstract);
        news.setTenantId(eosoperator.getTenantId());
        news.setOrgId(orgId);
        news.setOrgName(orgName);
        if(subNewsType!=null){
            news.setSubNewsType(Integer.parseInt(subNewsType));
        }
        if(newsFlag == null){
            news.setTrainId(trainId);//前期开发阶段trainId是0，以后此id将从培训列表中得到。
            news.setNewsType(0);
            //news.setRecommendTag(Integer.parseInt(recommendTag));
        }else{
            if(eosoperator.getTenantId() == 1){
                news.setNewsType(0);//机关应用默认是新闻动态
                news.setStatus(1092);//机关应用默认是发布
            } else{
                if(newsType==null){
                    news.setNewsType(0);//默认新闻动态
                } else{
                    news.setNewsType(Integer.parseInt(newsType));
                }
                news.setStatus(1091);
            }
        }
        this.mtTrainNewsMapper.insertSelective(news);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse changeStatus(HttpServletRequest request){

        Map<String,Object> returnMap = new HashMap<>();

        if(request.getParameter("newsId")!=null && !"".equals(request.getParameter("newsId"))){
            Integer newsId = Integer.valueOf(request.getParameter("newsId").toString());
            MtTrainNewsWithBLOBs news= this.findById(newsId);

            if(news.getStatus()==null || news.getStatus()!=1092){
                news.setStatus(1092);
                returnMap.put("flag",1092);
            }else if(news.getStatus()==1092){
                news.setStatus(1091);
                news.setRecommendTag(0); // 取消发布状态,推送取消
                returnMap.put("flag",1091);
            }
            this.mtTrainNewsMapper.updateByPrimaryKeySelective(news);
        }else{
            return ServiceResponse.createByErrorMessage("新闻已被删除，请刷新");
        }

        return ServiceResponse.createBySuccess(returnMap);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public MtTrainNewsWithBLOBs findById(Integer newsId){

        MtTrainNewsWithBLOBs mtTrainNewsWithBLOBs = this.mtTrainNewsMapper.selectByPrimaryKey(newsId);
        Integer tenantId = mtTrainNewsWithBLOBs.getTenantId();
        if(mtTrainNewsWithBLOBs.getOrgName() == null || mtTrainNewsWithBLOBs.getOrgName() == ""){
            Tenant tenant = this.tenantService.findById(tenantId);
            mtTrainNewsWithBLOBs.setOrgName(tenant.getTenantName());
        }
        return mtTrainNewsWithBLOBs;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse recommendNewsAjax(HttpServletRequest request){

        Map<String,Object> returnMap = new HashMap<>();

        String newsId = request.getParameter("newsId");
        if (newsId != null && !"".equals(newsId)) {
            MtTrainNewsWithBLOBs news= this.findById(Integer.parseInt(newsId));
            Integer recommendTag = news.getRecommendTag();
            if(recommendTag==null || recommendTag==0){
                news.setRecommendTag(1);
            } else{
                news.setRecommendTag(0);
            }
            this.mtTrainNewsMapper.updateByPrimaryKeySelective(news);

            return ServiceResponse.createBySuccess();
        }else{
            return ServiceResponse.createByError();
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void deleteNews(HttpServletRequest request){

        int newsId = Integer.valueOf(request.getParameter("newsId"));
        MtTrainNewsWithBLOBs news=this.mtTrainNewsMapper.selectByPrimaryKey(newsId);
        if(news!=null){
            this.mtTrainNewsMapper.deleteByPrimaryKey(newsId);
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void forUpdateNews(HttpServletRequest request){

        int trainId = 0;
        Object trainID = request.getSession().getAttribute("trainId");
        if(trainID!=null){
            trainId = (Integer)trainID;
            TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
            request.setAttribute("train", train);
        }

        EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int login_user_id=0;
        if(login_user!=null){
            login_user_id = login_user.getOperatorId();
        }
        String newsName = request.getParameter("newsName");

        Map<String,Object> map = new HashMap<>();
        map.put("operatorId", login_user_id);
        map.put("trainId", trainId);
        map.put("newsName", newsName);

        /*Pager pager = mtMixTrainNewsService.listNews(map, pageForm.getPageSize(), pageForm.getPageNo());
        List<MtMixTrainnews> newsList = pager.getResultList();*/

        List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsMapper.findByTrainId(trainId);//默认当前trainId为0

        int newsId = Integer.valueOf(request.getParameter("newsId"));
        MtTrainNews news = this.findById(newsId);
        request.setAttribute("trainNewsList", trainNewsList);
        request.setAttribute("news", news);
        //request.setAttribute("newsList", newsList);
        request.setAttribute("tenantId", Constants.tenantId);

        //获得工作流程下一级分类：国内培训、境外培训、公派留学
        List subNewsType=this.dictionaryService.getByParentCode("5100");
        request.setAttribute("subNewsType", subNewsType);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void updateNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request){

        String newsFlag= request.getParameter("newsFlag");

        String orgName = "";
        EosOperator login_user = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);
        int login_user_id=0;
        if(login_user!=null){
            login_user_id = login_user.getOperatorId();
        }
        int newsId = Integer.valueOf(request.getParameter("newsId"));
        Integer orgId = Integer.valueOf(request.getSession().getAttribute(Constants.ROOTORGID_KEY).toString());
        EosorgTOrganization org = eosorgTOrganizationService.findById(orgId);
        if(org!=null){
            orgName = org.getOrgName();
        }
        String newsContent=mtTrainNewsForm.getNewsContent();
        String newsTitle=mtTrainNewsForm.getNewsTitle();
        String newsAbstract = mtTrainNewsForm.getNewsAbstract();
        String recommendTag = mtTrainNewsForm.getRecommendTag();
        String newsType = mtTrainNewsForm.getNewsType();

        MtTrainNewsWithBLOBs news= this.findById(newsId);

        news.setNewsContent(newsContent);
        news.setNewsId(newsId);
        news.setNewsTitle(newsTitle);
        news.setOperatorId(login_user_id);
        news.setNewsAbstract(newsAbstract);
        news.setOrgName(orgName);
        news.setOrgId(orgId);

        if(newsFlag!=null){
            if(Constants.tenantId==1){
                news.setNewsType(0);
            }else{
                if(newsType==null){
                    news.setNewsType(0);//默认新闻动态
                } else{
                    news.setNewsType(Integer.parseInt(newsType));
                }
            }
        }else{
            news.setNewsType(0);
        }
        String subNewsType = mtTrainNewsForm.getSubNewsType();
        if(subNewsType!=null){
            news.setSubNewsType(Integer.parseInt(subNewsType));
        }
        this.mtTrainNewsMapper.updateByPrimaryKeySelective(news);

        request.setAttribute("tenantId", Constants.tenantId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void recommendNews(MtTrainNewsForm mtTrainNewsForm, HttpServletRequest request){
        int newsId = mtTrainNewsForm.getNewsId();
        MtTrainNewsWithBLOBs news= this.findById(newsId);
        Integer recommendTag = news.getRecommendTag();
        if(recommendTag==null||recommendTag==0){
            news.setRecommendTag(1);
        } else{
            news.setRecommendTag(0);
        }
        this.mtTrainNewsMapper.updateByPrimaryKeySelective(news);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse getNewsDetailInfo(HttpServletRequest request){

        String newsId = request.getParameter("newsId");
        MtTrainNews mtMixTrainnews = this.mtTrainNewsMapper.selectByPrimaryKey(Integer.parseInt(newsId));

        return ServiceResponse.createBySuccess(mtMixTrainnews);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse findTrainNews(HttpServletRequest request){

        //查询最新新闻
        String trainId = request.getParameter("trainId");
        List<MtTrainNewsWithBLOBs> trainNewsList = this.mtTrainNewsMapper.findByTrainId(Integer.parseInt(trainId));//默认当前trainId为0

        return ServiceResponse.createBySuccess(trainNewsList);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void getNewsDetailFromMainPage(HttpServletRequest request){

        EosOperator eosoperator = (EosOperator)request.getSession().getAttribute(Constants.USERINFO_KEY);

        String newsId = request.getParameter("newsId");
        String newsType = request.getParameter("newsType");
        MtTrainNewsWithBLOBs mtMixTrainnews = this.mtTrainNewsMapper.selectByPrimaryKey(Integer.parseInt(newsId));
        String mtMixTrainnewsInfo = mtMixTrainnews.getNewsContent();
        if(mtMixTrainnewsInfo!=null){
            mtMixTrainnewsInfo=mtMixTrainnewsInfo.replaceAll("&nbsp;", "").replaceAll("  ", "").replaceAll("   ","").replaceAll("　　", "");
        }
        mtMixTrainnews.setNewsContent(mtMixTrainnewsInfo);
        if(mtMixTrainnews.getTenantId() != null){
            Tenant tenant = this.tenantService.findById(mtMixTrainnews.getTenantId());
            if(mtMixTrainnews.getOrgId()!=null && mtMixTrainnews.getOrgId().equals(tenant.getOrgId())){

            } else{
                String orgName = tenant.getTenantName();
                if(mtMixTrainnews.getOrgName() != null){
                    if(orgName.equals(mtMixTrainnews.getOrgName())){

                    } else{
                        orgName+=" "+mtMixTrainnews.getOrgName();
                    }
                }
                mtMixTrainnews.setOrgName(orgName);
            }
        }
        request.setAttribute("newsType", newsType);
        request.setAttribute("mtMixTrainnews", mtMixTrainnews);

        //查询最新新闻
        /*Map<String, Object> newestNewsCondition = new HashMap<>();
        String length = request.getParameter("length");
        String startIndex = request.getParameter("startIndex");
        newestNewsCondition.put("inner_outter_flag", 2);
        newestNewsCondition.put("newsType", 0);
        if(startIndex!=null){
            newestNewsCondition.put("startIndex", Integer.valueOf(startIndex));
        } else{
            newestNewsCondition.put("startIndex", 0);
        }

        if(length!=null){
            newestNewsCondition.put("length", length);
        } else{
            newestNewsCondition.put("length", 5);
        }

        //List<MtTrainNews> newestNewsList = this.getMtMixTrainNewsService().findByConditions(newestNewsCondition);
        List<MtTrainNews> newestNewsList = this.mtTrainNewsMapper.findByConditions(newestNewsCondition);
        request.setAttribute("newestNewsList", newestNewsList);*/


        //传递标识字段inner_outter_flag，标识查询首页显示新闻（本所+门户）或者是全院新闻
        if(request.getParameter("inner_outter_flag")!=null && !request.getParameter("inner_outter_flag").trim().equals("")){
            request.setAttribute("inner_outter_flag", request.getParameter("inner_outter_flag"));
        }

        // 热门课程查找 2017/8/15 改
        String orgSEQ = "1.";
        if(eosoperator != null){
            EosorgTEmployee eosorgTEmployee = this.eosorgTEmployeeService.findById(eosoperator.getOperatorId());
            if(eosorgTEmployee.getOrgID() != null){
                EosorgTOrganization belongOrg = this.eosorgTOrganizationService.findById(eosorgTEmployee.getOrgID());
                orgSEQ = belongOrg.getOrgSEQ();
            }
        }

        List<CourseVo> courseVos = this.courseService.listPopularCourseReturnCourseVoList(orgSEQ);
        request.setAttribute("courseList", courseVos);

    }

}
