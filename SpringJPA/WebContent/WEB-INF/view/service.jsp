<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  width: 200px;
  background-color: green;
}

li a {
  display: block;
  color: #000;
  padding: 8px 16px;
  text-decoration: none;
}

/* Change the link color on hover */
li a:hover {
  background-color: #555;
  color: black;
}
</style>
<body>
<div style="text-align: center;">
<br><br><br><br>
<h1 style="color: purple;">Welcome ${name} to Mypayment Application</h1>
<ul>
<li><a class="active" href="#home">Home</a></li>
<li><a href="showbalance">Show Balance</a><br></li>
<li><a href="fundtransfer">Fund Transfer</a><br></li>
<li><a href="deposit">Deposit</a><br></li>
<li><a href="withdraw">Withdraw</a><br></li>
<li><a href="viewprofile">View Profile</a><br></li>

</ul>
</div>
</body>
</html>