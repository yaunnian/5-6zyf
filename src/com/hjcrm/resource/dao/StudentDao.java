package com.hjcrm.resource.dao;

import com.hjcrm.resource.bean.Student;

import java.util.HashMap;
import java.util.List;
/*
* 学生方法接口
* */
public interface StudentDao {
    public int addDealrecord(Student student);

    public List<Student> queryStudents(HashMap<String, Object> hashMap);

    public int StudentAllCount();

    public int addStudent(Student student);

    public int updateStudent(Student student);

    public int updateStudentName(HashMap<String, Object> hashMap);

    public List<Student> studentDetails(HashMap<String, String> hashMap);

    public int studentCommit(List<String> list);

    public int returnStudent(HashMap<String, String> hashMap);
}
