package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Systemmessage;

import java.util.List;
/*
 *消息管理方法接口
 * */
public interface SystemmessageDao {
    public List<Systemmessage> querySystemmessageAll(PageBean pageBean);
    public int querySystemmessageAllCount();
    public int insertSubject(Systemmessage systemmessage);
    public int updateSubjet(Systemmessage systemmessage);
    public int sendMessage(Systemmessage systemmessage);

}
