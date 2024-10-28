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

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String adharNumber = request.getParameter("adharNumber");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // Verify new password and confirm password match
        if (!newPassword.equals(confirmPassword)) {
            out.println("New password and confirm password do not match.");
            return;
        }
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // Update password in the database
            String updatePasswordSQL = "UPDATE Regid SET Pass = ? WHERE AdharNumber = ?";
            stmt = conn.prepareStatement(updatePasswordSQL);
            stmt.setString(1, newPassword);
            stmt.setString(2, adharNumber);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Password Reset</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; }");
                out.println(".container { width: 50%; margin: auto; text-align: center; }");
                out.println("button { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; cursor: pointer; }");
                out.println("button:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='container'>");
                out.println("<h2>Password updated successfully.</h2>");
                
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                String message = "   Redirecting into Login Page in 5 sec...." ;
                response.setHeader("Refresh", "10; URL=student_login.html");
                response.getWriter().println("<h2>" + message + "</h2>");
            } else {
                out.println("Failed to update password.");
                String message = "   Redirecting into Login Page in 5 sec...." ;
                response.setHeader("Refresh", "5; URL=student_login.html");
                response.getWriter().println("<h2>" + message + "</h2>");
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Password Reset</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; }");
                out.println(".container { width: 50%; margin: auto; text-align: center; }");
                out.println("button { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; cursor: pointer; }");
                out.println("button:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='container'>");
                out.println("<h2><button onclick=\"location.href='registration.html'\">Registration</button></h2>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("An error occurred while closing the resources: " + ex.getMessage());
            }
            out.close();
        }
    }
}
