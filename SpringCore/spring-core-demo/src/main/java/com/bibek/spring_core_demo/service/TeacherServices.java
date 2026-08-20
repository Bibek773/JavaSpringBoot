package com.bibek.spring_core_demo.service;

import com.bibek.spring_core_demo.model.Teacher;
import com.bibek.spring_core_demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServices {

    private final TeacherRepository teacherRepository;

    public TeacherServices(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;

    }
    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();

    }
    public Teacher getTeacherById( int id){
        return teacherRepository.findById(id);
    }
    public void deleteTeacherById(int id){
        teacherRepository.deleteById(id);
    }
    public void updateTeacher(Teacher teacher){
        teacherRepository.update(teacher);
    }
}
