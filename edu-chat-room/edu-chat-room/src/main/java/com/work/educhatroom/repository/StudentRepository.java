package com.work.educhatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.educhatroom.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
