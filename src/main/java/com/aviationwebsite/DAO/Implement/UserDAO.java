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
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://ec2-52-2-118-38.compute-1.amazonaws.com:5432/d935im5t85nmc0?sslmode=require";
			String username = "cjskovdfuzartw";
			String password = "401ed8c0ffd9d48dfe49fb40ca848730cb545f417e02f9faa3f959cd9c78e51c";
			return DriverManager.getConnection(url, username, password);
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
			String Sql = "SELECT * FROM ACCOUNT WHERE Username = ? AND Password = ?";
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
			String Sql = "INSERT INTO ACCOUNT VALUES (?,?,?)";
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
			String Sql = "SELECT * FROM ACCOUNT WHERE Username = ?";
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
