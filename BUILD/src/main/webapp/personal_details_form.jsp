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
    
    
    
    <h2 style="text-align: center;"> <i>Please Fill Personal Details</i></h2>
    <% 
        // Check if user details already exist in the database
        boolean userDetailsExist = false; // Flag to track if user details exist
        // Your database connection and query code here to check if user details exist
        // If user details exist, set userDetailsExist to true
        
        if (!userDetailsExist) { // If user details do not exist, display the form
    %>
    <form style="text-align: center;"action="InsertPersonalDetailsServlet" method="post">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br><br>
        
        <label for="middleName">Middle Name:</label>
        <input type="text" id="middleName" name="middleName" required><br><br>
        
        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br><br>
        
        <label for="motherName">Mother's Name:</label>
        <input type="text" id="motherName" name="motherName" required><br><br>
        
        <label for="age">Age:</label>
        <select id="age" name="age" required>
            <option value="">Select Age</option>
            <% for (int i = 1; i <= 100; i++) { %>
                <option value="<%= i %>"><%= i %></option>
            <% } %>
        </select><br><br>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="">Select Gender</option>
            <option value="Male">Male</option>
            <option value="Female">Female</option>
        </select><br><br>
        
        <label for="religion">Religion:</label>
        <select id="religion" name="religion" required>
            <option value="">Select Religion</option>
            <option value="Hindu">Hindu</option>
            <option value="Muslim">Muslim</option>
             <option value="Buddhism">Buddhist</option>
            <option value="Christian">Christian</option>
            <option value="Sikh">Sikh</option>
        </select><br><br>
        
        <label for="category">Category:</label>
        <select id="category" name="category" required>
            <option value="">Select Caste</option>
                <option value="SC">Scheduled Caste (SC)</option>
                <option value="ST">Scheduled Tribe (ST)</option>
                <option value="OBC">Other Backward Class (OBC)</option>
                <option value="OPEN">Open Category</option>
                 <option value="NTC">Open Category</option>
                <!-- Add more caste options specific to Maharashtra -->
                <option value="VJNT">Vimukta Jati Nomadic Tribes (VJNT)</option>
                <option value="NT">Nomadic Tribes (NT)</option>
                <option value="SBC">Special Backward Class (SBC)</option>
        </select><br><br>
        
        <label for="casteCertificateNo">Caste Certificate No:</label>
        <input type="text" id="casteCertificateNo" name="casteCertificateNo"><br><br>
        
        <label for="validityCertificateNo">Validity Certificate No:</label>
        <input type="text" id="validityCertificateNo" name="validityCertificateNo"><br><br>
        
        <label for="nonCreamyCertificateNo">Non Creamy Certificate No:</label>
        <input type="text" id="nonCreamyCertificateNo" name="nonCreamyCertificateNo"><br><br>
        
        <label for="hosteler">Hosteler:</label>
        <select id="hosteler" name="hosteler" required>
            <option value="Yes">Yes</option>
            <option value="No">No</option>
        </select><br><br>
        
        <input type="submit" value="Save" name="Save">
    </form>
    <% } else { // If user details exist, display them %>
    <div class="notification">Personal details already exist:</div>
    <!-- Display user details here -->
    <% } %>
</body>
</html>
