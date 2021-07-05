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

@WebServlet(urlPatterns = {"/Profile"})
public class Profile extends HttpServlet{
	private PhotoService photoService;
	
	public Profile() {
		this.photoService = new PhotoService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session");
		if(obj != null)
		{
				String Username = String.valueOf(obj);
				Photo photo = new Photo();
				photo.setListPhotos(photoService.findMyPhoto(Username)); 
				req.setAttribute(SystemConstant.MODEL, photo);
				RequestDispatcher rd = req.getRequestDispatcher("./views/web/Profile/profile.jsp");
				rd.forward(req, resp);
		}else {
			resp.sendRedirect("./views/web/Login/index.jsp");
			return;
		}
	}
}
