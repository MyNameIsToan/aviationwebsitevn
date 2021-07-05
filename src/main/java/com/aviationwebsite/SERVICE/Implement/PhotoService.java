package com.aviationwebsite.SERVICE.Implement;

import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.DAO.Implement.PhotoDAO;
import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.SERVICE.IPhotoService;

public class PhotoService implements IPhotoService{
	
	private PhotoDAO photoDAO;
	
	public PhotoService() {
		this.photoDAO = new PhotoDAO();
	}
	
	@Override
	public int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register,
			String Location, Timestamp TakenDate, Timestamp UploadDate) {
		if(photoDAO.UploadPhoto(Username, Photo, Airlines, Aircraft, Register, Location, TakenDate, UploadDate) == 1) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public List<Photo> findAll() {
		return photoDAO.findAll();
	}

	@Override
	public Photo findPhoto(Long id) {
		return photoDAO.findPhoto(id);
	}

	@Override
	public int ViewProcessing(Long id, int View) {
		return photoDAO.ViewProcessing(id, View);
	}

	@Override
	public List<Photo> findMyPhoto(String Username) {
		return photoDAO.findMyPhoto(Username);
	}

	@Override
	public int LikeProcessing(Long id, int Like) {
		return photoDAO.LikeProcessing(id, Like);
	}

	@Override
	public List<Photo> SearchPhotos(String condition) {
		return photoDAO.SearchPhotos(condition);
	}

	@Override
	public int CheckLikeValid(String Username, Long id) {
		if(photoDAO.CheckLikeValid(Username, id) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public int LikeOfEachAccount(String Username, Long id) {
		if(photoDAO.LikeOfEachAccount(Username, id) == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
