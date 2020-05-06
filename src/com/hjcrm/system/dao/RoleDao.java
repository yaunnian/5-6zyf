package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.bean.Role_menu;

import java.util.List;

/*
 *角色管理方法接口
 * */
public interface RoleDao {
    public List<Role> queryRoleOne(String deptid);

    public List<Role> queryAllRole(PageBean pageBean);

    public int queryAllRoleCount();

    //public List<Role> queryRoleByRoleid(HashMap<String,String> hashMap);
    public int addOrUpdateRole(Role role);

    public int deleteRoleByIds(List list);

    public int insertRoleMenu(Role_menu role_menu);

    public int removeRoleMenu(String roleid);

    public int updateRoleById(Role role);
}
