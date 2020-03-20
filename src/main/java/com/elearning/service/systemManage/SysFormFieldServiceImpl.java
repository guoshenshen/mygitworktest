package com.elearning.service.systemManage;

import com.elearning.dao.systemManage.SysFormFieldMapper;
import com.elearning.pojo.systemManage.SysFormField;
import com.elearning.pojo.systemManage.SysFormFieldKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.*;

@Service("sysFormFieldServic")
public class SysFormFieldServiceImpl implements ISysFormFieldService{

    @Autowired
    private SysFormFieldMapper sysFormFieldMapper;

    public SysFormField selectByPrimaryKey(SysFormFieldKey key){

        return sysFormFieldMapper.selectByPrimaryKey(key);
    }


    public TreeMap<Integer,HashMap<String,String>> getSysFormfieldSeqsMap(Integer tenantId, Integer formId){
        TreeMap<Integer,HashMap<String,String>> formfieldMap=new TreeMap<Integer,HashMap<String,String>>();

        List<SysFormField> sysFormFieldList = this.sysFormFieldMapper.getSysFormfieldSeqsMap(formId);

        Iterator<SysFormField> itr=sysFormFieldList.iterator();
        while(itr.hasNext()){
            SysFormField sysFormfield=itr.next();
            String fieldAttribute = sysFormfield.getFieldAttribute();
            Integer key = null;
            HashMap<String,String> innerMap = new HashMap<>();
            if(selectByPrimaryKey(new SysFormFieldKey(formId,tenantId,fieldAttribute))==null){
                innerMap.put("fieldName", sysFormfield.getFieldName());
                if(sysFormfield.getVisible()!=null){
                    innerMap.put("visible",sysFormfield.getVisible().toString());
                }
                if(sysFormfield.getColspan()!=null){
                    innerMap.put("colspan",sysFormfield.getColspan().toString());
                }
                if(sysFormfield.getDetailVisible()!=null){
                    innerMap.put("detailVisible",sysFormfield.getDetailVisible().toString());
                }
                if(sysFormfield.getStudentVisible()!=null){
                    innerMap.put("studentVisible",sysFormfield.getStudentVisible().toString());
                }
                if(sysFormfield.getQueryVisible()!=null){
                    innerMap.put("queryVisible",sysFormfield.getQueryVisible().toString());
                }
                if(sysFormfield.getDefaultValue()!=null){
                    innerMap.put("defaultValue",sysFormfield.getDefaultValue().toString());
                }
                if(sysFormfield.getValueFormat()!=null){
                    innerMap.put("valueFormat",sysFormfield.getValueFormat().toString());
                }
                if(sysFormfield.getDataResource()!=null){
                    innerMap.put("dataResource",sysFormfield.getDataResource().toString());
                }
                key = sysFormfield.getFieldSeq();
            }else{
                SysFormField _sysFormfield = selectByPrimaryKey(new SysFormFieldKey(formId,tenantId,fieldAttribute));
                innerMap.put("fieldName", _sysFormfield.getFieldName());
                if(_sysFormfield.getVisible()!=null){
                    innerMap.put("visible",_sysFormfield.getVisible().toString());
                }
                if(_sysFormfield.getColspan()!=null){
                    innerMap.put("colspan",_sysFormfield.getColspan().toString());
                }
                if(_sysFormfield.getDetailVisible()!=null){
                    innerMap.put("detailVisible",_sysFormfield.getDetailVisible().toString());
                }
                if(_sysFormfield.getStudentVisible()!=null){
                    innerMap.put("studentVisible",_sysFormfield.getStudentVisible().toString());
                }
                if(_sysFormfield.getQueryVisible()!=null){
                    innerMap.put("queryVisible",_sysFormfield.getQueryVisible().toString());
                }
                if(sysFormfield.getDefaultValue()!=null){
                    innerMap.put("defaultValue",_sysFormfield.getDefaultValue().toString());
                }
                if(_sysFormfield.getValueFormat()!=null){
                    innerMap.put("valueFormat",_sysFormfield.getValueFormat().toString());
                }
                if(_sysFormfield.getDataResource()!=null){
                    innerMap.put("dataResource",_sysFormfield.getDataResource().toString());
                }
                key = _sysFormfield.getFieldSeq();
            }
            innerMap.put("fieldAttribute",fieldAttribute);
            formfieldMap.put(key, innerMap);
        }
        return formfieldMap;
    }

