package com.bibek.spring_core_demo.controller;

import com.bibek.spring_core_demo.model.Student;
import com.bibek.spring_core_demo.service.StudentService;

import java.util.List;

public class StudentController {

    private final StudentService studentService;

    // StudentService is injected from outside
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public void saveStudent(Student student) {
        studentService.saveStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Student getStudentById(int id) {
        return studentService.getStudentById(id);
    }

    public void updateStudent(Student student) {
        studentService.updateStudent(student);
    }

    public void deleteStudentById(int id) {
        studentService.deleteStudentById(id);
    }
}