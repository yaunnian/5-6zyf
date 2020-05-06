package com.hjcrm.system.controller;
import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.service.impl.MenuServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 菜单管理
* */
@Controller
public class MenuController {
    @Autowired
    private MenuServiceImpl service;
    /*
    * 跳转菜单页面
    *
    * */
    @RequestMapping("/system/menuMang.do")
    public String menuManger(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.SYSTEM_MEUN_MANAGER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
        /*
        * 显示所有菜单
        * */
        @RequestMapping("/menu/queryAllMenu.do")
        @ResponseBody
    public  String queryAllMenu(){
            if (UserContext.getLoginUser()!=null){
                List<Menu> menuList = service.queryMenuByAll();
                String joinResult = PageBean.jsonToPage(menuList, null);
                return joinResult;
            }
            return ReturnConstants.PARAM_NULL;
        }
        /*
        * 菜单添加修改
        * */
        @RequestMapping("/menu/addOrUpdateMenu.do")
    public @ResponseBody String insertMenu(Menu menu){
            System.out.println(menu);
            if(menu!=null) {
                service.insertMenu(menu);
                return ReturnConstants.PARAM_SUCCESS;
            }
                return ReturnConstants.PARAM_NULL;
        }
    /*
    * 菜单删除 不计后果式
    * 不使用
    * */
     /*   @RequestMapping("/menu/delete.do")
        public @ResponseBody String deleteMenu(String ids){
        if (StringUtils.isNotBlank(ids)){
            service.deleteMenu(ids);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
        }
*/
     /*
     * 菜单删除
     * */
    @RequestMapping("/menu/delete.do")
    public @ResponseBody String deleteMenu(String ids){
        StringBuilder sb=new StringBuilder();
        boolean flag=false;
        if (StringUtils.isNotBlank(ids)){
            for (String id:ids.split(",")) {
                if (service.hasSecondaryChild(id)){
                    sb.append("一级菜单不可删除");
                    continue;
                }
                if (service.hasRole(id)){
                    sb.append("菜单被使用");
                    continue;
                }
                flag=true;
                service.deleteMenuById(id);

            }
            if (flag){
                return ReturnConstants.PARAM_SUCCESS;
            }else {
                return sb.toString();
            }
        }
        return ReturnConstants.PARAM_NULL;
    }
}
