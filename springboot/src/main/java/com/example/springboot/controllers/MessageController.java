package com.example.springboot.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dtos.MessageRecordDto;
import com.example.springboot.models.MessageModel;
import com.example.springboot.repositories.MessageRepository;

import jakarta.validation.Valid;

@RestController
public class MessageController {

	@Autowired
	MessageRepository messageRepository;

	@PostMapping("/messages")
	public ResponseEntity<MessageModel> saveMessage(@RequestBody @Valid MessageRecordDto dto) {

		var messageModel = new MessageModel();
		BeanUtils.copyProperties(dto, messageModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(messageRepository.save(messageModel));
	}

	@GetMapping("/messages")
	public ResponseEntity<List<MessageModel>> getAllMessages() {

		return ResponseEntity.status(HttpStatus.OK).body(messageRepository.findAll());
	}

	@GetMapping("/messages/{id}")
	public ResponseEntity<Object> getOneMessage(@PathVariable(value = "id") UUID id) {

		Optional<MessageModel> messageId = messageRepository.findById(id);

		if (messageId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(messageId.get());
	}

	@PutMapping("/messages/{id}")
	public ResponseEntity<Object> updateMessage(@PathVariable(value = "id") UUID id,
			@RequestBody @Valid MessageRecordDto dto) {
		Optional<MessageModel> messageId = messageRepository.findById(id);
		if (messageId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
		}

		var messagModel = messageId.get();
		BeanUtils.copyProperties(dto, messagModel);

		return ResponseEntity.status(HttpStatus.OK).body(messageRepository.save(messagModel));
	}

	@DeleteMapping("/messages/{id}")
	public ResponseEntity<Object> deleteMessage(@PathVariable(value = "id") UUID id) {
		Optional<MessageModel> messageId = messageRepository.findById(id);
		if (messageId.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
		}

		messageRepository.delete(messageId.get());

		return ResponseEntity.status(HttpStatus.OK).body("Message deleted successfully");
	}

}
