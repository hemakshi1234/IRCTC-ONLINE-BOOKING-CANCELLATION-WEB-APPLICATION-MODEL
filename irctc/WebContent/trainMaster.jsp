<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>

<%

ArrayList tmList = new ArrayList<TrainMaster>();
tmList = (ArrayList<TrainMaster>)request.getAttribute("tmList");



HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
trainstationHashMap = (HashMap<Integer,String>)request.getAttribute("trainstationHashMap");





String masterType = new String();


String trainMasterNo = new String();
String trainMasterName = new String();
int trainMasterSourceStation=0;
int trainMasterDestinationStation=0;
double trainMasterDistance = 0;
String trainMasterDepartureTime = new String();
String trainMasterArrivalTime = new String();
String trainMasterTravellingTime = new String();
String trainMasterRemarks = new String();
String trainMasterFlag = new String();


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
	trainMasterNo  = request.getAttribute("trainMasterNo").toString();
}
catch(Exception e)
{
	trainMasterNo ="";
	e.printStackTrace();
}



try
{
	trainMasterName  = request.getAttribute("trainMasterName").toString();
}
catch(Exception e)
{
	trainMasterName ="";
	e.printStackTrace();
}




try
{
	trainMasterSourceStation  =Integer.parseInt(request.getAttribute("trainMasterSourceStation").toString());
}
catch(Exception e)
{
	trainMasterSourceStation = 0;
	e.printStackTrace();
}

try
{
	trainMasterDestinationStation  =Integer.parseInt(request.getAttribute("trainMasterDestinationStation").toString());
}
catch(Exception e)
{
	trainMasterDestinationStation = 0;
	e.printStackTrace();
}




try
{
	trainMasterDistance = Double.parseDouble(request.getAttribute("trainMasterDistance").toString());
}
catch(Exception e)
{
	trainMasterDistance = 0.0;
	e.printStackTrace();
}


try
{
	trainMasterDepartureTime = request.getAttribute("trainMasterDepartureTime").toString();
}
catch(Exception e)
{
	trainMasterDepartureTime ="";
	e.printStackTrace();
}



try
{
	trainMasterArrivalTime = request.getAttribute("trainMasterArrivalTime").toString();
}
catch(Exception e)
{
	trainMasterArrivalTime ="";
	e.printStackTrace();
}


try
{
	trainMasterTravellingTime = request.getAttribute("trainMasterTravellingTime").toString();
}
catch(Exception e)
{
	trainMasterTravellingTime ="";
	e.printStackTrace();
}



try
{
	trainMasterRemarks  = request.getAttribute("trainMasterRemarks").toString();
}
catch(Exception e)
{
	trainMasterRemarks ="";
	e.printStackTrace();
}


try
{
	trainMasterFlag=request.getAttribute("trainMasterFlag").toString();
}
catch(Exception e)
{
	trainMasterFlag ="";
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
	if (trainMasterForm.trainMasterNo.value!="" &&trainMasterForm.trainMasterName.value!="" &&trainMasterForm.trainMasterSourceStation.value!="" &&trainMasterForm.trainMasterDestinationStation.value!="" &&trainMasterForm.trainMasterDistance.value!="" &&trainMasterForm.trainMasterDepartureTime.value!="" &&trainMasterForm.trainMasterArrivalTime.value!="" &&trainMasterForm.trainMasterTravellingTime.value!=""&&trainMasterForm.trainMasterRemarks.value!="" &&trainMasterForm.trainMasterFlag.value!="")
		return true;
	else 
		return false;
		
}


function validateInputsForUpdateOrDelete()
{
	if (trainMasterForm.trainMasterId.value!=""&&trainMasterForm.trainMasterNo.value!="" &&trainMasterForm.trainMasterName.value!="" &&trainMasterForm.trainMasterSourceStation.value!="" &&trainMasterForm.trainMasterDestinationStation.value!="" &&trainMasterForm.trainMasterDistance.value!="" &&trainMasterForm.trainMasterDepartureTime.value!="" &&trainMasterForm.trainMasterArrivalTime.value!="" &&trainMasterForm.trainMasterTravellingTime.value!=""&&trainMasterForm.trainMasterRemarks.value!="" &&trainMasterForm.trainMasterFlag.value!="")
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
	
	
	trainMasterForm.act.value=x;
	trainMasterForm.submit();
}


function populateValues(id,num,tname,src,des,dis,dtime,atime,ttime,remark,flag)
{
//	alert('inside func ' + id + ' ' + num  + ' ' + tname  + ' ' + src  + ' ' +des  + ' ' +dis  + ' ' +dtime  + ' ' +atime + ' ' +ttime + ' ' +remark + ' ' +flag);
	trainMasterForm.trainMasterId.value=id;
	trainMasterForm.trainMasterNo.value=num;
	trainMasterForm.trainMasterName.value=tname;
	trainMasterForm.trainMasterSourceStation.value=src;
	trainMasterForm.trainMasterDestinationStation.value=des;
	trainMasterForm.trainMasterDistance.value=dis;
	trainMasterForm.trainMasterDepartureTime.value=dtime;
	trainMasterForm.trainMasterArrivalTime.value=atime;
	trainMasterForm.trainMasterTravellingTime.value=ttime;
	trainMasterForm.trainMasterRemarks.value=remark;
	trainMasterForm.trainMasterFlag.value=flag;
	
}

</script>


</head>

<jsp:include page="header.jsp" />
<br><br>

<form class="form-style-9" name="trainMasterForm" action="MasterController" method="post">
<h1 align="center"> <b> TRAIN MASTER </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>



<br/><br/>
<input type="hidden" name="act">
<input type="hidden" name="trainMasterId">
<input type="hidden" name="masterType" value="<%=masterType%>">

