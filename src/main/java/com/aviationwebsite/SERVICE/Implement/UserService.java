package com.aviationwebsite.SERVICE.Implement;

import com.aviationwebsite.DAO.Implement.UserDAO;
import com.aviationwebsite.SERVICE.IUserService;

public class UserService implements IUserService{
	private UserDAO userDAO;
	public UserService() {
		this.userDAO = new UserDAO();
	}
	@Override
	public int CheckLogin(String username, String password) {
		if(userDAO.CheckLogin(username, password) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	@Override
	public int Register(String username, String password, String email) {
		if(userDAO.Register(username, password, email) == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	@Override
	public int CheckValid(String username) {
		if(userDAO.CheckValid(username) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	@Override
	public int CheckAdminLogin(String username, String password) {
		if(userDAO.CheckAdminLogin(username, password) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
