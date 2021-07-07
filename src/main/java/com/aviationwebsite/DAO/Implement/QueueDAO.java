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
import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.Model.Queue;

public class QueueDAO implements IQueueDAO{
	
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
				Queuephoto.setPhotoID(resultSet.getLong("ID"));
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

	@Override
	public List<Queue> SearchPhotos(String condition) {
		List<Queue> ListQueue = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String condition1 ="%"+condition +"%";
		try {
			String Sql = "SELECT * FROM queue WHERE Username LIKE ? OR Aircraft LIKE ? OR Location LIKE ? OR Registration LIKE ? OR Airlines LIKE ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, condition1.toString());
			statement.setString(2, condition1.toString());
			statement.setString(3, condition1.toString());
			statement.setString(4, condition1.toString());
			statement.setString(5, condition1.toString());
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Queue photo = new Photo();
				photo.setPhotoID(resultSet.getLong("ID"));
				photo.setUsername(resultSet.getString("Username"));
				photo.setPhoto(resultSet.getString("Photo"));
				photo.setAirlines(resultSet.getString("Airlines"));
				photo.setAircraft(resultSet.getString("Aircraft"));
				photo.setLocation(resultSet.getString("Location"));
				photo.setRegistration(resultSet.getString("Registration"));
				photo.setTakenDate(resultSet.getTimestamp("TakenDate"));
				photo.setUploadDate(resultSet.getTimestamp("UploadDate"));
				ListQueue.add(photo);
			}
			return ListQueue;
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
	public Queue findQueue(Long id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue photo = new Queue();
		try {
			String Sql = "SELECT * FROM queue WHERE id = ?";
			statement = connection.prepareStatement(Sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				photo.setPhotoID(resultSet.getLong("ID"));
				photo.setUsername(resultSet.getString("Username"));
				photo.setPhoto(resultSet.getString("Photo"));
				photo.setAirlines(resultSet.getString("Airlines"));
				photo.setAircraft(resultSet.getString("Aircraft"));
				photo.setLocation(resultSet.getString("Location"));
				photo.setRegistration(resultSet.getString("Registration"));
				photo.setTakenDate(resultSet.getTimestamp("TakenDate"));
				photo.setUploadDate(resultSet.getTimestamp("UploadDate"));
			}
			return photo;
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
}
