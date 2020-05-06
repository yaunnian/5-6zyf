package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/*
* 行政管理
* */
@Controller
public class StudentExeController {
    /*
    * 跳转行政部学员管理
    * */
    @RequestMapping("/executive/studentMang.do")
    public String studentMang(){
        if (UserContext.getLoginUser()!=null){
            return JumpViewConstants.EXECUTIVE_STUDENT_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
}
