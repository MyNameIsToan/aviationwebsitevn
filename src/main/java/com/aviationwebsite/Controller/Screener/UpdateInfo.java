package com.aviationwebsite.Controller.Screener;

import java.io.IOException;
import java.sql.Timestamp;

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

@WebServlet(urlPatterns = {"/screener-updateinfo"})
public class UpdateInfo extends HttpServlet{
	private PhotoService photoService;
	public UpdateInfo() {
		this.photoService = new PhotoService();
	}
	private Long ID;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			String photo = req.getParameter("photo");
			ID = Long.valueOf(photo);
			Photo returnphoto = photoService.findPhoto(Long.valueOf(photo));
			req.setAttribute(SystemConstant.MODEL, returnphoto);
			RequestDispatcher rd = req.getRequestDispatcher("./views/screener/Update/index.jsp");
			rd.forward(req, resp);
			return;
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String Airlines = req.getParameter("airlines");
		String Aircraft = req.getParameter("aircraft");
		String Registration = req.getParameter("registration");
		String Location  = req.getParameter("location");
		String TakenDate = req.getParameter("takendate");
		String year = TakenDate.substring(0, 4);
    	String month = TakenDate.substring(5, 7);
    	String date = TakenDate.substring(8, 10);
    	TakenDate = year + '-' + month + '-' + date + " 00:00:00";
		if(photoService.Update(ID, Airlines, Aircraft, Registration, Location, Timestamp.valueOf(TakenDate))==1) {
			System.out.println("Updated");
		}else {
			System.out.println("Failed");
		}
		resp.sendRedirect("./screener-allphoto");
	}
}
