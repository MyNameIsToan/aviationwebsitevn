package com.aviationwebsite.Controller.Web;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aviationwebsite.Model.Photo;
import com.aviationwebsite.SERVICE.Implement.PhotoService;

@WebServlet(urlPatterns = {"/decode"})
public class Decode extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private PhotoService photoService;
	public Decode() {
		this.photoService = new PhotoService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String photo = req.getParameter("photo");
		Photo returnphoto = photoService.findPhoto(Long.valueOf(photo));
		String pathFile = getServletContext().getRealPath("/image");
		pathFile = pathFile + "/" + photo + "decode.jpg";
		try (FileOutputStream imageOutFile = new FileOutputStream(pathFile)) {
			// Converting a Base64 String into Image byte array
			byte[] imageByteArray = Base64.getDecoder().decode(returnphoto.getPhoto());
			imageOutFile.write(imageByteArray);
			req.setAttribute("path", pathFile);
			RequestDispatcher rd = req.getRequestDispatcher("./views/web/Testing.jsp");
			rd.forward(req, resp);
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}
	}
}
