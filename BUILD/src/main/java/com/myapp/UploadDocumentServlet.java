package com.myapp;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadDocumentServlet")
@MultipartConfig
public class UploadDocumentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the directory path where documents will be stored
        String uploadDirectory = "C:\\Users\\BOSS\\Desktop\\BUILD\\doc";

        // Get the adhar number from the session
        String adharNumber = (String) request.getSession().getAttribute("adharNumber");
        if (adharNumber == null || adharNumber.isEmpty()) {
            response.getWriter().println("Error: Adhar number not found in session. Please login again.");
            return;
        }

        // Create a directory for the user if it does not exist
        File userDirectory = new File(uploadDirectory, adharNumber);
        if (!userDirectory.exists()) {    
            userDirectory.mkdirs();
        }

        // Get all parts from the request
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            // Extracting file name from the file part
            String fileName = extractFileName(part);
            // Saving file to the specified path
            if (fileName != null && !fileName.isEmpty()) {
                part.write(userDirectory + File.separator + fileName);
            }
        }
        response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
        // Show notification that documents are uploaded
        response.getWriter().println("Documents uploaded successfully. \n Redirecting to dashboard...");

        // Redirect to dashboard.jsp after 3 seconds
        response.setHeader("Refresh", "3; URL=dashboard.jsp");
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
