package com.hjcrm.system.controller;
import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.system.bean.Menu;
import com.hjcrm.system.bean.User;
import com.hjcrm.system.service.impl.MenuServiceImpl;
import com.hjcrm.system.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/*
* 登陆管理
* */
@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    MenuServiceImpl menuService;
    /*
    * 跳转登陆页面
    * */
    @RequestMapping(value="/login.do",method=RequestMethod.GET)
    public String login(Model model){
        return JumpViewConstants.SYSTEM_LOGIN;
    }

    /*
    * 验证登陆信息
    *
    * */
    @RequestMapping(value = "/toLogin.do",method = RequestMethod.POST)
    public
    String toLogin(String sign, String email, String password, HttpServletRequest request, Model model){
        String eamils=email+sign;
        System.out.println(password);
        System.out.println(eamils);
        if(StringUtils.isNotBlank(email)&&StringUtils.isNotBlank(password)) {
            User user=userService.queryUserEmail(new User(eamils));
            User userpassword=userService.queryUser(new User(eamils, password));
            if(user==null){
                model.addAttribute("msg",ReturnConstants.USER_NOT_EXIST);
                return JumpViewConstants.SYSTEM_LOGIN;
            }else
            if (userpassword == null) {
               model.addAttribute("msg",ReturnConstants.PSSWORD_ERROR);
                return JumpViewConstants.SYSTEM_LOGIN;
            }else {
                UserContext.setLoginUser(user);
                return "redirect:main.do";
            }
        }else {
            return ReturnConstants.PARAM_NULL;
        }
    }
    /*
    * 显示主页面信息
    * */
    @RequestMapping("/main.do")
    public String index(Model model){
        if(UserContext.getLoginUser()!=null){
            List<Menu> menus = menuService.queryMenuRolrId(UserContext.getLoginUser().getRoleid() + "");
            for (int i = 0; i <menus.size() ; i++) {
                System.out.println("zhao:"+menus.get(i));
            }
            model.addAttribute("menus",menus);
            //model.addAttribute("","");
            return JumpViewConstants.SYSTEM_INDEX;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 退出登陆
    *
    * */
    @RequestMapping("/logout.do")
    public String logout(Model model){
        UserContext.clearLoginUser();
        return JumpViewConstants.SYSTEM_LOGIN;
    }



}
