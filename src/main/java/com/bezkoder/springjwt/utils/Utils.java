package com.bezkoder.springjwt.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.bezkoder.springjwt.models.Role;

public class Utils {
	public static List<String> getRolesList(Set<Role> roles){		
		List<String> listRoles = new ArrayList<>();
		for(Role role: roles) {
			listRoles.add(role.getName().name());
		}	
		return listRoles;		
	}
}
