package com.bibek.spring_core_demo.repository;

import com.bibek.spring_core_demo.model.Teacher;

import java.util.List;

public interface TeacherRepository {
    void save(Teacher teacher);
    List<Teacher> findAll();



    Teacher findById(int id);
    void  deleteById(int id);
    void update(Teacher teacher);

}
