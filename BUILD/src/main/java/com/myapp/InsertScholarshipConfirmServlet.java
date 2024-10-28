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
import javax.servlet.http.HttpSession;

@WebServlet("/InsertScholarshipConfirmServlet")
public class InsertScholarshipConfirmServlet extends HttpServlet {
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
            out.println("<div class='notification'>Error: Aadhar number not found in session. Please login again.</div>");
            return;
        }

        String income = request.getParameter("income");
        String hostelr = request.getParameter("hostelr");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            String insertSql = "INSERT INTO ScholarshipConfirmDetails (adharNumber, income, hostelr) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(insertSql);
            stmt.setString(1, adharNumber);
            stmt.setString(2, income);
            stmt.setString(3, hostelr);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                response.sendRedirect("FetchCastServlet");
            } else {
            	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
                out.println("<div class='notification'>Failed to insert scholarship confirmation details.</div>");
            }
        } catch (SQLException | ClassNotFoundException e) {
        	out.println("<body style=\"background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;\" >");
            out.println("<div class='notification'><h1>last updated data found </div>");
            out.println("<div class='notification'>Redirecting......... </div>");
            response.setHeader("Refresh", "2; URL=Scholarship.jsp");
            //    update 

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            	
                out.println("<div class='notification'>An error occurred while closing resources: " + e.getMessage() + "</div>");
                           }
            out.close();
        }
    }
}
