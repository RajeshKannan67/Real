<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login Form</title>
<style>
body {
    background-color: #90EE90; /* Light green background color */
    font-family: Arial, sans-serif;
}

.container {
    width: 400px;
    margin: 100px auto;
    padding: 20px;
    background-color: #fff; /* White background for the form */
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    animation: wave 2s infinite alternate; /* Apply the waving animation */
}

@keyframes wave {
    0% { transform: rotate(0deg); }
    50% { transform: rotate(5deg); }
    100% { transform: rotate(0deg); }
}

.heading {
    margin-bottom: 20px;
    font-size: 24px;
    font-weight: bold;
    color: #333; /* Dark grey color */
    animation: blink 1s infinite alternate; /* Apply blinking animation */
}

@keyframes blink {
    0% { opacity: 1; }
    100% { opacity: 0.5; }
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

.submit-btn {
    background-color: #4CAF50; /* Green background for submit button */
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
}

.submit-btn:hover {
    background-color: #00FF7F; /* Darker green background on hover */
}
</style>
</head>
<body>

<div class="container">
    <div class="heading" align="center">Enter Your Login Credentials</div>
    <form action="log" method="post" modelAttribute="logDetails">
        <div class="form-group">
            <label for="user_id">User Id:</label>
            <input type="text" id="user_id" name="user_id" placeholder="Enter Your User ID" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter Your Password" required>
        </div>
        
        <div class="form-group">
            <input type="submit" value="Submit" class="submit-btn">
        </div>
    </form>
</div>

</body>
</html>