package com.aviationwebsite.DAO;

public interface IUserDAO {
	int CheckLogin(String username, String password);
	int CheckValid(String username);
	int Register(String username, String password, String email);
}
