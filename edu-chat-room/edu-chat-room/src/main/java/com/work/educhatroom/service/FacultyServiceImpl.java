package com.work.educhatroom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.educhatroom.model.Faculty;
import com.work.educhatroom.repository.FacultyRepository;

@Service
public class FacultyServiceImpl implements FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public List<Faculty> getAllFaculty() {
		return facultyRepository.findAll();
	}

	@Override
	public void saveFaculty(Faculty faculty) {
		this.facultyRepository.save(faculty);
	}

	@Override
	public Faculty getFacultyId(long id) {
		Optional<Faculty> optional = facultyRepository.findById(id);
		Faculty faculty = null;
		if (optional.isPresent()) {
			faculty = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return faculty;
	}

	@Override
	public void deleteFacultyById(long id) {
		this.facultyRepository.deleteById(id);
	}

	@Override
	public Page<Faculty> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.facultyRepository.findAll(pageable);
	}
}
