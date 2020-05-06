package com.hjcrm.system.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Systemmessage;
import com.hjcrm.system.service.impl.SystemmessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SystemmessageController {
    @Autowired
    private SystemmessageServiceImpl service;
    /*
    * 跳转消息管理页面
    * */
        @RequestMapping("/system/systemMessage.do")
    public String systemMessage(){
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SYSTEM_MESSAGE;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 查询所有消息信息
    * */
    @RequestMapping("/system/querySystemmessages.do")
    public @ResponseBody String querySystemmessageAll(Integer currentPage,Integer pageSize){
            PageBean pageBean=new PageBean();
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
        List<Systemmessage> list = service.querySystemmessageAll(pageBean);
        String jsonUserInfoToPage = PageBean.jsonUserInfoToPage(list, service.querySystemmessageAllCount(), currentPage);
            return jsonUserInfoToPage;
    }
    /*
    * 消息的添加和修改
    * */
    @RequestMapping("/system/saveOrUpdateMessage.do")
    public @ResponseBody String addOrUpdateSubject(Systemmessage systemmessage){
            if (systemmessage!=null){
                service.addOrUpdateSubject(systemmessage);
                return ReturnConstants.PARAM_SUCCESS;
            }
            return ReturnConstants.PARAM_NULL;
    }
    /*
    * 发布消息
    * */
    @RequestMapping("/system/sendMessage.do")
    public @ResponseBody String sendMessage(Systemmessage systemmessage) {
        if (systemmessage != null) {
            service.sendMessage(systemmessage);
            return ReturnConstants.PARAM_SUCCESS;
        }

            return ReturnConstants.PARAM_NULL;

    }
}
