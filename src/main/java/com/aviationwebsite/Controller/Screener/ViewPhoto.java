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
import com.aviationwebsite.Model.Queue;
import com.aviationwebsite.SERVICE.Implement.PhotoService;
import com.aviationwebsite.SERVICE.Implement.QueueService;
import com.aviationwebsite.constant.SystemConstant;

@WebServlet(urlPatterns = {"/screener-queuephoto"})
public class ViewPhoto extends HttpServlet{
	private QueueService queueService;
	public ViewPhoto() {
		this.queueService = new QueueService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			String photo = req.getParameter("photo");
			Queue returnphoto = queueService.findQueue(Long.valueOf(photo));
			req.setAttribute(SystemConstant.MODEL, returnphoto);
			RequestDispatcher rd = req.getRequestDispatcher("./views/screener/ViewPhoto/viewphoto.jsp");
			rd.forward(req, resp);
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
