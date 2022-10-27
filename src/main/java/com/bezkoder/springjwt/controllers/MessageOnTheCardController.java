package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.Exception.ResourceNotFoundException;
import com.bezkoder.springjwt.dto.MessageOnTheCardDto;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.security.services.MessageDetailsService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/message")
public class MessageOnTheCardController {

	@Autowired
	private MessageDetailsService messageDetailsService;
	
	
	@PostMapping("/user/{userId}/messageonthecard")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageOnTheCardDto> createPost(@RequestBody MessageOnTheCardDto messageOnTheCardDto, @PathVariable Long userId) throws ResourceNotFoundException {
		MessageOnTheCardDto messageOnTheCardDtoCreated = this.messageDetailsService.addMessage(messageOnTheCardDto, userId);
		return new ResponseEntity<MessageOnTheCardDto>(messageOnTheCardDtoCreated, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{userId}/messageonthecard/{messageId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageOnTheCardDto> updatePost(@RequestBody MessageOnTheCardDto messageOnTheCardDto, @PathVariable Long userId , @PathVariable Long messageId) throws ResourceNotFoundException {
		MessageOnTheCardDto messageOnTheCardDtoCreated = this.messageDetailsService.updateMessage(messageOnTheCardDto, userId, messageId);
		return new ResponseEntity<MessageOnTheCardDto>(messageOnTheCardDtoCreated, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{userId}/messageonthecard")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<MessageOnTheCardDto>> getAllPosts(@PathVariable Long userId) throws ResourceNotFoundException {
		List<MessageOnTheCardDto> messageOnTheCardDtoFetched = this.messageDetailsService.listMessage(userId);
		return new ResponseEntity<List<MessageOnTheCardDto>>(messageOnTheCardDtoFetched, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/messageonthecard/{messageId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageOnTheCardDto> getOnePost(@PathVariable Long userId , @PathVariable Long messageId) throws ResourceNotFoundException {
		MessageOnTheCardDto messageOnTheCardDtoCreated = this.messageDetailsService.getOneMessage(userId, messageId);
		return new ResponseEntity<MessageOnTheCardDto>(messageOnTheCardDtoCreated, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userId}/messageonthecard/{messageId}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> deleteOnePost(@PathVariable Long userId , @PathVariable Long messageId) throws ResourceNotFoundException {
		String message = this.messageDetailsService.deleteMessage(userId, messageId);
		return new ResponseEntity<MessageResponse>(new MessageResponse(message), HttpStatus.OK);
	}
}
