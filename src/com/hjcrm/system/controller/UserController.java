package com.hjcrm.system.controller;

import com.hjcrm.contstans.*;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.bean.User;
import com.hjcrm.system.service.IUserService;
import com.hjcrm.system.service.impl.RoleServiceImpl;
import com.hjcrm.system.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 用户管理
* */
@Controller
public class UserController {
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private RoleServiceImpl roleService;
    /*
    * 跳转用户页面
    *
    * */
    @RequestMapping("/system/userMang.do")
    public String user(Model model){
        if (UserContext.getLoginUser()!=null){
            return "system/userMang";
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 显示所有用户信息
    *
    * */
    @RequestMapping("/system/userlist.do")
    @ResponseBody
    public String queryUserAll(Integer pageSize,Integer currentPage){
        System.out.println();
        if (UserContext.getLoginUser()!=null){
            System.out.println("pageSize:"+pageSize+"///"+currentPage);
          PageBean pb=  new PageBean();
          pb.setCurrentPage((currentPage-1)*pageSize);
          pb.setPageSize(pageSize);
          List<User> userList=service.queryAllUserInfo(pb);
            for (User u:userList
                 ) {
                System.out.println("锤子："+u);
            }
           /* pb.setCurrentPage(currentPage);
            pb.setCountPage(pageSize);*/
            pb.setCountResult(service.queryAllUserInfoCount());
          String userJsonResult=JsonUtil.jsonToPage(userList,pb);
            System.out.println(userJsonResult);
          return  userJsonResult;

        }
    return ReturnConstants.PARAM_NULL;
    }
    /*
    * 用户添加 改了前台发送的地址
    *
    * */
    @RequestMapping("/system/saveOrInsert.do")
    @ResponseBody
    public String saveOrUpdateInfo(User user){
        if (user!=null){
            service.insertUser(user);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 用户删除
    * */
    @RequestMapping("/system/deleteUser.do")
    public @ResponseBody String deleteUserInfoByIds(String ids){
        if (StringUtils.isNotBlank(ids)){
            int delResult = service.deleteUserInfoByIds(ids);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 用户信息修改
    * */
    @RequestMapping("/system/saveOrUpdate.do")
    public @ResponseBody String  updateUserInfo(Model model,User user){
        if (user!=null){
            service.updateUserInfo(user);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 查询所有角色名称
    * */
    @RequestMapping("/role/queryRoleByDeptid.do")
    public @ResponseBody String queryRoleOne(String deptid){
        System.out.println(deptid);
        if(StringUtils.isNotBlank(deptid)){
            List<Role> roles = roleService.queryRoleOne(deptid);
            System.out.println(roles);
            String joinResult = PageBean.jsonToPage(roles, null);
            return joinResult;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 修改当前用户密码
    * */
    @RequestMapping("system/editPassword.do")
    public @ResponseBody String editPassword(String newPassword, String userid){
        service.editPassword(newPassword, userid);
        return ReturnConstants.PARAM_SUCCESS;
    }
}


