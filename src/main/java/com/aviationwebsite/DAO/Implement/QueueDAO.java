package com.aviationwebsite.DAO.Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aviationwebsite.DAO.IQueueDAO;
import com.aviationwebsite.Model.Queue;

public class QueueDAO implements IQueueDAO{
	
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgres://ec2-3-89-0-52.compute-1.amazonaws.com:5432/d3d5fef9j9e3kf?sslmode=require";
			String username = "uarltosuvjpcmt";
			String password = "7e894efef73a0df555b579bcc600e5069a0349893a00c9f1a7f01c5abaad0eab";
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//String url = "jdbc:mysql://localhost:3306/aviationwebsite";
			//String username = "root";
			//String password = "Toan3006";
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	@Override
	public int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register,
			String Location, Timestamp TakenDate, Timestamp UploadDate) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "Insert into queue(Username, Photo, Airlines, Aircraft, Registration, Location, TakenDate, UploadDate) VALUES (?,?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Username);
			statement.setString(2, Photo);
			statement.setString(3, Airlines);
			statement.setString(4, Aircraft);
			statement.setString(5, Register);
			statement.setString(6, Location);
			statement.setTimestamp(7, TakenDate);
			statement.setTimestamp(8, UploadDate);
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
	public List<Queue> findAll() {
		List<Queue> ListQueuePhotos = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM queue";
			statement = connection.prepareStatement(Sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Queue Queuephoto = new Queue();
				Queuephoto.setUsername(resultSet.getString("Username"));
				Queuephoto.setPhoto(resultSet.getString("Photo"));
				Queuephoto.setAirlines(resultSet.getString("Airlines"));
				Queuephoto.setAircraft(resultSet.getString("Aircraft"));
				Queuephoto.setLocation(resultSet.getString("Location"));
				Queuephoto.setRegistration(resultSet.getString("Registration"));
				Queuephoto.setTakenDate(resultSet.getTimestamp("TakenDate"));
				Queuephoto.setUploadDate(resultSet.getTimestamp("UploadDate"));
				ListQueuePhotos.add(Queuephoto);
			}
			return ListQueuePhotos;
		}catch(SQLException e) {
			return null;
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
				return null;
			}
		}
	}

	@Override
	public int DeletePhoto(String Username, String Photo, String Airlines, String Aircraft, String Register) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "DELETE FROM queue WHERE Username = ? AND Photo = ? AND Airlines = ? AND Aircraft = ? AND Registration = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Username);
			statement.setString(2, Photo);
			statement.setString(3, Airlines);
			statement.setString(4, Aircraft);
			statement.setString(5, Register);
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
}
