package com.example.springboot.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.springboot.service.MessageService;
import java.util.logging.Logger;

@Component
public class Scheduler {
	
	@Autowired
	private MessageService messageService;
	
	private static final Logger logger = Logger.getLogger(Scheduler.class.getName());
	
	
	@Scheduled(fixedRate = 120000)
	public void scheduleInsertion() {
		logger.info("Iniciando agendamento para inserção de mensagem...");
		messageService.saveMessage("Mensagem Agendada", "Schedule");

		
	}
	
	

}
