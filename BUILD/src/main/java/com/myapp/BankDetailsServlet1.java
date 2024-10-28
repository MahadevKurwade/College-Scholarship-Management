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

@WebServlet("/BankDetailsServlet1")
public class BankDetailsServlet1 extends HttpServlet {
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
               
            } else {
                // Bank details not found, insert new details
            	
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
                    String message = " Bank Detail Save Successfully  Redirecting into Personal Details Page in 5 sec...." ;
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
