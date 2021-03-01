package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.MessageUtil;

@WebServlet(urlPatterns = { "/admin-new" })
public class NewController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private INewService newService;

	@Inject
	private ICategoryService categoryService;
	
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String view = "";
//		NewsModel newModel = FormUtil.toModel(NewsModel.class, request);
		NewsModel newModel = new NewsModel();
		try {

			newModel.setType((String) request.getParameter("type"));
			if (request.getParameter("id") != null) {

				newModel.setId(Long.parseLong(request.getParameter("id")));
			}
			newModel.setPage(Integer.parseInt((String) request.getParameter("page")));
			newModel.setMaxPageItem(Integer.parseInt((String) request.getParameter("maxPageItem")));
			newModel.setSortName((String) request.getParameter("sortName"));
			newModel.setSortBy((String) request.getParameter("sortBy"));


		} catch (Exception e) {
			// TODO: handle exception
		}

		if (newModel.getType().equals(SystemConstant.LIST)) {
			Pageble pageble = new PageRequest(newModel.getPage(), newModel.getMaxPageItem(),
					new Sorter(newModel.getSortName(), newModel.getSortBy()));
			newModel.setListResult(newService.findAll(pageble));
			newModel.setTotalItem(newService.getTotalItem());
			newModel.setTotalPage((int) Math.ceil((double) newModel.getTotalItem() / newModel.getMaxPageItem()));
			view = "/view/admin/new/list.jsp";

		} else if (newModel.getType().equals(SystemConstant.EDIT)) {

			if (newModel.getId() != null) {

				newModel = newService.findOne(newModel.getId());

			}
			request.setAttribute(SystemConstant.CATEGORIES, categoryService.findAll());
			view = "/view/admin/new/edit.jsp";

		}
		
		MessageUtil.showMessage(request);

		request.setAttribute(SystemConstant.NEWSMODEL, newModel);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
