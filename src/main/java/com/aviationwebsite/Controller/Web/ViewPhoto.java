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

@WebServlet(urlPatterns = {"/viewphoto"})
public class ViewPhoto extends HttpServlet{
	private PhotoService photoService;
	public ViewPhoto() {
		this.photoService = new PhotoService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String photo = req.getParameter("photo");
		Photo returnphoto = photoService.findPhoto(Long.valueOf(photo));
		int viewsupdate = returnphoto.getView() + 1;
		photoService.ViewProcessing(Long.valueOf(photo), viewsupdate);
		req.setAttribute(SystemConstant.MODEL, returnphoto);
		RequestDispatcher rd = req.getRequestDispatcher("./views/web/ViewPhoto/viewphoto.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Username = "";
		String photo = req.getParameter("photo");
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session");
		if(obj != null)
		{
			Username = String.valueOf(obj);
		}else {
			resp.sendRedirect("./views/web/Login/index.jsp");
			return;
		}
		Photo returnphoto = photoService.findPhoto(Long.valueOf(photo));		
		if(photoService.CheckLikeValid(Username, returnphoto.getPhotoID()) != 1) {
			photoService.LikeOfEachAccount(Username, returnphoto.getPhotoID());
			int Likesupdate = returnphoto.getLike() + 1;
			photoService.LikeProcessing(Long.valueOf(photo), Likesupdate);
			System.out.println("Like success");
		}		
		req.setAttribute(SystemConstant.MODEL, photoService.findPhoto(Long.valueOf(photo)));
		RequestDispatcher rd = req.getRequestDispatcher("./views/web/ViewPhoto/viewphoto.jsp");
		rd.forward(req, resp);
	}
}
