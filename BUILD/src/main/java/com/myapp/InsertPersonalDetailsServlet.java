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

@WebServlet("/InsertPersonalDetailsServlet")
public class InsertPersonalDetailsServlet extends HttpServlet {
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

            {
            
                // User details do not exist, insert the details into the database
                String insertSql = "INSERT INTO PersonalDetails (adharNumber, firstName, middleName, lastName, motherName, age, gender, religion, category, casteCertificateNo, validityCertificateNo, nonCreamyCertificateNo, hosteler) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, adharNumber);
                stmt.setString(2, request.getParameter("firstName"));
                stmt.setString(3, request.getParameter("middleName"));
                stmt.setString(4, request.getParameter("lastName"));
                stmt.setString(5, request.getParameter("motherName"));
                stmt.setInt(6, Integer.parseInt(request.getParameter("age")));
                stmt.setString(7, request.getParameter("gender"));
                stmt.setString(8, request.getParameter("religion"));
                stmt.setString(9, request.getParameter("category"));
                stmt.setString(10, request.getParameter("casteCertificateNo"));
                stmt.setString(11, request.getParameter("validityCertificateNo"));
                stmt.setString(12, request.getParameter("nonCreamyCertificateNo"));
                stmt.setString(13, request.getParameter("hosteler"));

                int rowsInserted = stmt.executeUpdate();

                if (rowsInserted > 0) {
                    out.println("<div class='notification'> Personal details SAVE Successfully.</div>");
                    out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                    String message = "  \n  Redirecting into Personal Details Page in 5 sec...." ;
                    response.setHeader("Refresh", "3; URL=combined.jsp");
                    response.getWriter().println("<h2>" + message + "</h2>");
                    
                } else {
                    out.println("<div class='notification'>Failed to insert personal details.</div>");
                }
                
                
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
