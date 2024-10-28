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

@WebServlet("/FetchCastServlet")
public class FetchCastServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sql6688080";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String adharNumber = (String) session.getAttribute("adharNumber");
        if (adharNumber == null || adharNumber.isEmpty()) {
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            response.setHeader("Refresh", "1; URL=personal_details_form.jsp");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String selectCategorySql = "SELECT category FROM PersonalDetails WHERE adharNumber = ?";
            stmt = conn.prepareStatement(selectCategorySql);
            stmt.setString(1, adharNumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String category = rs.getString("category");
                // Set category as a request attribute to pass to the JSP
                session.setAttribute("category", category);
                response.sendRedirect("Scholarship.jsp");
            } else {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>Category details not found.</div>");
                response.setHeader("Refresh", "1; URL=personal_details_form.jsp");
                return;
            }
        } catch (SQLException | ClassNotFoundException e) {
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
            } catch (SQLException e) {
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
                response.setHeader("Refresh", "1; URL=personal_details_form.jsp");
            }
            out.close();
        }
    }
}
