package com.elearning.dao.pub;

import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.vo.pub.BasicOrgForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EosorgTOrganizationMapper {
    int deleteByPrimaryKey(Integer orgID);

    int insert(EosorgTOrganization record);

    int insertSelective(EosorgTOrganization record);

    EosorgTOrganization selectByPrimaryKey(Integer orgID);

    int updateByPrimaryKeySelective(EosorgTOrganization record);

    int updateByPrimaryKeyWithBLOBs(EosorgTOrganization record);

    int updateByPrimaryKey(EosorgTOrganization record);

    List<EosorgTOrganization> getListByIds(Map<String,Object> map);


    String  findSecondaryParentOrgId(int orgId);


    /**
     * 根据orgId查询 tenantId 的集合
     * @param orgId
     * @return
     */
    List<Integer> findOrgAndSubTenantIdList(@Param("orgId") Integer orgId);

    /**
     *查询中科院系统标准的院所
     * @param condition
     * @return
     */
    List<BasicOrgForm> findBasicInfoByCondition(Map<String, Object> condition);


    List<EosorgTOrganization> getTenantedOrg(Map<String,Object> map);

    EosorgTOrganization findByTenantId(Integer tenantId);

    List<BasicOrgForm> findBasicInfoByConditionSQL(@Param(value = "sqlQuery") String sqlQuery,
                                                   @Param(value = "orderPageString") String orderPageString);
    List<BasicOrgForm> getSubOrgInfo(Map<String,Object> map);

    List<EosorgTOrganization> getListByOrgNameAndParentOrgIdAndStatus(Map<String, Object> parmMap);

    List<EosorgTOrganization> findOneStepSubOrg(Map<String,Object> map);

    List<Integer> findSubOrgById(int orgID);

}