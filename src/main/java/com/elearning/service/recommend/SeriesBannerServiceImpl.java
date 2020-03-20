package com.elearning.service.recommend;

import com.elearning.dao.recommend.SeriesBannerMapper;
import com.elearning.pojo.recommend.SeriesBanner;
import com.elearning.service.recommend.ISeriesBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("seriesBannerService")
public class SeriesBannerServiceImpl implements ISeriesBannerService {

    @Autowired
    private SeriesBannerMapper seriesBannerMapper;

    public SeriesBanner selectByPrimaryKey(Integer id){

        return seriesBannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SeriesBanner> findSeriesBannerByCondition(Map<String, Object> map) {
        return seriesBannerMapper.findSeriesBannerByCondition(map);
    }

    @Override
    public int delete(SeriesBanner seriesBanner) {
        return seriesBannerMapper.deleteByPrimaryKey(seriesBanner.getId());
    }

    @Override
    public int insert(SeriesBanner seriesBanner) {
        return seriesBannerMapper.insertSelective(seriesBanner);
    }


}
