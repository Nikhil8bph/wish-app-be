package com.bezkoder.springjwt.security.services;

import java.util.List;

import com.bezkoder.springjwt.Exception.ResourceNotFoundException;
import com.bezkoder.springjwt.dto.MessageOnTheCardDto;
import com.bezkoder.springjwt.payload.response.MessageResponse;

public interface MessageDetailsService {
	
	MessageOnTheCardDto addMessage(MessageOnTheCardDto message,Long userId) throws ResourceNotFoundException;
	List<MessageOnTheCardDto> listMessage(Long userId) throws ResourceNotFoundException;
	MessageOnTheCardDto getOneMessage(Long userId,Long messageId) throws ResourceNotFoundException;
	String deleteMessage(Long userId, Long messageId)throws ResourceNotFoundException;
	MessageOnTheCardDto updateMessage(MessageOnTheCardDto message, Long userId, Long messageId) throws ResourceNotFoundException;
	
}
