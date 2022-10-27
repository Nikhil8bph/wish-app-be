package com.bezkoder.springjwt.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageOnTheCardDto {

	private Long id;

	private String wish;

	private String message;
	
	private String sweetNote1;
	
	private String sweetNote2;

	private String sweetNote3;

	private String messageFor;

	@NotBlank
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateDeliver;
	
	private Date dateCreated;
	
	private Date dateModified;
	
	private UserDto users;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWish() {
		return wish;
	}

	public void setWish(String wish) {
		this.wish = wish;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSweetNote1() {
		return sweetNote1;
	}

	public void setSweetNote1(String sweetNote1) {
		this.sweetNote1 = sweetNote1;
	}

	public String getSweetNote2() {
		return sweetNote2;
	}

	public void setSweetNote2(String sweetNote2) {
		this.sweetNote2 = sweetNote2;
	}

	public String getSweetNote3() {
		return sweetNote3;
	}

	public void setSweetNote3(String sweetNote3) {
		this.sweetNote3 = sweetNote3;
	}

	public String getMessageFor() {
		return messageFor;
	}

	public void setMessageFor(String messageFor) {
		this.messageFor = messageFor;
	}

	public UserDto getUsers() {
		return users;
	}

	public void setUsers(UserDto users) {
		this.users = users;
	}

	public Date getDateDeliver() {
		return dateDeliver;
	}

	public void setDateDeliver(Date dateDeliver) {
		this.dateDeliver = dateDeliver;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}




}
