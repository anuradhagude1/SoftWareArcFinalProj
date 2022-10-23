package com.work.educhatroom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.work.educhatroom.model.Faculty;

public interface FacultyService {
	List<Faculty> getAllFaculty();
	void saveFaculty(Faculty faculty);
	Faculty getFacultyId(long id);
	void deleteFacultyById(long id);
	Page<Faculty> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
