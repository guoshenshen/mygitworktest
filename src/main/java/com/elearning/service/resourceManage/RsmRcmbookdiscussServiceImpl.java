package com.elearning.service.resourceManage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.pub.EosOperatorMapper;
import com.elearning.dao.pub.EosorgTEmployeeMapper;
import com.elearning.dao.resourceManage.RsmRcmBookDiscussMapper;
import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.resourceManage.RsmRcmBookDiscuss;
import com.elearning.service.pub.IEosOperatorService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.util.DoubleUtil;
import com.elearning.vo.MapVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/15 16:02
 */
@Service("rsmRcmbookdiscussService")
public class RsmRcmbookdiscussServiceImpl implements IRsmRcmbookdiscussService{

    @Autowired
    private RsmRcmBookDiscussMapper rsmRcmBookDiscussMapper;

    @Autowired
    private EosorgTEmployeeMapper eosorgTEmployeeMapper;

    @Autowired
    private EosOperatorMapper eosOperatorMapper;

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosOperatorService eosOperatorService;


    /**
     * 该门课程的平均评分
     * @param scoreCondition
     * @return
     */
    @Override
    public Double getAverageScore(Map<String, Object> scoreCondition) {
        return rsmRcmBookDiscussMapper.getCourseScore(scoreCondition);
    }

    /**
     * 课程评价
     * @param scoreCondition
     * @return
     */
    @Override
    public ServiceResponse getScoreDistribute(Map<String, Object> scoreCondition) {
        List<MapVo> mapVoList = rsmRcmBookDiscussMapper.getScoreDistribute(scoreCondition);
        Map<Integer,Integer> map = new HashMap<>();
        for ( MapVo mapVo: mapVoList ) {
            map.put((Integer) mapVo.getKey(),Integer.parseInt( mapVo.getValue().toString()));
        }
        Integer[] scoreRange={1,2,3,4,5};
        Integer totaldiscuss = 0;
        Integer totalScore=0;
        Integer goodNum=0;
        for(Integer score:scoreRange){
            Integer count=   map.get(score);
            if(count==null){
                count=0;
                map.put(score, count);
            }
            totaldiscuss+=count;
            totalScore+=count*score;
            if(score>=4){
                goodNum+=count;
            }
        }
        Double averageScore = 0.00;
        String goodRate="0.0";
        if (totaldiscuss != 0) {
            averageScore = DoubleUtil.getTwoDigitalDoubleData(Double.valueOf(totalScore) / totaldiscuss);
        }
        if(totaldiscuss!=0){
            goodRate=DoubleUtil.getTwoDigitalDoubleData((goodNum+0.0) / totaldiscuss*100)+"%";
        }
        Map<String,Object> resultMap = new HashMap<>();

        for(Integer score:scoreRange){
            resultMap.put(score+"",map.get(score));
        }
        resultMap.put("averageScore",averageScore);
        resultMap.put("goodRate",goodRate);
        return ServiceResponse.createBySuccess(resultMap);
    }

    /**
     * 课程评论
     * @param map
     * @return
     */
    @Override
    public ServiceResponse listRsmRcmbookdiscuss(HashMap map) {
        PageHelper.startPage((int)map.get("num"),(int)map.get("size"));
        List<RsmRcmBookDiscuss> rsmRcmBookDiscussList = rsmRcmBookDiscussMapper.getRsmRcmbookdiscussList(map);
        List<RsmRcmBookDiscuss> resultList=new ArrayList<>();
        for(RsmRcmBookDiscuss rsm:rsmRcmBookDiscussList){
            RsmRcmBookDiscuss result=this.resetRsmInfo(rsm);
            if(result!=null){
                resultList.add(result);
            }
            map.put("parentDiscussId",result.getDiscussId());
            Set<RsmRcmBookDiscuss> rsmRcmBookDiscusses = rsmRcmBookDiscussMapper.getRsmRcmbookdiscussByparentDiscussId(map);
            result.setDiscussReplies(rsmRcmBookDiscusses);
            Set<RsmRcmBookDiscuss> replys = result.getDiscussReplies();
            if(replys!=null&&replys.size()>0){
                for(RsmRcmBookDiscuss reply:replys){
                    this.resetRsmInfo(reply);
                }
            }
        }
        PageInfo pageInfo = new PageInfo(rsmRcmBookDiscussList);
        pageInfo.setList(resultList);
        return ServiceResponse.createBySuccess(pageInfo);
    }

