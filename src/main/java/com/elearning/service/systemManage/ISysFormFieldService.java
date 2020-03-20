package com.elearning.service.systemManage;


import com.elearning.pojo.systemManage.SysFormField;
import com.elearning.pojo.systemManage.SysFormFieldKey;

import java.util.HashMap;
import java.util.TreeMap;

public interface ISysFormFieldService {

    SysFormField selectByPrimaryKey(SysFormFieldKey key);

    TreeMap<Integer,HashMap<String,String>> getSysFormfieldSeqsMap(Integer tenantId, Integer formId);

    HashMap<String,HashMap<String,String>> getSysFormfieldAttributesMap(Integer tenantId,Integer formId);


}
