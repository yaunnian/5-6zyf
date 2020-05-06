package com.hjcrm.patter.service.Impl;

import com.hjcrm.patter.bean.PatterType;
import com.hjcrm.patter.dao.PatterTypeDao;
import com.hjcrm.patter.service.IPatterTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * 话术场景业务层代码
 * */
@Service("patterTypeServiceImpl")
public class PatterTypeServiceImpl implements IPatterTypeService {
    @Autowired
    private PatterTypeDao dao;

    /*
     * 查询话术场景
     * */
    @Override
    public List<PatterType> queryPatterTypeAll() {
        return dao.queryPatterTypeAll();
    }

    /*
     * 查询我的话术
     * */
    @Override
    public int addPattertype(PatterType patterType) {
        if (patterType.getPatterTypeId() != 0) {
            return dao.updatePattertype(patterType);
        } else {
            return dao.insertPattertype(patterType);
        }
    }

    /*
     * 删除我的话术
     * */
    @Override
    public int deletePatterType(String ids) {
        if (StringUtils.isNotBlank(ids)) {
            List<String> list = new ArrayList<String>();
            for (String id : ids.split(",")) {
                list.add(id);
            }
            return dao.deletePatterType(list);
        }
        return -1;
    }


}
