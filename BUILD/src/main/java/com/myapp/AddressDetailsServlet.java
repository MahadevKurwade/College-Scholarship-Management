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

@WebServlet("/AddressDetailsServlet")
public class AddressDetailsServlet extends HttpServlet {
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

            // Check if address details exist for the user
            String selectSql = "SELECT * FROM AddressDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectSql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Address details already exist, display them
            	
            	
            	
                request.setAttribute("currentAddress", rs.getString("currentAddress"));
                request.setAttribute("currentPincode", rs.getString("currentPincode"));
                request.setAttribute("permanentAddress", rs.getString("permanentAddress"));
                request.setAttribute("permanentPincode", rs.getString("permanentPincode"));
                request.getRequestDispatcher("address_details.jsp").forward(request, response);
            } else {
                // Address details don't exist, redirect to a page to enter details
                response.sendRedirect("enter_address.jsp");
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
            } catch (Exception e) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
        }
    }
}
