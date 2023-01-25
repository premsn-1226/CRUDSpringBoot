package com.accenture.studentDetails.table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String name;
		private String email;
		private String department;
		private Integer age;
		private Long mobileNo;
		
		public Student(String name, String email, String department, Long mobileNo,Integer age) {
			super();
			this.name = name;
			this.email = email;
			this.department = department;
			this.mobileNo = mobileNo;
			this.age =age;
		}
		public Student() {
			super();
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getDepartment() {
			return department;
		}
		public void setDepartment(String department) {
			this.department = department;
		}
		public Integer getAge() {
			return age;
			//return Period.between(this.date, LocalDate.now()).getYears();
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public Long getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(Long mobileNo) {
			this.mobileNo = mobileNo;
		}
}
