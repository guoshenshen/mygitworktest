package com.elearning.service.pub;

import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.systemManage.Tenant;
import com.elearning.vo.EosOrgTOrganizationVo;
import com.elearning.vo.pub.BasicOrgForm;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/6/24 10:01
 */
public interface IEosorgTOrganizationService {

    /**
     * 将组织机构表单信息保存至数据库
     * @param eosorgTOrganizationForm
     * @param tenantOrgId
     * @return
     */
    public String save(EosOrgTOrganizationVo eosorgTOrganizationForm,int tenantOrgId);

    /**
     * 将组织机构表单信息更新至数据库
     * @param eosorgTOrganizationForm
     * @param tenantOrgId
     * @return
     */
    public String update(EosOrgTOrganizationVo eosorgTOrganizationForm, Integer tenantOrgId);

    /**
     * 根据id查询机构信息
     * @param orgId
     * @return
     */
    public EosorgTOrganization getEosorgTOrganizationById(Integer orgId);

    /**
     * 与getEosorgTOrganizationById功能一致
     * @param orgId
     * @return
     */
    public EosorgTOrganization findById(Integer orgId);

    /**
     * 依据org记录生成对应的表单信息
     * @param eosorgTOrganization
     * @return
     */
    public EosOrgTOrganizationVo getEosOrgTOrganizationVo(EosorgTOrganization eosorgTOrganization);

    /**
     * 依照一组orgId生成对应的org表单信息队列
     * @param orgIdList
     * @return
     */
    public List<EosOrgTOrganizationVo> findOrgInfoById(List<Integer> orgIdList);

    /**
     * 根据部门orgId获得父机构中的二级机构,例如"中国科学院计算机网络信息中心"就是"中国科学院"
     * Date:2017-05-25
     * author:xiongying
     * 方法二、正则表达式处理字符串的方式
     * 失败：则返回0
     * @param
     * @return
     */
    public int findSecondaryParentOrgId(int orgId);

    /**
     * 根据tenant的orgId查询出所有的tenantId
     * @param map
     */
    public void putSameSecondaryParentTenantIdListInMap(Map<String,Object> map);

    /**
     * 根据 orgId 公开范围查询 orgSEQ
     * @param orgId
     * @param openScope
     * @return
     */
    public String getOpenOrgSEQ(Integer orgId, Short openScope);

    /**
     * 根据中科院标准机构排行顺序显示各个院所
     * @param condition
     * @return
     */
    public List<BasicOrgForm> findOrderTenantOrgByCondition(Map<String,Object> condition);


    /***********************************涉及部门是否独立*****************************************/
    /**
     * 向上查找到第一个独立组织机构（包括自身）
     * @param orgid
     * @return
     */
    public Integer findOrgId(Integer orgid);

    /**
     * 向上查找到第一个独立组织机构（包括自身）
     * @param org
     * @return
     */
    public EosorgTOrganization findOrg(EosorgTOrganization org);


    /**
     * 返回机构的上一级独立机构的id
     * @param orgd
     * @return
     */
    public Integer findParentOrgId(Integer orgd);


    /**
     * 返回机构的上一级独立机构
     * @param org
     * @return
     */
    public EosorgTOrganization findParentOrg(EosorgTOrganization org);

    /*******************************************************************************************/
    public BasicOrgForm getSimpleOrgInfo(EosorgTOrganization eosorgTOrganization);


    /**
     * 根据租户id获取某该租户对应的组织机构
     * @param tenantId
     * @return
     */
    public EosorgTOrganization findByTenantId(Integer tenantId);


    /**
     * 获取某机构在特定租户下的机构名称
     * 若该机构隶属于该租户,则显示该机构名称
     * 若该机构不属于参数租户,则显示该机构隶属一级单位（如研究所，研究院）名称
     * @param targetOrg
     * @param tenantId
     * @return
     */
    public String getStandardOrgName(EosorgTOrganization targetOrg, Integer tenantId);

    /*************************************涉及租户查询接口***********************************************/

    /**
     * 根据单位标识返回所属租户信息
     * 若单位记录的tenantId未保存租户标识tenantId,
     * 则启动数据库查询操作获取对应租户并将租户tenantId字段存储在org记录中
     * @param org
     * @return
     */
    public Tenant findTenant(EosorgTOrganization org);

    /**
     * 根据单位标识返回所属租户信息
     * 若单位记录的tenantId未保存租户标识tenantId,
     * 则启动数据库查询操作获取对应租户并将租户tenantId字段存储在org记录中
     * @param orgid
     * @return
     */
    public Tenant findTenant(Integer orgid);


    /**
     * 获取某机构所属租户的名称
     * @param targetOrg
     * @return
     */
    public String getTenantOrgName(EosorgTOrganization targetOrg);

    /**
     * 返回部门所在租户id
     * @param org
     * @return
     */
    public Integer findTenantId(EosorgTOrganization org);

    /**
     * 返回部门所在租户id
     * @param orgid
     * @return
     */
    public Integer findTenantId(Integer orgid);

    /************************************获取BasicOrgForm（start）**********************************************************************/

    public List<BasicOrgForm> findByConditionSQL(String sqlQuery,Map<String,Object>  condition,String orderPageString);

    public Collection<? extends BasicOrgForm> getSubOrgInfo(Map<String, Object> condition);

    public List<BasicOrgForm> findByOrgCode(String orgCode, EosorgTOrganization tenantOrg);

    public List<BasicOrgForm> findBasicOrgInfoById(List<Integer> orgIdList);

    /**************************************获取BasicOrgForm（end）********************************************************************/



    public EosorgTOrganization getEosorgTOrganization(EosOrgTOrganizationVo eosorgTOrganizationForm);

    /**
     * 根据org 数据记录生成对应表单
     * @param eosorgTOrganization
     * @return
     */
    public EosOrgTOrganizationVo getOrgForm(EosorgTOrganization eosorgTOrganization);


    //----------------------gss--add------------------------------

    /**
     * 从Excel导入--->下载导入示例中用到
     * @param orgName
     * @param parentOrgId
     * @param status
     * @return
     */
    public List<EosorgTOrganization> getListByOrgNameAndParentOrgIdAndStatus( String orgName,Long parentOrgId,Boolean status);

    /**
     * 培训班管理--预览--培训总结（getTrainingSummary该方法中使用）
     * @param orgIds
     * @return
     */
    List<EosorgTOrganization> findOneStepSubOrg(int[] orgIds);

    /**
     *  获取当前机构及其子机构列表  ---  培训班管理--考试管理中用到
     * @param orgid
     * @return
     */
    List<Integer> getorgidlist(int orgid);

}
