package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.resource.bean.Resource;
import com.hjcrm.resource.bean.Visitrecord;
import com.hjcrm.resource.service.Impl.ResourceServiceImpl;
import com.hjcrm.system.bean.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*资源详情
 * */
@Controller
public class ResourceSaController {
    @Autowired
    private ResourceServiceImpl service;

    /*
     * 跳转销售资源管理
     * */
    @RequestMapping("sales/resourcesMang.do")
    public String resourcesMang() {
        if (UserContext.getLoginUser() != null) {
            return JumpViewConstants.SALES_RESOURCES_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }

    /*
     * 查询此部门下的所有销售人员
     * */
    @RequestMapping("/resource/queryXiaoShouByRoleid.do")
    public @ResponseBody
    String queryXiaoShouByRoleid(String deptid) {
        List<Role> list = service.queryXiaoShouByRoleid(deptid);
        String page = PageBean.jsonToPage(list, null);
        return page;
    }

    /*
     * 跳转资源详情页面
     * */
    @RequestMapping("/resource/details.do")
    public String details(String roleid, String userid, String deptid, String resourceId, HttpServletRequest request) {
        System.out.println("详情数据" + roleid + " /" + userid + " /" + deptid + " /" + resourceId);
        List<Resource> details = service.details(roleid, userid, deptid, resourceId);
        List<Visitrecord> huifang = service.huifang(roleid, userid, deptid, resourceId);
        request.getSession().setAttribute("resource", details);
        request.getSession().setAttribute("record", huifang);
        return JumpViewConstants.SALES_DETAILS;
    }

}
