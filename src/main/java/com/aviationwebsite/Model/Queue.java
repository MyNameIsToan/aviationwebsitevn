package com.aviationwebsite.Model;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Queue {
	private Long PhotoID;
	private String Username;
	private String Photo;
	private String Airlines;
	private String Aircraft;
	private String Registration;
	private String Location;
	private Timestamp TakenDate;
	private Timestamp UploadDate;
	private List<Queue> ListResult = new ArrayList<>();

	public List<Queue> getListResult() {
		return ListResult;
	}

	public void setListResult(List<Queue> listResult) {
		ListResult = listResult;
	}

	public Long getPhotoID() {
		return PhotoID;
	}

	public void setPhotoID(Long photoID) {
		PhotoID = photoID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPhoto() {
		return Photo;
	}

	public void setPhoto(String photo) {
		Photo = photo;
	}

	public String getAirlines() {
		return Airlines;
	}

	public void setAirlines(String airlines) {
		Airlines = airlines;
	}

	public String getAircraft() {
		return Aircraft;
	}

	public void setAircraft(String aircraft) {
		Aircraft = aircraft;
	}

	public String getRegistration() {
		return Registration;
	}

	public void setRegistration(String registration) {
		Registration = registration;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Timestamp getTakenDate() {
		return TakenDate;
	}

	public void setTakenDate(Timestamp takenDate) {
		TakenDate = takenDate;
	}

	public Timestamp getUploadDate() {
		return UploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		UploadDate = uploadDate;
	}

}
