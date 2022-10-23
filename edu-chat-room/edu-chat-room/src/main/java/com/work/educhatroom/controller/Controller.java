package com.work.educhatroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.work.educhatroom.model.Faculty;
import com.work.educhatroom.model.Session;
import com.work.educhatroom.model.Student;
import com.work.educhatroom.service.FacultyService;
import com.work.educhatroom.service.SessionService;
import com.work.educhatroom.service.StudentService;

@org.springframework.stereotype.Controller
public class Controller {

	@GetMapping("")
	public String homePaage(Model model){
		return "home";
	}

	@Autowired
	private StudentService studentService;


	@GetMapping("/studentList")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewStudentForm")
	public String showNewStudentForm(Model model) {
		// create model attribute to bind form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "new_student";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("employee") Student student) {
		// save students to database
		studentService.saveEmployee(student);
		return "redirect:/studentList/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Student student = studentService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("student", student);
		return "update_student";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable (value = "id") long id) {
		this.studentService.deleteEmployeeById(id);
		return "redirect:/studentList/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Student> page = studentService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Student> listStudents = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listStudents", listStudents);
		return "student_list";
	}

	@Autowired
	private FacultyService facultyService;

	@GetMapping("/facultyList")
	public String getAllFaculty(Model model){
		return findPaginatedFaculty(1, "firstName", "asc", model);
	}

	@GetMapping("faculty/page/{pageNo}")
	public String findPaginatedFaculty(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;

		Page<Faculty> page = facultyService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Faculty> listFaculty = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listFaculty", listFaculty);
		return "faculty_list";
	}

	@GetMapping("/showNewFacultyForm")
	public String showNewFacultyForm(Model model) {
		// create model attribute to bind form data
		Faculty faculty = new Faculty();
		model.addAttribute("faculty", faculty);
		return "new_faculty";
	}

	@PostMapping("/saveFaculty")
	public String saveStudent(@ModelAttribute("faculty") Faculty faculty) {
		// save employee to database
		facultyService.saveFaculty(faculty);
		return "redirect:/facultyList/";
	}

	@GetMapping("faculty/showFormForUpdate/{id}")
	public String showFormForUpdateFaculty(@PathVariable ( value = "id") long id, Model model) {

		// get employee from the service
		Faculty faculty = facultyService.getFacultyId(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("faculty", faculty);
		return "update_faculty";
	}

	@GetMapping("/deleteFaculty/{id}")
	public String deleteFaculty(@PathVariable (value = "id") long id) {

		// call delete employee method
		this.facultyService.deleteFacultyById(id);
		return "redirect:/facultyList/";
	}

	@Autowired
	private SessionService sessionService;

	@GetMapping("/sessionList")
	public String getAllSession(Model model){
		return findPaginatedSession(1, "facultyName", "asc", model);
	}

	@GetMapping("session/page/{pageNo}")
	public String findPaginatedSession(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;

		Page<Session> page = sessionService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Session> sessionList = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("sessionList", sessionList);
		return "session_list";
	}

	@GetMapping("/showNewSessionForm")
	public String showNewSessionForm(Model model) {
		Session session = new Session();
		model.addAttribute("session", session);
		return "new_session";
	}

	@PostMapping("/saveSession")
	public String saveSession(@ModelAttribute("session") Session session) {
		sessionService.saveSession(session);
		return "redirect:/sessionList/";
	}

	@GetMapping("session/showFormForUpdate/{id}")
	public String showFormForUpdateSession(@PathVariable ( value = "id") long id, Model model) {

		Session session = sessionService.getSessionById(id);
		model.addAttribute("session", session);
		return "update_session";
	}

	@GetMapping("/deleteSession/{id}")
	public String deleteSession(@PathVariable (value = "id") long id) {
		this.sessionService.deleteSessionById(id);
		return "redirect:/sessionList/";
	}
}
