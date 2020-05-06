package com.hjcrm.resource.controller;

import com.hjcrm.contstans.JumpViewConstants;
import com.hjcrm.contstans.PageBean;
import com.hjcrm.contstans.ReturnConstants;
import com.hjcrm.contstans.UserContext;
import com.hjcrm.resource.bean.Resource;
import com.hjcrm.resource.bean.Visitrecord;
import com.hjcrm.resource.service.Impl.ResourceServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/*
* 资源管理
* */
@Controller
public class ResourceController {
    @Autowired
    private ResourceServiceImpl service;
    /*
    * 跳转运营资源管理页面
    * */
    @RequestMapping("/resource/resourcesMang.do")
    public String resourcesMang(){
        if(UserContext.getLoginUser()!=null){
            return JumpViewConstants.OPERATE_RESOURCES_MANG;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 查询当前用户的资源信息
    * */
    @RequestMapping("/resource/queryResource.do")
    public @ResponseBody String queryAllResource(String userid, String deptid, String roleid,Integer currentPage,Integer pageSize){
        System.out.println("参数啊"+userid+deptid+roleid+currentPage+pageSize);
            if (StringUtils.isNotBlank(userid)){
                PageBean pageBean=new PageBean();
                pageBean.setCurrentPage(currentPage);
                pageBean.setPageSize(pageSize);
                List<Resource> list = service.queryAllResource(userid, deptid, roleid, currentPage,pageSize);
                String json = PageBean.jsonUserInfoToPage(list,service.count(),currentPage);
                return  json;
            }
            return ReturnConstants.PARAM_NULL;
    }
    /*
    * 查询所有的归属者
    * */
    @RequestMapping("/resource/queryAllCreatePerson.do")
    public @ResponseBody String queryAllCreatePerson(){
        List<Resource> list = service.queryAllCreatePerson();
        String json = PageBean.jsonToPage(list, null);
        return json;
    }
    /*
    * 资源的增加和修改
    * */
    @RequestMapping("/resource/addResource.do")
    public @ResponseBody String addResource(Resource resource){
        System.out.println("增加数据："+resource);
            service.addResource(resource);
        Visitrecord visitrecord=new Visitrecord();
        visitrecord.setResourceId(resource.getResourceId());
        visitrecord.setVisitRecord(resource.getVisitRecord());
        int addhuigang = service.addhuigang(visitrecord);

        return ReturnConstants.PARAM_SUCCESS;
    }
    /*
    * 所有资源
    * */
    @RequestMapping("/resource/queryResourceCount.do")
    public @ResponseBody String queryResourceCount(String userid, String roleid, String deptid) {
        List<Resource> list = service.queryResourceCount(userid, roleid, deptid);
        String s = PageBean.jsonToPage(list, null);
        return s;
    }
    /*
    * 资源的删除
    * */
    @RequestMapping("/resource/deleteResources.do")
    public @ResponseBody String deleteResources(String userid,String resourceId){
        System.out.println("删除数据"+userid+"/////////////"+resourceId);
        if (StringUtils.isNotBlank(resourceId)){
            if(UserContext.getLoginUser()!=null) {
                service.deleteResources(resourceId);
                return ReturnConstants.PARAM_SUCCESS;
            }
            return ReturnConstants.PARAM_NULL;
        }
        return ReturnConstants.PARAM_NULL;
    }

    /*
    * 查询所有的销售人员名称
    * */
    @RequestMapping("/resource/queryAllXiaoShou.do")
    public @ResponseBody String queryAllXiaoShou(){
        if (UserContext.getLoginUser()!=null){
            List<Resource> list = service.queryAllXiaoShou();
            String json = PageBean.jsonToPage(list, null);
            return json;
        }
        return JumpViewConstants.SYSTEM_LOGIN;
    }
    /*
    * 分配资源
    * */
        @RequestMapping("/resource/assigntoResource.do")
        public @ResponseBody String assigntoResource(String resourceIds, String belongid){
            System.out.println("生生世世是0"+resourceIds+"asdsa"+belongid);
            List<String> list=new ArrayList<>();
            HashMap<String,String> hashMap=new HashMap<>();
            for (String resourceId:resourceIds.split(",")
                 ) {
                list.add(resourceId);
            }
            for (int i=0;i<list.size();i++) {
                service.assigntoResource(list.get(i), belongid);
            }
            return ReturnConstants.PARAM_SUCCESS;
        }
        /*
        * 转销资源
        * */
        @RequestMapping("/resource/assignResource.do")
        public @ResponseBody String assignResource(String resourceIds, String belongid){
            List<String> list=new ArrayList<>();
            HashMap<String,String> hashMap=new HashMap<>();
            for (String resourceId:resourceIds.split(",")
                    ) {
                list.add(resourceId);
            }
            for (int i=0;i<list.size();i++) {
                service.assignResource(list.get(i), belongid);
            }
            return ReturnConstants.PARAM_SUCCESS;
        }
        /*
        * 转移资源
        * */
        @RequestMapping("/resource/assignResourceAndRecord.do")
    public @ResponseBody String assignResourceAndRecord(String resourceIds, String belongid){
            System.out.println("生生世世是1"+resourceIds+"asdsa"+belongid);
            service.assigntoResource(resourceIds,belongid);
            return ReturnConstants.PARAM_SUCCESS;
        }

}
