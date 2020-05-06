package com.hjcrm.patter.service.Impl;

import com.hjcrm.patter.bean.Patter;
import com.hjcrm.patter.dao.PatterDao;
import com.hjcrm.patter.service.IPatterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 我的话术业务成代码
 * */
@Service("patterServiceImpl")
public class PatterServiceImpl implements IPatterService {
    @Autowired
    private PatterDao dao;

    /*
     * 查询该用户所有的话术场景
     * */
    @Override
    public List<Patter> queryPattertype(String userid, String courseid) {
        System.out.println(Integer.parseInt(userid) + "asdsadsa" + Integer.parseInt(courseid));
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("userid", userid);
        hashMap.put("courseid", courseid);
        return dao.queryPattertype(hashMap);
    }

    /*
     * 查询该场景下的话术
     * */
    @Override
    public List<Patter> queryAllPatter(String userid, String courseid, String patterTypeId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userid", userid);
        hashMap.put("courseid", courseid);
        hashMap.put("patterTypeId", patterTypeId);
        return dao.queryAllPatter(hashMap);
    }

    /*
     * 我的话术的增加和修改
     * */
    @Override
    public int saveOrUpdatePatter(Patter patter) {
        if (patter.getPatterid() != 0) {
            return dao.updatePatter(patter);
        } else {
            return dao.insertPatter(patter);
        }
    }

    /*
     * 我的话术删除
     * */
    @Override
    public int deletePatter(String userid, String patterids) {
        System.out.println(patterids);
        if (StringUtils.isNotBlank(patterids)) {
            List<String> list = new ArrayList<>();
            for (String id : patterids.split(",")
                    ) {
                list.add(id);
            }
            return dao.deletePatter(list);
        }
        return -1;
    }

    /*
     * 共享
     * */
    @Override
    public int updatePatterIsShare(Patter patter) {
        return dao.updatePatterIsShare(patter);
    }

}
