package com.myapp;

import java.io.IOException;
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

@WebServlet("/CheckStudentEducationServlet")
public class CheckStudentEducationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Check if the user's education details exist
            String selectSql = "SELECT * FROM StudentEducation WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Education details exist
            	 response.getWriter().println("<style>");
            	 response.getWriter().println(".header { background-color: #333; color: #fff; padding: 10px; text-align: center; }");
            	 response.getWriter().println(".edit-btn { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease, transform 0.3s ease; }");
            	 response.getWriter().println(".edit-btn:hover { background-color: #; transform: scale(1.05); }");
            	 response.getWriter().println(".edit-btn:active { transform: scale(0.95); }");
            	 response.getWriter().println(".header-nav ul { list-style-type: none; padding: 0; }");
            	 response.getWriter().println(".header-nav li { display: inline-block; margin: 0 10px; }");
            	 response.getWriter().println(".header { background-color: #333; color: #fff; padding: 10px; text-align: center; }");
            	 response.getWriter().println(".edit-btn { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease, transform 0.3s ease; }");
            	 response.getWriter().println(".edit-btn:hover { background-color: #; transform: scale(1.05); }");
            	 response.getWriter().println(".edit-btn:active { transform: scale(0.95); }");
            	 response.getWriter().println(".header-nav ul { list-style-type: none; padding: 0; }");
            	 response.getWriter().println(".header-nav li { display: inline-block; margin: 0 10px; }");
            	 response.getWriter().println(".header-nav a:hover { text-decoration: underline;}");
            	 response.getWriter().println(".header-nav a { color: #fff; text-decoration: none;font-size: 16px;}");
            	 response.getWriter().println("</style>");
            	 response.getWriter().println("<div class='header'>");
            	 response.getWriter().println("<div class='user-info'>");
            	 response.getWriter().println("<h1> Education details </h1>");
            	 response.getWriter().println("<p>Adhar No: " + adharNumber + "</p>");
            	 response.getWriter().println("</div>");
            	 response.getWriter().println("<form action='ProfileServlet'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-user'></i> Profile</button>");
            	 response.getWriter().println("</form>");
            	 response.getWriter().println("<div class='header-nav'>");
            	 response.getWriter().println("<ul>");
            	 response.getWriter().println("<li><button class='edit-btn' onclick='location.href=\"combined.jsp\"'><i class='fas fa-user'></i> Edit Personal Detail</button></li>");
            	 response.getWriter().println("<li><form action='AddressDetailsServlet' method='post'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-address-card'></i> View Address Detail</button>");
            	 response.getWriter().println("</form></li>");
            	 response.getWriter().println("<li><form action='BankDetailsServlet' method='post'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-university'></i> View Bank Detail</button>");
            	 response.getWriter().println("</form></li>");
            	 response.getWriter().println("<li><form action='CheckStudentEducationServlet'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-graduation-cap'></i> Student Education Details</button>");
            	 response.getWriter().println("</form></li>");
            	 response.getWriter().println("<li><form action='ScholarshipConfirmServlet' method='post'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-money-bill-wave'></i> Apply for Scholarship</button>");
            	 response.getWriter().println("</form></li>");
            	 response.getWriter().println("<li><form action='FetchDetailsServlet' method='post'>");
            	 response.getWriter().println("<button class='edit-btn'><i class='fas fa-print'></i> Print Application Form</button>");
            	 response.getWriter().println("</form></li>");
            	 response.getWriter().println("<li><a href='upload_documents.jsp'><i class='fas fa-file-upload'></i> Upload Document</a></li>");
            	 response.getWriter().println("<li><a href='LogoutServlet'><i class='fas fa-sign-out-alt'></i> Logout</a></li>");
            	 response.getWriter().println("</ul>");
            	 response.getWriter().println("</div>");
            	 response.getWriter().println("</div>");
                // Education details exist
               
                
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            	  response.getWriter().println("<div style='text-align: center;'>");
                response.getWriter().println("<br>10th Percentage: " + rs.getBigDecimal("tenthPercentage"));
                response.getWriter().println("<br>10th Passing Year: " + rs.getInt("tenthPassingYear"));
                response.getWriter().println("<br>12th Percentage: " + rs.getBigDecimal("twelfthPercentage"));
                response.getWriter().println("<br>12th Passing Year: " + rs.getInt("twelfthPassingYear"));
                response.getWriter().println("<br>UG Percentage: " + rs.getBigDecimal("ugPercentage"));
                response.getWriter().println("<br>UG SGPA: " + rs.getBigDecimal("ugSGPA"));
                response.getWriter().println("<br>UG CGPA: " + rs.getBigDecimal("ugCGPA"));
                response.getWriter().println("<br>UG Passing Year: " + rs.getInt("ugPassingYear"));
                response.getWriter().println("<br>PG Course: " + rs.getString("pgCourse"));
                response.getWriter().println("<br>PG Admission Date: " + rs.getDate("pgAdmissionDate"));
                response.getWriter().println("<br>PG Institute: " + rs.getString("pgInstitute"));
                
                response.getWriter().println("<form action='combined.jsp' method='get' style='margin-top: 20px;'>");
                response.getWriter().println("<input type='submit' value='Edit personal Details ' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                response.getWriter().println("</form>");
                
                response.getWriter().println("<form action='dashboard.jsp' method='get' style='margin-top: 20px;'>");
                response.getWriter().println("<input type='submit' value='Dashbord ' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                response.getWriter().println("</form>");
                
                response.getWriter().println("<form action='UpdateEducationDetails.jsp' method='get' style='margin-top: 20px;'>");
                response.getWriter().println("<input type='submit' value='Update Education Details ' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                response.getWriter().println("</form>");
                
                
                
                
                
                
            } else {
                // Education details don't exist
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
               
                
                // Prompt the user to enter education details
                String message = "Education details do not exist for the current user Redirecting  3 sec  to fill Educatin details  ...." ;
                response.setHeader("Refresh", "3; URL=enter_education_details.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
                
                
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
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
                e.printStackTrace();
                response.getWriter().println("An error occurred while closing resources: " + e.getMessage());  
            }
        }
    }
}
