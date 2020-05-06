package com.hjcrm.patter.service;

import com.hjcrm.patter.bean.Patter;
import org.apache.poi.util.StringUtil;

import java.util.List;
/*
* 我的话术业务层接口
* */
public interface IPatterService {
    public List<Patter> queryPattertype(String userid, String courseid);

    public List<Patter> queryAllPatter(String userid, String courseid, String patterTypeId);

    public int saveOrUpdatePatter(Patter patter);

    public int deletePatter(String userid, String patterids);

    public int updatePatterIsShare(Patter patter);
}
