package com.example.courseregistrationsystemcrs.entity;

public class Student {
    private String studentId;
    private String name;
    private String dob;
    private String studyProgram;
    private int year;
    private String contact;

    public Student(String studentId, String name, String dob, String studyProgram, int year, String contact) {
        this.studentId = studentId;
        this.name = name;
        this.dob = dob;
        this.studyProgram = studyProgram;
        this.year = year;
        this.contact = contact;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
