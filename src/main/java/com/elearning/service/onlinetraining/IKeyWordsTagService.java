package com.elearning.service.onlinetraining;


import com.elearning.pojo.onlinetraining.KeyWordsTag;

import java.util.List;

public interface IKeyWordsTagService {


    List<KeyWordsTag> selectByKeyWords(String keyWords);

    int save(KeyWordsTag record);




}
