package com.example.courseregistrationsystemcrs.business.service;

import com.example.courseregistrationsystemcrs.entity.Student;

import java.util.ArrayList;

public interface StudentService{
    void addNewStudent(Student student) throws Exception;
    void updateStudent(Student student) throws Exception;
    void deleteStudent(String studentId) throws Exception;
    Student getStudent(String studentId) throws Exception;
    ArrayList<Student> getAllStudents() throws Exception;
}
