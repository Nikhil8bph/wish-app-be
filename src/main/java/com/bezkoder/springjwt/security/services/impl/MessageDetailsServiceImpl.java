package com.bezkoder.springjwt.security.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.Exception.ResourceNotFoundException;
import com.bezkoder.springjwt.dto.MessageOnTheCardDto;
import com.bezkoder.springjwt.models.MessageOnTheCard;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.MessageOnTheCardRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.MessageDetailsService;
import com.bezkoder.springjwt.utils.Utils;

@Service
public class MessageDetailsServiceImpl implements MessageDetailsService {

	@Autowired
	private MessageOnTheCardRepository messageOnTheCardRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MessageOnTheCardDto addMessage(MessageOnTheCardDto message,Long userId) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MessageOnTheCard messageOnTheCard = this.modelMapper.map(message, MessageOnTheCard.class);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));
		if(auth.isAuthenticated() && auth.getName().equalsIgnoreCase(user.getUsername())){
			messageOnTheCard.setDateCreated(new Date());
			messageOnTheCard.setDateModified(new Date());
			messageOnTheCard.setUsers(user);
		}
		else {
			throw new ResourceNotFoundException("Message","User Authentication Failed",userId);
		}
		MessageOnTheCard newMessageOnTheCard = this.messageOnTheCardRepo.save(messageOnTheCard);
		return this.modelMapper.map(newMessageOnTheCard, MessageOnTheCardDto.class);
	}

	@Override
	public MessageOnTheCardDto updateMessage(MessageOnTheCardDto message, Long userId, Long messageId) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		MessageOnTheCard messageOnTheCard = this.messageOnTheCardRepo.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("MessageOnTheCard ", "MessageOnTheCard id", messageId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));

		if(auth.isAuthenticated() && auth.getName().equalsIgnoreCase(user.getUsername()) && messageOnTheCard.getUsers().getId() == user.getId()) {
			if(!message.getMessage().equalsIgnoreCase(messageOnTheCard.getMessage()))
				messageOnTheCard.setMessage(message.getMessage());
			if(!message.getWish().equalsIgnoreCase(messageOnTheCard.getWish()))
				messageOnTheCard.setWish(message.getWish());
			if(!message.getSweetNote1().equalsIgnoreCase(messageOnTheCard.getSweetNote1()))
				messageOnTheCard.setSweetNote1(message.getSweetNote1());
			if(!message.getSweetNote2().equalsIgnoreCase(messageOnTheCard.getSweetNote2()))
				messageOnTheCard.setSweetNote2(message.getSweetNote2());
			if(!message.getSweetNote3().equalsIgnoreCase(messageOnTheCard.getSweetNote3()))
				messageOnTheCard.setSweetNote3(message.getSweetNote3());	
			if(!message.getMessageFor().equalsIgnoreCase(messageOnTheCard.getMessageFor()))
				messageOnTheCard.setMessageFor(message.getMessageFor());
			messageOnTheCard.setDateDeliver(message.getDateDeliver());
			messageOnTheCard.setDateModified(new Date());
		}
		else {
			throw new ResourceNotFoundException("Message","User Authentication Failed",userId);
		}
		MessageOnTheCard newMessageOnTheCard = this.messageOnTheCardRepo.save(messageOnTheCard);
		return this.modelMapper.map(newMessageOnTheCard, MessageOnTheCardDto.class);
	}

	@Override
	public List<MessageOnTheCardDto> listMessage(Long userId) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "User id", userId));
		List<MessageOnTheCard> messageOnTheCardList = null;
		List<String> listRoles = Utils.getRolesList(user.getRoles());
		if(auth.isAuthenticated() && auth.getName().equalsIgnoreCase(user.getUsername())) {
			if(listRoles.contains("ROLE_ADMIN")) {
				messageOnTheCardList = this.messageOnTheCardRepo.findAll();
			}
			else if(listRoles.contains("ROLE_USER")) {
				messageOnTheCardList = this.messageOnTheCardRepo.findAll().stream().filter(messageOnTheCard -> messageOnTheCard.getUsers().getId() == userId).collect(Collectors.toList());
			}
		}
		else {
			throw new ResourceNotFoundException("Message","User Authentication Failed",userId);
		}
		List<MessageOnTheCardDto> messageOnTheCardDtoList = messageOnTheCardList.stream().map((messageOnTheCard) -> this.modelMapper.map(messageOnTheCard, MessageOnTheCardDto.class)).collect(Collectors.toList());

		return messageOnTheCardDtoList;
	}

	@Override
	public MessageOnTheCardDto getOneMessage(Long userId, Long messageId) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		MessageOnTheCard messageOnTheCard = this.messageOnTheCardRepo.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message","Message Id",messageId));
		MessageOnTheCardDto messageOnTheCardDto = null;
		if(auth.isAuthenticated() && auth.getName().equalsIgnoreCase(messageOnTheCard.getUsers().getUsername()) && messageOnTheCard.getUsers().getId()==userId) {
			messageOnTheCardDto = this.modelMapper.map(messageOnTheCard, MessageOnTheCardDto.class);
		}		
		else {
			throw new ResourceNotFoundException("Message","Message Id",messageId);
		}
		return messageOnTheCardDto;
	}

	@Override
	public String deleteMessage(Long userId, Long messageId) throws ResourceNotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		MessageOnTheCard messageOnTheCard = this.messageOnTheCardRepo.findById(messageId)
				.orElseThrow(() -> new ResourceNotFoundException("MessageOnTheCard ", "MessageOnTheCard id", messageId));
		if(auth.isAuthenticated() && auth.getName().equalsIgnoreCase(messageOnTheCard.getUsers().getUsername()) && messageOnTheCard.getUsers().getId()==userId) {
			this.messageOnTheCardRepo.delete(messageOnTheCard);
			return "Deleted Successfully UserId:"+userId;
		}else {
			return "User not authorized with userId:"+userId;
		}
	}



}
