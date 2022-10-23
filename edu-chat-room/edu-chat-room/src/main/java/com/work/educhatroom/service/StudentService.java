package com.work.educhatroom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.work.educhatroom.model.Student;

public interface StudentService {
	List<Student> getAllEmployees();
	void saveEmployee(Student student);
	Student getEmployeeById(long id);
	void deleteEmployeeById(long id);
	Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