<body>
<ul>
<li>
	
    <input type="text" name="trainMasterNo" class="field-style field-split align-center" placeholder="Train Number" value="<%=trainMasterNo%>" maxlength="6">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainMasterName" class="field-style field-split align-center" placeholder="train Name" value="<%=trainMasterName%>" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    
    <select name="trainMasterSourceStation" class="field-style field-split align-center" placeholder="Source station">
 	<option value="0"><--Select Source Station--></option>
 	<%
 	Iterator s_trainstationHashMapIterator =trainstationHashMap.entrySet().iterator(); 
  
        while (s_trainstationHashMapIterator.hasNext()) 
        { 
            Map.Entry mapElement = (Map.Entry)s_trainstationHashMapIterator.next(); %> 
        
        <option value="<%=Integer.parseInt(mapElement.getKey().toString())%>" <%=trainMasterSourceStation==Integer.parseInt(mapElement.getKey().toString()) ? "SELECTED" : "" %>> <%=mapElement.getValue().toString() %> </option>
        <%
        }
        %>
        
    
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    

     <select name="trainMasterDestinationStation" class="field-style field-split align-center" placeholder="destination station">
 	<option value="0"><--Select Destination Station--></option>
 	<%
 	Iterator d_trainstationHashMapIterator =trainstationHashMap.entrySet().iterator(); 
  
        while (d_trainstationHashMapIterator.hasNext()) 
        { 
            Map.Entry mapElement = (Map.Entry)d_trainstationHashMapIterator.next(); %> 
        
        <option value="<%=Integer.parseInt(mapElement.getKey().toString())%>" <%=trainMasterDestinationStation==Integer.parseInt(mapElement.getKey().toString()) ? "SELECTED" : "" %>> <%=mapElement.getValue().toString() %> </option>
        <%
        }
        %>
        
    
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainMasterDistance" class="field-style field-split align-center" placeholder="Distance" value="<%=trainMasterDistance%>" maxlength="4">
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     
      <input type="text" name="trainMasterDepartureTime" class="field-style field-split align-center" placeholder="Departure time" value="<%=trainMasterDepartureTime%>" maxlength="5">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainMasterArrivalTime" class="field-style field-split align-center" placeholder="Arrival time" value="<%=trainMasterArrivalTime%>" maxlength="5">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainMasterTravellingTime" class="field-style field-split align-center" placeholder="duration time" value="<%=trainMasterTravellingTime%>" maxlength="5">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainMasterRemarks" class="field-style field-split align-center" placeholder="remarks" value="<%=trainMasterRemarks%>" maxlength="500">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
   <select name="trainMasterFlag" class="field-style field-split align-center" placeholder="Flag">
    <option value="Y" <%=trainMasterFlag.equals("Y") ? "SELECTED" : "" %>> Active </option>
    <option value="N" <%=trainMasterFlag.equals("N") ? "SELECTED" : "" %>> In-Active </option>
    </select>
    
   
    
</li>

<br><br>
<li align="middle">
<input type="submit" value="Submit" onclick="onSubmit('Add')"/> 
<input type="submit" value="Search" onclick="onSubmit('Search')"/>
<input type="submit" value="Update" onclick="onSubmit('Update')"/>
<input type="submit" value="Delete" onclick="onSubmit('Delete')"/>
<input type="submit" value="Clear" onclick="trainMasterForm.reset()"/>
</li>
</ul>

<br><br>



<% 

 %>

<table class="responstable">
<tr> 
<td> train Master Id </td>
<td> train No </td>
<td>train Name</td>
<td> Source Station</td> 
<td> Destination Station </td>
<td> Distance </td>
<td> Departure Time </td>
<td> Arrival Time </td>
<td> Traveling Time </td>
<td>Remarks</td>
<td> Flag</td>
</tr>

<% if (tmList!=null && tmList.size() > 0)
{
		Iterator iterator = tmList.iterator(); 
		TrainMaster tm = new TrainMaster();	
		while (iterator.hasNext()) 
			{
			%>
			<%
			tm = (TrainMaster) iterator.next();
			%>
	         <tr onClick="populateValues(<%=tm.getTrain_master_id()%>,'<%=tm.getTrain_master_no()%>','<%=tm.getTrain_master_name()%>',<%=tm.getTrain_master_source_station()%>,<%=tm.getTrain_master_destination_station()%>,<%=tm.getTrain_master_distance()%>,'<%=tm.getTrain_master_departure_time()%>','<%=tm.getTrain_master_arrival_time()%>','<%=tm.getTrain_master_travelling_time()%>','<%=tm.getTrain_master_remarks()%>','<%=tm.getTrain_master_flag()%>')">
	         <td> <%=tm.getTrain_master_id()%></td>
	         <td> <%=tm.getTrain_master_no()%></td>
	         <td> <%=tm.getTrain_master_name()%></td>
	         <td> <%=trainstationHashMap.get(tm.getTrain_master_source_station())%></td>
	         <td> <%=trainstationHashMap.get(tm.getTrain_master_destination_station())%></td>
	         <td> <%=tm.getTrain_master_distance()%></td>
	         <td> <%=tm.getTrain_master_departure_time()%></td>
	         <td> <%=tm.getTrain_master_arrival_time()%></td>
	         <td> <%=tm.getTrain_master_travelling_time()%></td>
	         <td> <%=tm.getTrain_master_remarks()%></td>
	         <td> <%=tm.getTrain_master_flag()%></td>
	         
	   
			</tr> 
			<%	
			}
}
else
{ %>
<tr> 
<td colspan=11> No Records Found  </td>

</tr>

	
<%
}
%>
</table>
</body>
</form>