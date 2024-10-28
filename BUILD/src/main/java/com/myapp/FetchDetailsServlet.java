package com.myapp;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@WebServlet("/FetchDetailsServlet")
public class FetchDetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false); // Get session, don't create if it doesn't exist
        if(session != null) {
            String adharNumber = (String) session.getAttribute("adharNumber");
            
            // JDBC parameters
            String jdbcURL = "jdbc:mysql://localhost:3306/sql6688080";
            String dbUser = "root";
            String dbPassword = "root"; // Update with your actual password
            
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
                String query = "SELECT * FROM PersonalDetails WHERE adharNumber=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, adharNumber);
                resultSet = preparedStatement.executeQuery();
                
                if(resultSet.next()) {
                    String firstName = resultSet.getString("firstName");
                    String middleName = resultSet.getString("middleName");
                    String lastName = resultSet.getString("lastName");
                    String motherName = resultSet.getString("motherName");
                    int age = resultSet.getInt("age");
                    String gender = resultSet.getString("gender");
                    String religion = resultSet.getString("religion");
                    String category = resultSet.getString("category");
                    
                    // Forward the personal details to JSP
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("middleName", middleName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("motherName", motherName);
                    request.setAttribute("age", age);
                    request.setAttribute("gender", gender);
                    request.setAttribute("religion", religion);
                    request.setAttribute("category", category);
                    
                    // Fetch address details
                    String addressQuery = "SELECT * FROM AddressDetails WHERE adharNumber=?";
                    preparedStatement = connection.prepareStatement(addressQuery);
                    preparedStatement.setString(1, adharNumber);
                    ResultSet addressResult = preparedStatement.executeQuery();
                    if(addressResult.next()) {
                        // Forward the address data to JSP
                        request.setAttribute("currentAddress", addressResult.getString("currentAddress"));
                        request.setAttribute("currentPincode", addressResult.getString("currentPincode"));
                        request.setAttribute("permanentAddress", addressResult.getString("permanentAddress"));
                        request.setAttribute("permanentPincode", addressResult.getString("permanentPincode"));
                    }
                    
                    // Fetch bank details
                    String bankQuery = "SELECT * FROM BankDetails WHERE adharNumber=?";
                    preparedStatement = connection.prepareStatement(bankQuery);
                    preparedStatement.setString(1, adharNumber);
                    ResultSet bankResult = preparedStatement.executeQuery();
                    if(bankResult.next()) {
                        // Forward the bank data to JSP
                        request.setAttribute("accountHolderName", bankResult.getString("accountHolderName"));
                        request.setAttribute("accountNumber", bankResult.getString("accountNumber"));
                        request.setAttribute("ifscCode", bankResult.getString("ifscCode"));
                        request.setAttribute("branchName", bankResult.getString("branchName"));
                        request.setAttribute("bankName", bankResult.getString("bankName"));
                    }
                    
                 // Fetch student education details
                    String educationQuery = "SELECT * FROM StudentEducation WHERE adharNumber=?";
                    preparedStatement = connection.prepareStatement(educationQuery);
                    preparedStatement.setString(1, adharNumber);
                    ResultSet educationResult = preparedStatement.executeQuery();
                    if (educationResult.next()) {
                        // Forward the education data to JSP
                        request.setAttribute("tenthPercentage", educationResult.getBigDecimal("tenthPercentage"));
                        request.setAttribute("tenthPassingYear", educationResult.getInt("tenthPassingYear"));
                        request.setAttribute("twelfthPercentage", educationResult.getBigDecimal("twelfthPercentage"));
                        request.setAttribute("twelfthPassingYear", educationResult.getInt("twelfthPassingYear"));
                        request.setAttribute("ugPercentage", educationResult.getBigDecimal("ugPercentage"));
                        request.setAttribute("ugSGPA", educationResult.getBigDecimal("ugSGPA"));
                        request.setAttribute("ugCGPA", educationResult.getBigDecimal("ugCGPA"));
                        request.setAttribute("ugPassingYear", educationResult.getInt("ugPassingYear"));
                        request.setAttribute("pgCourse", educationResult.getString("pgCourse"));
                        request.setAttribute("pgAdmissionDate", educationResult.getDate("pgAdmissionDate"));
                        request.setAttribute("pgInstitute", educationResult.getString("pgInstitute"));
                    }
                    
                 // Fetch scholarship details
                    String scholarshipQuery = "SELECT * FROM scholarshipdetails WHERE adharNumber=?";
                    preparedStatement = connection.prepareStatement(scholarshipQuery);
                    preparedStatement.setString(1, adharNumber);
                    ResultSet scholarshipResult = preparedStatement.executeQuery();
                    if(scholarshipResult.next()) {
                        // Forward the scholarship data to JSP
                        request.setAttribute("collegeName", scholarshipResult.getString("collegeName"));
                        request.setAttribute("scholarshipName", scholarshipResult.getString("scholarshipName"));
                        request.setAttribute("fees", scholarshipResult.getBigDecimal("fees"));
                        // Add more attributes as needed
                    }

                    
                    request.getRequestDispatcher("user_details.jsp").forward(request, response);
                } else {
                    out.println("No details found for the current user.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if(resultSet != null) resultSet.close();
                    if(preparedStatement != null) preparedStatement.close();
                    if(connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            out.println("Session expired or user not logged in.");
        }
    }
}
