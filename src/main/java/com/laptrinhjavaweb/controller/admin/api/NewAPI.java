package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;



@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -915988021506484384L;
	
	@Inject 
	private INewService newService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); //trả về kiểu dữ liệu là json cho client
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class); //chuyển dữ liệu từ json sang dạng model
		UserModel user = (UserModel)SessionUtil.getInstance().getValue(request, "USERMODEL");
		newsModel.setCreatedBy(user .getUserName());
		newsModel = newService.save(newsModel);
		mapper.writeValue(response.getOutputStream(), newsModel.getId());
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); //trả về kiểu dữ liệu là json cho client
		NewsModel newsModel = HttpUtil.of(request.getReader()).toModel(NewsModel.class); // chuyển dữ liệu từ json sang dạng model
		UserModel user = (UserModel)SessionUtil.getInstance().getValue(request, "USERMODEL");
		newsModel.setCreatedBy(user .getUserName());
		newsModel = newService.update(newsModel);
		mapper.writeValue(response.getOutputStream(),newsModel.getId());
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		NewsModel newModel =  HttpUtil.of(request.getReader()).toModel(NewsModel.class);
		newService.delete(newModel.getIds());
		mapper.writeValue(response.getOutputStream(), "{}");
	}

}
