package com.aviationwebsite.Model;

import java.util.ArrayList;
import java.util.List;

public class Photo extends Queue{
	private int like;
	private int view;
	private List<Photo> ListPhotos = new ArrayList<>();
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public List<Photo> getListPhotos() {
		return ListPhotos;
	}
	public void setListPhotos(List<Photo> listPhotos) {
		ListPhotos = listPhotos;
	}
}
