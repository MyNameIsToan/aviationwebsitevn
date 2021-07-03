package com.aviationwebsite.DAO;

import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.Model.Queue;

public interface IQueueDAO {
	int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register, String Location, Timestamp TakenDate, Timestamp UploadDate);
	List<Queue> findAll();
	int DeletePhoto(String Username, String Photo, String Airlines, String Aircraft, String Register);
}
