package com.culture.api.gameserver.service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.culture.api.gameserver.domain.GameChangeEvent;

@Service
public class EventGenerator {
	
	@Autowired
	EventService eventService;
	
	private boolean process = true;
	
	@Async
	public Future<String> createEvents(){
		
		Random r = new Random();
		int counter = 0;
		while(process){
			
			
			
			String transactionId = "EventId-" + (r.nextInt(10000) + 1);
			GameChangeEvent event = new GameChangeEvent();
			event.setTransactionId(transactionId);
			event.setCreatedDateTime(new Date());
			
			eventService.saveAndFlush(event);
			System.out.println("Creating event");
			
			if(++counter > 100){
				process = false;
			}
			
			
		}
		
		
		return new AsyncResult<String>("done");
		
		
	}

}
