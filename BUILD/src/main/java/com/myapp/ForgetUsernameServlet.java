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

@WebServlet("/ForgetUsernameServlet")
public class ForgetUsernameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String adharNumber = request.getParameter("adharNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");
            
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // SQL query to check if the Aadhar number and date of birth match
            String sql = "SELECT UserName FROM Regid WHERE AdharNumber = ? AND DateOfBirth = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, adharNumber);
            stmt.setString(2, dateOfBirth);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                String username = rs.getString("UserName");
                out.println("<div><h1>" +"Your username is: " + username+"</h1></div>");
                String message = "   Redirecting into Login Page in 5 sec...." ;
                response.setHeader("Refresh", "5; URL=student_login.html");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("Invalid Aadhar number or date of birth.");
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("An error occurred: " + e.getMessage());
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
            } catch (SQLException ex) {
                out.println("An error occurred while closing the resources: " + ex.getMessage());
            }
            out.close();
        }
    }
}
