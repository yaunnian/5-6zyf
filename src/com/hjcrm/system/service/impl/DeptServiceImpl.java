package com.hjcrm.system.service.impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Dept;
import com.hjcrm.system.bean.Role;
import com.hjcrm.system.dao.DeptDao;
import com.hjcrm.system.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 部门管理业务实现类
 * */
@Service("deptServiceImpl")
public class DeptServiceImpl implements IDeptService {
    @Autowired
    private DeptDao dao;

    @Override
    public int queryDeptAllCount() {
        return dao.queryDeptAllCount();
    }

    @Override
    public List<Dept> queryAllDept(PageBean pageBean) {
        pageBean.setCurrentPage(pageBean.getPageSize() * (pageBean.getCurrentPage() - 1));
        return dao.queryAllDept(pageBean);

    }

    @Override
    public List<Dept> queryDeptAll() {
        return dao.queryDeptAll();
    }

    @Override
    public int saveOrUpdate(Dept dept) {
        if (dept.getDeptid() != 0) {
            return dao.updatesaveOrUpdate(dept);
        } else {
            return dao.addsaveOrUpdate(dept);
        }

    }

    @Override
    public List<Dept> queryAllDepts() {
        return dao.queryAllDepts();
    }

    @Override
    public int delete(String ids) {
        List<String> list=new ArrayList<>();
        for (String id:ids.split(",")
             ) {
            list.add(id);
        }
        return dao.delete(list);
    }

    @Override
    public boolean hasSecondaryChild(String id) {
        return dao.hasSecondaryChild(Integer.parseInt(id)).size() > 0 ? true : false;
    }

    @Override
    public boolean hasRole(String id) {
        return dao.hasRole(Integer.parseInt(id)).size() > 0 ? true : false;
    }


}
