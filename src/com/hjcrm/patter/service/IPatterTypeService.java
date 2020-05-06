package com.hjcrm.patter.service;

import com.hjcrm.patter.bean.PatterType;

import java.util.List;
/*
* 话术场景的业务层接口
* */
public interface IPatterTypeService {
    public List<PatterType> queryPatterTypeAll();

    public int addPattertype(PatterType patterType);

    public int deletePatterType(String ids);

}
