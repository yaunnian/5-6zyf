package com.hjcrm.resource.dao;

import com.hjcrm.resource.bean.Resource;
import com.hjcrm.resource.bean.Visitrecord;
import com.hjcrm.system.bean.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* 资源方法接口
* */
public interface ResourceDao {

    public List<Resource> queryAllResource(Map<String, Object> hashMap);

    public int addResource(Resource resource);

    public int updateResource(Resource resource);

    public List<Resource> queryAllCreatePerson();

    public List<Resource> queryResourceCount(HashMap<String, String> hashMap);

    public int count();

    public int deleteResources(List<String> list);

    public List<Resource> queryAllXiaoShou();

    public int assigntoResource(HashMap<String, String> hashMap);

    public int assignResource(HashMap<String, String> hashMap);

    public List<Role> queryXiaoShouByRoleid(int id);

    public List<Resource> details(HashMap<String, Object> hashMap);

    public int updateResourcestate(int resourceId);

    public List<Visitrecord> huifang(HashMap<String, Object> hashMap);
}
