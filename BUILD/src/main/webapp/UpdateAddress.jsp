<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Address Details</title>
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

        h1 {
            margin: 0;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .address-form label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .address-form input[type="text"],
        .address-form input[type="checkbox"] {
            width: calc(100% - 10px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }

        .checkbox-label {
            display: block;
            margin-bottom: 15px;
            color: #333;
        }

        .submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        .submit-btn:hover {
            background-color: #45a049;
        }

        #permanentAddress,
        #permanentPincode {
            opacity: 0.5;
        }

        #sameAsCurrent:checked ~ #permanentAddress,
        #sameAsCurrent:checked ~ #permanentPincode {
            opacity: 0.5;
            pointer-events: none;
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

<div class="container">
    <div class="address-form">
        <form action="UpdateAddressServlet" method="post" id="addressForm" style="text-align: center">
            <label for="currentAddress">Current Address</label>
            <input type="text" id="currentAddress" name="currentAddress" required>
            
            <label for="currentPincode">Pincode</label>
            <input type="text" id="currentPincode" name="currentPincode" required>
            
            <label class="checkbox-label" for="sameAsCurrent">
                <input type="checkbox" id="sameAsCurrent" name="sameAsCurrent"> Same as Current Address
            </label>
            
            <label for="permanentAddress">Permanent Address</label>
            <input type="text" id="permanentAddress" name="permanentAddress" disabled>
            
            <label for="permanentPincode">Permanent Pincode</label>
            <input type="text" id="permanentPincode" name="permanentPincode" disabled>
            
            <input type="submit" name="Submit" class="submit-btn" value="Update">
        </form>
    </div>
</div>

<script>
    document.getElementById('sameAsCurrent').addEventListener('change', function() {
        var permanentAddress = document.getElementById('permanentAddress');
        var permanentPincode = document.getElementById('permanentPincode');

        if (this.checked) {
            permanentAddress.value = document.getElementById('currentAddress').value;
            permanentPincode.value = document.getElementById('currentPincode').value;
            permanentAddress.disabled = true;
            permanentPincode.disabled = true;
        } else {
            permanentAddress.value = '';
            permanentPincode.value = '';
            permanentAddress.disabled = false;
            permanentPincode.disabled = false;
        }
    });

    // Function to autofill permanent address if unchecked
    function autofillPermanentAddress() {
        var sameAsCurrentCheckbox = document.getElementById('sameAsCurrent');
        var permanentAddress = document.getElementById('permanentAddress');
        var permanentPincode = document.getElementById('permanentPincode');
        var currentAddress = document.getElementById('currentAddress').value;
        var currentPincode = document.getElementById('currentPincode').value;

        if (!sameAsCurrentCheckbox.checked) {
            permanentAddress.value = currentAddress;
            permanentPincode.value = currentPincode;
        }
    }

    // Call autofillPermanentAddress() on page load
    window.onload = function() {
        autofillPermanentAddress();
    };
</script>

</body>
</html>
