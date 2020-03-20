package com.elearning.service.plan;

import com.elearning.common.Constants;
import com.elearning.common.ServiceResponse;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.dao.coursemanage.ItemInfoMapper;
import com.elearning.dao.plan.TpTrainPlanDetailMapper;
import com.elearning.dao.pub.CourseMapper;
import com.elearning.dao.pub.DDictionaryMapper;
import com.elearning.pojo.coursemanage.ItemInfo;
import com.elearning.pojo.plan.TpTrainPlanDetail;
import com.elearning.pojo.plan.TpTrainPlanDetailWithBLOBs;
import com.elearning.pojo.pub.Course;
import com.elearning.pojo.pub.Train;
import com.elearning.pojo.pub.TrainWithBLOBs;
import com.elearning.service.pub.IExportService;
import com.elearning.util.DateTimeUtil;
import com.elearning.util.PropertiesUtil;
import com.elearning.util.ToolsUtil;
import com.elearning.util.ZipUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;



@Service("tpTrainPlanDetailService")
public class TpTrainPlanDetailImpl implements ITpTrainPlanDetailService{


    @Autowired
    private TpTrainPlanDetailMapper tpTrainPlanDetailMapper;

    @Override
    public TpTrainPlanDetailWithBLOBs findById(Long ID){
        return this.tpTrainPlanDetailMapper.selectByPrimaryKey(ID);
    }


    @Override
    public int updateByPrimaryKeySelective(TpTrainPlanDetailWithBLOBs record){
        return this.tpTrainPlanDetailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public TrainWithBLOBs castToTrain(TpTrainPlanDetail tpTrainPlanDetail){

        TrainWithBLOBs result=new TrainWithBLOBs();
        result.setIsPlaned(Byte.valueOf("1"));
        result.setTrainPlanID(tpTrainPlanDetail.getID());
        result.setTrainID(tpTrainPlanDetail.getTrainCode());
        result.setTrainName(tpTrainPlanDetail.getTrainName());
        result.setTrainPlanName(tpTrainPlanDetail.getTrainName());
        result.setIsStationTrain(false);
        result.setIsNoted(tpTrainPlanDetail.getIsNoted());
        result.setTrainTypeID(tpTrainPlanDetail.getTrainTypeID());
        result.setSubTrainTypeID(tpTrainPlanDetail.getSubTrainTypeID());
        result.setProgramNo(0);
        result.setStartTime(tpTrainPlanDetail.getStartTime());
        result.setEndTime(tpTrainPlanDetail.getEndTime());
        result.setTrainWay(2);//设置培训方式为混合培训
        result.setSponsorName(tpTrainPlanDetail.getSponsorName());
        result.setOrganizerName(tpTrainPlanDetail.getOrganizerName());
        result.setTelephone(tpTrainPlanDetail.getTelephone());
        result.setOrganizerEmail(tpTrainPlanDetail.getOrganizerEmail());
        result.setClassHour(tpTrainPlanDetail.getClassHour());
        result.setDays(tpTrainPlanDetail.getDays());
        //result.setTrainingContent(tpTrainPlanDetail.getTrainContent());
        result.setStationId(tpTrainPlanDetail.getStationId());
        result.setAttendants(tpTrainPlanDetail.getAttendants());
        result.setAttendantCount(tpTrainPlanDetail.getAttendantCount());
        //result.setTrainGoal(tpTrainPlanDetail.getTrainGoal());
        result.setIfBJ(tpTrainPlanDetail.getIfBJ());
        result.setLocation(tpTrainPlanDetail.getLocation());
        result.setIsEnrolled(true);//表示正在报名
        result.setIsIssued(false);
        result.setSponsorID(tpTrainPlanDetail.getSponsorID());
        result.setOrganizerID(tpTrainPlanDetail.getOrganizerID());
        result.setOrgId(tpTrainPlanDetail.getOrgID());
        result.setYear(tpTrainPlanDetail.getYear());
        result.setOperatorId(tpTrainPlanDetail.getOperatorId());
        result.setOpenScope(tpTrainPlanDetail.getOpenScope());
        result.setCreamProject(tpTrainPlanDetail.getCreamProject());
        result.setTrainMode(tpTrainPlanDetail.getTrainMode());
        result.setTenantId(tpTrainPlanDetail.getTenantId());
        result.setFee(tpTrainPlanDetail.getFee());
        result.setComment(tpTrainPlanDetail.getComment());
        result.setFeeChannel(tpTrainPlanDetail.getFeeChannel());
        result.setApproveStatusName(tpTrainPlanDetail.getApproveStatusName());
        result.setCad_report(tpTrainPlanDetail.getCad_report());
        result.setProgramStartTime(result.getStartTime());
        result.setProgramEndTime(result.getEndTime());
        result.setItemType(0);
        return result;

    }


}
