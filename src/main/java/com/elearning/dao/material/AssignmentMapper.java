package com.elearning.dao.material;

import com.elearning.pojo.material.Assignment;

import java.util.List;
import java.util.Map;

public interface AssignmentMapper {
    int insert(Assignment record);

    int insertSelective(Assignment record);

    int deleteByPrimaryKey(Long id);

    Assignment findById(Long id);

    int updateByPrimaryKeySelective(Assignment record);

    List<Assignment> getAssignmentByIdReturnList(Long id);

    List<Assignment> getListByTypeAndResourceId(Map<String,Object> parm);




}