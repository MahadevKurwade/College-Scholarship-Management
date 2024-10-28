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

@WebServlet("/UpdateEducationDetailsServlet")
public class UpdateEducationDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");

        // Retrieve form data
        double tenthPercentage = Double.parseDouble(request.getParameter("tenthPercentage"));
        int tenthPassingYear = Integer.parseInt(request.getParameter("tenthPassingYear"));
        double twelfthPercentage = Double.parseDouble(request.getParameter("twelfthPercentage"));
        int twelfthPassingYear = Integer.parseInt(request.getParameter("twelfthPassingYear"));
        double ugPercentage = Double.parseDouble(request.getParameter("ugPercentage"));
        double ugSGPA = Double.parseDouble(request.getParameter("ugSGPA"));
        double ugCGPA = Double.parseDouble(request.getParameter("ugCGPA"));
        int ugPassingYear = Integer.parseInt(request.getParameter("ugPassingYear"));
        String pgCourse = request.getParameter("pgCourse");
        String pgAdmissionDate = request.getParameter("pgAdmissionDate");
        String pgInstitute = request.getParameter("pgInstitute");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update education details
            String updateSql = "UPDATE StudentEducation SET tenthPercentage = ?, tenthPassingYear = ?, "
                    + "twelfthPercentage = ?, twelfthPassingYear = ?, ugPercentage = ?, ugSGPA = ?, ugCGPA = ?, "
                    + "ugPassingYear = ?, pgCourse = ?, pgAdmissionDate = ?, pgInstitute = ? WHERE adharNumber = ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setDouble(1, tenthPercentage);
            stmt.setInt(2, tenthPassingYear);
            stmt.setDouble(3, twelfthPercentage);
            stmt.setInt(4, twelfthPassingYear);
            stmt.setDouble(5, ugPercentage);
            stmt.setDouble(6, ugSGPA);
            stmt.setDouble(7, ugCGPA);
            stmt.setInt(8, ugPassingYear);
            stmt.setString(9, pgCourse);
            stmt.setString(10, pgAdmissionDate);
            stmt.setString(11, pgInstitute);
            stmt.setString(12, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                String message = "Education details updated successfully. Redirecting into 5 sec ...." ;
                response.setHeader("Refresh", "5; URL=combined.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
                response.getWriter().println("Failed to update education details.");
            }
        } catch (Exception e) {
            response.getWriter().println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                response.getWriter().println("An error occurred while closing the resources: " + ex.getMessage());
            }
        }
    }
}
