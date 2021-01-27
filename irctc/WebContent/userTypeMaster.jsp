<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>

<%

ArrayList utList = new ArrayList<UserType>();


utList = (ArrayList<UserType>)request.getAttribute("utList");



String masterType = new String(); 
String userType = new String();
String remarks = new String();
String flag = new String();


try
{
masterType = request.getAttribute("masterType").toString();
}
catch(Exception e)
{
	masterType="";
	e.printStackTrace();
}

try
{
userType = request.getAttribute("userType").toString();
}
catch(Exception e)
{
	userType="";
	e.printStackTrace();
}

try
{
remarks = request.getAttribute("remarks").toString();
}
catch(Exception e)
{
	remarks="";
	e.printStackTrace();
}

try
{
flag = request.getAttribute("flag").toString();
}
catch(Exception e)
{
	flag="";
	e.printStackTrace();
}


%>

<!DOCTYPE html>
<html>
<head>


 <link rel="stylesheet" href="./css/table_style.css">
 <link rel="stylesheet" type="text/css" media="all" href="./css/input_style.css">

<meta charset="ISO-8859-1">
<title>IRCTC - Welcome <%=session.getAttribute("loginId") %></title>


<script language = "javascript">

function validateInputsForAdd()
{
	if (userTypeInputForm.userType.value!="" && userTypeInputForm.remarks.value!="" && userTypeInputForm.flag.value!="")
		return true;
	else 
		return false;
}


function validateInputsForUpdateOrDelete()
{
	if (userTypeInputForm.userTypeId.value!="" && userTypeInputForm.userType.value!="" && userTypeInputForm.remarks.value!="" && userTypeInputForm.flag.value!="")
		return true;
	else 
		return false;
}


function onSubmit(x)
{
	if (x=='Add' && !validateInputsForAdd())
	{
			alert('Please check your Input Values!...');
			return; 
	}
	
	if (x=='Update' && !validateInputsForUpdateOrDelete())
	{
			alert('Please click on the row in the Table to Update / Delete !...');
			return; 
	}
	
	if (x=='Delete' && !validateInputsForUpdateOrDelete())
	{
			alert('Please click on the row in the Table to Update / Delete !...');
			return; 
	}
	
	
	userTypeInputForm.act.value=x;
	userTypeInputForm.submit();
}

function populateValues(id,ut,rm,fl)
{
	//alert(id + ' ' + ut + ' ' + rm + ' ' + fl);
	userTypeInputForm.userType.value = ut;
	userTypeInputForm.remarks.value = rm;
	userTypeInputForm.flag.value = fl;
	userTypeInputForm.userTypeId.value = id;
}

</script>

</head>

<jsp:include page="header.jsp" />

<br><br>




<form class="form-style-9" name="userTypeInputForm" action="MasterController" method="post">
<h1 align="center"> <b> USER TYPE MASTER </b></h1>
<br/><br/>
<input type="hidden" name="act">
<input type="hidden" name="userTypeId">
<input type="hidden" name="masterType" value="<%=masterType%>">
<%
String s = flag.equals("Y") ? "SELECTED" : "";

%>
<body>
<ul>
<li>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userType" class="field-style field-split align-center" placeholder="User Type" value="<%=userType%>">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="remarks" class="field-style field-split align-center" placeholder="Remarks" value="<%=remarks%>">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    
    <select name="flag" class="field-style field-split align-center" placeholder="Flag">
    <option value="Y" <%=flag.equals("Y") ? "SELECTED" : "" %>> Active </option>
    <option value="N" <%=flag.equals("N") ? "SELECTED" : "" %>> In-Active </option>
    </select>
</li>
<br><br>
<li align="middle">
<input type="submit" value="Submit" onclick="onSubmit('Add')"/> 
<input type="submit" value="Search" onclick="onSubmit('Search')"/>
<input type="submit" value="Update" onclick="onSubmit('Update')"/>
<input type="submit" value="Delete" onclick="onSubmit('Delete')"/>
<input type="submit" value="Clear" onclick="userTypeInputForm.reset()"/>
</li>
</ul>

<br><br>



<% 

 %>

<table class="responstable">
<tr> 
<td> Id  </td>
<td> User Type </td>
<td> Remarks </td>
<td> Flag </td> 
</tr>
<% if (utList!=null && utList.size() > 0)
{
		Iterator iterator = utList.iterator(); 
		UserType ut = new UserType();	
		while (iterator.hasNext()) 
			{
			%>
			<%
			ut = (UserType) iterator.next();
			%>
	        <tr onClick="populateValues(<%=ut.getUserId() %>,'<%=ut.getType() %>','<%=ut.getRemark() %>','<%=ut.getFlag() %>')">
			<td> <%=ut.getUserId() %></td> 
			<td> <%=ut.getType() %> </td> 
			<td> <%=ut.getRemark() %></td> 
			<td> <%=ut.getFlag() %></td>
			</tr>
			<%	
			}
}
else
{ %>
<tr> 
<td colspan=4> No Records Found  </td>

</tr>

	
<%
}
%>
</table>
</body>
</form>