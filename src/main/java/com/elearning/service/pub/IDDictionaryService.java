package com.elearning.service.pub;


import com.elearning.pojo.pub.DDictionary;

import java.util.List;

public interface IDDictionaryService {

    DDictionary selectByPrimaryKey(Integer ID);

    List<DDictionary> getByParentCode(String parentCode);

    DDictionary getDDictionaryMapperByCode(String code, String parentCode);

    List<DDictionary> findChildren(String code, Integer tenantId, Integer visible);

    List<DDictionary> findByCode(String code);

    List<DDictionary> find(String code, String parentCode);

    List<DDictionary> findTrainByCodeAndTenantId(String code, Integer tenantId);

    DDictionary findTrainByCodeAndTenantIdReturnDDictionary(String code, Integer tenantId);

    List<DDictionary> findByCodeAndTenantIdAndVisible(String code, Integer tenantId, Integer visible);

    String getDDictionaryByCodeAndParentCode(String code, String parentCode);


}
