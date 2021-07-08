package com.aviationwebsite.Controller.Screener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.SERVICE.Implement.PhotoService;
import com.aviationwebsite.SERVICE.Implement.QueueService;

@WebServlet(urlPatterns = { "/acceptedphoto" })
public class UploadPhotos extends HttpServlet {
	private PhotoService photoService;
	private QueueService queueService;
	public UploadPhotos() {
		this.photoService = new PhotoService();
		this.queueService = new QueueService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ID = req.getParameter("id");
		String Username = req.getParameter("username");
		String Photo = req.getParameter("photo");
		String Airlines = req.getParameter("airlines");
		String Aircraft = req.getParameter("aircraft");
		String Registration = req.getParameter("registration");
		String Location  = req.getParameter("location");
		String TakenDate = req.getParameter("takendate");
		String UploadDate = req.getParameter("uploaddate");
		String submit = req.getParameter("submit");
		if(submit.equals("Accept") == true) {
			if(photoService.UploadPhoto(Username, Photo, Airlines, Aircraft, Registration, Location, Timestamp.valueOf(TakenDate), Timestamp.valueOf(UploadDate)) == 1) {
				System.out.println("Success01");
			}else {
				System.out.println("Failed");
			}
			if(queueService.DeletePhoto(Username, Photo, Airlines, Aircraft, Registration) == 1) {
				System.out.println("Success02");
			}else {
				System.out.println("Failed");
			}
			String pathFile = getServletContext().getRealPath("/image");
			pathFile = pathFile + "/" + ID + "decode.jpg";
			try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
				byte[] imageByteArray = Base64.getDecoder().decode(Photo);
				imageOutFile.write(imageByteArray);
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading the Image " + ioe);
			}
		}else{
			if(queueService.DeletePhoto(Username, Photo, Airlines, Aircraft, Registration) == 1) {
				System.out.println("Success02");
			}else {
				System.out.println("Failed");
			}
		}	
		resp.sendRedirect("./screener-controll");
	}
}
