package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 课程管理业务接口
 * */
public interface ICourseService {
    public int queryCourseCount();

    public List<Course> queryCourse(PageBean pageBean);

    public int addCourse(Course course);

    public List<Course> queryCourseAll();
}
