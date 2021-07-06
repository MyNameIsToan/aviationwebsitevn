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

@WebServlet(urlPatterns = {"/screener-delete"})
public class DeletePhoto extends HttpServlet{
	private PhotoService photoService;
	
	public DeletePhoto() {
		this.photoService = new PhotoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			String photo = req.getParameter("photo");
			if(photoService.DeletePhoto(Long.valueOf(photo))==1) {
				System.out.println("Deleted");
			}else {
				System.out.println("Failed");
			}
			resp.sendRedirect("./screener-allphoto");
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
