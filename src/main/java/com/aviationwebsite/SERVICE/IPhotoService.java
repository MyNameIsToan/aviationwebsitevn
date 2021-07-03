package com.aviationwebsite.SERVICE;

import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.Model.Photo;

public interface IPhotoService {
	int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate, Timestamp UploadDate);
	List<Photo> findAll();
}
