package com.aviationwebsite.SERVICE;

import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.Model.Photo;

public interface IPhotoService {
	int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate, Timestamp UploadDate);
	List<Photo> findAll();
	Photo findPhoto(Long id);
	int ViewProcessing(Long id, int View);
	int LikeProcessing(Long id, int Like);
	List<Photo> findMyPhoto(String Username);
	List<Photo> SearchPhotos(String condition);
	int CheckLikeValid(String Username, Long id);
	int LikeOfEachAccount(String Username, Long id);
	int DeletePhoto(Long id);
	int Update(Long id, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate);
}
