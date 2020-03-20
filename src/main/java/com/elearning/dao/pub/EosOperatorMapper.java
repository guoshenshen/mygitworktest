package com.elearning.dao.pub;

import com.elearning.pojo.pub.EosOperator;
import com.elearning.vo.BasicUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EosOperatorMapper {
    int deleteByPrimaryKey(Integer operatorID);

    int insert(EosOperator record);

    int insertSelective(EosOperator record);

    EosOperator selectByPrimaryKey(Integer operatorID);

    EosOperator findByUserId(String userId);

    int updateByPrimaryKeySelective(EosOperator record);

    int updateByPrimaryKey(EosOperator record);

    List<EosOperator> isValid(@Param("userId") String username,@Param("password") String password);

    List<BasicUserVo> findByConditionSQL(Map<String,Object> parmMap);

    List<EosOperator> findByOperatorName(String operatorName);


}