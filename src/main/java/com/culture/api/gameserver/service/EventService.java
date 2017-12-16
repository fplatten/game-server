package com.culture.api.gameserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.culture.api.gameserver.domain.GameChangeEvent;
import com.culture.api.gameserver.repository.EventRepository;

@Service
@Repository
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	
	@Transactional
	public List<GameChangeEvent> getAll(){
		
		List<GameChangeEvent> events = eventRepository.findAll();
		
		return events;
	}
	
	@Transactional
	public GameChangeEvent saveAndFlush(GameChangeEvent event){
		
		if(event != null){
			event = eventRepository.saveAndFlush(event);
		}
		
		return event;
		
	}

}
