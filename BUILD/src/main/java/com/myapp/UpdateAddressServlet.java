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

@WebServlet("/UpdateAddressServlet")
public class UpdateAddressServlet extends HttpServlet {
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

        String currentAddress = request.getParameter("currentAddress");
        String currentPincode = request.getParameter("currentPincode");     
        String permanentAddress = request.getParameter("permanentAddress");
        String permanentPincode = request.getParameter("permanentPincode");
        if (request.getParameter("sameAsCurrent") != null) {
            permanentAddress = currentAddress;
            permanentPincode = currentPincode;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Connect to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Update address details
            String updateSql = "UPDATE AddressDetails SET currentAddress = ?, currentPincode = ?, permanentAddress = ?, permanentPincode = ? WHERE adharNumber = ?";
            stmt = conn.prepareStatement(updateSql);
            stmt.setString(1, currentAddress);
            stmt.setString(2, currentPincode);
            stmt.setString(3, permanentAddress);
            stmt.setString(4, permanentPincode);
            stmt.setString(5, adharNumber);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
            	 response.getWriter().println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='header'>");
                out.println("<h1>Address details updated successfully.</h1>");
                out.println("<p><b>Redirecting to Personal Details Page...</p>");
                out.println("</div>");

                // Redirect to combined.jsp after 3 seconds
                out.println("<script>");
                out.println("setTimeout(function(){ window.location.href = 'combined.jsp'; }, 3000);");
                out.println("</script>");

            } else {
                out.println("<div class='notification'>Failed to update address details.</div>");
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
            } catch (Exception e) {
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
            }
        }
    }
}
