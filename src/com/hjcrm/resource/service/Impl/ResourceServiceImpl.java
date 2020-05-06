package com.hjcrm.resource.service.Impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.resource.bean.Resource;
import com.hjcrm.resource.bean.Visitrecord;
import com.hjcrm.resource.dao.ResourceDao;
import com.hjcrm.resource.dao.VisitrecordDao;
import com.hjcrm.resource.service.IResourceService;
import com.hjcrm.system.bean.Role;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("resourceServiceImpl")
public class ResourceServiceImpl implements    IResourceService {
    @Autowired
    private ResourceDao dao;
    @Autowired
    private VisitrecordDao visitrecordDao;
    @Override
    public List<Resource> queryAllResource(String userid, String deptid, String roleid,Integer currentPage,Integer pageSize) {
        PageBean pageBean=new PageBean();
        pageBean.setCurrentPage(pageBean.getPageSize()*(pageBean.getCurrentPage()-1));
        if (userid!=null){
            Map<String,Object> hashMap=new HashMap<>();
            hashMap.put("userid",userid);
            hashMap.put("deptid",deptid);
            hashMap.put("roleid",roleid);
            hashMap.put("currentPage",currentPage-1);
            hashMap.put("pageSize",pageSize);
            List<Resource> list=dao.queryAllResource(hashMap);
            return list;
        }
        return null;
    }

    @Override
    public int addResource(Resource resource) {
        if (resource.getResourceId() != 0) {
            return dao.updateResource(resource);
        } else {
            return dao.addResource(resource);
        }
    }
    @Override
    public List<Resource> queryAllCreatePerson() {
        return dao.queryAllCreatePerson();
    }

    @Override
    public List<Resource> queryResourceCount(String userid, String roleid, String deptid) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userid",userid);
        hashMap.put("roleid",roleid);
        hashMap.put("deptid",deptid);
        return dao.queryResourceCount(hashMap);
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public int deleteResources(String resourceId) {
        if (StringUtils.isNotBlank(resourceId)){
            List<String> list=new ArrayList<>();
            for (String ids:resourceId.split(",")) {
            list.add(ids);
        }
            return dao.deleteResources(list);
        }
        return -1;
    }

    @Override
    public List<Resource> queryAllXiaoShou() {
        return dao.queryAllXiaoShou();
    }

    @Override
    public int assigntoResource(String resourceId, String belongid) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("resourceIds",resourceId);
        hashMap.put("belongid",belongid);
        return dao.assigntoResource(hashMap);
    }

    @Override
    public int assignResource(String resourceId, String belongid) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("resourceIds",resourceId);
        hashMap.put("belongid",belongid);
        return dao.assignResource(hashMap);

    }

    @Override
    public List<Role> queryXiaoShouByRoleid(String id) {

        return dao.queryXiaoShouByRoleid(Integer.parseInt(id));
    }

    @Override
    public List<Resource> details(String roleid, String userid, String deptid, String resourceId) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("roleid",roleid);
        hashMap.put("userid",userid);
        hashMap.put("deptid",deptid);
        hashMap.put("resourceId",resourceId);
        return dao.details(hashMap);
    }

    @Override
    public int updateResourcestate(String resourceId) {
        return dao.updateResourcestate(Integer.parseInt(resourceId));
    }

    @Override
    public int addhuigang(Visitrecord visitrecord) {
        return visitrecordDao.addhuigang(visitrecord);
    }

    @Override
    public List<Visitrecord> huifang(String roleid, String userid, String deptid, String resourceId) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("roleid",roleid);
        hashMap.put("userid",userid);
        hashMap.put("deptid",deptid);
        hashMap.put("resourceId",resourceId);
        return dao.huifang(hashMap);
    }
}
