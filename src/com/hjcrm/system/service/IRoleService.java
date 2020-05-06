package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.bean.Role_menu;

import java.util.List;
/*
 * 角色管理业务接口
 * */
public interface IRoleService {

    public List<Role> queryAllRole(PageBean pageBean);

    public List<Role> queryRoleOne(String deptid);

    public int queryAllRoleCount();

    public int addOrUpdateRole(Role role);

    public int deleteRoleByIds(String ids);

    public int insertRoleMenu(Role_menu role_menu);

    public int removeRoleMenu(String roleid);

}
