package com.hjcrm.system.service.impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Systemmessage;
import com.hjcrm.system.dao.SystemmessageDao;
import com.hjcrm.system.service.ISystemmessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
 * 消息管理业务实现类
 * */
@Service("systemmessageServiceImpl")
public class SystemmessageServiceImpl implements ISystemmessageService {
    @Autowired
    private SystemmessageDao dao;
    @Override
    public List<Systemmessage> querySystemmessageAll(PageBean pageBean) {
        pageBean.setCurrentPage(pageBean.getPageSize()*(pageBean.getCurrentPage()-1));
        return dao.querySystemmessageAll(pageBean);
    }

    @Override
    public int querySystemmessageAllCount() {
        return dao.querySystemmessageAllCount();
    }

    @Override
    public int addOrUpdateSubject(Systemmessage systemmessage) {
        if (systemmessage.getSystemmessageId()!=0){
            return dao.updateSubjet(systemmessage);
        }else {
            return dao.insertSubject(systemmessage);
        }
    }

    @Override
    public int sendMessage(Systemmessage systemmessage) {
        return dao.sendMessage(systemmessage);
    }
}
