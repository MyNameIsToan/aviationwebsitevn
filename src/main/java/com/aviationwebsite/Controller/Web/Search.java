package com.aviationwebsite.Controller.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.SERVICE.Implement.PhotoService;
import com.aviationwebsite.constant.SystemConstant;

@WebServlet(urlPatterns = {"/Search"})
public class Search extends HttpServlet{
	private PhotoService photoService;
	public Search() {
		this.photoService = new PhotoService();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String SearchValue = req.getParameter("search");
		Photo photo = new Photo();
		photo.setListPhotos(photoService.SearchPhotos(SearchValue)); 
		req.setAttribute(SystemConstant.MODEL, photo);
		RequestDispatcher rd = req.getRequestDispatcher("./views/web/SearchResult/search.jsp");
		rd.forward(req, resp);
	}
}
