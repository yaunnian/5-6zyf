package com.hjcrm.system.controller;


import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Dept;
import com.hjcrm.system.service.impl.DeptServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 部门管理
* */
@Controller
public class DeptController {

    @Autowired
    private DeptServiceImpl service;
    /*
    * 跳转部门管理页面
    * */
    @RequestMapping("/system/deptMang.do")
    public String deptManger(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.SYSTEM_DEPT_MANAGER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 显示所有的部门信息
    *
    * */
    @RequestMapping(value = "/dept/queryDept.do",method = RequestMethod.GET,params = {"currentPage","pageSize"})
    @ResponseBody
    public String queryDeptAll(Integer currentPage,Integer pageSize){
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        List<Dept> deptes = service.queryAllDept(pageBean);
        int index=service.queryDeptAllCount();
        System.out.println("depts 内容"+deptes);
        String jsonResult = PageBean.jsonUserInfoToPage(deptes,index,currentPage);
        System.out.println("部门信息："+jsonResult);
        return jsonResult;
    }
    /*
    * 查询所有的部门
    *
    * */
    @RequestMapping(value = "/dept/queryDept.do",method = RequestMethod.GET)
    @ResponseBody
    public String queryDeptAll(){
        List<Dept> depts = service.queryAllDepts();
        String jsonResult = PageBean.jsonToPage(depts,null);
        System.out.println("部门信息："+jsonResult);
        return jsonResult;
    }
    /*
    * 部门的增加和修改
    * */
    @RequestMapping("/dept/saveOrUpdate.do")
    public @ResponseBody String  saveOrUpdate(Dept dept){
        if(dept!=null){
            service.saveOrUpdate(dept);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 部门删除
    * */
    @RequestMapping("/dept/delete.do")
    public @ResponseBody String  deleteDept(String ids){
        System.out.println("删除信息"+ids);
        StringBuilder sb=new StringBuilder();
        boolean flag=false;
        if (StringUtils.isNotBlank(ids)){
            for (String id:ids.split(",")
                 ) {
                if(service.hasSecondaryChild(id)){
                    sb.append("一级部门不可删除");
                    continue;
                }
                if (service.hasRole(id)){
                    sb.append("此部门有人员存在");
                    continue;
                }
                flag=true;

            }
            if (flag){
                service.delete(ids);
                return ReturnConstants.PARAM_SUCCESS;
            }else {
                return sb.toString();
            }
        }
        return ReturnConstants.PARAM_NULL;
    }
}


