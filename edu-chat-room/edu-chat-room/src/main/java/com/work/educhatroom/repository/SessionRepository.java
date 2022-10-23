package com.work.educhatroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.educhatroom.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}
