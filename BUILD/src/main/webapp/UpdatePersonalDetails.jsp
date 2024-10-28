<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Module Selection</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        

        .container {
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); 
        }

        h2 {
            margin-top: 0;
            color: #333;
        }

        .button-group {
            display: flex;
            flex-direction: column;
            gap: 10px; /* Add gap between buttons */
            margin-top: 20px;
        }

        .module-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            transition: transform 0.3s;
        }

        .module-button:hover {
            background-color: #45a049;
            transform: scale(1.05);
        }

        .module-button:active {
            transform: scale(0.95);
        }
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
    <% 
        if (request.getParameter("updated") != null) { // Check if updated parameter is present in URL
            String updated = request.getParameter("updated");
            if (updated.equals("true")) { // Check if personal details were successfully updated
    %>
    <div class="notification">Personal details updated successfully. Redirecting to Personal Details Page </div>
    <%      } else { // If update failed
    %>
    <div class="notification">Failed to update personal details.</div>
    <%      }
        }
    %>
    
    <h2 style="text-align: center;"> <i>Update Personal Details </i></h2>
    <% 
        // Retrieve user details from the database and pre-fill the form fields
        // Your database connection and query code here
        
        // Sample code to pre-fill form fields (replace with actual data retrieval)
        String firstName = "";
        String middleName = "";
        String lastName = "";
        String motherName = "";
        int age = 25;
        String gender = "Male";
        String religion = "Hindu";
        String category = "General";
        String casteCertificateNo = "";
        String validityCertificateNo = "";
        String nonCreamyCertificateNo = "";
        String hosteler = "Yes";
    %>
    <form style="text-align: center;"action="UpdatePersonalDetailsServlet" method="post" id="updateForm">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" value="<%= firstName %>" required><br><br>
        
        <label for="middleName">Middle Name:</label>
        <input type="text" id="middleName" name="middleName" value="<%= middleName %>" required><br><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" value="<%= lastName %>" required><br><br>
        
        <label for="motherName">Mother's Name:</label>
        <input type="text" id="motherName" name="motherName" value="<%= motherName %>" required><br><br>
        
        <label for="age">Age:</label>
        <input type="text" id="age" name="age" value="<%= age %>" required><br><br>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="Male" <%= gender.equals("Male") ? "selected" : "" %>>Male</option>
            <option value="Female" <%= gender.equals("Female") ? "selected" : "" %>>Female</option>
        </select><br><br>
        
        <label for="religion">Religion:</label>
        <input type="text" id="religion" name="religion" value="<%= religion %>" required><br><br>
        
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="<%= category %>" required><br><br>
        
        <label for="casteCertificateNo">Caste Certificate No:</label>
        <input type="text" id="casteCertificateNo" name="casteCertificateNo" value="<%= casteCertificateNo %>"><br><br>
        
        <label for="validityCertificateNo">Validity Certificate No:</label>
        <input type="text" id="validityCertificateNo" name="validityCertificateNo" value="<%= validityCertificateNo %>"><br><br>
        
        <label for="nonCreamyCertificateNo">Non Creamy Certificate No:</label>
        <input type="text" id="nonCreamyCertificateNo" name="nonCreamyCertificateNo" value="<%= nonCreamyCertificateNo %>"><br><br>
        
        <label for="hosteler">Hosteler:</label>
        <select id="hosteler" name="hosteler" required>
            <option value="Yes" <%= hosteler.equals("Yes") ? "selected" : "" %>>Yes</option>
            <option value="No" <%= hosteler.equals("No") ? "selected" : "" %>>No</option>
        </select><br><br>
        
        <input type="submit" value="Update" name="Update">
    </form>
    
    <%-- JavaScript to display notification and redirect after delay --%>
    <%
        // Check if the request parameter 'updated' is present
        String updated = request.getParameter("updated");
        if (updated != null && updated.equals("true")) {
    %>
    <div class="notification">Personal details updated successfully.</div>
    <%-- Redirect to combined.jsp after 3 seconds --%>
    <script>
        redirectWithDelay("combined.jsp", 3000); // 3000 milliseconds = 3 seconds
    </script>
    <% } %>
</body>
</html>
