package com.myapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SaveEducationDetailsServlet")
public class SaveEducationDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
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

            // Insert education details into the database
            String insertSql = "INSERT INTO StudentEducation (adharNumber, tenthPercentage, tenthPassingYear, twelfthPercentage, twelfthPassingYear, ugPercentage, ugSGPA, ugCGPA, ugPassingYear, pgCourse, pgAdmissionDate, pgInstitute) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, adharNumber);
            stmt.setDouble(2, tenthPercentage);
            stmt.setInt(3, tenthPassingYear);
            stmt.setDouble(4, twelfthPercentage);
            stmt.setInt(5, twelfthPassingYear);
            stmt.setDouble(6, ugPercentage);
            stmt.setDouble(7, ugSGPA);
            stmt.setDouble(8, ugCGPA);
            stmt.setInt(9, ugPassingYear);
            stmt.setString(10, pgCourse);
            stmt.setString(11, pgAdmissionDate);
            stmt.setString(12, pgInstitute);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                String message = "Education details saved successfully \n  Redirecting into 5 sec ...." ;
                response.setHeader("Refresh", "5; URL=combined.jsp");
                response.getWriter().println("<h2>" + message + "</h2>");
                
            } else {
                response.getWriter().println("<div class='notification'>Failed to save education details.</div>");
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
            } catch (Exception ex) {
                response.getWriter().println("<div class='notification'>An error occurred while closing the resources: " + ex.getMessage() + "</div>");
            }
        }
    }
}

