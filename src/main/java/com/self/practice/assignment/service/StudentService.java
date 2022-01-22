package com.self.practice.assignment.service;

import com.self.practice.assignment.entity.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getAllStudents();

    public Student getStudentById(Integer id);

    public void addNewStudent(Student student);

    public void deleteStudentbyId(Integer id);

    public void modifyStudent(Student student);
}
