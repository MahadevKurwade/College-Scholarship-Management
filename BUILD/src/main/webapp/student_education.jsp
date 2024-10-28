<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Education Details</title>
</head>
<body style="background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;">
<div class="header">
       <h1>Welcome, <%= session.getAttribute("username") %> (Adhar No: <%= session.getAttribute("adharNumber") %>)</h1>
    </div>
    
    <h1>Student Education Details</h1>
    <table border="1">
        <tr> 
            <th>Exam</th>
            <th>Percentage/SGPA/CGPA</th>
            <th>Passing Year</th>
        </tr>
        <tr>
            <td>10th Standard</td>
            <td><%= request.getAttribute("tenthPercentage") %></td>
            <td><%= request.getAttribute("tenthPassingYear") %></td>
        </tr> 
        <tr>
            <td>12th Standard</td>
            <td><%= request.getAttribute("twelfthPercentage") %></td>
            <td><%= request.getAttribute("twelfthPassingYear") %></td>
        </tr>
        <tr>
            <td>Undergraduate (UG)</td>
            <td>
                <% 
                    Double ugPercentage = (Double) request.getAttribute("ugPercentage");
                    Double ugSGPA = (Double) request.getAttribute("ugSGPA");
                    Double ugCGPA = (Double) request.getAttribute("ugCGPA");
                    if (ugPercentage != null) {
                        out.print("Percentage: " + ugPercentage);
                    } else if (ugSGPA != null) {
                        out.print("SGPA: " + ugSGPA);
                    } else if (ugCGPA != null) {
                        out.print("CGPA: " + ugCGPA);
                    }
                %>
            </td>
            <td><%= request.getAttribute("ugPassingYear") %></td>
        </tr>
        <tr>
            <td>Postgraduate (PG)</td>
            <td><%= request.getAttribute("pgCourse") %></td>
            <td><%= request.getAttribute("pgAdmissionDate") %></td>
            <td><%= request.getAttribute("pgInstitute") %></td>
        </tr>
    </table>
</body>
</html>
