package com.hjcrm.patter.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.patter.bean.Patter;
import com.hjcrm.patter.service.Impl.PatterServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 我的话术
* */
@Controller
public class PatterController {
    @Autowired
    private PatterServiceImpl service;
    /**
     * 跳转我的话术 页面
     * */
    @RequestMapping("/patter/myPatter.do")
    public String myPatter(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.SYSTEM_MYPATTER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /**
     * 查询所有的话术场景
     * */
    @RequestMapping("/patter/queryPattertype.do")
    public @ResponseBody
    String queryPatterType(String userid, String courseid){
        System.out.println(userid+"daswwweq"+courseid);
        List<Patter> patterTypes = service.queryPattertype(userid, courseid);
        String json = PageBean.jsonToPage(patterTypes, null);
        return json;
    }
/*
* 查询此场景下我的话术
* */
    @RequestMapping("/patter/queryPatter.do")
    public @ResponseBody String  queryPatter(String userid,String courseid,String patterTypeId){
            if (userid!=null){
                List<Patter> patters = service.queryAllPatter(userid,courseid,patterTypeId);
                System.out.println(patters+"sdsadsadsa");
                String json = PageBean.jsonToPage(patters, null);
                System.out.println(json+"sadsa");
                return json;
            }
            return ReturnConstants.PARAM_NULL;
    }
    /**
     * 我的话术增加和修改
     * */
    @RequestMapping("/patter/saveOrUpdatePatter.do")
    public @ResponseBody String saveOrUpdatePatter(Patter patter){
        System.out.println("传的啥"+patter);
        if (patter!=null){
            service.saveOrUpdatePatter(patter);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /**
     * 我的话术删除
     * */
    @RequestMapping("/patter/deletePatter.do")
    public @ResponseBody String deletePatter(String userid,String patterids){
        System.out.println(patterids+"zoubuzou"+userid);
        if (StringUtils.isNotBlank(patterids)){

            service.deletePatter(userid,patterids);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    * 话术共享
    * */
    @RequestMapping("/patter/updatePatterIsShare.do")
    public @ResponseBody String updatePatterIsShare(Patter patter){
        if (patter!=null){
            service.updatePatterIsShare(patter);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
}
