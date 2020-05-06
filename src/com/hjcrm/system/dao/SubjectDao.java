package com.hjcrm.system.dao;

import com.hjcrm.contstans.PageBean;
import com.hjcrm.system.bean.Subject;

import java.util.List;
/*
 *科目管理方法接口
 * */
public interface SubjectDao {
    public List<Subject> queryAllSubject(PageBean pageBean);
    public List<Subject> querySubject(int courseid);
    public int insertSubject(Subject subject);
    public int updateSubjet(Subject subject);
    public int queryAllSubjectCount();
    public int deleteSubject(List<String> subjectids);
}
