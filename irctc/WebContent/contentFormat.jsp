<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>


<!DOCTYPE html>
<html>
<head>


 <link rel="stylesheet" href="./css/table_style.css">
 <link rel="stylesheet" type="text/css" media="all" href="./css/input_style.css">

<meta charset="ISO-8859-1">
<title>IRCTC - Welcome <%=session.getAttribute("loginId") %></title>
</head>

<jsp:include page="header.jsp" />
<br><br>

<form class="form-style-9" name="userTypeInputForm" action="MasterController" method="post">
<body>
<ul>
<li>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="field1" class="field-style field-split align-center" placeholder="User Type" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="field1" class="field-style field-split align-center" placeholder="Remarks" />
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <select name="flag" class="field-style field-split align-center" placeholder="Flag">
    <option value="Y"> Active </option>
    <option value="N"> In-Active </option>
    </select>
</li>
<br><br>
<li align="middle">
<input type="submit" value="Submit" /> 
<input type="submit" value="Search" />
<input type="submit" value="Update" />
<input type="submit" value="Delete" />
</li>
</ul>

<br><br>

<table class="responstable">
<tr> 
<td> Id  </td>
<td> User Type </td>
<td> Remarks </td>
<td> Flag </td> 
</tr>
</body>
</form>