package com.hjcrm.system.service;

import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.bean.Role_menu;

import java.util.HashMap;
import java.util.List;
/*
 * 菜单管理业务接口
 * */
public interface IMenuService {
    public List<Menu> queryMenuRolrId(String roleId);
    public List<Menu> queryMenuByAll();
    public List<Menu> queryRoleMenu(String roleid);
   public int insertMenu(Menu menu);
/*   public int deleteMenu(String ids);*/
   public int deleteMenuById(String id);
    public boolean hasSecondaryChild(String id);
    public boolean hasRole(String id);

}