    @Override
    public ServiceResponse addRsmRcmBookDiscuss(RsmRcmBookDiscuss rsmRcmBookDiscuss) {
        int result = rsmRcmBookDiscussMapper.insertSelective(rsmRcmBookDiscuss);
        if(result > 0){
            return ServiceResponse.createBySuccessMessage("添加成功");
        }
        return ServiceResponse.createByErrorMessage("添加失败");
    }


    private RsmRcmBookDiscuss  resetRsmInfo(RsmRcmBookDiscuss rsm){
        RsmRcmBookDiscuss result=null;
        Integer operatorId=rsm.getOperatorId();
        if(operatorId!=null){
            EosorgTEmployee employee = eosorgTEmployeeMapper.selectByPrimaryKey(operatorId);
            EosOperator operator;
            if(employee!=null){
                operator = eosOperatorMapper.selectByPrimaryKey(operatorId);
                if(operator.getStatus()!=null&&operator.getStatus().equals(0)){
                }
                else{
                    String operatorName=operator.getOperatorName();
                    rsm.setOperatorName(operatorName);
                    rsm.setPicurl(employee.getADDRESS());
                    rsm.setGender(employee.getGender());
                    String rsmContent=rsm.getContent();
                    if(rsmContent!=null){
                        rsmContent=rsmContent.replaceAll("&lt;p&gt;", "<p>");
                        rsmContent=rsmContent.replaceAll("&lt;/p&gt;", "</p>");
                        rsmContent=rsmContent.replaceAll("&lt;br&gt;", "<br>");
                        rsmContent=rsmContent.replaceAll("&lt;br/&gt;", "<br/>");
                        rsmContent=rsmContent.replaceAll("&lt;span", "<span");
                        rsmContent=rsmContent.replaceAll("&amp;", "&");
                        rsm.setContent(rsmContent);
                    }
                }
                result=rsm;
            }
        }
        return result;
    }

    @Override
    public List<RsmRcmBookDiscuss> getListByBookIdAndDiscussTypeAndIsReply(Map<String,Object> parm) {

        return this.rsmRcmBookDiscussMapper.getListByBookIdAndDiscussTypeAndIsReply(parm);
    }

    @Override
    public RsmRcmBookDiscuss findById(Integer discussId) {
        return rsmRcmBookDiscussMapper.selectByPrimaryKey(discussId);
    }

    @Override
    public List<RsmRcmBookDiscuss> getRsmRcmbookdiscussList(Map<String, Object> map) {
        return rsmRcmBookDiscussMapper.getRsmRcmbookdiscussList(map);
    }

    @Override
    public List<RsmRcmBookDiscuss> queryRsmRcmbookdiscussList(Map<String, Object> map) {
        List<RsmRcmBookDiscuss> rsmRcmBookDiscussList = this.getRsmRcmbookdiscussList(map);
        List<RsmRcmBookDiscuss> _rsmbookDiscussList = new ArrayList<>();
        for(RsmRcmBookDiscuss rsm : rsmRcmBookDiscussList){
            Integer operatorId = rsm.getOperatorId();
            if(operatorId!=null){
                EosorgTEmployee employee = this.eosorgTEmployeeService.findById(operatorId);
                if(employee!=null){
                    EosOperator operator = this.eosOperatorService.findById(operatorId);
                    if(operator.getStatus()!=null && operator.getStatus().equals(0)){
                        //
                    } else{
                        String operatorName=operator.getOperatorName();
                        rsm.setOperatorName(operatorName);
                        rsm.setPicurl(employee.getADDRESS());
                        rsm.setGender(employee.getGender());
                        String rsmContent=rsm.getContent();
                        if(rsmContent!=null){
                            rsmContent=rsmContent.replaceAll("&lt;p&gt;", "<p>");
                            rsmContent=rsmContent.replaceAll("&lt;/p&gt;", "</p>");
                            rsmContent=rsmContent.replaceAll("&lt;br&gt;", "<br>");
                            rsmContent=rsmContent.replaceAll("&lt;br/&gt;", "<br/>");
                            rsmContent=rsmContent.replaceAll("&lt;span", "<span");
                            rsmContent=rsmContent.replaceAll("&amp;", "&");
                            rsm.setContent(rsmContent);
                        }
                    }
                }
            }
            _rsmbookDiscussList.add(rsm);
        }
        return _rsmbookDiscussList;
    }

}
