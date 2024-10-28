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

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String adharNumber = request.getParameter("adharNumber");
        String dateOfBirth = request.getParameter("dateOfBirth");   
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // SQL query to check if the username, Aadhar number, and date of birth match
            String sql = "SELECT Pass FROM Regid WHERE UserName = ? AND AdharNumber = ? AND DateOfBirth = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, adharNumber);
            stmt.setString(3, dateOfBirth);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String password = rs.getString("Pass");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Forget Password</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; }");
                out.println(".container { width: 50%; margin: auto; text-align: center; }");
                out.println("button { padding: 10px 20px; background-color: #007bff; color: #fff; border: none; cursor: pointer; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='container'>");
                out.println("<h2>Your password is: " + password + "</h2>");
                out.println("<h3>Redirecting to login page in 5 seconds...</h3>");
                out.println("<form action='resetPassword.jsp'>");
                out.println("<input type='hidden' name='adharNumber' value='" + adharNumber + "'>");
                out.println("<button type='submit'>Reset Password</button>");
                out.println("</form>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            } else {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("Invalid username, Aadhar number, or date of birth.");
            }
        } catch (Exception e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("An error occurred: " + e.getMessage());
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
            } catch (SQLException ex) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("An error occurred while closing the resources: " + ex.getMessage());
            }
            out.close();
        }
    }
}
