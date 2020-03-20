package com.elearning.service.mixtraining;

import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.pojo.systemManage.PictureBase;
import com.elearning.service.onlinetraining.IOnlineTrainingService;
import com.elearning.service.systemManage.IPictureBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service("mtMixTrainTopbandService")
public class MtMixTrainTopbandService implements IMtMixTrainTopbandService{

    @Autowired
    private IOnlineTrainingService onlineTrainingService;

    @Autowired
    private IPictureBaseService pictureBaseService;

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void selectTopband(HttpServletRequest request){

        Integer trainId = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        Integer topbandId = Integer.valueOf(request.getParameter("id").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        train.setTopbandId(topbandId);
        this.onlineTrainingService.updateByPrimaryKeyWithBLOBs(train);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void deleteTrainTopband(HttpServletRequest request){

        Integer trainId = Integer.valueOf(request.getSession().getAttribute("trainId").toString());
        TrainWithBLOBs train = this.onlineTrainingService.findById(trainId);
        train.setTopbandId(0);
        this.onlineTrainingService.updateByPrimaryKeyWithBLOBs(train);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void deleteSelfTopband(HttpServletRequest request){

        String message = "";
        Integer topbandId = Integer.valueOf(request.getParameter("id").toString());

        List<TrainWithBLOBs> trainWithBLOBsList = this.onlineTrainingService.getListByTopbandId(topbandId);

        if(trainWithBLOBsList.size() == 0){
            PictureBase picturebase = this.pictureBaseService.findById(topbandId);
            if(picturebase != null){
                this.pictureBaseService.deleteByPrimaryKey(topbandId);
                message = "成功删除标题背景图片";
            }else{
                message = "删除数据错误，请联系管理员";
            }
        }else{
            message = "标题背景图片已使用，无法删除";
        }
        request.setAttribute("message", message);

    }



}
