package com.elearning.service;

import com.elearning.common.ServiceResponse;
import com.elearning.dao.pub.CourseMapper;
import com.elearning.pojo.pub.Course;
import com.elearning.service.ITestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 〈<br>
 * 〈〉
 *
 * @author lxx
 * @create 2019/7/2 8:54
 */

/**
 * service 层加 service 注解
 */
@Service("testService")
public class TestServiceImpl implements ITestService {

    /**
     * 通过@Autowired 注解 注入 mapper
     */
    @Autowired
    private CourseMapper courseMapper;


    @Override
    public Course getCourseById(Long courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public ServiceResponse listCourse(Integer num, Integer size) {
        //初始化分页插件
        PageHelper.startPage(num,size);
        List<Course> courseList = courseMapper.listCourse();
        //将根据条件查询出来的list集合放入pageInfo中 pageInfo会自己算出 list的长度 如一共有多少条total ,总共多少页pages 详情见结果数据
        PageInfo pageInfo = new PageInfo(courseList);
        // 将查出来的10条数据封装到list中
        pageInfo.setList(courseList);
        //serviceResponse中封装了各种返回成功或失败的方法,如createBySuccess(String msg) 将 pageInfo返回后会以json的形式返回 详情见ServiceResponse
        return ServiceResponse.createBySuccess(pageInfo);
    }


    /**
     * Transactional 注解为事务注解  需要开启事务的时候添加 该注解  默认 RuntimeException 设置rollbackFor参数以后为Exception 后所有异常都回滚
     *
     * @param courseId
     * @return
     */
    @Override
    @Transactional(rollbackFor = { Exception.class })
    public ServiceResponse delete(Long courseId) {
        try {
            transac(courseId);
            return ServiceResponse.createBySuccessMessage("修改成功");
        }catch (Exception e){
            //手动回滚   在将异常捕获的情况下 不会事务回滚 需要手动回滚 不捕获异常时 不需要添加
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ServiceResponse.createBySuccessMessage("事务回滚");
        }
    }

    public int transac(Long courseId){
        Course course = new Course();
        course.setCourseId(courseId);
        course.setCourseName("测试删除没有报错没有回滚9");
        int a = courseMapper.updateByPrimaryKeySelective(course);
        int j = 1/0;
        return a;
    }

}
