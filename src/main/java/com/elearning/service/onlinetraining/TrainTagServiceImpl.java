package com.elearning.service.onlinetraining;

import com.elearning.dao.onlinetraining.KeyWordsTagMapper;
import com.elearning.dao.onlinetraining.TrainTagMapper;
import com.elearning.pojo.onlinetraining.KeyWordsTag;
import com.elearning.pojo.onlinetraining.TrainTagKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainTagService")
public class TrainTagServiceImpl implements ITrainTagService {

    @Autowired
    private TrainTagMapper trainTagMapper;

    @Autowired
    private IKeyWordsTagService keyWordsTagService;

    @Override
    public void saveorUpdateTraintags(Integer trainId, String traintags){

        if(traintags != null){
            //1.删除此培训已有标签关联链接
            List<TrainTagKey> trainTagKeyList = this.trainTagMapper.findByTrainID(trainId.longValue());
            if(trainTagKeyList != null && trainTagKeyList.size() > 0){
                for(TrainTagKey trainTagKey:trainTagKeyList){
                    this.trainTagMapper.deleteByPrimaryKey(trainTagKey);
                }
            }
            String[] trainTags = traintags.split(";");
            if(trainTags!=null&&trainTags.length>0)
                for(String tag:trainTags){
                    if(!tag.equals("")){

                        int tagId = 0;

                        List<KeyWordsTag> keyWordsTagList = this.keyWordsTagService.selectByKeyWords(tag);
                        if(keyWordsTagList != null && keyWordsTagList.size()>0){
                            tagId = keyWordsTagList.get(0).getTagID();
                        }else{
                            KeyWordsTag example = new KeyWordsTag();
                            example.setKeyWords(tag);
                            tagId = this.keyWordsTagService.save(example);
                        }

                        TrainTagKey trainTagKey = new TrainTagKey();
                        trainTagKey.setTagID(tagId);
                        trainTagKey.setTrainID(trainId.intValue());

                        if(this.trainTagMapper.findByTrainIDAndTagID(trainTagKey) == null){
                            this.trainTagMapper.insert(trainTagKey);
                        }
                    }
                }
        }

    }








}
