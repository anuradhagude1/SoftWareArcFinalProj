package com.work.educhatroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.work.educhatroom.model.Session;
import com.work.educhatroom.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService{
	@Autowired
	private SessionRepository sessionRepository;

	@Override
	public List<Session> getAllSessions() {
		return sessionRepository.findAll();
	}

	@Override
	public void saveSession(Session session) {
		sessionRepository.save(session);
	}

	@Override
	public Session getSessionById(long id) {
		return sessionRepository.getById(id);
	}

	@Override
	public void deleteSessionById(long id) {
		sessionRepository.deleteById(id);
	}

	@Override
	public Page<Session> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
				Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.sessionRepository.findAll(pageable);
	}
}
