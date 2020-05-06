package com.hjcrm.system.service.impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Course;
import com.hjcrm.system.dao.CourseDao;
import com.hjcrm.system.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * 课程管理业务实现类
 * */
@Service("courseServiceImpl")
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseDao dao;

    @Override
    public int queryCourseCount() {
        return dao.queryCourseCount();
    }

    @Override
    public List<Course> queryCourse(PageBean pageBean) {
        pageBean.setCurrentPage(pageBean.getPageSize()*(pageBean.getCurrentPage()-1));
        return dao.queryCourse(pageBean);
    }

    @Override
    public int addCourse(Course course) {
        if (course.getCourseid()!=0){
            return dao.updateCourse(course);
        }else {
            return dao.addCourse(course);
        }
    }

    @Override
    public List<Course> queryCourseAll() {
        return dao.queryCourseAll();
    }
}
