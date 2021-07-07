package com.aviationwebsite.Controller.Web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aviationwebsite.SERVICE.Implement.UserService;

@WebServlet(urlPatterns = {"/register"})
public class AccountRegister extends HttpServlet {
	private UserService userService;
	
	public AccountRegister() {
		this.userService = new UserService();
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
		String repassword = req.getParameter("repassword");
		String email  = req.getParameter("email");
		if(password.equals(repassword) == false) {
			resp.sendRedirect("./views/web/Register/index.html");
			return;
		}
		if(userService.CheckValid(username) == 1) {
			resp.sendRedirect("./views/web/Register/index.html");
			return;
		}
		userService.Register(username, repassword, email);
		resp.sendRedirect("./views/web/Login/index.jsp");
	}
}
