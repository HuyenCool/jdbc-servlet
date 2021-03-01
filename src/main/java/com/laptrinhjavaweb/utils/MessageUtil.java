package com.laptrinhjavaweb.utils;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {

	
	public static void showMessage(HttpServletRequest request) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
		String messageResponse = "";
		String arlet = "";
		String message = request.getParameter("message");
		if(message!= null ) {
			if(message.equals("usename_password_invalid")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "danger";
				
			}
			if(message.equals("not_login")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "danger";
				
			}
			if(message.equals("not_permission")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "danger";
				
			}
			if(message.equals("insert_success")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "success";
				
			}else if(message.equals("update_success")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "success";
			}else if(message.equals("delete_success")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "success";
			}else if(message.equals("erorr_system")) {
				messageResponse = resourceBundle.getString(message);
				arlet = "danger";
				
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("arlet", arlet);
		}
	}
}
