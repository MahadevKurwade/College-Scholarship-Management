package com.myapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        
        String applicantName = request.getParameter("applicantName");
        String userName = request.getParameter("userName");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String mobileNumber = request.getParameter("mobileNumber");
        String adharNumber = request.getParameter("adharNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // SQL query to insert user data into the database
            String sql = "INSERT INTO Regid (ApplicantName, UserName, Pass, Email, MobileNumber, AdharNumber, DateOfBirth) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, applicantName);
            stmt.setString(2, userName);
            stmt.setString(3, pass);
            stmt.setString(4, email);
            stmt.setString(5, mobileNumber);
            stmt.setString(6, adharNumber);
            stmt.setString(7, dateOfBirth);
            stmt.executeUpdate();
            
            // Registration successful, set message and redirect
            response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            String message = "Registration successful! \n  Redirecting into login Page...." ;
            response.setHeader("Refresh", "10; URL=student_login.html");
            response.getWriter().println("<h2>" + message + "</h2>");
        } catch (Exception e) {
            response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");

            response.getWriter().println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");

                response.getWriter().println("An error occurred while closing the resources: " + ex.getMessage());
            }
        }
    }
}
