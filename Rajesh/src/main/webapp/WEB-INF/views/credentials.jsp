<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.learning.all.entity.SignupEntity" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    
    <style>
        body {
            background-color: #6B3B85; /* Royal purple background color */
            overflow: hidden; /* Hide overflow for the waving effect */
        }

        form {
            width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f2f2f2; /* Light grey background for the form */
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

        .form-group input[type="text"], .form-group input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        #userId {
            background-color: #ddd; /* Light grey background for disabled input */
        }
        
   
    #phnumber {
        display: none;
    }
    </style>
    <script>
        function validateForm() {
            var password = document.getElementById("password").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (password !== confirmPassword) {
                alert("Passwords do not match. Please enter the same password in both fields.");
                return false; // Prevent form submission
            }
            return true; // Allow form submission
        }
    </script>
</head>
<body>
<form action="userInfo" method="post" modelAttribute="allrecords" onsubmit="return validateForm()" >
    <div class="form-group">
        <label for="userId">User ID:</label>
        <input type="text" id="userId" name=userId  value="${all.userId}">
    </div>
    
     <div class="form-group">
        <label for="userId">Name:</label>
        <input type="text" id="name" name="name" value="${all.name}">
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div class="form-group">
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
    </div>
    
     <div class="form-group">
        <input type="text" id="phnumber" name="phnumber" value="${all.phnumber}">
    </div>

    <div class="form-group">
        <input type="submit" value="Submit">
    </div>
</form>
</body>
</html>