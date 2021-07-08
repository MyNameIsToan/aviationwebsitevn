package com.aviationwebsite.DAO;

import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.Model.Photo;

public interface IPhotoDAO {
	int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate, Timestamp UploadDate);
	List<Photo> findAll();
	List<Photo> findMyPhoto(String Username);
	Photo findPhoto(Long id);
	int ViewProcessing(Long id, int View);
	int LikeProcessing(Long id, int Like);
	int LikeOfEachAccount(String Username, Long id);
	int CheckLikeValid(String Username, Long id);
	List<Photo> SearchPhotos(String condition);
	int DeletePhoto(Long id);
	int Update(Long id, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate);
}
