package com.aviationwebsite.DAO.Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aviationwebsite.DAO.IPhotoDAO;
import com.aviationwebsite.Model.Photo;

public class PhotoDAO implements IPhotoDAO{
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/aviationwebsite";
			String username = "root";
			String password = "Toan3006";
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
			String Sql = "Insert into photo(Username, Photo, Airlines, Aircraft, Registration, Location, TakenDate, UploadDate) VALUES (?,?,?,?,?,?,?,?)";
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
	public List<Photo> findAll() {
		List<Photo> ListPhotos = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM photo";
			statement = connection.prepareStatement(Sql);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Photo photo = new Photo();
				photo.setUsername(resultSet.getString("Username"));
				photo.setPhoto(resultSet.getString("Photo"));
				photo.setAirlines(resultSet.getString("Airlines"));
				photo.setAircraft(resultSet.getString("Aircraft"));
				photo.setLocation(resultSet.getString("Location"));
				photo.setRegistration(resultSet.getString("Registration"));
				photo.setTakenDate(resultSet.getTimestamp("TakenDate"));
				photo.setUploadDate(resultSet.getTimestamp("UploadDate"));
				photo.setLike(resultSet.getInt("Likes"));
				photo.setView(resultSet.getInt("Views"));
				ListPhotos.add(photo);
			}
			return ListPhotos;
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
