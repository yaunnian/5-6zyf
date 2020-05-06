package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.resource.bean.Student;
import com.hjcrm.resource.service.Impl.ResourceServiceImpl;
import com.hjcrm.resource.service.Impl.StudentServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*
 * 销售部门管理
 * */
@Controller
public class StudentSalseController {
    @Autowired
    private StudentServiceImpl service;
    @Autowired
    private ResourceServiceImpl resourceService;

    /*
     * 跳转销售部门学员页面
     * */
    @RequestMapping("/sales/studentMang.do")
    public String StudentSalseMang() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SALES_STUDENT_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }

    /*
     * 将资源转为学员
     * */
    @RequestMapping("/resource/addDealrecord.do")
    public @ResponseBody
    String addDealrecord(Student student) {
        service.addDealrecord(student);
        service.updateStudentName(student.getResourceId() + "");
        resourceService.updateResourcestate(student.getResourceId() + "");
        return ReturnConstants.PARAM_SUCCESS;
    }

    /*
     * 查询学员信息
     * */
    @RequestMapping("/student/queryStudents.do")
    public @ResponseBody
    String queryStudents(String userid, String deptid, String roleid, Integer currentPage, Integer pageSize) {
        if (StringUtils.isNotBlank(userid)) {
            List<Student> students = service.queryStudents(userid, deptid, roleid, currentPage, pageSize);
            String toPage = PageBean.jsonUserInfoToPage(students, service.StudentAllCount(), currentPage);
            return toPage;
        }
        return ReturnConstants.PARAM_NULL;
    }

    /*
     * 销售部门学员添加
     * */
    @RequestMapping("/student/addStudent.do")
    public @ResponseBody
    String addStudent(Student student) {
        if (student != null) {
            service.addStudent(student);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }

    /*
     * 提交信息
     * */
    @RequestMapping("/student/studentCommit.do")
    public @ResponseBody
    String studentCommit(String studentIds, String resourceIds) {
        service.studentCommit(studentIds, resourceIds);
        return ReturnConstants.PARAM_SUCCESS;
    }

    /*
     * 退回学员
     * */
    @RequestMapping("/student/returnStudent.do")
    public @ResponseBody
    String returnStudent(String userid, String studentIds, String resourceIds, String returnNote) {
        List<String> list = new ArrayList<>();
        for (String studentId : studentIds.split(",")
                ) {
            list.add(studentId);
        }
        for (int i = 0; i < list.size(); i++) {
            service.returnStudent(list.get(i), returnNote);
        }
        return ReturnConstants.PARAM_SUCCESS;
    }
}