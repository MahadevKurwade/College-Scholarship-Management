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
import javax.servlet.http.HttpSession;

@WebServlet("/PersonalDetailsServlet1")
public class PersonalDetailsServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");
        if (adharNumber == null || adharNumber.isEmpty()) {
            response.getWriter().println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update user details in the database
            String updateSql = "UPDATE PersonalDetails SET firstName=?, middleName=?, lastName=?, motherName=?, age=?, gender=?, religion=?, category=?, casteCertificateNo=?, validityCertificateNo=?, nonCreamyCertificateNo=?, hosteler=? WHERE adharNumber=?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, request.getParameter("firstName"));
            stmt.setString(2, request.getParameter("middleName"));
            stmt.setString(3, request.getParameter("lastName"));
            stmt.setString(4, request.getParameter("motherName"));
            stmt.setInt(5, Integer.parseInt(request.getParameter("age")));
            stmt.setString(6, request.getParameter("gender"));
            stmt.setString(7, request.getParameter("religion"));
            stmt.setString(8, request.getParameter("category"));
            stmt.setString(9, request.getParameter("casteCertificateNo"));
            stmt.setString(10, request.getParameter("validityCertificateNo"));
            stmt.setString(11, request.getParameter("nonCreamyCertificateNo"));
            stmt.setString(12, request.getParameter("hosteler"));
            stmt.setString(13, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                String message = " Detail updated successfully. Successfully \n  Redirecting into Personal Details Page in 5 sec...." ;
                response.setHeader("Refresh", "5; URL=combined.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
                response.getWriter().println("<div class='notification'>Failed to update personal details.</div>");
            }
        } catch (Exception e) {
            response.getWriter().println("<div class='notification'>An error occurred: " + e.getMessage() + "</div>");
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                response.getWriter().println("<div class='notification'>An error occurred while closing resources: " + ex.getMessage() + "</div>");
            }
        }
    }
}
