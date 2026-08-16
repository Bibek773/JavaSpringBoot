package com.bibek.spring_core_demo.service;

import com.bibek.spring_core_demo.repository.StudentRepository;

public class StudentService {
    // A StudentService needs a StudentRepository to work with student data.


    // "final"    → once the repository is assigned, it cannot be replaced.
    // "StudentRepository" → the type is the interface.
    // "studentRepository" → the variable that holds the repository object.

    private final StudentRepository studentRepository;


    // The constructor receives a StudentRepository from outside.
    // This is Dependency Injection in plain Java.
    //
    // Instead of StudentService creating the repository itself:
    //     new StudentRepositoryImpl()
    //
    // someone else provides it.
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        // recive vayeko Repository store garera rakheko
    }
}