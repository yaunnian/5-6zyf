package com.hjcrm.system.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.bean.Role_menu;
import com.hjcrm.system.service.impl.MenuServiceImpl;
import com.hjcrm.system.service.impl.RoleServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
/*
* 角色管理
* */
@Controller
public class RoleController {
    /*
    *
    * */
    @Autowired
    private RoleServiceImpl service;
    @Autowired
    private MenuServiceImpl menuService;
    /*
    * 跳转角色页面
    *
    * */
    @RequestMapping("/system/roleMang.do")
    public String roleManger() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SYSTEM_ROLE_MANAGER;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 查询所有角色信息
    *
    * */
    @RequestMapping("/role/queryAllRole.do")
    public @ResponseBody
    String queryAllRole(Integer currentPage, Integer pageSize) {
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        System.out.println(pageSize + "adsadsadsadsadsaas");
        List<Role> roles = service.queryAllRole(pageBean);
        int index = service.queryAllRoleCount();
        String jsonResult = PageBean.jsonUserInfoToPage(roles, index, currentPage);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /*
    *显示权限
    */
    @RequestMapping("/rolemenu/queryAllMenuAndSelected.do")
    public @ResponseBody
    String queryMenuByAll(String roleid) {
        List<Menu> menus = menuService.queryMenuByAll();
        List<Menu> menuList = menuService.queryRoleMenu(roleid);
        if (menuList != null && menuList.size() > 0) {
            for (int i = 0; i < menus.size(); i++) {
                for (int j = 0; j < menuList.size(); j++) {
                    if (menuList.get(j) != null) {
                        if (menuList.get(j).getMenuid() == menus.get(i).getMenuid()) {
                            menus.get(i).setSelected(true);
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < menus.size(); i++) {
            List<Menu> serondary = menus.get(i).getChildren();
            if (serondary != null && serondary.size() > 0) {
                for (int j = 0; j < menuList.size(); j++) {
                    for (int z = 0; z < serondary.size(); z++) {
                        if (menuList.get(j) != null) {
                            if (menuList.get(j).getMenuid() == serondary.get(z).getMenuid()) {
                                serondary.get(z).setSelected(true);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
        return PageBean.jsonToPage(menus, null);
    }
    /*
    * 分配权限
    * */
    @RequestMapping("/rolemenu/assignMenu.do")
    public @ResponseBody
    String assignMenu(String roleid,String menuid) {
        if (StringUtils.isNotBlank(roleid)){
            service.removeRoleMenu(roleid);
            for (String menuids:menuid.split(",")) {
                Role_menu rm=new Role_menu();
                rm.setRoleid(Integer.parseInt(roleid));
                rm.setMenuid(Integer.parseInt(menuids));
                service.insertRoleMenu(rm);
            }
            return ReturnConstants.PARAM_SUCCESS;
        }
        return "0";
    }
    /*
    * 角色添加修改
    *
    * */
    @RequestMapping("/role/addOrUpdateRole.do")
    public @ResponseBody
    String addOrUpdateRole(Role role) {
        if (role != null) {
            System.out.println(role);
            service.addOrUpdateRole(role);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
    /*
    *
    * 角色删除
    * */
    @RequestMapping("/role/deleteRole.do")
    public @ResponseBody String deleteRoleByIds(String ids){
        if(StringUtils.isNotBlank(ids)){
            service.deleteRoleByIds(ids);
            return ReturnConstants.PARAM_SUCCESS;
        }
        return ReturnConstants.PARAM_NULL;
    }
}


