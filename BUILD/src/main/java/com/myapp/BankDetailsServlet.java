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

@WebServlet("/BankDetailsServlet")
public class BankDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data 
        String accountHolderName = request.getParameter("accountHolderName");
        String accountNumber = request.getParameter("accountNumber");
        String ifscCode = request.getParameter("ifscCode");
        String branchName = request.getParameter("branchName");
        String bankName = request.getParameter("bankName");
        
        // Get Aadhar number from session
        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");
        
        if (adharNumber == null || adharNumber.isEmpty()) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Check if bank details exist for the user
            String checkSql = "SELECT * FROM BankDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Bank details exist, display them
            	out.println("<style>");
            	 out.println(".header { background-color: #333; color: #fff; padding: 10px; text-align: center; }");
            	  out.println(".edit-btn { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; border-radius: 5px; cursor: pointer; transition: background-color 0.3s ease, transform 0.3s ease; }");
                  out.println(".edit-btn:hover { background-color: #; transform: scale(1.05); }");
                  out.println(".edit-btn:active { transform: scale(0.95); }");
            	 out.println(".header-nav ul { list-style-type: none; padding: 0; }");
                 out.println(".header-nav li { display: inline-block; margin: 0 10px; }");
                 out.println(".header-nav a:hover { text-decoration: underline;}");
                out.println(".header-nav a { color: #fff; text-decoration: none;font-size: 16px;}");

                 out.println("</style>");
                 out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            	 out.println("<div class='header'>");
                 out.println("<div class='user-info'>");
                out.println("<h1>  Bank Details</h1>");
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
                 out.println("<h2>Bank Details</h2>");
                 out.println("<table style='margin: auto;'>");
                 out.println("<tr><td>Account Holder Name:</td><td>" + rs.getString("accountHolderName") + "</td></tr>");
                 out.println("<tr><td>Account Number:</td><td>" + rs.getString("accountNumber") + "</td></tr>");
                 out.println("<tr><td>IFSC Code:</td><td>" + rs.getString("ifscCode") + "</td></tr>");
                 out.println("<tr><td>Branch Name:</td><td>" + rs.getString("branchName") + "</td></tr>");
                 out.println("<tr><td>Bank Name:</td><td>" + rs.getString("bankName") + "</td></tr>");
                 out.println("</table>");

                 // Add button to update bank details
                 out.println("<form action='UpdateBankDetails.jsp' method='get'>");
                 out.println("<input type='hidden' name='adharNumber' value='" + adharNumber + "'>");
                 out.println("<input type='submit' value='Update Bank Details' style='margin-top: 20px; background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                 out.println("</form>");

                 // Add button to redirect to dashboard
                 out.println("<form action='dashboard.jsp' method='get'>");
                 out.println("<input type='submit' value='Dashboard' style='margin-top: 20px; background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer;'>");
                 out.println("</form>");

                 out.println("</div>");

            } else {
                // Bank details not found, insert new details
            	response.sendRedirect("bank_details_form.jsp");
                String insertSql = "INSERT INTO BankDetails (adharNumber, accountHolderName, accountNumber, ifscCode, branchName, bankName) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, adharNumber);
                stmt.setString(2, accountHolderName);
                stmt.setString(3, accountNumber);
                stmt.setString(4, ifscCode);
                stmt.setString(5, branchName);
                stmt.setString(6, bankName);
                int rowsInserted = stmt.executeUpdate();
                
               

                if (rowsInserted > 0) {
                	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                    out.println("<div class='notification'>Bank details Save successfully.</div>");
                    String message = " \n  Redirecting into Personal Details Page in 5 sec...." ;
                    response.setHeader("Refresh", "5; URL=combined.jsp");
                    response.getWriter().println("<h2>" + message + "</h2>");
                } else {
                	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                    out.println("<div class='notification'>Failed to insert bank details.</div>");
                }
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
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
            } catch (Exception ex) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>An error occurred while closing the resources: " + ex.getMessage() + "</div>");
            }
            out.close();
        }
    }
}
