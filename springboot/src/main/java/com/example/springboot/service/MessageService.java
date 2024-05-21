package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.models.MessageModel;
import com.example.springboot.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public void saveMessage(String contentMessage, String author) {

		MessageModel message = new MessageModel();
		message.setMessage(contentMessage);
		message.setAuthor(author);

		messageRepository.save(message);
		
	}

}
