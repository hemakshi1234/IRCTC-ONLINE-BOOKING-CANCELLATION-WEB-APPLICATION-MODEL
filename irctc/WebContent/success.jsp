<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>


<!DOCTYPE html>
<html>
<head>

 <link rel="stylesheet" href="./css/table_style.css">

<meta charset="ISO-8859-1">
<title>IRCTC - Welcome <%=session.getAttribute("loginId") %></title>
</head>
<body>
<h2> Welcome to IRCTC Home Page!  Your have successfully Login!....</h2>
<table> 
<%

ArrayList arrayList = new ArrayList<String>();

ArrayList utList = new ArrayList<UserType>();

utList = (ArrayList<UserType>)request.getAttribute("utList");

%>

<% /*
int slno=1;

for (int i=0; i<arrayList.size();i++) 
{
     
%>

<tr>
	<td> <%=slno++%>  </td> 
	<td> <%=arrayList.get(i).toString()%> </td>
	
</tr>

<% } */ 

Iterator iterator = utList.iterator(); 

System.out.println("List of UserType Objects Value : "); 

UserType ut = new UserType(); %>
<table class="responstable">
<tr> 
<td> Id  </td>
<td> User Type </td>
<td> Remarks </td>
<td> Flag </td> 
</tr>

<% 
while (iterator.hasNext()) 
	{
	%>
      <tr>
	<%
	ut = (UserType) iterator.next();
	%>
	<td> <%=ut.getUserId() %></td> 
	<td> <%=ut.getType() %> </td> 
	<td> <%=ut.getRemark() %></td> 
	<td> <%=ut.getFlag() %></td>
	</tr>
	<%	
	}
	%>

</table>
</body>

</html>