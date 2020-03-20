package com.elearning.service.coursemanage;

import com.elearning.dao.coursemanage.CourseCoursetypeMapper;
import com.elearning.dao.coursemanage.CourseTypeMapper;
import com.elearning.pojo.coursemanage.CourseCoursetype;
import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.pub.Course;
import com.elearning.service.courseStudy.IChapterService;
import com.elearning.service.pub.ICourseService;
import com.elearning.vo.CourseFormAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("courseCoursetypeService")
public class CourseCoursetypeServiceImpl implements ICourseCoursetypeService{

    @Autowired
    private CourseCoursetypeMapper courseCoursetypeMapper;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IChapterService chapterService;

    @Override
    public CourseCoursetype selectByPrimaryKey(Integer ID) {

        return this.courseCoursetypeMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int insert(CourseCoursetype record) {

        return this.courseCoursetypeMapper.insert(record);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class })
    public void listMySelectCourseToSchedule(HttpServletRequest request) {

        String selectedCourseIdStr = request.getParameter("courseIdStr");
        String step = request.getParameter("step");
        String trainWay = request.getParameter("trainWay");

        List<CourseFormAll> courseFormList = new ArrayList<>();
        if(selectedCourseIdStr.indexOf(",")>-1){
            String [] courseIdArr=selectedCourseIdStr.split(",");
            for(int i=0;i<courseIdArr.length;i++){
                if(courseIdArr[i]!="" && !courseIdArr[i].equals("")){
                    Course course = this.courseService.findCourseById(Long.valueOf(courseIdArr[i].toString().trim()));
                    if(course != null){
                        courseFormList.add(this.courseService.getCourseForm(course));
                    }
                }
            }
        }else{
            courseFormList.add(this.courseService.getCourseForm(courseService.findCourseById(Long.valueOf(selectedCourseIdStr.toString().trim()))));
        }
        int courseHasEnterUrlNum=0;
        if(courseFormList!=null && courseFormList.size()>0){
            for(CourseFormAll courseForm : courseFormList){
                if(!courseForm.getEnterUrl().equals("")){
                    courseHasEnterUrlNum++;
                }
                if(courseForm.getIsCoursePackage() != null && courseForm.getIsCoursePackage() == 1){
                    Map<String,Object> map = new HashMap<>();
                    map.put("courseId", courseForm.getCourse_id());
                    courseForm.setSecondCourseId(this.chapterService.findByMap(map).get(0).getSubset().get(0).getCourseId());
                }else{
                    courseForm.setSecondCourseId(courseForm.getCourse_id());
                }
            }
        }
        request.getSession().setAttribute("courseFormList", courseFormList);
        request.setAttribute("step", step);
        request.setAttribute("trainWay", trainWay);
        request.setAttribute("courseSize", courseFormList.size());
        request.setAttribute("noEnterUrlNum", courseFormList.size()-courseHasEnterUrlNum);

    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteMySelectCourseToSchedule(HttpServletRequest request) {

        String step=request.getParameter("step");
        String trainWay=request.getParameter("trainWay");
        long courseId=Long.parseLong(request.getParameter("courseId"));

        List<CourseFormAll> courseFormList=(List<CourseFormAll>) request.getSession().getAttribute("courseFormList");
        List<CourseFormAll> remainCourseFormList=new ArrayList();

        if(courseFormList!=null&&courseFormList.size()>0){
            for(CourseFormAll courseForm:courseFormList){
                if(courseForm.getCourse_id()!=courseId){
                    remainCourseFormList.add(courseForm);
                }
            }
        }
        int courseHasEnterUrlNum=0;
        if(remainCourseFormList!=null&&remainCourseFormList.size()>0){
            for(CourseFormAll courseForm:remainCourseFormList){
                if(!courseForm.getEnterUrl().equals(""))courseHasEnterUrlNum++;
            }
        }
        request.getSession().setAttribute("courseFormList", remainCourseFormList);
        request.setAttribute("step", step);
        request.setAttribute("trainWay", trainWay);
        request.setAttribute("courseSize", remainCourseFormList.size());
        request.setAttribute("noEnterUrlNum", remainCourseFormList.size()-courseHasEnterUrlNum);

    }






}
