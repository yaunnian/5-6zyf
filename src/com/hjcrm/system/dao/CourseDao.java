package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Course;

import java.util.List;

/*
 *课程管理方法接口
 * */
public interface CourseDao {
    public List<Course> queryCourse(PageBean pageBean);

    public int queryCourseCount();

    public int addCourse(Course course);

    public int updateCourse(Course course);

    public int delete(int id);

    public List<Course> queryCourseAll();
}
