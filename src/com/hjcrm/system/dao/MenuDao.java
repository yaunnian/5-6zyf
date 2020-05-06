package com.hjcrm.system.dao;

import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.bean.Role_menu;
import com.hjcrm.system.bean.User;

import java.util.HashMap;
import java.util.List;
/*
 *菜单管理方法接口
 * */
public interface MenuDao {
    public List<Menu> queryMenuByRolrid(HashMap<String, String> hashMap);
    public List<Menu> queryMenuByAll(HashMap<String, String> hashMap);
    public int insertMenu(Menu menu);
    public int deleteMenu(int id);
    public List<Menu> hasSecondaryChild(int id);
    public List<Role_menu> hasRole(int id);
    public int deleteMenuById(int id);
    public int addOrUpdateMenu(Menu menu);
}
