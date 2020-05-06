package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.resource.bean.Student;
import com.hjcrm.resource.service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/*
* 学生管理
* */
@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl service;
    /*
    * 跳转学生管理页面
    * */
    @RequestMapping("/student/studentMang.do")
    public String  studentMang(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.OPERATE_STUDENT_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 跳转学员详情信息
    * */
    @RequestMapping("/student/studentDetails.do")
    public String studentDetails(String roleid, String userid, String deptid, String studentId, HttpServletRequest request){
        List<Student> students = service.studentDetails(roleid, userid, deptid, studentId);
        request.getSession().setAttribute("liststudent",students);
        return JumpViewConstants.SALES_STUDENTDETAILS;
    }

}
