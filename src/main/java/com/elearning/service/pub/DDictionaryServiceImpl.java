package com.elearning.service.pub;

import com.elearning.dao.pub.DDictionaryMapper;
import com.elearning.pojo.pub.DDictionary;
import com.elearning.service.pub.IDDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dDictionaryService")
public class DDictionaryServiceImpl implements IDDictionaryService {

    @Autowired
    private DDictionaryMapper dDictionaryMapper;

    public DDictionary selectByPrimaryKey(Integer ID){

        return dDictionaryMapper.selectByPrimaryKey(ID);
    }

    @Override
    public List<DDictionary> getByParentCode(String parentCode) {
        return dDictionaryMapper.getByParentCode(parentCode);
    }

    @Override
    public DDictionary getDDictionaryMapperByCode(String code, String parentCode) {
        return dDictionaryMapper.getDDictionaryMapperByCode(code,parentCode);
    }

    @Override
    public List<DDictionary> findChildren(String code, Integer tenantId, Integer visible) {

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("code",code);
        parmMap.put("tenantId",tenantId);
        parmMap.put("visible",visible);


        return dDictionaryMapper.findChildren(parmMap);
    }

    @Override
    public List<DDictionary> findByCode(String code) {

        return dDictionaryMapper.findByCode(code);
    }

    @Override
    public List<DDictionary> find(String code, String parentCode) {
        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("code",code);
        parmMap.put("parentCode",parentCode);

        return dDictionaryMapper.find(parmMap);
    }

    @Override
    public List<DDictionary> findTrainByCodeAndTenantId(String code, Integer tenantId) {

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("code",code);
        parmMap.put("tenantId",tenantId);

        return dDictionaryMapper.findTrainByCodeAndTenantId(parmMap);
    }
    @Override
    public DDictionary findTrainByCodeAndTenantIdReturnDDictionary(String code, Integer tenantId) {

        Map<String,Object> parmMap = new HashMap<>();
        parmMap.put("code",code);
        parmMap.put("tenantId",tenantId);

        List<DDictionary> dDictionaryList = this.dDictionaryMapper.findTrainByCodeAndTenantIdReturnDDictionary(parmMap);

        if(dDictionaryList.size()>0){
            return dDictionaryList.get(0);
        } else{
            return null;
        }
    }

    @Override
    public List<DDictionary> findByCodeAndTenantIdAndVisible(String code, Integer tenantId, Integer visible) {
        List<DDictionary> dDictionaryList = this.findChildren(code,tenantId,visible);

        if(dDictionaryList.size()>0){
            return 	this.findChildren(code,tenantId,1);
        } else{
            return 	this.findChildren(code,0,1);
        }

    }
    @Override
    public String getDDictionaryByCodeAndParentCode(String code, String parentCode){
        Map<String,Object> parm = new HashMap<>();
        parm.put("code",code);
        parm.put("parentCode",parentCode);

        List<DDictionary> dDictionaryList = this.dDictionaryMapper.getDDictionaryByCodeAndParentCode(parm);
        if(dDictionaryList!=null && dDictionaryList.size()>0){
            return dDictionaryList.get(0).getName();
        }else{
            return "未查询到结果";
        }


    }


}
