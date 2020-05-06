package com.hjcrm.operate.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.UserContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TRecordController {
    @RequestMapping("/operate/transferRecord.do")
    public String TRecord(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.OPERATE_TRECORD;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
}
