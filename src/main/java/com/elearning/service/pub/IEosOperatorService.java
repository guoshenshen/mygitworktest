package com.elearning.service.pub;

import com.elearning.pojo.pub.EosOperator;
import com.elearning.vo.BasicUserVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * last edited by zq@cnic.cn
 * 注意operator由于和employee高度相关，因此在本接口中不开放operator保存、更新相应方法，相应方法请参考employee对外接口，也请不要添加
 */
public interface IEosOperatorService {

    public EosOperator selectByPrimaryKey(Integer operatorID);

    /**
     * @author zq
     * @createDate 2019.08.01
     * 根据operatorId查询DB并返回operator的原始信息
     * @param operatorId
     * @return
     */
    public EosOperator findById(Integer operatorId);

    public EosOperator findByUserId(String userId);

    public Boolean isValid(String userId,String password);

    public void getFullInfoByBasicInfo(EosOperator operator);

    public Boolean validateEnter(HttpServletRequest request);

    public List<BasicUserVo> findBasicUserInfoById(List<Integer> operatorIdList, Integer tenantId);

    List<EosOperator> findByOperatorName(String operatorName);


}
