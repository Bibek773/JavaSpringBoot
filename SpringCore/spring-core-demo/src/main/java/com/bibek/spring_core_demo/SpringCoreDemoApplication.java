package com.bibek.spring_core_demo;

import com.bibek.spring_core_demo.controller.StudentController;
import com.bibek.spring_core_demo.controller.TeacherController;
import com.bibek.spring_core_demo.repository.StudentRepository;
import com.bibek.spring_core_demo.repository.StudentRepositoryImpl;
import com.bibek.spring_core_demo.repository.TeacherRepository;
import com.bibek.spring_core_demo.repository.TeacherRepositoryImpl;
import com.bibek.spring_core_demo.service.StudentService;
import com.bibek.spring_core_demo.service.TeacherServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);
	}
	StudentRepository repository = new StudentRepositoryImpl();

	StudentService service = new StudentService(repository);

	StudentController controller = new StudentController(service);

	TeacherRepository trepo = new TeacherRepositoryImpl();

	TeacherServices tser = new TeacherServices(trepo);

	TeacherController tcon = new TeacherController(tser);
}
