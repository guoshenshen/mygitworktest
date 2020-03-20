package com.elearning.service.testPaper;

import com.elearning.common.Constants;
import com.elearning.dao.testPaper.TpaPapersMapper;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.testPaper.TpaPaperQuestions;
import com.elearning.pojo.testPaper.TpaPaperStrategyQuesType;
import com.elearning.pojo.testPaper.TpaPapers;
import com.elearning.pojo.testPaper.TpaQuestions;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IDDictionaryService;
import com.elearning.vo.testPaper.TpaPaperStrategyQuesTypeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service("tpaPapersService")
public class TpaPapersService implements ITpaPapersService{


    @Autowired
    private TpaPapersMapper tpaPapersMapper;

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ITpaQuestionsService tpaQuestionsService;

    @Autowired
    private IDDictionaryService dictionaryService;

    @Autowired
    private ITpaPaperStrategyQuesTypeService tpaPaperStrategyQuesTypeService;


    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaPapers selectByPrimaryKey(Integer ID){

        return this.tpaPapersMapper.selectByPrimaryKey(ID);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPapers> findTpaPapersByCondition(Map<String,Object> parmMap){

        return this.tpaPapersMapper.findTpaPapersByCondition(parmMap);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public TpaPapers findPaperById(Integer id){

        return this.appendInfoTpaPapers(this.tpaPapersMapper.selectByPrimaryKey(id));
    }

    public TpaPapers appendInfoTpaPapers(TpaPapers temp) {
        if(temp != null) {
            Map<String,Object> map = this.findMapForCourseOrTrain(temp.getClassifyId(),temp.getClassifySign());
            temp.setClassifyName((String)map.get("classifyName"));
            temp.setClassifyNumber((String)map.get("classifyNumber"));
        }
        return temp;
    }

    private Map<String,Object> findMapForCourseOrTrain(long id,Integer sign) {
        Map<String,Object> map = new HashMap();
        if(sign == Integer.parseInt(Constants.I_CLASSIFY_TRAIN)){
            Long _id = id;
            int Id = _id.intValue();
            map = this.findMapForTrain(Id);
        }else if(sign==Integer.parseInt(Constants.I_CLASSIFY_COURSE)){
            map = this.findMapForCourse(id);
        }else{
            map.put("classifyNumber","");
            map.put("classifyName","无分类");
        }
        return map;
    }

    private Map findMapForTrain(Integer trainID) {
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainID);

        Map map=new HashMap();
        map.put("classifyNumber","");
        map.put("classifyName","");

        if(train != null){
            if(train.getTrainID() != null) {
                map.put("classifyNumber",train.getTrainID().toString());
            }
            if(train.getTrainName() != null) {
                map.put("classifyName",train.getTrainName());
            }
        }

        return map;
    }

    private Map findMapForCourse(Long courseID) {
        Course course = courseService.getCourse(courseID);

        Map<String,Object> map = new HashMap<>();
        map.put("classifyNumber","");
        map.put("classifyName","");

        if(course!=null){
            if(course.getCourseNo()!=null) {
                map.put("classifyNumber",course.getCourseNo().toString());
            }
            if(course.getCourseName()!=null) {
                map.put("classifyName",course.getCourseName());
            }
        }

        return map;
    }

    /**
     *  试卷试题分类总方法
     * @param tpaPaper
     * @param tpaPaperQuestionsList
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class })
    public List<TpaPaperStrategyQuesTypeForm> getTpaPaperForClassification(TpaPapers tpaPaper, List<TpaPaperQuestions> tpaPaperQuestionsList){

        int singleChoiceNum = 0 , multiChoiceNum = 0 , fillBlankNum = 0 , shortQuesNum = 0 , discussNum = 0;
        int singleChoiceScore = 0 , multiChoiceScore = 0 , fillBlankScore = 0 , shortQuesScore = 0 , discussScore = 0;

        List<TpaQuestions> singleChoiceFormList = new ArrayList<>();
        List<TpaQuestions> multiChoiceFormList = new ArrayList<>();
        List<TpaQuestions> fillBlankFormList = new ArrayList<>();
        List<TpaQuestions> shortQuesFormList = new ArrayList<>();
        List<TpaQuestions> discussFormList = new ArrayList<>();

        List<TpaPaperStrategyQuesTypeForm> testQuesList = new ArrayList<>();
        List seqList = new ArrayList();

        Integer strategyId = tpaPaper.getStrategyId();

        //int paperId = tpaPaper.getID();

        for(TpaPaperQuestions tpaPaperQuestions:tpaPaperQuestionsList){

            //TpaQuestions tpaQuestions = this.tpaQuestionsService.selectByPrimaryKey(tpaPaperQuestions.getQuestion_id());

            int questionType = tpaPaperQuestions.getTpaQuestions().getQustioinTypeID();

            switch(questionType){
                case 1011:
                    singleChoiceNum++;
                    singleChoiceScore += tpaPaperQuestions.getQuestionMark();
                    singleChoiceFormList.add(tpaPaperQuestions.getTpaQuestions());
                    break;
                case 1012:
                    multiChoiceNum++;
                    multiChoiceScore += tpaPaperQuestions.getQuestionMark();
                    multiChoiceFormList.add(tpaPaperQuestions.getTpaQuestions());
                    break;
                case 1013:
                    fillBlankNum++;
                    fillBlankScore += tpaPaperQuestions.getQuestionMark();
                    fillBlankFormList.add(tpaPaperQuestions.getTpaQuestions());
                    break;
                case 1014:
                    shortQuesNum++;
                    shortQuesScore += tpaPaperQuestions.getQuestionMark();
                    shortQuesFormList.add(tpaPaperQuestions.getTpaQuestions());
                    break;
                case 1015:
                    discussNum++;
                    discussScore += tpaPaperQuestions.getQuestionMark();
                    discussFormList.add(tpaPaperQuestions.getTpaQuestions());
                    break;
                default:break;
            }
        }

        Integer[] seq = null;
        if(strategyId==null){//表示非策略试卷
            seq = new Integer[]{1011,1012,1013,1014,1015};//默认试卷序列
        }else{//表示策略组卷

            List<TpaPaperStrategyQuesType> tpaPaperStrategyquestypeList = this.tpaPaperStrategyQuesTypeService.findByStrategyId(strategyId);
            seq = new Integer[tpaPaperStrategyquestypeList.size()];
            for(TpaPaperStrategyQuesType tpaPaperStrategyquestype:tpaPaperStrategyquestypeList){
                seqList.add(tpaPaperStrategyquestype.getStrategyQuesSeq());
            }
            seq = (Integer[])seqList.toArray(new Integer[seqList.size()]);
            Arrays.sort(seq);
        }

        int perStrategyQuesScore = 0;
        Integer tqType= 0;
        for(int i=0;i<seq.length;i++){
            Map<String,Object> parmMap = new HashMap<>();
            parmMap.put("strategyId",strategyId);
            parmMap.put("strategyQuesSeq",seq[i]);

            List<TpaPaperStrategyQuesType> temp_tpaPaperStrategyquestypeList = this.tpaPaperStrategyQuesTypeService.getListByStrategyIdAndStrategyQuesSeq(parmMap);
            TpaPaperStrategyQuesTypeForm tpaPaperStrategyquestypeForm = new TpaPaperStrategyQuesTypeForm();
            if(temp_tpaPaperStrategyquestypeList.size()>0){
                TpaPaperStrategyQuesType temp_tpaPaperStrategyquestype = temp_tpaPaperStrategyquestypeList.get(0);
                tqType= temp_tpaPaperStrategyquestype.getStrategyQuesType();
            }else{
                tqType = seq[i];
            }

            switch(tqType){
                case 1011:
                    tpaPaperStrategyquestypeForm.setStrategyQuesTypeName((dictionaryService.findByCode(tqType.toString()).get(0)).getName());
                    tpaPaperStrategyquestypeForm.setStrategyQuesNum(singleChoiceNum);
                    if(singleChoiceNum!=0){
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(singleChoiceScore/singleChoiceNum);
                    } else{
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(perStrategyQuesScore);
                    }
                    tpaPaperStrategyquestypeForm.setStrategyQuesScore(singleChoiceScore);
                    tpaPaperStrategyquestypeForm.setQuestionList(singleChoiceFormList);
                    if(singleChoiceFormList.size()>0){
                        testQuesList.add(tpaPaperStrategyquestypeForm);
                    }
                    break;
                case 1012:
                    tpaPaperStrategyquestypeForm.setStrategyQuesTypeName((dictionaryService.findByCode(tqType.toString()).get(0)).getName());
                    tpaPaperStrategyquestypeForm.setStrategyQuesNum(multiChoiceNum);
                    if(multiChoiceNum!=0){
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(multiChoiceScore/multiChoiceNum);
                    } else{
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(perStrategyQuesScore);
                    }
                    tpaPaperStrategyquestypeForm.setStrategyQuesScore(multiChoiceScore);
                    tpaPaperStrategyquestypeForm.setQuestionList(multiChoiceFormList);
                    if(multiChoiceFormList.size()>0)
                        testQuesList.add(tpaPaperStrategyquestypeForm);
                    break;
                case 1013:
                    tpaPaperStrategyquestypeForm.setStrategyQuesTypeName((dictionaryService.findByCode(tqType.toString()).get(0)).getName());
                    tpaPaperStrategyquestypeForm.setStrategyQuesNum(fillBlankNum);
                    if(fillBlankNum!=0){
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore( fillBlankScore/ fillBlankNum);
                    } else{
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(perStrategyQuesScore);
                    }
                    tpaPaperStrategyquestypeForm.setStrategyQuesScore( fillBlankScore);
                    tpaPaperStrategyquestypeForm.setQuestionList( fillBlankFormList);
                    if(fillBlankFormList.size()>0){
                        testQuesList.add(tpaPaperStrategyquestypeForm);
                    }
                    break;
                case 1014:
                    tpaPaperStrategyquestypeForm.setStrategyQuesTypeName((dictionaryService.findByCode(tqType.toString()).get(0)).getName());
                    tpaPaperStrategyquestypeForm.setStrategyQuesNum(shortQuesNum);
                    if(shortQuesNum!=0){
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(shortQuesScore/ shortQuesNum);
                    } else{
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(perStrategyQuesScore);
                    }
                    tpaPaperStrategyquestypeForm.setStrategyQuesScore(shortQuesScore);
                    tpaPaperStrategyquestypeForm.setQuestionList(shortQuesFormList);
                    if(shortQuesFormList.size()>0){
                        testQuesList.add(tpaPaperStrategyquestypeForm);
                    }
                    break;
                case 1015:
                    tpaPaperStrategyquestypeForm.setStrategyQuesTypeName((dictionaryService.findByCode(tqType.toString()).get(0)).getName());
                    tpaPaperStrategyquestypeForm.setStrategyQuesNum(discussNum);
                    if(discussNum!=0){
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(discussScore/discussNum);
                    } else{
                        tpaPaperStrategyquestypeForm.setPerStrategyQuesScore(perStrategyQuesScore);
                    }
                    tpaPaperStrategyquestypeForm.setStrategyQuesScore(discussScore);
                    tpaPaperStrategyquestypeForm.setQuestionList(discussFormList);
                    if(discussFormList.size()>0){
                        testQuesList.add(tpaPaperStrategyquestypeForm);
                    }
                    break;
                default:break;
            }
        }

        return testQuesList;
    }





}
