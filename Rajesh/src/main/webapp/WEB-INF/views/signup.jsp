<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign UP</title>

<style>
    body {
        background-color: #6B3B85; /* Royal purple background color */
        overflow: hidden; /* Hide overflow for the waving effect */
    }

    .container {
        width: 400px;
        margin: 50px auto;
        padding: 20px;
        background-color: #7FFFD4; /* Light grey background for the form container */
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        animation: wave 2s infinite alternate; /* Apply the waving animation */
    }

    @keyframes wave {
        0% { transform: rotate(0deg); }
        50% { transform: rotate(5deg); }
        100% { transform: rotate(0deg); }
    }

    .form-group {
        margin-bottom: 20px;
    }

    .form-group label {
        display: block;
        font-weight: bold;
    }

    .form-group input[type="text"], .form-group input[type="radio"] {
        width: calc(50% - 20px);
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    .form-group input[type="radio"] {
        display: inline-block;
        /* Adjust spacing between radio buttons if needed */
    }

    .form-group input[type="submit"] {
        width: 100%;
        padding: 10px;
        border: none;
        border-radius: 5px;
        background-color: #007bff;
        color: #fff;
        font-size: 16px;
        cursor: pointer;
    }

    .form-group input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>


<style>
  /* Style to display radio buttons inline */
  .radio-group input[type="radio"] {
    display: inline-block;
    margin-right: 10px; /* Adjust margin as needed */
  }
</style>
</head>
<body>
	<div align="center">
		<b>Fill all Information</b>
		<div class="container">
			<form action="sigingUp" method="post" modelAttribute="signupForm">
				<div class="form-group">
					<label for="name">Name:</label> <input type="text" id="name"
						name="name" required>
				</div>

				<div class="form-group">
					<label for="place">Place:</label> <input type="text" id="place"
						name="place" required>
				</div>

				<div class="form-group">
					<label for="school">School:</label> <input type="text" id="school"
						name="school" required>
				</div>

				<div class="form-group">
					<label for="age">Age:</label> <input type="text" id="age"
						name="age" required>
				</div>
				
			<div class="form-group">
    			<label for="phnumber">Contact Number:</label> 
    			<input type="text" id="phnumber" name="phnumber" pattern="[0-9]{10}" required>
			</div>



				<div >
					<label>Role:</label> 
					<input type="radio" id="student" name="role" value="student" required>    <label for="student">Student</label> 
					<input type="radio" id="teacher" name="role" value="teacher" required>    <label for="teacher">Teacher</label> 
					<input type="radio" id="principal" name="role" value="principal" required><label for="principal">Principal</label>
				</div>

				<div class="form-group">
					<br><br><input type="submit" value="Submit">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
