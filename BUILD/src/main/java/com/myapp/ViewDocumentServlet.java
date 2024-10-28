package com.myapp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewDocumentServlet")
public class ViewDocumentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the directory path where documents are uploaded
        String adharNumber = (String) request.getSession().getAttribute("adharNumber");
        String uploadDirectory = "E:/doc_uplodes/" + adharNumber;

        // Get the list of files in the directory
        File directory = new File(uploadDirectory);
        File[] files = directory.listFiles();

        // Create a list to store file names
        List<String> fileNames = new ArrayList<>();

        // If the directory exists and is not empty, add file names to the list
        if (directory.exists() && files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }

        // Set the list of file names as an attribute to be accessed in JSP
        request.setAttribute("fileNames", fileNames);

        // Forward the request to the JSP page for rendering   ....
        request.getRequestDispatcher("/view_documents.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if needed
        // You can add logic here if you need to process form submissions
    }
}
