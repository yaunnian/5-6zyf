package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Dept;
import com.hjcrm.system.bean.Role;

import java.util.List;
/*
 * 部门管理业务接口
 * */
public interface IDeptService {
    public int queryDeptAllCount();

    public List<Dept> queryAllDept(PageBean pageBean);

    public List<Dept> queryDeptAll();

    public int saveOrUpdate(Dept dept);

    public List<Dept> queryAllDepts();

    public int delete(String ids);

    public boolean hasSecondaryChild(String id);

    public boolean hasRole(String id);

}
