package com.culture.api.gameserver.repository;

import org.springframework.transaction.annotation.Transactional;

import com.culture.api.gameserver.domain.GameChangeEvent;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface EventRepository extends JpaRepository<GameChangeEvent,Long>{
	
	@Query("SELECT e FROM GameChangeEvent e WHERE e.sessionId = :sessionId")
	List<GameChangeEvent> findAllBySessionId(@Param("sessionId") String sessionId);
}
