package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.User;

import java.util.HashMap;
import java.util.List;

/*
 *用户管理方法接口
 * */
public interface UserDao {
    public User queryUser(User user);

    public User queryUserEmail(User user);

    public List<User> queryAllUserInfo(PageBean pb);

    public int deleteUserInfoByIds(List list);

    public int insertUser(User user);

    public int queryAllUserInfoCount();

    public int updateUserInfo(User user);

    public int editPassword(HashMap<String, String> hashMap);

}
