package com.accenture.studentDetails.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.studentDetails.table.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	Optional<Student> findStudentByEmail(String email);
}
