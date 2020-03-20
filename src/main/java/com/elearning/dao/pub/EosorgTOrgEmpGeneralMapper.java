package com.elearning.dao.pub;

import com.elearning.pojo.pub.EosorgTOrgEmpGeneral;

public interface EosorgTOrgEmpGeneralMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EosorgTOrgEmpGeneral record);

    int insertSelective(EosorgTOrgEmpGeneral record);

    EosorgTOrgEmpGeneral selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EosorgTOrgEmpGeneral record);

    int updateByPrimaryKey(EosorgTOrgEmpGeneral record);
}