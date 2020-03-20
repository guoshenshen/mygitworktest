package com.elearning.service.pub;


import com.elearning.pojo.pub.EosOperator;
import com.elearning.pojo.pub.EosorgTEmployee;
import com.elearning.pojo.pub.EosorgTOrganization;
import com.elearning.pojo.pub.UserForm;
import com.elearning.pojo.systemManage.VUserInfo;
import com.elearning.vo.BasicUserForm;

import java.util.List;
import java.util.Map;

public interface IEosorgTEmployeeService {
    
    public EosorgTEmployee findById(Integer operatorID);


    /**
     * 针对employee信息可能仅包含数据库employee对应字段,不包含关联的operator信息和organization信息
     * 本方法检测employee的operator成员和organization成员是否有信息，无信息，则调用数据库进行填充
     * @param eosorgTEmployee
     */
    public void getFullInfoByBasicInfo(EosorgTEmployee eosorgTEmployee);

    public UserForm findByUserId(Integer operatorId);

    public UserForm findByUserId(Integer operatorId, Integer adminTenantId);

    /**
     * 根据检索条件获取人员详细信息（包含employee,operator,）
     * @param condition
     * @return
     */
    public List<VUserInfo> getFullInfoList(Map<String, Object> condition);

    public List<BasicUserForm> findBasicUserInfoByCondition(String sqlQuery, Integer tenantId, Map<String, Object> condition,String orderPageString);

    /**
     * 查询某组织及该组织下的全部学员基本信息
     * @param orgId
     * @param tenantId
     * @return
     */
    public List<BasicUserForm> findBasicUserInfoByOrgId(Integer orgId, Integer tenantId,Map<String,Object> condition);


    /**
     * 根据operatorId数组获取用户的基本信息（邮箱、所属部门名称、人名、电话等）
     * 对属于不同租户的成员，部门显示为其所属租户名称，属于相同租户的成员，部门为其所属具体部门
     * @param operatorIdList
     * @param tenantId
     * @return
     */
    public List<BasicUserForm> findBasicUserInfoById(List<Integer> operatorIdList,Integer tenantId);

    /**
     * 查询用户所属机构
     * @param employee
     * @param isOrgStatus  true:查询独立机构   false查询非独立机构
     * @return
     */
    public EosorgTOrganization findOrg(EosorgTEmployee employee,Boolean isOrgStatus);


    /**
     * 是否可以通过表单编辑或excel表格导入添加/修改用户
     * @param userForm
     * @param orgseq  该人员隶属的具有独立人员编号的机构orgseq
     * @return status:是否可以true/false   msg:原因说明
     */
    public Map<String,Object> canUpdateUser(UserForm userForm,String orgseq);

    /**
     * 通过表格编辑或excel表格导入添加/修改用户
     * @param userForm
     * @param orgseq
     * @return
     */
    public Map<String,Object> updateUser(UserForm userForm,String orgseq,Integer operatorId);

    /**
     * 修改employee信息 (注意，如果涉及到了orgId修改，请使用deepUpdate)
     * @param employee
     * @param adminOperatorId 修改人
     * @return
     */
    public Integer update(EosorgTEmployee employee,Integer adminOperatorId);


    /**
     * 修改operator信息，同时修改最后修改人最后修改时间到employee中，修改前请务必根据情况进行canUpdateUser校验
     * @param operator
     * @param adminOperatorId
     * @return
     */
    public Integer update(EosOperator operator,Integer adminOperatorId);

    /**
     * 深度修改employee信息
     * 如果用户的orgId发生调整，可能该用户的角色、学习记录都牵扯到相应调整，因此当发生orgId变化时，应当调用此方法
     * @param employee
     * @param adminOperatorId
     * @return
     */
    public Integer  deepUpdate(EosorgTEmployee employee,Integer adminOperatorId);


    /**
     * 添加employee,同时添加对应的operator,添加前请务必进行canUpdateUser校验
     * @param employee 对应operator属性不得为空
     * @param adminOperatorId 修改人
     * @return
     */
    public Integer add(EosorgTEmployee employee,Integer adminOperatorId);


    /**
     *  考试管理--成绩管理
     * @param empCode
     * @return
     */
    List<EosorgTEmployee> findByEmpCode(String empCode);


}
