package com.bibek.spring_core_demo;

import com.bibek.spring_core_demo.controller.StudentController;
import com.bibek.spring_core_demo.model.Student;
import com.bibek.spring_core_demo.repository.StudentRepository;
import com.bibek.spring_core_demo.repository.StudentRepositoryImpl;
import com.bibek.spring_core_demo.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ManualTest {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.bibek.spring_core_demo");
        StudentController controller = context.getBean(StudentController.class);
      

        Student student = new Student();

        student.setIdNumber(1);
        student.setFirstName("Bibek");
        student.setLastName("Ghimire");
        student.setAge(21);

        // 5. Save the student through the controller
        controller.saveStudent(student);

        // 6. Get all students
        System.out.println(controller.getAllStudents());

        // 7. Find student by ID
        System.out.println(controller.getStudentById(1));

        // 8. Delete student
        controller.deleteStudentById(1);

        // 9. Check again
        System.out.println(controller.getAllStudents());
    }
}