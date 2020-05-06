package com.hjcrm.system.service.impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.bean.Role_menu;
import com.hjcrm.system.dao.RoleDao;
import com.hjcrm.system.service.IRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
 * 角色管理业务实现类
 * */
@Service("roleServiceImpl")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDao dao;

    /*
     *这是显示角色
     */
    @Override
    public List<Role> queryAllRole(PageBean pageBean) {
        pageBean.setCurrentPage(pageBean.getPageSize() * (pageBean.getCurrentPage() - 1));
        System.out.println(pageBean + "=asdasdasdsadsa");
        return dao.queryAllRole(pageBean);
    }

    /*
     *这是显示角色
     */
    @Override
    public List<Role> queryRoleOne(String deptid) {
        return dao.queryRoleOne(deptid);
    }

    /*
     *这是求所有角色的数量
     */
    @Override
    public int queryAllRoleCount() {
        return dao.queryAllRoleCount();
    }

    @Override
    public int addOrUpdateRole(Role role) {
        if (role.getRoleid() != 0) {
            return dao.updateRoleById(role);
        }
        return dao.addOrUpdateRole(role);
    }

    /*
     *
     *删除
     */
    @Override
    public int deleteRoleByIds(String ids) {
        List<String> list = new ArrayList<String>();
        if (StringUtils.isNotBlank(ids)) {
            for (String id : ids.split(",")) {
                list.add(id);
            }
            return dao.deleteRoleByIds(list);
        }
        return -1;
    }

    @Override
    public int insertRoleMenu(Role_menu role_menu) {
        return dao.insertRoleMenu(role_menu);
    }

    @Override
    public int removeRoleMenu(String roleid) {
        return dao.removeRoleMenu(roleid);
    }

}
