<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Scholarship Details Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        select, input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            box-sizing: border-box;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body style="background-image: url('https://i.pinimg.com/originals/89/0f/2b/890f2b80e3b616d5d8c83a34a98400e2.jpg'); background-size: cover;">
    <div class="container">
        <h2>Scholarship Details Form</h2>
        <form action="UpdateScholarshipDetailsServlet" method="post">
            <label for="collegeName">College Name:</label>
      <select id="collegeName" name="collegeName" required>
    <option value="">Select College</option>
    <h1> <option value="Sipna College of Engineering and Technology, Amravati">Sant Gadge Baba Amravati University , Amravati</option> </h1>
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
</select><br><br>
            
            <label for="scholarshipName">Scholarship Name:</label>
            <select id="scholarshipName" name="scholarshipName">
                <!-- Populate scholarship options from database -->
                <%-- Here you can add Java code to fetch and display scholarship options from the database --%>
              <option value="Maharashtra State Scholarship for Economically Backward Class (EBC) Students">Maharashtra State Scholarship for Economically Backward Class (EBC) Students</option>
<option value="Maharashtra State Scholarship for VJNT Students">Maharashtra State Scholarship for VJNT Students</option>
<option value="Maharashtra State Scholarship for SBC Students">Maharashtra State Scholarship for SBC Students</option>
<option value="Maharashtra State Scholarship for OBC Students">Maharashtra State Scholarship for OBC Students</option>
<option value="Maharashtra State Scholarship for SC Students">Maharashtra State Scholarship for SC Students</option>
<option value="Maharashtra State Scholarship for ST Students">Maharashtra State Scholarship for ST Students</option>
<option value="Maharashtra State Minority Scholarship for OBC Students">Maharashtra State Minority Scholarship for OBC Students</option>
<option value="Maharashtra State Minority Scholarship for SC Students">Maharashtra State Minority Scholarship for SC Students</option>
<option value="Maharashtra State Minority Scholarship for ST Students">Maharashtra State Minority Scholarship for ST Students</option>
<option value="Maharashtra State Minority Scholarship for EBC Students">Maharashtra State Minority Scholarship for EBC Students</option>
<option value="Maharashtra State Minority Scholarship for SBC Students">Maharashtra State Minority Scholarship for SBC Students</option>
<option value="Rajarshi Chhatrapati Shahu Maharaj Shikshan Shulkh Shishyavrutti Yojana">Rajarshi Chhatrapati Shahu Maharaj Shikshan Shulkh Shishyavrutti Yojana</option>
<!-- Add the rest of the scholarships here -->
<option value="Post-Matric Scholarship for SBC Boys">Post-Matric Scholarship for SBC Boys</option>

<option value="Maharashtra State Scholarship for Economically Backward Class (EBC) Students">Maharashtra State Scholarship for Economically Backward Class (EBC) Students</option>
<option value="Maharashtra State Scholarship for VJNT Students">Maharashtra State Scholarship for VJNT Students</option>
<option value="Maharashtra State Scholarship for SBC Students">Maharashtra State Scholarship for SBC Students</option>
<option value="Maharashtra State Scholarship for OBC Students">Maharashtra State Scholarship for OBC Students</option>
<option value="Maharashtra State Scholarship for SC Students">Maharashtra State Scholarship for SC Students</option>
<option value="Maharashtra State Scholarship for ST Students">Maharashtra State Scholarship for ST Students</option>
<option value="Maharashtra State Minority Scholarship for OBC Students">Maharashtra State Minority Scholarship for OBC Students</option>
<option value="Maharashtra State Minority Scholarship for SC Students">Maharashtra State Minority Scholarship for SC Students</option>
<option value="Maharashtra State Minority Scholarship for ST Students">Maharashtra State Minority Scholarship for ST Students</option>
<option value="Maharashtra State Minority Scholarship for EBC Students">Maharashtra State Minority Scholarship for EBC Students</option>
<option value="Maharashtra State Minority Scholarship for SBC Students">Maharashtra State Minority Scholarship for SBC Students</option>
<option value="Rajarshi Chhatrapati Shahu Maharaj Shikshan Shulkh Shishyavrutti Yojana">Rajarshi Chhatrapati Shahu Maharaj Shikshan Shulkh Shishyavrutti Yojana</option>
<option value="Post-Matric Scholarship for SBC Boys">Post-Matric Scholarship for SBC Boys</option>
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
<option value="Scholarship A">Scholarship A</option>
            </select><br><br>
            
            <label for="fees">Fees (Hostel & College Fees):</label>
            <input type="number" id="fees" name="fees" required><br><br>
            
            <input type="submit" value="Save">
        </form>
    </div>
</body>
</html>
