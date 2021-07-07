package com.aviationwebsite.Controller.Web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aviationwebsite.SERVICE.Implement.QueueService;

@MultipartConfig
@WebServlet(urlPatterns = { "/uploadphoto" })
public class UploadPhotos extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2368846296585843637L;
	private QueueService queueService;
	public UploadPhotos() {
		queueService = new QueueService();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{	
		String username = "";
 		String airlines = "";
 		String aircraft = "";
 		String registration = "";
		String location = "";
		String uploaddate = "";
		String takendate = "";
		String photo = "";
		HttpSession httpSession = req.getSession();
		Object obj = httpSession.getAttribute("session");
		if(obj != null)
		{
			username = String.valueOf(obj);
		}else {
			return;
		}
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldname = item.getFieldName();
	                String fieldvalue = item.getString();
	               if(fieldname.equals("airlines")) {
	                	airlines = fieldvalue;
	                }else if(fieldname.equals("aircraft")) {
	                	aircraft = fieldvalue;
	                }else if(fieldname.equals("registration")) {
	                	registration = fieldvalue;
	                }else if(fieldname.equals("location")) {
	                	location = fieldvalue;
	                }else if (fieldname.equals("takendate")) {
	                	String date = fieldvalue.substring(0, 2);
	                	String month = fieldvalue.substring(3, 5);
	                	String year = fieldvalue.substring(6, fieldvalue.length());
	                	takendate = year + '-' + month + '-' + date + " 00:00:00";
	                }
	            }else {
					InputStream input = item.getInputStream();
					try {
						BufferedImage sourceimage = ImageIO.read(input);
						ByteArrayOutputStream bytes = new ByteArrayOutputStream();
						ImageIO.write(sourceimage, "jpg", bytes);
						String result = Base64.getEncoder().encodeToString(bytes.toByteArray());
						photo = result;
					} catch (Exception e) {
						e.printStackTrace();
					}
	              }
	        }
			LocalDateTime now = LocalDateTime.now(); 
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
            String formatDateTime = now.format(format);  
            System.out.println("After Formatting: " + formatDateTime);  
            uploaddate = formatDateTime + " 00:00:00";
			int condition = 0;
			if(username.equals("")){
				condition = 1;
			}else if (airlines.equals("")){
				condition = 1;
			}else if (aircraft.equals("")){
				condition = 1;
			}else if (registration.equals("")){
				condition = 1;
			}else if (location.equals("")){
				condition = 1;
			}else if (photo.equals("")) {
				condition = 1;
			}else if (takendate.equals("")) {
				condition = 1;
			}
			
			if(condition == 1) {
				resp.sendRedirect("./views/web/Upload/index.jsp");
				return;
			}
			if(queueService.UploadPhoto(username, photo, airlines, aircraft, registration, location, Timestamp.valueOf(takendate), Timestamp.valueOf(uploaddate)) == 1){
				System.out.println("success");
			}else {
				System.out.println("failed");
			}
			resp.sendRedirect("./views/web/Upload/index.jsp");
		} catch (Exception e) {
			return;
		}      		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
