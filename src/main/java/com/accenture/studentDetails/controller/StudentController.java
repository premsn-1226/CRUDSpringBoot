package com.accenture.studentDetails.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.accenture.studentDetails.repo.StudentRepo;
import com.accenture.studentDetails.table.Student;

import io.micrometer.core.ipc.http.HttpSender.Request;
import io.micrometer.core.ipc.http.HttpSender.Response;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
	
	@Autowired
	private StudentRepo studentRepo; 
	
	RestTemplate rest = new RestTemplate();
	
	@GetMapping("/getStudent")
	public List<Student> getStudent() {
		System.out.println("api hit for getting student details");
		return studentRepo.findAll();
	}
	
	@GetMapping("/getStudentById")
	public Optional<Student> getStudentById(@RequestParam int id) {
		System.out.println("api hit for getting student details by id");
		Optional<Student> student = studentRepo.findById(id);
		return student;
	}
	
	
//	@GetMapping("/saveStudent")
//	public ResponseEntity<Student> getStudentData(@RequestParam String name, @RequestParam String email, @RequestParam String department, 
//			@RequestParam Long phone,@RequestParam Integer age ) {
//		System.out.println("api hit for loading data "+name);
//		Optional<Student> emailValidate = studentRepo.findStudentByEmail(email);
//		Student stu = new Student();
//		stu.setName(name);
//		stu.setEmail(email);
//		stu.setDepartment(department);
//		stu.setMobileNo(phone);
//		stu.setAge(age);
//		if (emailValidate.isPresent()) {
//			throw new IllegalStateException("Student details already exists");
//		} else {
//			studentRepo.save(stu);
//			return ResponseEntity.ok(stu);
//		}
//	}
	@PostMapping("/saveStudent")
	public String addStudent(@RequestParam String name, @RequestParam String email, @RequestParam String department, 
			@RequestParam Long phone,@RequestParam Integer age ){
		System.out.println("api hit for adding student");
		JSONObject json = new JSONObject();
		Optional<Student> emailValidate = studentRepo.findStudentByEmail(email);
		Student stu = new Student();
		Student student = null;
		if(emailValidate.isPresent()) {
			student = studentRepo.findById(emailValidate.get().getId()).get();
			if(!student.getName().equals(name)) {
				student.setName(name);
			}
			if(!student.getEmail().equals(email)) {
				student.setEmail(email);
			}
			if(!student.getDepartment().equals(department)) {
				student.setDepartment(department);
			}
			if(!student.getAge().equals(age)) {
				student.setAge(age);
			}
			if(!student.getMobileNo().equals(phone)) {
				student.setMobileNo(phone);
			}
		}else {
		
		stu.setName(name);
		stu.setEmail(email);
		stu.setDepartment(department);
		stu.setMobileNo(phone);
		stu.setAge(age);
		}
		//studentRepo.save(stu);
		if (emailValidate.isPresent()) {
			studentRepo.save(student);
			json.put("id", stu.getId());
			json.put("status", false);
		} else {
			studentRepo.save(stu);
			json.put("id", stu.getId());
			json.put("status", true);
		}
		return json.toString();
	}
	
//	@PostMapping("/saveStudent")
//	public String addStudent(@RequestParam String name, @RequestParam String email, @RequestParam String department, 
//			@RequestParam Long phone,@RequestParam Integer age ){
//		System.out.println("api hit for adding student");
//		JSONObject json = new JSONObject();
//		Optional<Student> emailValidate = studentRepo.findStudentByEmail(email);
//		Student stu = new Student();
//		stu.setName(name);
//		stu.setEmail(email);
//		stu.setDepartment(department);
//		stu.setMobileNo(phone);
//		stu.setAge(age);
//		//studentRepo.save(stu);
//		if (emailValidate.isPresent()) {
//			
//			json.put("id", emailValidate.get().getId());
//			json.put("status", false);
//		} else {
//			studentRepo.save(stu);
//			json.put("id", stu.getId());
//			json.put("status", true);
//		}
//		return json.toString();
//	}
	
	@DeleteMapping("/deleteStudent")
	public void deleteStudent(@RequestParam int id){
		System.out.println("api hit for deleting student");
		JSONObject json = new JSONObject();
		studentRepo.deleteById(id);
	}
	
}
