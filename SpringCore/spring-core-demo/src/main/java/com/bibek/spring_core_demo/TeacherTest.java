package com.bibek.spring_core_demo;

import com.bibek.spring_core_demo.controller.TeacherController;
import com.bibek.spring_core_demo.model.Teacher;
import com.bibek.spring_core_demo.repository.TeacherRepository;
import com.bibek.spring_core_demo.repository.TeacherRepositoryImpl;
import com.bibek.spring_core_demo.service.TeacherServices;

public class TeacherTest {

    public static void main(String[] args) {

        // Repository
        TeacherRepository repository = new TeacherRepositoryImpl();

        // Service
        TeacherServices service = new TeacherServices(repository);

        // Controller
        TeacherController controller = new TeacherController(service);

        // Create teachers
        Teacher teacher1 = new Teacher();
        teacher1.setId(1);
        teacher1.setName("Ram");
        teacher1.setAge(35);
        teacher1.setSubject("Java");

        Teacher teacher2 = new Teacher();
        teacher2.setId(2);
        teacher2.setName("Shyam");
        teacher2.setAge(40);
        teacher2.setSubject("Database");

        // SAVE
        controller.saveTeacher(teacher1);
        controller.saveTeacher(teacher2);

        System.out.println("After Save:");
        System.out.println(controller.getAllTeacher());

        // FIND BY ID
        System.out.println("\nFind Teacher with ID 1:");
        System.out.println(controller.getTeacherById(1));

        // UPDATE
        teacher1.setName("Ram Kumar");
        teacher1.setAge(36);
        teacher1.setSubject("Spring Boot");

        controller.updateTeacherById(teacher1);

        System.out.println("\nAfter Update:");
        System.out.println(controller.getAllTeacher());

        // DELETE
        controller.deleteTeacherById(2);

        System.out.println("\nAfter Delete:");
        System.out.println(controller.getAllTeacher());
    }
}