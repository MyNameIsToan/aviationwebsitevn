package com.aviationwebsite.SERVICE;

public interface IUserService {
	int CheckLogin(String username, String password);
	int CheckValid(String username);
	int Register (String username, String password, String email);
}
