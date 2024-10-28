<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f2f2f2;
        margin: 0;
        padding: 0;
    }
    .container {
        max-width: 800px;
        margin: 20px auto;
        padding: 20px;
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        color: #333;
        text-align: center;
    }
    h2 {
        color: #666;
        border-bottom: 2px solid #ccc;
        padding-bottom: 5px;
        margin-bottom: 10px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #ccc;
    }
    th, td {
        padding: 8px;
        text-align: left;
    }
    tr:nth-child(even) {
        background-color: #f2f2f2;
    }
    tr:hover {
        background-color: #ddd;
    }
    .print-button {
        text-align: center;
        margin-top: 20px;
    }
    .print-button button {
        padding: 10px 20px;
        font-size: 16px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
    }
    .print-button button:hover {
        background-color: #45a049;
    }
    .passport-photo-container {
        float: right;
        width: 150px;
        height: 150px;
        border: 2px dashed #ccc;
        margin-left: 20px;
    }
    .passport-photo-container h3 {
        text-align: center;
        margin-top: 50px;
        color: #999;
    }
</style>
</head>
<body style="background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;">
 
    <div class="container">
        <h1>Scholarship Details.. <h1> <%= session.getAttribute("username") %> (Application Number <%= session.getAttribute("adharNumber") %>)</h1></h1>
        
        <%-- Personal Details --%>
        <h2>Personal Details</h2>
        <table>
            <tr>
                <th>First Name</th>
                <td><%= request.getAttribute("firstName") %></td>
            </tr>
            <tr>
                <th>Middle Name</th>
                <td><%= request.getAttribute("middleName") %></td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td><%= request.getAttribute("lastName") %></td>
            </tr>
            <tr>
                <th>Mother's Name</th>
                <td><%= request.getAttribute("motherName") %></td>
            </tr>
            <tr>
                <th>Age</th>
                <td><%= request.getAttribute("age") %></td>
            </tr>
            <tr>
                <th>Gender</th>
                <td><%= request.getAttribute("gender") %></td>
            </tr>
            <tr>
                <th>Religion</th>
                <td><%= request.getAttribute("religion") %></td>
            </tr>
            <tr>
                <th>Category</th>
                <td><%= request.getAttribute("category") %></td>
            </tr>
        </table>
        
        <%-- Address Details --%>
        <h2>Address Details</h2>
        <table>
            <tr>
                <th>Current Address</th>
                <td><%= request.getAttribute("currentAddress") %></td>
            </tr>
            <tr>
                <th>Current Pincode</th>
                <td><%= request.getAttribute("currentPincode") %></td>
            </tr>
            <tr>
                <th>Permanent Address</th>
                <td><%= request.getAttribute("permanentAddress") %></td>
            </tr>
            <tr>
                <th>Permanent Pincode</th>
                <td><%= request.getAttribute("permanentPincode") %></td>
            </tr>
        </table>
        
        <%-- Bank Details --%>
        <h2>Bank Details</h2>
        <table>
            <tr>
                <th>Account Holder Name</th>
                <td><%= request.getAttribute("accountHolderName") %></td>
            </tr>
            <tr>
                <th>Account Number</th>
                <td><%= request.getAttribute("accountNumber") %></td>
            </tr>
            <tr>
                <th>IFSC Code</th>
                <td><%= request.getAttribute("ifscCode") %></td>
            </tr>
            <tr>
                <th>Branch Name</th>
                <td><%= request.getAttribute("branchName") %></td>
            </tr>
            <tr>
                <th>Bank Name</th>
                <td><%= request.getAttribute("bankName") %></td>
            </tr>
        </table>
        
        <%-- Student Education Details --%>
        <h2>Student Education Details</h2>
        <table>
            <tr>
                <th>Tenth Percentage</th>
                <td><%= request.getAttribute("tenthPercentage") %></td>
            </tr>
            <tr>
                <th>Tenth Passing Year</th>
                <td><%= request.getAttribute("tenthPassingYear") %></td>
            </tr>
            <tr>
                <th>Twelfth Percentage</th>
                <td><%= request.getAttribute("twelfthPercentage") %></td>
            </tr>
            <tr>
                <th>Twelfth Passing Year</th>
                <td><%= request.getAttribute("twelfthPassingYear") %></td>
            </tr>
            <tr>
                <th>UG Percentage</th>
                <td><%= request.getAttribute("ugPercentage") %></td>
            </tr>
            <tr>
                <th>UG SGPA</th>
                <td><%= request.getAttribute("ugSGPA") %></td>
            </tr>
            <tr>
                <th>UG CGPA</th>
                <td><%= request.getAttribute("ugCGPA") %></td>
            </tr>
            <tr>
                <th>UG Passing Year</th>
                <td><%= request.getAttribute("ugPassingYear") %></td>
            </tr>
            <tr>
                <th>PG Course</th>
                <td><%= request.getAttribute("pgCourse") %></td>
            </tr>
            <tr>
                <th>PG Admission Date</th>
                <td><%= request.getAttribute("pgAdmissionDate") %></td>
            </tr>
            <tr>
                <th>PG Institute</th>
                <td><%= request.getAttribute("pgInstitute") %></td>
            </tr>
        </table>
        
        <%-- Scholarship Details --%>
        <h2>Scholarship Details</h2>
        <table>
            <tr>
                <th>College Name</th>
                <td><%= request.getAttribute("collegeName") %></td>
            </tr>
            <tr>
                <th>Scholarship Name</th>
                <td><%= request.getAttribute("scholarshipName") %></td>
            </tr>
            <tr>
                <th>Fees</th>
                <td><%= request.getAttribute("fees") %></td>
            </tr>
        </table>
        
        <div class="print-button">
            <button onclick="window.print()">Print this page</button>
        </div>
    </div>
</body>
</html>
