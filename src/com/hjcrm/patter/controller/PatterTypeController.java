package com.hjcrm.patter.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.patter.bean.PatterType;
import com.hjcrm.patter.service.Impl.PatterTypeServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 话术场景管理
* */
@Controller
public class PatterTypeController {
    @Autowired
    private PatterTypeServiceImpl service;
    /*
    * 跳转话术场景页面
    * */
    @RequestMapping("/system/patterType.do")
    public String patterTrpe(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.SYSTEM_PATTERTYPE;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 查询所有的话术场景
    * */
    @RequestMapping("/course/queryPattertype.do")
    public @ResponseBody
    String queryPatterTypeAll(){
        List<PatterType> patterTypes = service.queryPatterTypeAll();
        String jsonToPage = PageBean.jsonToPage(patterTypes, null);
        return jsonToPage;
    }
    /*
    * 话术场景的增加和修改
    * */
    @RequestMapping("/course/addPattertype.do")
    public @ResponseBody String addPattertype(PatterType patterType){
            if (patterType!=null){
                service.addPattertype(patterType);
                return ReturnConstants.PARAM_SUCCESS;
            }
            return ReturnConstants.PARAM_NULL;
    }
    /*
    * 话术场景的删除
    * */
    @RequestMapping("/course/deletepatterType.do")
    public @ResponseBody String deletePatterType(String patterTypeIds){
        if (StringUtils.isNotBlank(patterTypeIds)){
            service.deletePatterType(patterTypeIds);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }

}
