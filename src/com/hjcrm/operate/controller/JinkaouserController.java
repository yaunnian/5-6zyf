package com.hjcrm.operate.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JinkaouserController {

    @RequestMapping("/user/jinkaouser.do")
    public String jinkaouser(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.OPERATE_JINKAOUSER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
}
