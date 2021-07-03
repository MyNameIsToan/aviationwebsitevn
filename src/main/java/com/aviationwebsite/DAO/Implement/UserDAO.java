package com.aviationwebsite.DAO.Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aviationwebsite.DAO.IUserDAO;

public class UserDAO implements IUserDAO{
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aviationwebsite";
			String username = "root";
			String password = "Toan3006";
			return DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public int CheckLogin(String username, String password) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM user WHERE Username = ? AND Password = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, username);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				return 1;
			}
			return 0;
		}catch(SQLException e) {
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			}catch(SQLException e) {
				return 0;
			}
		}
	}

	@Override
	public int Register(String username, String password, String email) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "INSERT INTO user VALUES (?,?,?)";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, email);
			statement.executeUpdate();
			connection.commit();
			return 1;
		}catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
			}catch(SQLException e) {
				return 0;
			}
		}
	}

	@Override
	public int CheckValid(String username) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM user WHERE Username = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, username);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				return 1;
			}
			return 0;
		}catch(SQLException e) {
			return 0;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			}catch(SQLException e) {
				return 0;
			}
		}
	}
}
