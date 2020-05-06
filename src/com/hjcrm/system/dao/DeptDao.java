package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Dept;
import com.hjcrm.system.bean.Role;

import java.util.List;
/*
 *部门管理方法接口
 * */
public interface DeptDao {
    public int queryDeptAllCount();

    public List<Dept> queryAllDept(PageBean pageBean);

    public List<Dept> queryDeptAll();

    public List<Dept> queryAllDepts();

    public int addsaveOrUpdate(Dept dept);

    public int updatesaveOrUpdate(Dept dept);

    public List<Dept> hasSecondaryChild(int id);

    public List<Dept> hasRole(int id);

    public int delete(List<String> id);

}
