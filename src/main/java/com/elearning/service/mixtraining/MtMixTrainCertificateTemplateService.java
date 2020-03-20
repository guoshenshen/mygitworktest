package com.elearning.service.mixtraining;

import com.elearning.dao.mixtraining.MtMixTrainCertificateTemplateMapper;
import com.elearning.pojo.mixtraining.MtMixTrainCertificateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("mtMixTrainCertificateTemplateService")
public class MtMixTrainCertificateTemplateService implements IMtMixTrainCertificateTemplateService{

    @Autowired
    private MtMixTrainCertificateTemplateMapper mtMixTrainCertificateTemplateMapper;

    @Override
    public MtMixTrainCertificateTemplate selectByPrimaryKey(Integer id){

        return this.mtMixTrainCertificateTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MtMixTrainCertificateTemplate> findByOperatorId(Integer operatorId){

        return this.mtMixTrainCertificateTemplateMapper.findByOperatorId(operatorId);
    }


}
