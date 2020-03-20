package com.elearning.service.coursemanage;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.coursemanage.UscLectureFileMapper;
import com.elearning.pojo.coursemanage.UscLectureFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/17 14:05
 */
@Service("uscLectureFileService")
public class UscLectureFileServiceImp implements IUscLectureFileService{

    @Autowired
    private UscLectureFileMapper uscLectureFileMapper;

    /**
     * 相关资料
     * @param courseId
     * @return
     */
    @Override
    public ServiceResponse<List<UscLectureFile>> findAllLectureFile(Long courseId) {
        List<UscLectureFile> lectureFileList = uscLectureFileMapper.getUscLectureFileByCourseId(courseId);
        return ServiceResponse.createBySuccess(lectureFileList);
    }

    @Override
    public int insertLectureFile(UscLectureFile uscLectureFile) {
        return uscLectureFileMapper.insertSelective(uscLectureFile);
    }

    @Override
    public UscLectureFile findById(Integer lectureId) {
        return uscLectureFileMapper.selectByPrimaryKey(lectureId);
    }

    @Override
    public int update(UscLectureFile uscLectureFile) {
        return uscLectureFileMapper.updateByPrimaryKeySelective(uscLectureFile);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public int deleteUscLectureFile(String[] releCourseIds) {
        int result = 0;
        for (int i = 0 ;i < releCourseIds.length ; i ++ ){
            UscLectureFile uscLectureFile   =  uscLectureFileMapper.selectByPrimaryKey(Integer.parseInt(releCourseIds[i]));
            result = uscLectureFileMapper.deleteByPrimaryKey(uscLectureFile.getLectureId());
        }
        return result;
    }
}
