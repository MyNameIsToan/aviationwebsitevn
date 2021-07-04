package com.aviationwebsite.Controller.Web;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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
	private String filePath;
	private File file;

	public void init() {
		// Get the file location where it would be stored.
		filePath = "..\\image";
	}

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
	                	takendate = fieldvalue + " 00:00:00";
	                }else if(fieldname.equals("uploaddate")) {
	                	uploaddate = fieldvalue + " 00:00:00";
	                }
	            }else {
	                // Process form file field (input type="file").
	                String fileName = item.getName();
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")).substring(0, 1)
								+ username + registration + fileName.substring(fileName.lastIndexOf("\\")).substring(1,
										fileName.substring(fileName.lastIndexOf("\\")).length()));
						System.out.println(fileName.substring(fileName.lastIndexOf("\\")).substring(1, 1) + username
								+ registration + fileName.substring(fileName.lastIndexOf("\\")).substring(1,
										fileName.substring(fileName.lastIndexOf("\\")).length()));
						photo = fileName.substring(fileName.lastIndexOf("\\")).substring(1, 1) + username
								+ registration + fileName.substring(fileName.lastIndexOf("\\")).substring(1,
										fileName.substring(fileName.lastIndexOf("\\")).length());
					} else {
						file = new File(filePath + "\\" + username + registration + fileName);
						photo = username + registration + fileName;
					}
					item.write(file);
					req.setAttribute("path", file.getAbsolutePath());
	              }
	        }
//			if(queueService.UploadPhoto(username, photo, airlines, aircraft, registration, location, Timestamp.valueOf(takendate), Timestamp.valueOf(uploaddate)) == 1){
//				System.out.println("success");
//			}else {
//				System.out.println("failed");
//			}
			RequestDispatcher rd = req.getRequestDispatcher("./views/web/Upload/index.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			return;
		}      		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
}
