package com.bibek.spring_core_demo;

import com.bibek.spring_core_demo.config.AppConfig;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCoreDemoApplication.class, args);

		ApplicationContext context =new AnnotationConfigApplicationContext(AppConfig.class);

		ObjTest objTest = context.getBean(ObjTest.class);

		System.out.println(objTest);
	}
	StudentRepository repository = new StudentRepositoryImpl();

	StudentService service = new StudentService(repository);

	StudentController controller = new StudentController(service);

	TeacherRepository trepo = new TeacherRepositoryImpl();

	TeacherServices tser = new TeacherServices(trepo);

	TeacherController tcon = new TeacherController(tser);


}
