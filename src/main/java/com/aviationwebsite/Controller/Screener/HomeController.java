package com.aviationwebsite.Controller.Screener;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aviationwebsite.Model.Queue;
import com.aviationwebsite.SERVICE.Implement.QueueService;
import com.aviationwebsite.constant.SystemConstant;

@WebServlet(urlPatterns = {"/screener-controll"})
public class HomeController extends HttpServlet{
	private QueueService queueService;
	public HomeController() {
		this.queueService = new QueueService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			Queue QueuePhoto = new Queue();
			QueuePhoto.setListResult(queueService.findAll());
			req.setAttribute(SystemConstant.MODEL, QueuePhoto);
			RequestDispatcher rd = req.getRequestDispatcher("./views/screener/Queue/Queue.jsp");
			rd.forward(req, resp);
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
