package com.elearning.dao.pub;

import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.vo.BasicUserForm;

import java.util.List;
import java.util.Map;

public interface EosorgTEmployeeMapper {
    int deleteByPrimaryKey(Integer operatorID);

    int insert(EosorgTEmployee record);

    int insertSelective(EosorgTEmployee record);

    EosorgTEmployee selectByPrimaryKey(Integer operatorID);

    int updateByPrimaryKeySelective(EosorgTEmployee record);

    int updateByPrimaryKey(EosorgTEmployee record);

    List<BasicUserForm> findBasicUserInfoByCondition(Map<String,Object> condition);

    List<EosorgTEmployee> findByEmpCode(String empCode);


}
