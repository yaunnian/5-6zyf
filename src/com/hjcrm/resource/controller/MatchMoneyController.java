package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MatchMoneyController {
    @RequestMapping("/executive/matchMoney.do")
    public String matchMoney(){
        if (UserContext.getLoginUser()!=null){
            return JumpViewConstants.EXECUTIVE_MARCH_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
}
