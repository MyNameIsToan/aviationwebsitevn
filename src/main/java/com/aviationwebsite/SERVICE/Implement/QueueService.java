package com.aviationwebsite.SERVICE.Implement;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.List;

import com.aviationwebsite.DAO.Implement.QueueDAO;
import com.aviationwebsite.Model.Queue;
import com.aviationwebsite.SERVICE.IQueueService;

public class QueueService implements IQueueService{
	private QueueDAO queueDAO;
	
	public QueueService() {
		this.queueDAO = new QueueDAO();
	}
	@Override
	public int UploadPhoto(String Username, String Photo, String Airlines, String Aircraft, String Register,
			String Location, Timestamp TakenDate, Timestamp UploadDate) {
		if(queueDAO.UploadPhoto(Username, Photo, Airlines, Aircraft, Register, Location, TakenDate, UploadDate) == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	@Override
	public List<Queue> findAll() {
		return queueDAO.findAll();
	}
	@Override
	public int DeletePhoto(String Username, String Photo, String Airlines, String Aircraft, String Register) {
		if(queueDAO.DeletePhoto(Username, Photo, Airlines, Aircraft, Register) == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	@Override
	public List<Queue> SearchPhotos(String condition) {
		return queueDAO.SearchPhotos(condition);
	}
	@Override
	public Queue findQueue(Long id) {
		return queueDAO.findQueue(id);
	}
}
