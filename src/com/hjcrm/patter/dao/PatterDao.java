package com.hjcrm.patter.dao;

import com.hjcrm.patter.bean.Patter;

import java.util.HashMap;
import java.util.List;
/*
* 我的话术方法接口
* */
public interface PatterDao {
    ///patter/queryPattertype.do?userid=1&courseid=2
    public List<Patter> queryAllPatter(HashMap<String, String> hashMap);

    public List<Patter> queryPattertype(HashMap<String, String> hashMap);

    public int updatePatterIsShare(Patter patter);

    public int insertPatter(Patter patter);

    public int updatePatter(Patter patter);

    public int deletePatter(List<String> patterids);
    ///patter/updatePatterIsShare.do
}
