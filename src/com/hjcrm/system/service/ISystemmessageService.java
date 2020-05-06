package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Systemmessage;

import java.util.List;

/*
 * 消息管理业务接口
 * */
public interface ISystemmessageService {
    public List<Systemmessage> querySystemmessageAll(PageBean pageBean);

    public int querySystemmessageAllCount();

    public int addOrUpdateSubject(Systemmessage systemmessage);

    public int sendMessage(Systemmessage systemmessage);
}
