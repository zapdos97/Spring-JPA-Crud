<%@ page import="com.cg.mypaymentapp.beans.Customer" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style="text-align: center;">
<h1 style="color: red;">Your Profile</h1><br>
<h2 style="font-style: italic;">Name    </h2><h2 style="color: blue;">${name}</h2>
<h2 style="font-style: italic;">Mobile Number    </h2><h2 style="color:blue;">${mobileNumber}</h2>
<h2 style="font-style: italic;">Balance  </h2><h2 style="color:blue;">${balance}</h2>
</div>
</body>
</html>