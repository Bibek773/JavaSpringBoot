package com.bibek.spring_core_demo;

import com.bibek.spring_core_demo.controller.StudentController;
import com.bibek.spring_core_demo.model.Student;
import com.bibek.spring_core_demo.repository.StudentRepository;
import com.bibek.spring_core_demo.repository.StudentRepositoryImpl;
import com.bibek.spring_core_demo.service.StudentService;

public class ManualTest {

    public static void main(String[] args) {

        // 1. Create the repository implementation
        StudentRepository repository = new StudentRepositoryImpl();

        // 2. Inject repository into the service
        StudentService service = new StudentService(repository);

        // 3. Inject service into the controller
        StudentController controller = new StudentController(service);


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