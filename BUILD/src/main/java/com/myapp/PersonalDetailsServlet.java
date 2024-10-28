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

@WebServlet("/PersonalDetailsServlet")
public class PersonalDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); 

        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");
        if (adharNumber == null || adharNumber.isEmpty()) {
            out.println("<div class='notification'>Error: Adhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Check if the user details already exist
            String selectSql = "SELECT * FROM PersonalDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
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
           	 response.getWriter().println("<h1> Personal details </h1>");
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
              
               
           	 out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
           	  response.getWriter().println("<div style='text-align: center;'>");
                // User details already exist, show the details
                out.println("<div style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'> Personal details already exist:</div>");
                out.println("<p>First Name: " + rs.getString("firstName") + "</p>");
                out.println("<p>Middle Name: " + rs.getString("middleName") + "</p>");
                out.println("<p>Last Name: " + rs.getString("lastName") + "</p>");
                out.println("<p>Mother's Name: " + rs.getString("motherName") + "</p>");
                out.println("<p>Age: " + rs.getInt("age") + "</p>");
                out.println("<p>Gender: " + rs.getString("gender") + "</p>");
                out.println("<p>Religion: " + rs.getString("religion") + "</p>");
                out.println("<p>Category: " + rs.getString("category") + "</p>");
                out.println("<p>Caste Certificate No: " + rs.getString("casteCertificateNo") + "</p>");
                out.println("<p>Validity Certificate No: " + rs.getString("validityCertificateNo") + "</p>");
                out.println("<p>Non Creamy Certificate No: " + rs.getString("nonCreamyCertificateNo") + "</p>");
                out.println("<p>Hosteler: " + rs.getString("hosteler") + "</p>");

                	
                String message = " Detail View Successfully \n  Redirecting into Personal Details Page in 5 sec...." ;
                response.setHeader("Refresh", "20; URL=combined.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
                // Add button to redirect to next page
                
                out.println("<form action='update_personal_details_form.jsp' method='get' style='margin-top: 20px;'>");
                out.println("<input type='submit'  value='Update Personal Detail' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                out.println("</form>");
                
                
            
                out.println("<form action='dashboard.jsp' method='get' style='margin-top: 20px;'>");
                out.println("<input type='submit' value='Dashboard' style='background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                out.println("</form>");
                
                
                

                
            } else {
            	 response.sendRedirect("personal_details_form.jsp");              
                
            }
        } catch (Exception e) {
            out.println("<div class='notification'>An error occurred: " + e.getMessage() + "</div>");
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
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
        }
    }
}
