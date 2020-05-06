package com.hjcrm.system.service.impl;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.User;
import com.hjcrm.system.dao.UserDao;
import com.hjcrm.system.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
 * 用户管理业务实现类
 * */
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao dao;

    @Override
    public User queryUser(User user) {
        return dao.queryUser(user);

    }

    @Override
    public User queryUserEmail(User user) {
        return dao.queryUserEmail(user);
    }
    @Cacheable(value ="baseCache" ,key = "'queryAllUserInfo'")
    @Override
    public List<User> queryAllUserInfo(PageBean pb) {
        return dao.queryAllUserInfo(pb);
    }

    @Override
    public int deleteUserInfoByIds(String ids) {
/*
        if (StringUtils.isNotBlank(ids)){
            for (String id:ids.split(",")) {
                return dao.deleteUserInfoByIds(ids);
            }
        }
*/
        if (StringUtils.isNotBlank(ids)) {
            List<String> list = new ArrayList<String>();
            for (String id : ids.split(",")) {
                list.add(id);
            }
            return dao.deleteUserInfoByIds(list);
        }
        return -1;
    }

    @Override
    public int insertUser(User user) {
        return dao.insertUser(user);
    }

    @Override
    public int queryAllUserInfoCount() {
        return dao.queryAllUserInfoCount();
    }

    @Override
    public int updateUserInfo(User user) {
        return dao.updateUserInfo(user);
    }

    @Override
    public int editPassword(String newPassword, String userid) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("password", newPassword);
        hashMap.put("userid", userid);
        return dao.editPassword(hashMap);
    }
}
