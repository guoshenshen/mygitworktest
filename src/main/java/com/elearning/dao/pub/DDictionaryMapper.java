package com.elearning.dao.pub;

import com.elearning.pojo.pub.DDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DDictionaryMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(DDictionary record);

    int insertSelective(DDictionary record);

    DDictionary selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(DDictionary record);

    int updateByPrimaryKey(DDictionary record);


    /**
     * 通过code以及parentCode获取字典
     * @param code
     * @param parentCode
     * @return
     */
    DDictionary getDDictionaryMapperByCode(@Param("code") String code,
                                           @Param("parentCode")String parentCode);

    List<DDictionary> getByParentCode(@Param("parentCode")String parentCode);

    List<DDictionary> findChildren(Map<String,Object> parmMap);

    List<DDictionary> findByCode(@Param("code")String code);

    List<DDictionary> find(Map<String,Object> parmMap);

    List<DDictionary> findTrainByCodeAndTenantId(Map<String,Object> parmMap);

    List<DDictionary> findTrainByCodeAndTenantIdReturnDDictionary(Map<String,Object> parmMap);

    List<DDictionary> getDDictionaryByCodeAndParentCode(Map<String,Object> parmMap);



}