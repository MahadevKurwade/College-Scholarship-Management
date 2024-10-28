<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        input[type="file"] {
            margin-bottom: 10px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
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
<h1>Update Education Details</h1>
<form action="UpdateEducationDetailsServlet" method="post" onsubmit="return validateForm()" style="text-align: center">
   <label for="tenthPercentage">10th Percentage:</label>
        <input type="text" id="tenthPercentage" name="tenthPercentage" value="0.00" required><br><br>
        
        <label for="tenthPassingYear">10th Passing Year:</label>
        <input type="text" id="tenthPassingYear" name="tenthPassingYear" required><br><br>
        
        <label for="twelfthPercentage">12th Percentage:</label>
        <input type="text" id="twelfthPercentage" name="twelfthPercentage" value="0.00" required><br><br>
        
        <label for="twelfthPassingYear">12th Passing Year:</label>
        <input type="text" id="twelfthPassingYear" name="twelfthPassingYear" required><br><br>
        
        <label for="ugPercentage">UG Percentage:</label>
        <input type="text" id="ugPercentage" name="ugPercentage" value="0.00"><br><br>
        
        <label for="ugSGPA">UG Last Sem SGPA:</label>
        <input type="text" id="ugSGPA" name="ugSGPA" value="0.00"><br><br> 
        
        <label for="ugCGPA">UG CGPA:</label>
        <input type="text" id="ugCGPA" name="ugCGPA" value="0.00"><br><br>
        
        <label for="ugPassingYear">UG Passing Year:</label>
        <input type="text" id="ugPassingYear" name="ugPassingYear" required><br><br>
        
        <label for="pgCourse">PG Course:</label>
        <input type="text" id="pgCourse" name="pgCourse" required><br><br>
        
        <label for="pgAdmissionDate">PG Admission Date:</label>
        <input type="date" id="pgAdmissionDate" name="pgAdmissionDate" required><br><br>
        
        <label for="pgInstitute">PG Institute:</label>
        <select id="pgInstitute" name="pgInstitute" required size="4">
           <h1> <option value="Sant Gadge Baba Amravati University , Amravati">Sant Gadge Baba Amravati University , Amravati</option> </h1>
    <option value="Sipna College of Engineering and Technology, Amravati">Sipna College of Engineering and Technology, Amravati</option>
    <option value="PR Pote Patil Education and Welfare Trusts Group of Institutions College of Architecture, Amravati">PR Pote Patil Education and Welfare Trusts Group of Institutions College of Architecture, Amravati</option>
    <option value="Manav School of Architecture, Akola">Manav School of Architecture, Akola</option>
    <option value="Dr Rajendra Gode Institute of Technology and Research, Amravati">Dr Rajendra Gode Institute of Technology and Research, Amravati</option>
    <option value="Hanuman Vyayam Prasarak Mandal's College of Engineering and Technology, Amravati">Hanuman Vyayam Prasarak Mandal's College of Engineering and Technology, Amravati</option>
    <option value="Pankaj Laddhad Institute of Technology and Management Studies, Buldana">Pankaj Laddhad Institute of Technology and Management Studies, Buldana</option>
    <option value="Shri Sant Gajanan Maharaj College of Engineering, Shegaon">Shri Sant Gajanan Maharaj College of Engineering, Shegaon</option>
    <option value="Siddhivinayak Technical Campus, Buldana">Siddhivinayak Technical Campus, Buldana</option>
    <option value="Sanmati Engineering College, Washim">Sanmati Engineering College, Washim</option>
    <option value="Prof Ram Meghe Institute of Technology and Research, Badnera">Prof Ram Meghe Institute of Technology and Research, Badnera</option>
    <option value="Prof Ram Meghe College of Engineering and Management, Badnera">Prof Ram Meghe College of Engineering and Management, Badnera</option>
    <option value="PR Patil College of Engineering and Technology, Amravati">PR Patil College of Engineering and Technology, Amravati</option>
    <option value="Pratap Institute of Management and Technology, Washim">Pratap Institute of Management and Technology, Washim</option>
    <option value="Padmashri Dr VB Kolte College of Engineering, Malkapur">Padmashri Dr VB Kolte College of Engineering, Malkapur</option>
    <option value="PR Pote College of Engineering and Management, Amravati">PR Pote College of Engineering and Management, Amravati</option>
    <option value="Mauli Group of Institutions, College of Engineering and Technology, Shegaon">Mauli Group of Institutions, College of Engineering and Technology, Shegaon</option>
    <option value="Rajarshi Shahu College of Engineering, Buldana">Rajarshi Shahu College of Engineering, Buldana</option>
    <option value="Jawaharlal Darda Institute of Engineering and Technology, Yavatmal">Jawaharlal Darda Institute of Engineering and Technology, Yavatmal</option>
    <option value="Jagadambha College of Engineering and Technology, Yavatmal">Jagadambha College of Engineering and Technology, Yavatmal</option>
    <option value="Government College of Engineering, Amravati">Government College of Engineering, Amravati</option>
    <option value="GH Raisoni College of Engineering and Management, Amravati">GH Raisoni College of Engineering and Management, Amravati</option>
    <option value="Dr Sau Kamaltai Gawai Institute of Engineering and Technology, Amravati">Dr Sau Kamaltai Gawai Institute of Engineering and Technology, Amravati</option>
    <option value="Dr Bhausaheb Nandurkar College of Engineering and Technology, Yavatmal">Dr Bhausaheb Nandurkar College of Engineering and Technology, Yavatmal</option>
    <option value="College of Engineering and Technology, Akola">College of Engineering and Technology, Akola</option>
    <option value="Bhonsla College of Engineering and Research, Akola">Bhonsla College of Engineering and Research, Akola</option>
    <option value="Babasaheb Naik College of Engineering, Yavatmal">Babasaheb Naik College of Engineering, Yavatmal</option>
    <option value="Anuradha Engineering College, Buldana">Anuradha Engineering College, Buldana</option>
    <option value="Shri Shivaji Education Society's College of Engineering and Technology, Yavatmal">Shri Shivaji Education Society's College of Engineering and Technology, Yavatmal</option>
    <option value="Manav School of Engineering and Technology, Akola">Manav School of Engineering and Technology, Akola</option>
    <option value="Vidya Bharati Mahavidyalaya, Amravati">Vidya Bharati Mahavidyalaya, Amravati</option>
    <option value="Dr Babasaheb Nandurkar College of Physical Education, Yavatmal">Dr Babasaheb Nandurkar College of Physical Education, Yavatmal</option>
    <option value="Saraswati College, Shegaon">Saraswati College, Shegaon</option>
    <option value="Harikisan Maloo Institute of Management and Technology, Shegoan">Harikisan Maloo Institute of Management and Technology, Shegoan</option>
    <option value="Government College of Pharmacy, Amravati">Government College of Pharmacy, Amravati</option>
    <option value="Sudhakarrao Naik Institute of Pharmacy, Pusad">Sudhakarrao Naik Institute of Pharmacy, Pusad</option>
    <option value="Vidyabharati College of Pharmacy, Amravati">Vidyabharati College of Pharmacy, Amravati</option>
    <option value="P Wadhwani College of Pharmacy, Yavatmal">P Wadhwani College of Pharmacy, Yavatmal</option>
    <option value="Late Shri Ramraoji Gawande Institute of Pharmacy, Akola">Late Shri Ramraoji Gawande Institute of Pharmacy, Akola</option>
    <option value="Indira Bahuuddeshiy Shikshan Sanstha's College of Pharmacy, Malkapur">Indira Bahuuddeshiy Shikshan Sanstha's College of Pharmacy, Malkapur</option>
    <option value="Rajashri Shahu College of Pharmacy, Buldhana">Rajashri Shahu College of Pharmacy, Buldhana</option>
</select>
<br><br>
        </select><br><br>
        
        <input type="submit" value="Save">
    </form>

    <script>
        function validateForm() {
            var tenthPercentage = parseFloat(document.getElementById("tenthPercentage").value);
            var twelfthPercentage = parseFloat(document.getElementById("twelfthPercentage").value);
            var ugPercentage = parseFloat(document.getElementById("ugPercentage").value);
            var ugSGPA = parseFloat(document.getElementById("ugSGPA").value);
            var ugCGPA = parseFloat(document.getElementById("ugCGPA").value);
            var tenthPassingYear = document.getElementById("tenthPassingYear").value;
            var twelfthPassingYear = document.getElementById("twelfthPassingYear").value;
            var ugPassingYear = document.getElementById("ugPassingYear").value;

            if (tenthPercentage < 35 || twelfthPercentage < 35) {
                alert("Percentage is less than 35, therefore not eligible for scholarship.");
                return false;
            }

            if (!/^\d{4}$/.test(tenthPassingYear) || !/^\d{4}$/.test(twelfthPassingYear) || !/^\d{4}$/.test(ugPassingYear)) {
                alert("Passing year must be a 4-digit integer.");
                return false;
            }

          

            return true;
        }
    </script>
</body>
</html>





