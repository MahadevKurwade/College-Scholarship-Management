<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Bank Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .address-details {
            margin-bottom: 20px;
        }

        .address-details h2 {
            margin-bottom: 10px;
        }

        .address-details p {
            margin: 5px 0;
        }

        .update-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
            transition: background-color 0.3s ease;
        }

        .update-btn:hover {
            background-color: #45a049;
            transform: scale(1.05);
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

    <div id="message" style="display: none;" >
        <p>Details submitted successfully. Redirecting...</p>
    </div>

    <form id="bankForm" action="BankDetailsServlet1" method="post" style="text-align: center;">
        <label for="accountHolderName">Account Holder Name:</label>
        <input type="text" id="accountHolderName" name="accountHolderName" required><br><br>
        
        <label for="accountNumber">Account Number:</label>
        <input type="text" id="accountNumber" name="accountNumber" required><br><br>
        
        <label for="confirmAccountNumber">Confirm Account Number:</label>
        <input type="text" id="confirmAccountNumber" name="confirmAccountNumber" required><br>
        <span id="accountNumberError" class="error-message"></span><br>
        
        <label for="ifscCode">IFSC Code:</label>
        <input type="text" id="ifscCode" name="ifscCode" required><br><br>
        
        <label for="branchName">Branch Name:</label>
        <input type="text" id="branchName" name="branchName" required><br><br>
        
        <label for="bankName">Bank Name:</label>
        <input type="text" id="bankName" name="bankName" required><br><br>
        
        <input class="edit-btn" type="submit" value="Submit">
    </form>

    <script>
        // Function to show message and redirect after 3 seconds
        function showMessageAndRedirect() {
            var messageDiv = document.getElementById('message');
            messageDiv.style.display = 'block';
            setTimeout(function() {
                window.location.href = 'combined.jsp';
            }, 3000); // 3000 milliseconds = 3 seconds
        }

        // Submit form event listener
        document.getElementById('bankForm').addEventListener('submit', function(event) {
            // Check if account numbers match
            var accountNumber = document.getElementById('accountNumber').value;
            var confirmAccountNumber = document.getElementById('confirmAccountNumber').value;
            if (accountNumber !== confirmAccountNumber) {
                document.getElementById('accountNumberError').innerText = "Account numbers do not match.";
                event.preventDefault(); // Prevent form submission
            } else {
                // Show message and redirect
                showMessageAndRedirect();
            }
        });
    </script>
</body>
</html>