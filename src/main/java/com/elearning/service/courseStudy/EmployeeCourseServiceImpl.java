package com.elearning.service.courseStudy;

import com.elearning.pojo.coursemanage.CourseType;
import com.elearning.pojo.pub.Course;
import com.elearning.service.coursemanage.ICourseTypeService;
import com.elearning.service.pub.ICourseService;
import com.elearning.service.pub.IEosorgTEmployeeService;
import com.elearning.service.pub.IEosorgTOrganizationService;
import com.elearning.vo.logon.CourseStudyForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("employeeCourseService")
public class EmployeeCourseServiceImpl implements IEmployeeCourseService {

    @Autowired
    private IEosorgTEmployeeService eosorgTEmployeeService;

    @Autowired
    private IEosorgTOrganizationService eosorgTOrganizationService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseTypeService courseTypeService;

    public List<CourseStudyForm> listCoursesByOperatorId(Map<String, Object> map){
        Integer currentOperatorId = null;
        if (map.get("currentOperatorId") != null) {
            currentOperatorId = Integer.parseInt(map.get("currentOperatorId").toString());
        }
        Integer operatorOrgId = this.eosorgTEmployeeService.findById(currentOperatorId).getOrgID();
        ArrayList<Integer> parentOrgIdList = new ArrayList<>();
        //this.getParentOrgIdList(operatorOrgId, parentOrgIdList);
        parentOrgIdList.add(operatorOrgId);
        map.put("parentOrgId",parentOrgIdList);

        //2017.8.17 查找该平台对应的二级机构id，从而确定属于同一个二级机构下的所有机构的TenantId列表
        int tenantOrgId = 1;
        if(map.get("tenantOrgId")!=null && !map.get("tenantOrgId").toString().trim().equals("")){
            tenantOrgId = Integer.parseInt(map.get("tenantOrgId").toString());
        }
        this.eosorgTOrganizationService.putSameSecondaryParentTenantIdListInMap(map);

        List<Course> courseList = this.courseService.listCoursesByOperatorId(map);

        List<CourseStudyForm> courseFormList = new ArrayList<>();

        for (Course course : courseList) {
            CourseStudyForm form = new CourseStudyForm(course);
            Integer expertAreaId = null;
            if (course.getExpertAreaId() != null){
                expertAreaId = Integer.parseInt(course.getExpertAreaId());
                form.setCourseTypeName(this.getCourseType(expertAreaId));
            }else{
                String typeName = this.courseTypeService.getCourseTypeNameByCourseId(course.getCourseId());
                form.setCourseTypeName(typeName == null ? "" : typeName);
            }
            courseFormList.add(form);
        }
        return courseFormList;

    }

    public String getCourseType(Integer courseTypeId){
        if(courseTypeId==null){
            return null;
        }
        String courseTypeName = "";
        CourseType courseType = this.courseTypeService.selectByPrimaryKey(courseTypeId);
        if(courseType != null){
            courseTypeName = courseType.getCourseTypeName();
        }
        return courseTypeName;
    }





}
