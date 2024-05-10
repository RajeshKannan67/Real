<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center"><h1><b>You Successfully Signed in...!</b></h1></div>

<div>
    <form action="/id" method="post">
        <label for="studentId">Enter Student ID:</label>
        <input type="text" id="studentId" name="studentId">
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>