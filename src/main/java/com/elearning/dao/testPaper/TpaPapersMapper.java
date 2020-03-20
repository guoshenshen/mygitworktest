package com.elearning.dao.testPaper;

import com.elearning.pojo.testPaper.TpaPapers;

import java.util.List;
import java.util.Map;

public interface TpaPapersMapper {

    int deleteByPrimaryKey(Integer ID);

    int insert(TpaPapers record);

    int insertSelective(TpaPapers record);

    TpaPapers selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(TpaPapers record);

    int updateByPrimaryKey(TpaPapers record);

    List<TpaPapers> findTpaPapersByCondition(Map<String,Object> parmMap);

}