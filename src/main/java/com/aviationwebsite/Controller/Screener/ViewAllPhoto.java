package com.aviationwebsite.Controller.Screener;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.SERVICE.Implement.PhotoService;
import com.aviationwebsite.constant.SystemConstant;

@WebServlet(urlPatterns = {"/screener-allphoto"})
public class ViewAllPhoto extends HttpServlet{
	private PhotoService photoService;
	
	public ViewAllPhoto() {
		this.photoService = new PhotoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			Photo photo = new Photo();
			photo.setListPhotos(photoService.findAll()); 
			req.setAttribute(SystemConstant.MODEL, photo);
			RequestDispatcher rd = req.getRequestDispatcher("./views/screener/Photo/Photo.jsp");
			rd.forward(req, resp);
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
}
