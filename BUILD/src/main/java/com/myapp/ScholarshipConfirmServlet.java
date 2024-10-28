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

@WebServlet("/ScholarshipConfirmServlet")
public class ScholarshipConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
            out.println("<div style='background-color: #f44336; color: white; padding: 15px;'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Check if details are present in the table
            String selectSql = "SELECT * FROM ScholarshipDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
            	
            	out.println("<style>");
           	 out.println(".header { background-color: #333; color: #fff; padding: 10px; text-align: center; }");
           	  out.println(".edit-btn { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease, transform 0.3s ease; }");
                 out.println(".edit-btn:hover { background-color: #; transform: scale(1.05); }");
                 out.println(".edit-btn:active { transform: scale(0.95); }");
           	 out.println(".header-nav ul { list-style-type: none; padding: 0; }");
                out.println(".header-nav li { display: inline-block; margin: 0 10px; }");
                out.println(".header-nav a:hover { text-decoration: underline;}");
                out.println(".header-nav a:hover { text-decoration: underline;}");
                out.println(".header-nav a { color: #fff; text-decoration: none;font-size: 16px;}");
                out.println("</style>");
                
           	 out.println("<div class='header'>");
                out.println("<div class='user-info'>");
               out.println("<h1>  Scholarship Details</h1>");
                out.println("<p>Adhar No: " + adharNumber + "</p>");
                out.println("</div>");
                out.println("<form action='ProfileServlet'>");
                out.println("<button class='edit-btn'><i class='fas fa-user'></i> Profile</button>");
                out.println("</form>");
                out.println("<div class='header-nav'>");
                out.println("<ul>");
                out.println("<li><button class='edit-btn' onclick='location.href=\"combined.jsp\"'><i class='fas fa-user'></i> Edit Personal Detail</button></li>");
                out.println("<li><form action='AddressDetailsServlet' method='post'>");
                out.println("<button class='edit-btn'><i class='fas fa-address-card'></i> View Address Detail</button>");
                out.println("</form></li>");
                out.println("<li><form action='BankDetailsServlet' method='post'>");
                out.println("<button class='edit-btn'><i class='fas fa-university'></i> View Bank Detail</button>");
                out.println("</form></li>");
                out.println("<li><form action='CheckStudentEducationServlet'>");
                out.println("<button class='edit-btn'><i class='fas fa-graduation-cap'></i> Student Education Details</button>");
                out.println("</form></li>");
                out.println("<li><form action='ScholarshipConfirmServlet' method='post'>");
                out.println("<button class='edit-btn'><i class='fas fa-money-bill-wave'></i> Apply for Scholarship</button>");
                out.println("</form></li>");
                out.println("<li><form action='FetchDetailsServlet' method='post'>");
                out.println("<button class='edit-btn'><i class='fas fa-print'></i> Print Application Form</button>");
                out.println("</form></li>");
                out.println("<li><a href='upload_documents.jsp'><i class='fas fa-file-upload'></i> Upload Documents</a></li>");

                out.println("<li><a href='LogoutServlet'><i class='fas fa-sign-out-alt'></i> Logout</a></li>");
                out.println("</ul>");
                out.println("</div>");
                out.println("</div>");
                out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div style='text-align: center;'>");
            	
                // Details found, display them
                String collegeName = rs.getString("collegeName");
                String scholarshipName = rs.getString("scholarshipName");
                double fees = rs.getDouble("fees");

                out.println("<div style='background-color: #4CAF50; color: white; padding: 15px;'>Scholarship Details:</div>");
                out.println("<div style='padding: 15px;'>");
                out.println("<p><strong>College Name:</strong> " + collegeName + "</p>");
                out.println("<p><strong>Scholarship Name:</strong> " + scholarshipName + "</p>");
                out.println("<p><strong>Fees:</strong> &#8377;" + fees + "</p>");
                out.println("</div>");

                // Redirect to another page or refresh as needed
                out.println("<div style='background-color: #4CAF50; color: white; padding: 15px;'>Redirecting to dashboard in 10 sec ...</div>");
                response.setHeader("Refresh", "10; URL=dashboard.jsp");
            } else {
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div style='background-color: #f44336; color: white; padding: 15px;'>Details not found. Please fill in the scholarship confirmation form.</div>");
                out.println("<div style='background-color: #4CAF50; color: white; padding: 15px;'>Redirecting to scholarship confirmation page...</div>");
                response.setHeader("Refresh", "5; URL=scholarship_confirmation.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            out.println("<div style='background-color: #f44336; color: white; padding: 15px;'>An error occurred: " + e.getMessage() + "</div>");
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
                out.println("<div style='background-color: #f44336; color: white; padding: 15px;'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
            out.close();
        }
    }
}
