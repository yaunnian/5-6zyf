package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.User;
import org.apache.poi.util.StringUtil;

import java.util.List;
/*
 * 用户管理业务接口
 * */
public interface IUserService {

    public User queryUser(User user);

    public User queryUserEmail(User user);

    public List<User> queryAllUserInfo(PageBean pb);

    public int deleteUserInfoByIds(String ids);

    public int insertUser(User user);

    public int queryAllUserInfoCount();

    public int updateUserInfo(User user);

    public int editPassword(String newPassword, String userid);
}
