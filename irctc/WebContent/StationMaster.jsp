<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>

<%
	ArrayList smList = new ArrayList<StationMaster>();

smList = (ArrayList<StationMaster>)request.getAttribute("smList");

String masterType = new String(); 

String stationCode = new String();
String stationName = new String();
String stationRemark = new String();
String stationFlag = new String();


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
	stationCode = request.getAttribute("stationCode").toString();
}
catch(Exception e)
{
	stationCode="";
	e.printStackTrace();
}

try
{
	stationName  = request.getAttribute("stationName").toString();
}
catch(Exception e)
{
	stationName="";
	e.printStackTrace();
}

try
{
	stationRemark = request.getAttribute("stationRemark").toString();
}
catch(Exception e)
{
	stationRemark="";
	e.printStackTrace();
}

try
{
	stationFlag = request.getAttribute("stationFlag").toString();
}
catch(Exception e)
{
	stationFlag="";
	e.printStackTrace();
}
%>

<!DOCTYPE html>
<html>
<head>


 <link rel="stylesheet" href="./css/table_style.css">
 <link rel="stylesheet" type="text/css" media="all" href="./css/input_style.css">

<meta charset="ISO-8859-1">
<title>IRCTC - Welcome <%=session.getAttribute("loginId")%></title>


<script language = "javascript">

function validateInputsForAdd()
{
	if (StationMasterForm.stationCode.value!="" && StationMasterForm.stationName.value!="" && StationMasterForm.stationRemark.value!=""&& StationMasterForm.stationFlag.value!="")
		return true;
	else 
		return false;
}


function validateInputsForUpdateOrDelete()
{
	if (StationMasterForm.stationCode.value!="" && StationMasterForm.stationName.value!="" && StationMasterForm.stationRemark.value!=""&& StationMasterForm.stationFlag.value!="")
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
	
	
	StationMasterForm.act.value=x;
	StationMasterForm.submit();
}

function populateValues(si,sc,sn,sr,sf)
{
	StationMasterForm.stationId.value=si;
	StationMasterForm.stationCode.value =sc ;
	StationMasterForm.stationName.value = sn;
	StationMasterForm.stationRemark.value = sr;
	StationMasterForm.stationFlag.value =sf;
}

</script>


</head>

<jsp:include page="header.jsp" />
<br><br>

<form class="form-style-9" name="StationMasterForm" action="MasterController" method="post">
<h1 align="center"> <b> STATION MASTER </b></h1>
<br/><br/>
<input type="hidden" name="act">
<input type="hidden" name="stationId">
<input type="hidden" name="masterType" value="<%=masterType%>">
<%
	String s = stationFlag.equals("Y") ? "SELECTED" : "";
%>
<body>
<ul>
<li>
    <input type="text" name="stationCode" class="field-style field-split align-center" placeholder=" Station Code" value="<%=stationCode%>" maxlength="4">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="stationName" class="field-style field-split align-center" placeholder="Station name" value="<%=stationName%>" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <input type="text" name="stationRemark" class="field-style field-split align-center" placeholder="Station Remarks" value="<%=stationRemark%>" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <select name="stationFlag" class="field-style field-split align-center" placeholder="station Flag">
    <option value="Y" <%=stationFlag.equals("Y") ? "SELECTED" : ""%>> Active </option>
    <option value="N" <%=stationFlag.equals("N") ? "SELECTED" : ""%>> In-Active </option>
    </select>
</li>
<br><br>
<li align="middle">
<input type="submit" value="Submit" onclick="onSubmit('Add')"/> 
<input type="submit" value="Search" onclick="onSubmit('Search')"/>
<input type="submit" value="Update" onclick="onSubmit('Update')"/>
<input type="submit" value="Delete" onclick="onSubmit('Delete')"/>
<input type="submit" value="Clear" onclick="StationMasterForm.reset()"/>
</li>
</ul>

<br><br>




<table class="responstable">
<tr> 
<td> stationId  </td>
<td> station Code  </td>
<td> station Name</td>
<td> station Remark</td> 
<td> station Flag</td> 
</tr>
<%
	if (smList!=null && smList.size() > 0)
{
		Iterator iterator = smList.iterator(); 
		StationMaster sm = new  StationMaster();	
		while (iterator.hasNext()) 
	{
%>
			<%
				sm = (StationMaster) iterator.next();
			%>
	        <tr onClick="populateValues(<%=sm.getStationMasterId() %>,'<%=sm.getStationMasterCode() %>','<%=sm.getStationMasterName() %>','<%=sm.getStationMasterRemarks() %>','<%=sm.getStationMasterFlag() %>')">
			<td> <%=sm.getStationMasterId() %></td> 
			<td> <%=sm.getStationMasterCode() %> </td> 
			<td> <%=sm.getStationMasterName() %></td>
			<td> <%=sm.getStationMasterRemarks() %></td> 
			<td> <%=sm.getStationMasterFlag()%></td>
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