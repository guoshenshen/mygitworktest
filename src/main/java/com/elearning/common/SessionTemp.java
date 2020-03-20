package com.elearning.common;

public enum SessionTemp {
	
	ADMIN_MENU("admin_menu"),
	
	CURRENT_ROLE("userRoleId"),
	
	OPERATOR_ID(Constants.USERINFO_KEY);
	
	
	
	
	
	private SessionTemp(String name){
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	private final String name;
}
