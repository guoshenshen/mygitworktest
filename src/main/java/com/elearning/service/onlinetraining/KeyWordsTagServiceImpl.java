package com.elearning.service.onlinetraining;

import com.elearning.dao.onlinetraining.KeyWordsTagMapper;
import com.elearning.pojo.onlinetraining.KeyWordsTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("keyWordsTagService")
public class KeyWordsTagServiceImpl implements IKeyWordsTagService {

    @Autowired
    private KeyWordsTagMapper keyWordsTagMapper;

    @Override
    public List<KeyWordsTag> selectByKeyWords(String keyWords){
        return this.keyWordsTagMapper.selectByKeyWords(keyWords);
    }

    @Override
    public int save(KeyWordsTag record){
        return this.keyWordsTagMapper.insertSelective(record);
    }








}
