package com.example.courseregistrationsystemcrs.persistence.dao;

import com.example.courseregistrationsystemcrs.entity.Student;

import java.util.ArrayList;

public interface StudentDAO {
    void save(Student student) throws Exception;
    void update(Student students) throws Exception;
    void delete(String studentId) throws Exception;
    Student find(String studentId) throws Exception;
    ArrayList<Student> findAll() throws Exception;
}
