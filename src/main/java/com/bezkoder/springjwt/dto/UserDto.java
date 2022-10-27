package com.bezkoder.springjwt.dto;

import java.util.HashSet;
import java.util.Set;

import com.bezkoder.springjwt.models.Role;

public class UserDto {
	
	private Long id;

	private String username;

	private String email;

	private String displayname;
	
	private String password;

	private Set<Role> roles = new HashSet<>();

	public UserDto() {
	}

	public UserDto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	
}
