package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String adharNumber = (String) session.getAttribute("adharNumber");

        if (username == null || username.isEmpty() || adharNumber == null || adharNumber.isEmpty()) {
            out.println("<div class='notification'>Error: User information not found in session. Please login again.</div>");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Fetch user profile details
            String selectSql = "SELECT * FROM Regid WHERE AdharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // User profile details found, display them in the servlet page
                out.println("<!DOCTYPE html>");
                out.println("<html lang='en'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>User Profile</title>");
                out.println("<link rel='stylesheet' href='styles.css'>");
                out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css'>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
                out.println(".header { background-color: #333; color: #fff; padding: 10px; text-align: center; }");
                out.println(".container { max-width: 800px; margin: 20px auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); }");
                out.println(".profile-details { margin-bottom: 20px; }");
                out.println(".profile-details h2 { margin-bottom: 10px; }");
                out.println(".profile-details p { margin: 5px 0; }");
                out.println(".edit-btn { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease, transform 0.3s ease; }");
                out.println(".edit-btn:hover { background-color: #; transform: scale(1.05); }");
                out.println(".edit-btn:active { transform: scale(0.95); }");
                out.println(".header-nav ul { list-style-type: none; padding: 0; }");
                out.println(".header-nav li { display: inline-block; margin: 0 10px; }");
                out.println(".header-nav a:hover { text-decoration: underline;}");
                out.println(".header-nav a { color: #fff; text-decoration: none;font-size: 16px;}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");

                out.println("<div class='header'>");
                out.println("<div class='user-info'>");
                out.println("<h1>Welcome, " + username + "</h1>");
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
                out.println("<li><a href='upload_documents.jsp'><i class='fas fa-file-upload'></i> Upload Document</a></li>");
                out.println("<li><a href='LogoutServlet'><i class='fas fa-sign-out-alt'></i> Logout</a></li>");
                out.println("</ul>");
                out.println("</div>");
                out.println("</div>");
                
                out.println("<div class='container' style=\"background-image: url(''); background-size: cover; background-position: center; width: 400px; height: 250px;\">");
                out.println("<div class='profile-details'>");
                out.println("<h2>User Profile Details</h2>");
                out.println("<p><strong>Applicant Name:</strong> " + rs.getString("ApplicantName") + "</p>");
                out.println("<p><strong>Username:</strong> " + rs.getString("UserName") + "</p>");
                out.println("<p><strong>Email:</strong> " + rs.getString("Email") + "</p>");
                out.println("<p><strong>Mobile Number:</strong> " + rs.getLong("MobileNumber") + "</p>");
                out.println("<p><strong>Aadhar Number:</strong> " + rs.getString("AdharNumber") + "</p>");
                out.println("<p><strong>Date of Birth:</strong> " + rs.getDate("DateOfBirth") + "</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
                // User profile details not found
                out.println("<div class='notification'>User profile details not found.</div>");
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
            } catch (Exception e) {
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
        }
    }
}
