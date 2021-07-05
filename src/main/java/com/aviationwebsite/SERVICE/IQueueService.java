package com.aviationwebsite.SERVICE;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.Model.Queue;

public interface IQueueService {
	int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate, Timestamp UploadDate);
	List<Queue> findAll();
	int DeletePhoto(String Username, String Photo, String Airlines, String Aircraft, String Register);
}
