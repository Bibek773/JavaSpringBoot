package com.bibek.spring_core_demo.controller;


import com.bibek.spring_core_demo.model.Teacher;

import com.bibek.spring_core_demo.service.TeacherServices;

import java.util.List;

public class TeacherController {
    private TeacherServices teacherService;

    public TeacherController(TeacherServices teacherService){
        this.teacherService= teacherService;
    }
    public void saveTeacher(Teacher teacher){
      teacherService.saveTeacher(teacher);
    }
    public List<Teacher> getAllTeacher(){
        return teacherService.getAllTeachers();
    }
    public Teacher getTeacherById(int id){
        return teacherService.getTeacherById(id);
    }
    public void deleteTeacherById(int id){
        teacherService.deleteTeacherById(id);
    }
    public void updateTeacherById(Teacher teacher){
        teacherService.updateTeacher(teacher);
    }
}

