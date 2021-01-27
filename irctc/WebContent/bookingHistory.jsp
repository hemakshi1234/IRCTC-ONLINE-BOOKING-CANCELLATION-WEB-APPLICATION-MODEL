<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>
<%@ page import="com.irctc.utilities.*" %>


<%
ArrayList<HashMap> hmList = new ArrayList<HashMap>();
ArrayList<HashMap> passList = new ArrayList<HashMap>();
HashMap<String,Object> hm = new HashMap<String,Object>();
String pnr = "";

try
{
hmList  = (ArrayList<HashMap>)request.getAttribute("hmList");
}
catch(Exception e)
{
	e.printStackTrace();
}


try
{
pnr  = request.getAttribute("pnr").toString();
}
catch(Exception e)
{
	pnr="";
	e.printStackTrace();
}


try
{
passList  = (ArrayList<HashMap>)request.getAttribute("passList");
}
catch(Exception e)
{
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

function showPassengerDetails(pnr)
{
	bookingHistoryForm.pnr.value=pnr;
	bookingHistoryForm.act.value='bookingHistory';
	bookingHistoryForm.submit();
}

function onSubmit()
{
	var checkboxes = document.getElementsByName('cancelCheckBox');
	var vals = "";
	for (var i=0, n=checkboxes.length;i<n;i++) 
	{
	    if (checkboxes[i].checked) 
	    {
	        vals += ","+checkboxes[i].value;
	    }
	}
	if (vals) vals = vals.substring(1);
	

	bookingHistoryForm.act.value='bookingCancellation';
	bookingHistoryForm.ticketsToBeCancelled.value=vals;
	bookingHistoryForm.submit();
}

	
</script>


</head>
<br><br>

<form class="form-style-9" name="bookingHistoryForm" action="BookingController" method="post">
<input type="hidden" name="act" value="">
<input type="hidden" name="pnr" value="<%=pnr%>">
<input type="hidden" name="ticketsToBeCancelled" value="">

<h1 align="center"> <b> Booking History </b></h1>
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
<td> </td>
<td> PNR Number</td> 
<td> Date of Journey </td>
<td> Train Name </td>
<td> Source Station </td>
<td> Destination Station </td>
<td> Class </td>
</tr>

<% if (hmList!=null && hmList.size() > 0)
{
		Iterator iterator = hmList.iterator(); 
		hm = new HashMap<String,Object>();
		while (iterator.hasNext()) 
		{
			hm = (HashMap<String,Object>)iterator.next();
%>			
	<tr>
	<% if (pnr.equalsIgnoreCase(hm.get("pnr").toString().trim())) { %>
	<td>  <input type="radio" name="radio" onClick="showPassengerDetails(<%=hm.get("pnr").toString()%>);" checked/> </td>
	<% } else { %>
	<td>  <input type="radio" name="radio" onClick="showPassengerDetails(<%=hm.get("pnr").toString()%>);" /> </td>
	<% } %>
	<td> <%=hm.get("pnr").toString() %> </td>
	<td> <%=hm.get("doj").toString() %> </td>
	<td> <%=hm.get("train").toString() %> </td>
	<td> <%=hm.get("src").toString() %> </td>
	<td> <%=hm.get("dest").toString() %> </td>
	<td> <%=hm.get("class").toString() %> </td>
	
	</tr>		
			
<%			
		}
}
%>
</table>

<br/><br/><br/>



<% if (passList!=null && passList.size() > 0)
{
		Iterator iterator = passList.iterator(); 
		hm = new HashMap<String,Object>();
		%>
<h1 align="center"> <b> Passenger Details </b></h1>
<table class="responstable">
<tr>
<td> </td> 
<td> Passenger Name</td> 
<td> Age </td>
<td> Food Preference</td>
<td> Id Proof</td>
<td> Class</td>
<td> Seat Details </td>
</tr>
		<%
		while (iterator.hasNext()) 
		{
			hm = (HashMap<String,Object>)iterator.next();
%>			
	<tr>
	<td> <input type="checkbox" name="cancelCheckBox" value="<%=Integer.parseInt(hm.get("Id").toString())%>"> </td>
	<td> <%=hm.get("name").toString() %> </td>
	<td> <%=hm.get("age").toString() %> </td>
	<td> <%=hm.get("fp").toString() %> </td>
	<td> <%=hm.get("id").toString() %> </td>
	<td> <%=hm.get("class").toString() %> </td>
	<td> <%=hm.get("seat").toString() %> </td>
	</tr>		
			
<%			
		} %>
</table>
<ul>
<li align="middle">
<input type="button" value="Cancel" onclick="onSubmit();"/> 
</li>
</ul>

<%
}
%>


</body>
</form>