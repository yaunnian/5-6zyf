package com.hjcrm.patter.dao;

import com.hjcrm.patter.bean.PatterType;

import java.util.List;
/*
* 话术场景的方法接口
* */
public interface PatterTypeDao {
    public List<PatterType> queryPatterTypeAll();

    public int insertPattertype(PatterType patterType);

    public int updatePattertype(PatterType patterType);

    public int deletePatterType(List<String> id);
}
