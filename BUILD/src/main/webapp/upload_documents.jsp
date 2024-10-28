<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Upload Documents</title>
<style>
.header {
    background-color: #333;
    color: #fff;
    padding: 20px;
    text-align: center;
    margin-bottom: 20px;
}

.user-info {
    margin-bottom: 10px;
}

.container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}

.header h1 {
    margin: 0;
    font-size: 24px;
}

.header p {
    margin: 5px 0;
    font-size: 16px;
}

.edit-btn {
    padding: 10px 20px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

.edit-btn:hover {
    background-color: #0056b3;
    transform: scale(1.05);
}

.edit-btn:active {
    transform: scale(0.95);
}

.header-nav {
    text-align: center;
}

.header-nav ul {
    list-style-type: none;
    padding: 0;
}

.header-nav li {
    display: inline-block;
    margin: 0 10px;
}

.header-nav a {
    color: #fff;
    text-decoration: none;
    font-size: 16px;
}

.header-nav a:hover {
    text-decoration: underline;
}
</style>
   
</head>
<body style="background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;">
<div class="header">
       <div class="user-info">
           <h1>Welcome, <%= session.getAttribute("username") %></h1>
           <p>Adhar No: <%= session.getAttribute("adharNumber") %></p>
       </div>
       <form action="ProfileServlet">
                <button  class="edit-btn"><i class="fas fa-user"></i> Profile</button>
            </form>
       <div class="header-nav">
           <ul>
              
             <li> <button class="edit-btn" onclick="location.href='combined.jsp'"><i  class="fas fa-user"></i> Edit Personal Detail</button></li>
           
               <li> <form action="AddressDetailsServlet" method="post">
                <button  class="edit-btn"><i class="fas fa-address-card"></i> View Address Detail</button>
            </form></li>
            
               <li><form action="BankDetailsServlet" method="post">
                <button  class="edit-btn"><i class="fas fa-university"></i> View Bank Detail</button>
            </form></li>
               <li><form action="CheckStudentEducationServlet">
                <button  class="edit-btn"><i class="fas fa-graduation-cap"></i> Student Education Details</button>
            </form></li>
              
               <li> <form action="ScholarshipConfirmServlet" method="post">
                <button class="edit-btn"><i class="fas fa-money-bill-wave"></i> Apply for Scholarship</button>
            </form></li>
            
               <li> <form action="FetchDetailsServlet" method="post">
                <button class="edit-btn"><i class="fas fa-print"></i> Print Application Form</button>
            </form>
            </li> <li><a href="upload_documents.jsp"><i class="fas fa-file-upload"></i> Upload Document</a></li>
            <br>
               <li><a href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
           </ul>
       </div>
    </div>
<div class="container">
<h1>Upload Documents</h1>
<form action="UploadDocumentServlet" method="post" enctype="multipart/form-data" style="text-align: center">
    <label for="adharCard">Adhar Card:</label>
    <input type="file" name="adharCard" id="adharCard" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="admissionLetter">Admission Letter/CAP:</label>
    <input type="file" name="admissionLetter" id="admissionLetter" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="tenthMarkSheet">10th Marksheet:</label>
    <input type="file" name="tenthMarkSheet" id="tenthMarkSheet" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="twelfthMarkSheet">12th Marksheet:</label>
    <input type="file" name="twelfthMarkSheet" id="twelfthMarkSheet" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="ugMarkSheet">UG Marksheet:</label>
    <input type="file" name="ugMarkSheet" id="ugMarkSheet" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="photo">Photo:</label>
    <input type="file" name="photo" id="photo" accept=".jpg,.png" required><br><br>
    
    <label for="signature">Signature:</label>
    <input type="file" name="signature" id="signature" accept=".jpg,.png" required><br><br>
    
    <label for="incomeCertificate">Income Certificate:</label>
    <input type="file" name="incomeCertificate" id="incomeCertificate" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="nonCreamyLayer">Non-Creamy Layer Certificate:</label>
    <input type="file" name="nonCreamyLayer" id="nonCreamyLayer" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="validityCertificate">Validity Certificate:</label>
    <input type="file" name="validityCertificate" id="validityCertificate" accept=".pdf,.jpg,.png" required><br><br>
    
    <label for="tc">Transfer Certificate (TC):</label>
    <input type="file" name="tc" id="tc" accept=".pdf,.jpg,.png" required><br><br>
    
    <input class="edit-btn" type="submit" value="Upload Documents">
</form>
<br>
</div>
</body>
</html>