    public HashMap<String, HashMap<String, String>> getSysFormfieldAttributesMap(Integer tenantId, Integer formId) {

        HashMap<String,HashMap<String,String>> formfieldMap=new HashMap<>();

        List<SysFormField> sysFormFieldList = this.sysFormFieldMapper.getSysFormfieldSeqsMap(formId);

        if(sysFormFieldList.size()>0){

            Iterator itr=sysFormFieldList.iterator();
            while(itr.hasNext()){
                SysFormField sysFormfield=(SysFormField)itr.next();
                //SysFormfieldId id = sysFormfield.getId();
                String fieldAttribute = sysFormfield.getFieldAttribute();
                HashMap<String,String> innerMap = new HashMap<>();
                if(this.sysFormFieldMapper.selectByPrimaryKey(new SysFormFieldKey(formId,tenantId,fieldAttribute))==null){
                    innerMap.put("fieldName", sysFormfield.getFieldName());
                    innerMap.put("fieldSeq",sysFormfield.getFieldSeq().toString());
                    if(sysFormfield.getVisible()!=null)
                        innerMap.put("visible",sysFormfield.getVisible().toString());
                    if(sysFormfield.getColspan()!=null)
                        innerMap.put("colspan",sysFormfield.getColspan().toString());
                    if(sysFormfield.getDetailVisible()!=null)
                        innerMap.put("detailVisible",sysFormfield.getDetailVisible().toString());
                    if(sysFormfield.getStudentVisible()!=null)
                        innerMap.put("studentVisible",sysFormfield.getStudentVisible().toString());
                    if(sysFormfield.getQueryVisible()!=null)
                        innerMap.put("queryVisible",sysFormfield.getQueryVisible().toString());
                    if(sysFormfield.getDefaultValue()!=null){
                        innerMap.put("defaultValue",sysFormfield.getDefaultValue().toString());
                    }
                    if(sysFormfield.getValueFormat()!=null){
                        innerMap.put("valueFormat",sysFormfield.getValueFormat().toString());
                    }
                    if(sysFormfield.getDataResource()!=null){
                        innerMap.put("dataResource",sysFormfield.getDataResource().toString());
                    }
                }else{
                    SysFormField _sysFormfield = this.sysFormFieldMapper.selectByPrimaryKey(new SysFormFieldKey(formId,tenantId,fieldAttribute));
                    innerMap.put("fieldName", _sysFormfield.getFieldName());
                    innerMap.put("fieldSeq",_sysFormfield.getFieldSeq().toString());
                    if(_sysFormfield.getVisible()!=null)
                        innerMap.put("visible",_sysFormfield.getVisible().toString());
                    if(_sysFormfield.getColspan()!=null)
                        innerMap.put("colspan",_sysFormfield.getColspan().toString());
                    if(_sysFormfield.getDetailVisible()!=null)
                        innerMap.put("detailVisible",_sysFormfield.getDetailVisible().toString());
                    if(_sysFormfield.getStudentVisible()!=null)
                        innerMap.put("studentVisible",_sysFormfield.getStudentVisible().toString());
                    if(_sysFormfield.getQueryVisible()!=null)
                        innerMap.put("queryVisible",_sysFormfield.getQueryVisible().toString());
                    if(_sysFormfield.getDefaultValue()!=null){
                        innerMap.put("defaultValue",_sysFormfield.getDefaultValue().toString());
                    }
                    if(_sysFormfield.getValueFormat()!=null){
                        innerMap.put("valueFormat",_sysFormfield.getValueFormat().toString());
                    }
                    if(_sysFormfield.getDataResource()!=null){
                        innerMap.put("dataResource",_sysFormfield.getDataResource().toString());
                    }
                }
                formfieldMap.put(fieldAttribute, innerMap);
            }
        }

        return formfieldMap;

    }




}
