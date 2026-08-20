package com.bibek.spring_core_demo.repository;

import com.bibek.spring_core_demo.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void save(Student student) {
        students.add(student);

    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Student findById(int idNumber) {
        for (Student student : students) {
            if (student.getIdNumber() == idNumber) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int idNumber) {
        for (Student student : students) {
            if (student.getIdNumber() == idNumber) {
                students.remove(student);
                return;
            }
        }
    }

    @Override
    public void update(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getIdNumber() == student.getIdNumber()) {
                students.set(i, student);
                return;
            }
        }
    }
}