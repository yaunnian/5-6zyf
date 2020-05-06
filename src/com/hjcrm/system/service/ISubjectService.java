package com.hjcrm.system.service;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Subject;

import java.util.List;

/*
 * 科目管理业务接口
 * */
public interface ISubjectService {
    public List<Subject> queryAllSubject(PageBean pageBean);

    public int queryAllSubjectCount();

    public int addOrUpdateSubject(Subject subject);

    public int deleteSubject(String subjectids);

    public List<Subject> querySubject(String courseid);
}
