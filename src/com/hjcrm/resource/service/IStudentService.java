package com.hjcrm.resource.service;

import com.hjcrm.resource.bean.Student;

import java.util.HashMap;
import java.util.List;

public interface IStudentService {
    public int addDealrecord(Student student);
    public List<Student> queryStudents(String userid, String deptid, String roleid, Integer currentPage, Integer pageSize);
    public int StudentAllCount();
    public int addStudent(Student student);
    public int updateStudentName(String resourceId);
    public List<Student> studentDetails(String roleid, String userid, String deptid, String studentId);
    public int studentCommit(String studentIds, String resourceIds);
    public int returnStudent(String studentIds, String returnNote);
}
