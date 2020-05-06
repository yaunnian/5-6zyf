package com.hjcrm.resource.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.resource.bean.Resource;
import com.hjcrm.resource.bean.Visitrecord;
import com.hjcrm.system.bean.Role;

import java.util.HashMap;
import java.util.List;

public interface IResourceService {
    public List<Resource> queryAllResource(String userid, String deptid, String roleid, Integer currentPage, Integer pageSize);
    public int addResource(Resource resource);
    public List<Resource> queryAllCreatePerson();
    public List<Resource> queryResourceCount(String userid, String roleid, String deptid);
    public int count();
    public int deleteResources(String resourceId);
    public List<Resource> queryAllXiaoShou();
    public int assigntoResource(String resourceId, String belongid);
    public int assignResource(String resourceId, String belongid);
    public List<Role> queryXiaoShouByRoleid(String id);
    public List<Resource>  details(String roleid, String userid, String deptid, String resourceId);
    public int updateResourcestate(String resourceId);
    public int addhuigang(Visitrecord visitrecord);
    public List<Visitrecord> huifang(String roleid, String userid, String deptid, String resourceId);
}
