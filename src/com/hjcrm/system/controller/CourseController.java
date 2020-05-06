package com.hjcrm.system.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Course;
import com.hjcrm.system.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 课程管理
* */
@Controller
public class CourseController {
    /*
     * 跳转课程管理页面
     * */
    @Autowired
    private CourseServiceImpl service;

    @RequestMapping("/system/courseMang.do")
    public String courseManger() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SYSTEM_COURSE;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
     * 显示课程信息
     *
     * */

    @RequestMapping(value = "/course/queryCourse.do", method = RequestMethod.GET, params = {"currentPage", "pageSize"})
    public @ResponseBody
    String queryCourse(Integer currentPage, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        List<Course> courses = service.queryCourse(pageBean);
        String jsonCourse = PageBean.jsonUserInfoToPage(courses, service.queryCourseCount(), currentPage);
        return jsonCourse;
    }
    /*
     * 显示所有课程
     *
     * */
    @RequestMapping(value = "/course/queryCourse.do", method = RequestMethod.GET)
    @ResponseBody
    public String queryCourseAll() {
        List<Course> courses = service.queryCourseAll();
        String s = PageBean.jsonToPage(courses, null);
        return s;

    }
        /*
        * 课程添加修改
        * */
    @RequestMapping("/course/addCourse.do")
    public @ResponseBody
    String addCourse(Course course) {
        if (course != null) {
            service.addCourse(course);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
}


