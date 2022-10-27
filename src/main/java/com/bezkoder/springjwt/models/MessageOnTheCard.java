package com.bezkoder.springjwt.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bezkoder.springjwt.dto.UserDto;

@Entity
@Table(name="messageonthecard")
public class MessageOnTheCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String wish;

	@NotBlank
	@Size(max = 240)
	private String message;

	@Size(max = 120)
	private String sweetNote1;

	@Size(max = 120)
	private String sweetNote2;

	@Size(max = 120)
	private String sweetNote3;
	
	@NotBlank
	@Size(max = 50)
	private String messageFor;
	
	private Date dateDeliver;
	
	private Date dateCreated;
	
	private Date dateModified;
	
	@ManyToOne
    @JoinColumn(name="users_id", nullable=false)
	private User users;

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

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
