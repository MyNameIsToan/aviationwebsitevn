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
				photo.setPhotoID(resultSet.getLong("ID"));
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

	@Override
	public Photo findPhoto(Long id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Photo photo = new Photo();
		try {
			String Sql = "SELECT * FROM photo WHERE id = ?";
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
				photo.setLike(resultSet.getInt("Likes"));
				photo.setView(resultSet.getInt("Views"));
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

	@Override
	public int ViewProcessing(Long id, int View) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "UPDATE photo SET Views = ? WHERE id =?";
			statement = connection.prepareStatement(Sql);
			statement.setLong(2, id);
			statement.setInt(1, View);
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
	public List<Photo> findMyPhoto(String Username) {
		List<Photo> ListPhotos = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM photo WHERE Username = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Username);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Photo photo = new Photo();
				photo.setPhotoID(resultSet.getLong("ID"));
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

	@Override
	public int LikeProcessing(Long id, int Like) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "UPDATE photo SET Likes = ? WHERE id =?";
			statement = connection.prepareStatement(Sql);
			statement.setLong(2, id);
			statement.setInt(1, Like);
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
	public List<Photo> SearchPhotos(String condition) {
		List<Photo> ListPhotos = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String condition1 ="%"+condition +"%";
		try {
			String Sql = "SELECT * FROM photo WHERE Username LIKE ? OR Aircraft LIKE ? OR Location LIKE ? OR Registration LIKE ? OR Airlines LIKE ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, condition1.toString());
			statement.setString(2, condition1.toString());
			statement.setString(3, condition1.toString());
			statement.setString(4, condition1.toString());
			statement.setString(5, condition1.toString());
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Photo photo = new Photo();
				photo.setPhotoID(resultSet.getLong("ID"));
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

	@Override
	public int CheckLikeValid(String Username, Long id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			String Sql = "SELECT * FROM LIKES WHERE Username = ? AND ID = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Username);
			statement.setLong(2, id);
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
	public int LikeOfEachAccount(String Username, Long id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "INSERT INTO LIKES VALUES (?,?)";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Username);
			statement.setLong(2, id);
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
	public int DeletePhoto(Long id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			if(DeleteLike(id)==1) {
				connection.setAutoCommit(false);
				String Sql2 = "DELETE FROM photo WHERE id = ?";
				statement = connection.prepareStatement(Sql2);
				statement.setLong(1, id);
				statement.executeUpdate();
				connection.commit();
				return 1;
			}else {
				return 0;
			}			
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
	
	protected int DeleteLike(Long id){
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "DELETE FROM LIKES WHERE ID = ?";
			statement = connection.prepareStatement(Sql);
			statement.setLong(1, id);
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
	public int Update(Long id, String Airlines, String Aircraft, String Register, String Location,
			Timestamp TakenDate) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			String Sql = "UPDATE photo SET Airlines = ?, Aircraft = ?, Registration = ?, Location = ?, TakenDate = ? WHERE id = ?";
			statement = connection.prepareStatement(Sql);
			statement.setString(1, Airlines);
			statement.setString(2, Aircraft);
			statement.setString(3, Register);
			statement.setString(4, Location);
			statement.setTimestamp(5, TakenDate);
			statement.setLong(6, id);
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
