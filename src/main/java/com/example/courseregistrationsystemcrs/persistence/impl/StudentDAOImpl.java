package com.example.courseregistrationsystemcrs.persistence.impl;

import com.example.courseregistrationsystemcrs.entity.Student;
import com.example.courseregistrationsystemcrs.persistence.DBConnection;
import com.example.courseregistrationsystemcrs.persistence.dao.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void save(Student student) throws SQLException {
        String sqlQuery = "INSERT INTO student (student_id, name, date_of_birth, program, student_year, contact) " +
                "VALUES (?,?,?,?,?,?)";

        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlQuery);

        ps.setString(1, student.getStudentId());
        ps.setString(2, student.getName());
        ps.setString(3, student.getDob());
        ps.setString(4, student.getStudyProgram());
        ps.setInt(5, student.getYear());
        ps.setString(6, student.getContact());

        ps.executeUpdate();
    }

    @Override
    public void update(Student student) throws SQLException {
        String sqlQuery = "UPDATE student SET name=?, date_of_birth=?, program=?, student_year=?, contact=?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlQuery);

        ps.setString(1, student.getName());
        ps.setString(2, student.getDob());
        ps.setString(3, student.getStudyProgram());
        ps.setInt(4, student.getYear());
        ps.setString(5, student.getContact());

        ps.executeUpdate();
    }

    @Override
    public void delete(String studentId) throws SQLException {
        String sqlQuery = "DELETE student FROM student WHERE student_id =?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlQuery);

        ps.setString(1, studentId);
        ps.executeUpdate();
    }

    @Override
    public Student find(String studentId) throws SQLException {
        String sqlQuery = "SELECT * FROM student WHERE student_id =?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sqlQuery);

        ps.setString(1, studentId);
        ResultSet resultSet = ps.executeQuery();

        if(resultSet.next()){
            return new Student(
                    resultSet.getString("student_id"),
                    resultSet.getString("name"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("program"),
                    resultSet.getInt("student_year"),
                    resultSet.getString("contact")
            );
        }
        return null;
    }

    @Override
    public ArrayList<Student> findAll() throws SQLException {
        String sqlQuery = "SELECT * FROM student";
        Statement statement = DBConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        ArrayList<Student> studentArrayList = new ArrayList<>();
        while (resultSet.next()){
            studentArrayList.add(new Student(
                    resultSet.getString("student_id"),
                    resultSet.getString("name"),
                    resultSet.getString("date_of_birth"),
                    resultSet.getString("program"),
                    resultSet.getInt("student_year"),
                    resultSet.getString("contact")
            ));
        }
        return studentArrayList;
    }
}
