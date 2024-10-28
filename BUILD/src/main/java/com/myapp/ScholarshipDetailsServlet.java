package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ScholarshipDetailsServlet")
public class ScholarshipDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get Aadhar number from session
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");

        if (adharNumber == null || adharNumber.isEmpty()) {
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Check if scholarship details exist for the user
            String selectSql = "SELECT * FROM ScholarshipDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Scholarship details exist, display them
                out.println("<h2>Scholarship Details</h2>");
                out.println("<p>College Name: " + rs.getString("collegeName") + "</p>");
                out.println("<p>Scholarship Name: " + rs.getString("scholarshipName") + "</p>");
                out.println("<p>Fees: " + rs.getDouble("fees") + "</p>");
                
                String message = "  Redirecting into Dashboard Page in 5 sec...." ;
                response.setHeader("Refresh", "5; URL=dashboard.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
                
                out.println("<form action='updatescholarship.jsp' method='get' style='margin-top: 20px;'>");
                out.println("<input type='submit'  value='Update Scholarship  Detail' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                out.println("</form>");
            } else {
                // No scholarship details found
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>No scholarship details found for the current user.</div>");
              	
                String message = "  Redirecting into Scholarship Details Page in 5 sec...." ;
                response.setHeader("Refresh", "5; URL=Scholarship.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
 
                
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("<div class='notification'> Already Applied....</div>");
            response.setHeader("Refresh", "5; URL=dashboard.jsp");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
            out.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String collegeName = request.getParameter("collegeName");
        String scholarshipName = request.getParameter("scholarshipName");
        double fees = Double.parseDouble(request.getParameter("fees"));

        // Get Aadhar number from session
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");

        if (adharNumber == null || adharNumber.isEmpty()) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Insert scholarship details into the database
            String insertSql = "INSERT INTO ScholarshipDetails (adharNumber, collegeName, scholarshipName, fees) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, adharNumber);
            stmt.setString(2, collegeName);
            stmt.setString(3, scholarshipName);
            stmt.setDouble(4, fees);

            int rowsInserted = stmt.executeUpdate();
            
           
            if (rowsInserted > 0) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>Scholarship details Save successfully.</div>");
                String message = "  Redirecting into Complete Personal Details Page in 2 sec...." ;
                response.setHeader("Refresh", "2; URL=dashboard.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>Failed to Save scholarship details.</div>");
                String message = "  Redirecting into Dashboard Page in 2 sec...." ;
                response.setHeader("Refresh", "2; URL=dashboard.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("<div class='notification'>Already Applied..... </div>");
            String message = "  Redirecting into Dashboard Page in 2 sec...." ;
            response.setHeader("Refresh", "2; URL=dashboard.jsp");
            response.getWriter().println("<h2>" + message + "</h2>");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
            out.close();
        }
    }
}
