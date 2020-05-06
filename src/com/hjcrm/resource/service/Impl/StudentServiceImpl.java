package com.hjcrm.resource.service.Impl;

import com.hjcrm.resource.bean.Student;
import com.hjcrm.resource.dao.StudentDao;
import com.hjcrm.resource.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller("StudentServiceImpl")
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentDao dao;
    @Override
    public int addDealrecord(Student student) {
        return dao.addDealrecord(student);
    }

    @Override
    public List<Student> queryStudents(String userid, String deptid, String roleid, Integer currentPage, Integer pageSize) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("userid",userid);
        hashMap.put("deptid",deptid);
        hashMap.put("roleid",deptid);
        hashMap.put("currentPage",currentPage-1);
        hashMap.put("pageSize",pageSize);
        return dao.queryStudents(hashMap);
    }

    @Override
    public int StudentAllCount() {
        return dao.StudentAllCount();
    }

    @Override
    public int addStudent(Student student) {
        if (student.getStudentId()!= 0) {
            return dao.updateStudent(student);
        } else {
            return dao.addStudent(student);
        }
    }
    @Override
    public int updateStudentName(String resourceId) {
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("resourceId",resourceId);
        return dao.updateStudentName(hashMap);
    }

    @Override
    public List<Student> studentDetails(String roleid, String userid, String deptid, String studentId) {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("roleid",roleid);
        hashMap.put("userid",userid);
        hashMap.put("deptid",deptid);
        hashMap.put("studentId",studentId);
        return dao.studentDetails(hashMap);
    }

    @Override
    public int studentCommit(String studentIds,String resourceIds ) {
        List<String> list=new ArrayList<>();
        for (String id:studentIds.split(",")
             ) {
            list.add(id);
        }
        return dao.studentCommit(list);
    }

    @Override
    public int returnStudent(String studentId, String returnNote) {
        HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("studentId",studentId);
            hashMap.put("returnNote",returnNote);
        return dao.returnStudent(hashMap);
    }
}
