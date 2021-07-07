package com.aviationwebsite.Controller.Web;

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
@WebServlet(urlPatterns = {"/homepage"})
public class HomeController extends HttpServlet{
	private PhotoService photoService;
	
	public HomeController() {
		this.photoService = new PhotoService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = getServletContext().getRealPath("src");
		req.setAttribute("path", path);
		Photo photo = new Photo();
		photo.setListPhotos(photoService.findAll()); 
		req.setAttribute(SystemConstant.MODEL, photo);
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session");
		if(obj != null)
		{
			req.setAttribute("condition", "1");
		}else {
			req.setAttribute("condition", "0");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/views/web/Homepage/homepage.jsp");
		rd.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
