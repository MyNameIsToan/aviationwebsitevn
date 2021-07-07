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
import com.aviationwebsite.SERVICE.Implement.UserService;
import com.aviationwebsite.constant.SystemConstant;
@WebServlet(urlPatterns = {"/screener-login"})
public class LoginController extends HttpServlet{
	private UserService userService;
	
	public LoginController() {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session-screener");
		if(obj != null)
		{
			RequestDispatcher rd = req.getRequestDispatcher("./screener-allphoto");
			rd.forward(req, resp);
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(userService.CheckAdminLogin(username, password) == 1) {
			HttpSession Session = req.getSession();
			Session.setAttribute("session-screener", username);
			resp.sendRedirect("./screener-allphoto");
		}else {
			resp.sendRedirect("./views/screener/Login/index.jsp");
		}
	}
}
