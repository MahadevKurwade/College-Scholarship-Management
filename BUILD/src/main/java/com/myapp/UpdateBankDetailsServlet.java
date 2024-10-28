package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateBankDetailsServlet")
public class UpdateBankDetailsServlet extends HttpServlet {
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
        String adharNumber = request.getParameter("adharNumber");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update bank details
            String updateSql = "UPDATE BankDetails SET accountHolderName = ?, accountNumber = ?, ifscCode = ?, branchName = ?, bankName = ? WHERE adharNumber = ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, accountHolderName);
            stmt.setString(2, accountNumber);
            stmt.setString(3, ifscCode);
            stmt.setString(4, branchName);
            stmt.setString(5, bankName);
            stmt.setString(6, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            	 String message = "Bank details updated successfully ! \n  Redirecting into 5 sec ...." ;
                 response.setHeader("Refresh", "5; URL=combined.jsp");
                 response.getWriter().println("<h2>" + message + "</h2>");
            } else {
                out.println("<div class='notification'>Failed to update bank details.</div>");
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
            } catch (Exception ex) {
                out.println("<div class='notification'>An error occurred while closing the resources: " + ex.getMessage() + "</div>");
            }
            out.close();
        }
    }
}
