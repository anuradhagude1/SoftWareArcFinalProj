package com.work.educhatroom.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.work.educhatroom.model.Session;

public interface SessionService {
	List<Session> getAllSessions();
	void saveSession(Session session);
	Session getSessionById(long id);
	void deleteSessionById(long id);
	Page<Session> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
