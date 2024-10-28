package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/UpdatePersonalDetailsServlet")
public class UpdatePersonalDetailsServlet extends HttpServlet {
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

        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String motherName = request.getParameter("motherName");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String religion = request.getParameter("religion");
        String category = request.getParameter("category");
        String casteCertificateNo = request.getParameter("casteCertificateNo");
        String validityCertificateNo = request.getParameter("validityCertificateNo");
        String nonCreamyCertificateNo = request.getParameter("nonCreamyCertificateNo");
        String hosteler = request.getParameter("hosteler");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update personal details in the database
            String updateSql = "UPDATE PersonalDetails SET firstName=?, middleName=?, lastName=?, motherName=?, age=?, gender=?, religion=?, category=?, casteCertificateNo=?, validityCertificateNo=?, nonCreamyCertificateNo=?, hosteler=? WHERE adharNumber=?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, firstName);
            stmt.setString(2, middleName);
            stmt.setString(3, lastName);
            stmt.setString(4, motherName);
            stmt.setInt(5, age);
            stmt.setString(6, gender);
            stmt.setString(7, religion);
            stmt.setString(8, category);
            stmt.setString(9, casteCertificateNo);
            stmt.setString(10, validityCertificateNo);
            stmt.setString(11, nonCreamyCertificateNo);
            stmt.setString(12, hosteler);
            stmt.setString(13, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                // Personal details updated successfully
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                response.sendRedirect("UpdatePersonalDetails.jsp?updated=true");
            } else {
                // Failed to update personal details
                out.println("<div class='notification'>Failed to update personal details.</div>");
            }
        } catch (Exception e) {
            out.println("<div class='notification'>An error occurred: " + e.getMessage() + "</div>");
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
        }
    }
}
