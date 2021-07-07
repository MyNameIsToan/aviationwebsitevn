package com.aviationwebsite.Controller.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aviationwebsite.SERVICE.Implement.UserService;
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet{
	private UserService userService;
	
	public LoginController() {
		userService = new UserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if(userService.CheckLogin(username, password) == 1) {
			HttpSession Session = req.getSession();
			Session.setAttribute("session", username);
			resp.sendRedirect("./homepage");
		}else {
			resp.sendRedirect("./views/web/Login/index.jsp");
		}
	}
}
