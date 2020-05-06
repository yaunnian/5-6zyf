package com.hjcrm.system.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Subject;
import com.hjcrm.system.service.impl.SubjectServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 科目管理
* */
@Controller
public class SubjectController {
    @Autowired
    private SubjectServiceImpl service;
    /*
     * 跳转科目页面
     *
     * */
    @RequestMapping("/system/subjectMang.do")
    public String subjectManger() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SYSTEM_SUBJECT;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
/*显示所有科目信息
*
* */
    @RequestMapping(value = "/subject/querySubject.do",method = RequestMethod.GET,params = {"currentPage","pageSize"})
    public @ResponseBody
    String queryAllSubject(Integer currentPage,Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        List<Subject> subjects = service.queryAllSubject(pageBean);
        String jsonSubject = PageBean.jsonUserInfoToPage(subjects, service.queryAllSubjectCount(), currentPage);
        return jsonSubject;
    }
    /*
    * 科目添加和修改
    * */
    @RequestMapping("/subject/addOrUpdateSubject.do")
    public @ResponseBody String addOrUpdateSubject(Subject subject){
        if (subject!=null){
            System.out.println("subject!!!!!!!!!!!"+subject);
            service.addOrUpdateSubject(subject);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 科目删除
    * */
    @RequestMapping("/subject/deleteSubject.do")
    public @ResponseBody String deleteSubject(String subjectids){
        System.out.println("删除用id"+subjectids);
        if (StringUtils.isNotBlank(subjectids)){
            service.deleteSubject(subjectids);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 查询所有科目名称
    * */
    @RequestMapping(value = "/subject/querySubject.do",method = RequestMethod.GET,params = {"courseid"})
    public @ResponseBody String querySubject(String courseid){
        List<Subject> subjects = service.querySubject(courseid);
        String json = PageBean.jsonToPage(subjects, null);
        return json;
    }
}
