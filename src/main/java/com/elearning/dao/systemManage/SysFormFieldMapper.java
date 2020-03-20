package com.elearning.dao.systemManage;

import com.elearning.pojo.systemManage.SysFormField;
import com.elearning.pojo.systemManage.SysFormFieldKey;

import java.util.List;
import java.util.Map;

public interface SysFormFieldMapper {
    int deleteByPrimaryKey(SysFormFieldKey key);

    int insert(SysFormField record);

    int insertSelective(SysFormField record);

    SysFormField selectByPrimaryKey(SysFormFieldKey key);

    int updateByPrimaryKeySelective(SysFormField record);

    int updateByPrimaryKey(SysFormField record);

    List<SysFormField> getSysFormfieldSeqsMap(Integer formId);


}