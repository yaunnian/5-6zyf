package com.hjcrm.system.service.impl;

import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.bean.Role_menu;
import com.hjcrm.system.dao.MenuDao;
import com.hjcrm.system.service.IMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 菜单管理业务实现类
 * */
@Service("menuServiceImpl")
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> queryMenuRolrId(String roleId) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("roleid", roleId);
        hashMap.put("menuparaid", -1 + "");
        List<Menu> menus = menuDao.queryMenuByRolrid(hashMap);
        for (Menu m : menus) {
            System.out.println("一级" + m);
            HashMap<String, String> hashMap2 = new HashMap<String, String>();
            hashMap2.put("roleid", roleId);
            hashMap2.put("menuparaid", m.getMenuid() + "");
            System.out.println(m.getMenuparaid());
            List<Menu> menus1 = menuDao.queryMenuByRolrid(hashMap2);
            System.out.println("二级" + menus1.size() + menus1);
            m.setChildren(menus1);
        }
        return menus;
    }

    @Override
    public List<Menu> queryMenuByAll() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("menuparaid", "-1");
        List<Menu> menus = menuDao.queryMenuByRolrid(hashMap);
        for (Menu m : menus) {
            System.out.println("菜单一级:" + m);
            hashMap.put("menuparaid", m.getMenuid() + "");
            List<Menu> menus1 = menuDao.queryMenuByRolrid(hashMap);
            m.setChildren(menus1);
        }
        return menus;
    }

    @Override
    public List<Menu> queryRoleMenu(String roleid) {
        HashMap<String, String> menus = new HashMap<>();
        menus.put("roleid", roleid);
        List<Menu> menuList = menuDao.queryMenuByRolrid(menus);
        return menuList;
    }

    @Override
    public int insertMenu(Menu menu) {
        if (menu.getMenuid() != 0) {
            return menuDao.addOrUpdateMenu(menu);
        } else {
            return menuDao.insertMenu(menu);
        }
    }


/*
    @Override
    public int deleteMenu(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            List<String> list = new ArrayList<String>();
            for (String id : ids.split(",")) {
                list.add(id);
            }
            return menuDao.deleteMenu(list);
        }
        return -1;
    }
*/

    @Override
    public int deleteMenuById(String id) {
        return menuDao.deleteMenu(Integer.parseInt(id));
    }

    @Override
    public boolean hasSecondaryChild(String id) {
        return menuDao.hasSecondaryChild(Integer.parseInt(id)).size() > 0 ? true : false;
    }

    @Override
    public boolean hasRole(String id) {
        return menuDao.hasRole(Integer.parseInt(id)).size() > 0 ? true : false;
    }

}