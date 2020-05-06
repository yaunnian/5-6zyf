package com.hjcrm.system.service.impl;
/*
 * 科目管理业务实现类
 * */
import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Subject;
import com.hjcrm.system.dao.SubjectDao;
import com.hjcrm.system.service.ISubjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("subjectServiceImpl")
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectDao dao;
    @Override
    public List<Subject> queryAllSubject(PageBean pageBean) {
        pageBean.setCurrentPage(pageBean.getPageSize()*(pageBean.getCurrentPage()-1));
        return dao.queryAllSubject(pageBean);
    }

    @Override
    public int queryAllSubjectCount() {
        return dao.queryAllSubjectCount();
    }

    @Override
    public int addOrUpdateSubject(Subject subject) {
        if (subject.getSubjectid()!=0){
            return dao.updateSubjet(subject);
        }else{
            return dao.insertSubject(subject);
        }
    }

    @Override
    public int deleteSubject(String subjectids) {
        if (StringUtils.isNotBlank(subjectids)){
            List<String> list=new ArrayList<String>();
            for (String id:subjectids.split(",")) {
                list.add(id);
            }
            return dao.deleteSubject(list);
        }
        return -1;
    }

    @Override
    public List<Subject> querySubject(String courseid) {

        return dao.querySubject(Integer.parseInt(courseid));
    }

 /*   @Override
    public int deleteSubject(String subjecti) {
        if (StringUtils.isNotBlank(subjectids)) {
            List<String> list = new ArrayList<String>();
            for (String id : subjectids.split(",")) {
                list.add(id);
            }
            return dao.deleteSubject(list);
        }
        return -1;
    }*/
}
