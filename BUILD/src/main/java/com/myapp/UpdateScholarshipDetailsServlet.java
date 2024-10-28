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

@WebServlet("/UpdateScholarshipDetailsServlet")
public class UpdateScholarshipDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

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
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update scholarship details for the user
            String updateSql = "UPDATE ScholarshipDetails SET collegeName = ?, scholarshipName = ?, fees = ? WHERE adharNumber = ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, collegeName);
            stmt.setString(2, scholarshipName);
            stmt.setDouble(3, fees);
            stmt.setString(4, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                out.println("<div class='notification'>Scholarship details updated successfully.</div>");
                
                String message = "  Redirecting into Personal Details Page in 5 sec...." ;
                response.setHeader("Refresh", "10; URL=dashboard.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
                out.println("<div class='notification'>No scholarship details found to update.</div>");
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
            out.close();
        }
    }
}
