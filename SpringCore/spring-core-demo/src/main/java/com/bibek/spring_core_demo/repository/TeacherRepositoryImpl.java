package com.bibek.spring_core_demo.repository;

import com.bibek.spring_core_demo.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository{
    private final List<Teacher> teachers = new ArrayList<>();

    @Override
    public void save(Teacher teacher){
        teachers.add(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return teachers;
    }

    @Override
    public Teacher findById(int id){
        for(Teacher teacher: teachers){
            if (teacher.getId() == id){
                return teacher;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (Teacher teacher: teachers){
            if(teacher.getId()==id){
                teachers.remove(teacher);
            }
        }
    }

    @Override
    public void update(Teacher teacher) {
        for (int i =0; i< teachers.size(); i++){
            if(teachers.get(i).getId()== teacher.getId()){
                teachers.set(i, teacher);
                return;
            }
        }
    }
}
