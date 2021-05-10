package project_database.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import project_database.persist.DerbyDatabase;

@MultipartConfig(maxFileSize = 16177215) // Max file size of ~16MB
public class UploadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		DerbyDatabase db = new DerbyDatabase();
		InputStream inputStream = null;
		
		Part filePart = req.getPart("userImage");
		
		String userIDString = (String) session.getAttribute("userID");
		int userID = Integer.parseInt(userIDString);
		
		if(filePart != null) {
			inputStream = filePart.getInputStream();
			
			db.updateImage(userID, inputStream);
		}
		
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
	}
}
