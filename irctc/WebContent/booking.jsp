<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>
<%@ page import="com.irctc.utilities.*" %>


<%

String trainMasterSourceStationName = "";
String trainMasterDestinationStationName="";
String dateOfJourney = "";
String tc = "";
String trainName = "";
int trainId = 0;


try
{
tc = request.getAttribute("tc").toString();
}
catch(Exception e)
{
	tc="";
	e.printStackTrace();
}

try
{
trainName = request.getAttribute("trainName").toString();
}
catch(Exception e)
{
	trainName="";
	e.printStackTrace();
}


try
{
trainId = Integer.parseInt(request.getAttribute("trainId").toString());	
}
catch(Exception e)
{
	trainId = 0;
	e.printStackTrace();
}


try
{
	dateOfJourney  = request.getAttribute("dateOfJourney").toString();
}
catch(Exception e)
{
	dateOfJourney = "";
	e.printStackTrace();
}




try
{
	trainMasterSourceStationName  = request.getAttribute("trainMasterSourceStationName").toString();
}
catch(Exception e)
{
	trainMasterSourceStationName = "";
	e.printStackTrace();
}

try
{
	trainMasterDestinationStationName  = request.getAttribute("trainMasterDestinationStationName").toString();
}
catch(Exception e)
{
	trainMasterDestinationStationName = "";
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
<jsp:include page="header.jsp" />

<script language = "javascript">

function onSubmit(x)
{
	//validation for atleast one input / passenger
	
	for (var i=1;i<=6;i++)
	{
	var nop = document.getElementById("nameOfPassenger"+i);
	var age = document.getElementById("age"+i);
	var fp = document.getElementById("foodPref"+i);
	var id = document.getElementById("idProof"+i);
	if (nop.value=='' && age.value=='' && fp.value=='' && id.value=='')
		{
		alert('Please check your Input!... Altease one passenger details must be entered. Pl make entry row wise!... ');
		return false; 
		}
	else
		break;
	}
	
	
	//validation for age
	var message = "Check Age in ";
	var cond = false;
	for (var i=1;i<=6;i++)
	{
	var age = document.getElementById("age"+i);
	if (isNaN(age.value))
		{
		cond = true;
		message = message + " Row:" + i + " ";
		}
	}
	
	if (cond==true)
	{
		alert(message);
		return false;
	}
	
	
	
	//validation if name / age is absent
	
		for (var i=1;i<=6;i++)
		{
			var nop = document.getElementById("nameOfPassenger"+i);
			var age = document.getElementById("age"+i);
			if ((nop.value!='' && age.value=='') || (nop.value=='' && age.value!='') )
				{
				alert('Please check your Input!... Missing Passenger Name / Age.......');
				return false; 
				}
		}

	 
	 bookingForm.submit();
	
}
	
</script>


</head>
<br><br>

<form class="form-style-9" name="bookingForm" action="BookingController" method="post">
<input type="hidden" name="act" value="Booking">
<input type="hidden" name="tc" value="<%=tc%>">
<input type="hidden" name="trainId" value="<%=trainId%>">
<input type="hidden" name="dateOfJourney" value="<%=dateOfJourney%>">

<h1 align="center"> <b> Booking Details </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>

<br/><br/>

<body>
<table class="responstable">
<tr> 
<td> Date of Journey </td>
<td> Train Name </td>
<td> Source Station </td>
<td> Destination Station </td>
<td> Class </td>
</tr>

<tr>
<td><input type="text" name="dateOfJourney" class="field-style field-split align-center" placeholder="Date of Journey" value="<%=dateOfJourney%>" readonly></td>
<td><input type="text" name="trainName" class="field-style field-split align-center" placeholder="Train Name" value="<%=trainName%>" readonly></td>
<td><input type="text" name="source" class="field-style field-split align-center" placeholder="Source" value="<%=trainMasterSourceStationName%>" readonly></td>
<td><input type="text" name="destination" class="field-style field-split align-center" placeholder="Destination" value="<%=trainMasterDestinationStationName%>" readonly></td>
<td><input type="text" name="tc" class="field-style field-split align-center" placeholder="Class" value="<%=tc%>" readonly></td>
</tr>
</table>



<br/>
<br/>
<br/>
<br/>

<h2 align="center"> <b> Passenger Details </b></h2>
<table class="responstable">
<tr> 
<td> Name of Passenger </td>
<td> Age </td>
<td> Food Preference</td>
<td> Id Proof </td> 
</tr>

<% for (int i=1;i<=6;i++) {  %>
<tr>

<td>
 <input type="text" name="nameOfPassenger<%=i%>"  id="nameOfPassenger<%=i%>"  placeholder="Name of Passenger" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
</td>

<td>
<input type="text" name="age<%=i%>" id="age<%=i%>"  placeholder="Age" maxlength="2">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
</td>


<td>
<input type="text" name="foodPref<%=i%>" id="foodPref<%=i%>" placeholder="Food Preference" maxlength="10">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
</td>


<td>
<input type="text" name="idProof<%=i%>"  id="idProof<%=i%>" placeholder="Id Proof with Number" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
</td>

</tr>

<% } %>

</table>
<ul>
<li align="middle">
<input type="button" value="Proceed" onclick="onSubmit('Search');"/> 
</li>
</ul>
</body>
</form>