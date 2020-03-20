package com.elearning.service.examManage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.examManage.ExamExamPapersMapper;
import com.elearning.pojo.examManage.ExamExamPapers;
import com.elearning.pojo.testPaper.TpaPapers;
import com.elearning.service.testPaper.ITpaPapersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service("examExamPapersService")
public class ExamExamPapersServiceImpl implements IExamExamPapersService {

    @Autowired
    private ExamExamPapersMapper examExamPapersMapper;

    @Autowired
    private ITpaPapersService tpaPapersService;

    @Override
    public ExamExamPapers selectByPrimaryKey(Integer ID) {
        return examExamPapersMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int update(ExamExamPapers record) {
        return examExamPapersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insert(ExamExamPapers record) {
        return examExamPapersMapper.insert(record);
    }

    @Override
    public List<ExamExamPapers> findByExamId(Integer examID){
        return examExamPapersMapper.findByExamId(examID);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ExamExamPapers findByExamIdReturnExamExamPapers(Integer examID){
        List<ExamExamPapers> list = this.examExamPapersMapper.findByExamId(examID);
        ExamExamPapers examPaper = new ExamExamPapers();
        if(list.size()>0){
            examPaper = list.get(0);
            return examPaper;
        }else{
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public ServiceResponse searchPaperList(Map<String,Object> queryConditionMap){

        //如果该方法出现在查询前，则后面的查询都将会分页
        PageHelper.startPage((Integer) queryConditionMap.get("startIndex"),(Integer) queryConditionMap.get("count"));

        //根据查询条件进行查询
        List<TpaPapers> tpaPapersList = this.tpaPapersService.findTpaPapersByCondition(queryConditionMap);
        PageInfo pageInfo = new PageInfo(tpaPapersList);
        pageInfo.setList(tpaPapersList);



        return ServiceResponse.createBySuccess(pageInfo);
    }






}
