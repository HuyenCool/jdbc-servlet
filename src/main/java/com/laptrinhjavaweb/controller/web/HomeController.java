package com.laptrinhjavaweb.controller.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet{

	/**
	 * 
	 */
	@Inject
	private ICategoryService categoryService;
	
	@Inject IUserService userSerice;

	private static final long serialVersionUID = 1L;
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action!= null && action.equals("login")) {
			MessageUtil.showMessage(request);
			RequestDispatcher rd = request.getRequestDispatcher("/view/login.jsp");
			rd.forward(request, response);  
		}else if(action!= null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu"); // sau khi click vào 1 nút thì muốn nó trỏ tới trang nào
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/view/web/home.jsp"); // trả thẳng về 1 view
			rd.forward(request, response);  
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!= null && action.equals("login")) {
			UserModel userModel = new UserModel();
			userModel.setUserName(request.getParameter("userName"));
			userModel.setPassWord(request.getParameter("password"));
			userModel = userSerice.findByUsernameAndPasswordAndStatus(userModel.getUserName(),
					userModel.getPassWord(), 1);
			if(userModel!= null) // đã check username&pasword 
			{
				SessionUtil.getInstance().putValue(request, "USERMODEL", userModel);
				
				if(userModel.getRole().getCode().equals(SystemConstant.USER)) {
					response.sendRedirect(request.getContextPath()+"/trang-chu"); // load đến trang sau
					
				}
				else if(userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=usename_password_invalid");
			}
			
		}
			
	}
	
}