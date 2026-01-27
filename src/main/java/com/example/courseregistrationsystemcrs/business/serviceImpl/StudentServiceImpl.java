package com.example.courseregistrationsystemcrs.business.serviceImpl;

import com.example.courseregistrationsystemcrs.business.service.StudentService;
import com.example.courseregistrationsystemcrs.entity.Student;
import com.example.courseregistrationsystemcrs.persistence.dao.StudentDAO;
import com.example.courseregistrationsystemcrs.persistence.impl.StudentDAOImpl;

import java.util.ArrayList;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public void addNewStudent(Student student) throws Exception {
        //Business rule 1: Student ID must not be empty.
        if(student.getStudentId() ==  null || student.getStudentId().isEmpty()){
            throw new IllegalArgumentException("Student ID cannot be empty");
        }

        //Business rule 2: No duplicate student IDs.
        if(studentDAO.find(student.getStudentId()) != null){
            throw new IllegalArgumentException("Student already exists");
        }

        //Business rule 3: Academic year must be valid
        if(student.getYear() <= 0){
            throw new IllegalArgumentException("Invalid academic year");
        }

        studentDAO.save(student);
    }

    @Override
    public void updateStudent(Student student) throws Exception {

        //Student must exist before update
        if(studentDAO.find(student.getStudentId()) == null){
            throw new RuntimeException("Student does not exist");
        }
        studentDAO.update(student);
    }

    @Override
    public void deleteStudent(String studentId) throws Exception {

        //Student must exist before delete
        if(studentDAO.find(studentId) == null){
            throw new RuntimeException("Student does not exist");
        }
        studentDAO.delete(studentId);
    }

    @Override
    public Student getStudent(String studentId) throws Exception {
        return studentDAO.find(studentId);
    }

    @Override
    public ArrayList<Student> getAllStudents() throws Exception {
        return studentDAO.findAll();
    }
}
