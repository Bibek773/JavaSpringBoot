package com.bibek.spring_core_demo.repository;

import com.bibek.spring_core_demo.model.Student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    List<Student> findAll();

    Student findById(int idNumber);

    void deleteById(int idNumber);
}