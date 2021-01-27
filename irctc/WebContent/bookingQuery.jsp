<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>
<%@ page import="com.irctc.utilities.*" %>


<%

HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();

ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();

int trainMasterSourceStation=0;
int trainMasterDestinationStation=0;
String dateOfJourney = "";
String tc = "";
String tcCombo = "";
int noOfSeats=0;
int trainId = 0;

try
{
tc = request.getParameter("tc");
}
catch(Exception e)
{
	tc="";
	e.printStackTrace();
}

try
{
tcCombo = request.getParameter("tcCombo");	
}
catch(Exception e)
{
	tcCombo="";
	e.printStackTrace();
}



try
{
	trainstationHashMap = (HashMap<Integer,String>)request.getAttribute("trainstationHashMap");
}
catch(Exception e)
{
	e.printStackTrace();
}


try
{
	tmList  = (ArrayList<TrainMaster>) request.getAttribute("tmList");
}
catch(Exception e)
{
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
	noOfSeats  = Integer.parseInt(request.getAttribute("noOfSeats").toString());
	System.out.println("noOfSeats " + noOfSeats);
}
catch(Exception e)
{
	noOfSeats = 0;
	e.printStackTrace();
}



try
{
	trainId = Integer.parseInt(request.getAttribute("trainId").toString());
}
catch(Exception e)
{
	noOfSeats = 0;
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
	dateOfJourney  =request.getAttribute("dateOfJourney").toString();
}
catch(Exception e)
{
	dateOfJourney = "";
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
			if (bookingQueryForm.trainMasterSourceStation.value!=0 && bookingQueryForm.trainMasterDestinationStation.value!=0 && bookingQueryForm.dateOfJourney.value!="")
			{
					if (bookingQueryForm.trainMasterSourceStation.value!=bookingQueryForm.trainMasterDestinationStation.value)
						{
						bookingQueryForm.act.value=x;
						bookingQueryForm.submit();
						}
					else
						{
						alert('Both Source & Destination Station cannot be the Same!....');
						return false;
						}
			}
			else
			{
				alert('Please Check your Input Values!... All fields are mandatory!..... ');
				return false; 
				
			}	
}
	
			
	
		

function onSeatAvailability(x,i,train_id)
{
	var e = document.getElementById("trainClass"+i);
    //var str = e.options[e.selectedIndex].text;
    var tc = e.options[e.selectedIndex].value;
    
    if (tc!="")
    {
    	bookingQueryForm.act.value=x;
	    bookingQueryForm.tc.value=tc;
	    bookingQueryForm.trainId.value=train_id;
	    bookingQueryForm.tcCombo.value="trainClass"+i;
	    bookingQueryForm.submit();
    }
    else
    {
    	alert('Please select the class!..... ');
    	return false;
    }
    
        /* 
	var selObj = bookForm.getElementByName("");
	var i;
    var count = 0;
    for (i=0; i<selObj.options.length; i++) 
    { 
        if (selObj.options[i].selected) 
        {
            alert(selObj.options[i].value);
        } 
        
	}
 */
 }
 
 
 
function onBookNow(x,i,train_id)
{
	var e = document.getElementById("trainClass"+i);
    var tc = e.options[e.selectedIndex].value;
    
    if (tc!="")
    {
    	bookingQueryForm.act.value=x;
	    bookingQueryForm.tc.value=tc;
	    bookingQueryForm.trainId.value=train_id;
	    bookingQueryForm.submit();
    }
        
        /* 
	var selObj = bookForm.getElementByName("");
	var i;
    var count = 0;
    for (i=0; i<selObj.options.length; i++) 
    { 
        if (selObj.options[i].selected) 
        {
            alert(selObj.options[i].value);
        } 
        
	}
 */
 }
 
 
 


</script>


</head>
<br><br>

<form class="form-style-9" name="bookingQueryForm" action="BookingController" method="post">
<input type="hidden" name="act">
<input type="hidden" name="noOfTrains">
<input type="hidden" name="tc">
<input type="hidden" name="trainId" value="<%=trainId%>">
<input type="hidden" name="tcCombo">
<h1 align="center"> <b> Booking Query </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>

<br/><br/>

<body>
<ul>
<li>
	
	 SOURCE 
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

      
    
	DESTINATION 
    <select name="trainMasterDestinationStation" class="field-style field-split align-center" placeholder="destination station">
 	<option value=0><--Select Destination Station--></option>
 	<%
 	Iterator d_trainstationHashMapIterator = trainstationHashMap.entrySet().iterator(); 
  
        while (d_trainstationHashMapIterator.hasNext()) 
        { 
            Map.Entry mapElement = (Map.Entry)d_trainstationHashMapIterator.next(); %> 
        
        <option value="<%=Integer.parseInt(mapElement.getKey().toString())%>" <%=trainMasterDestinationStation==Integer.parseInt(mapElement.getKey().toString()) ? "SELECTED" : "" %>> <%=mapElement.getValue().toString() %> </option>
        <%
        }
        %>
        
    
    </select>
    
     DATE OF JOURNEY 
    
    <input type="date" name="dateOfJourney"  placeholder="Date of Journey" value="<%=dateOfJourney%>" title="Date of Journey">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
</li>

<li align="middle">
<input type="button" value="Submit" onclick="onSubmit('Search');"/> 
</li>
</ul>

<br><br>



<% 

 %>

<table class="responstable">
<tr> 
<td> Train Name & No  </td>
<td> Departure </td>
<td> Arrival  </td>
<td> Duration </td> 
<td> Class </td>
<td> Availability & Fare </td>
</tr>

<% if (tmList!=null && tmList.size() > 0)
{ %>
<script> bookingQueryForm.noOfTrains.value=<%=tmList.size()%>  </script>
		
	<%	Iterator iterator = tmList.iterator(); 
		TrainMaster tm = new TrainMaster();	
		int i=1; int j=1;
		while (iterator.hasNext()) 
			{
			tm = (TrainMaster) iterator.next();
			%>
	         <tr>
	         <td> <%=tm.getTrain_master_name() + " [" + tm.getTrain_master_no() + "]"%></td>
	         <td> <%=tm.getTrain_master_departure_time()%></td>
	         <td> <%=tm.getTrain_master_arrival_time()%></td>
	         <td> <%=tm.getTrain_master_travelling_time()%></td>
	         
	         <% if (tcCombo.equalsIgnoreCase("trainClass".concat(Integer.toString(j)))) {  %>
	         <td>
	         	<select name="trainClass<%=j%>" id="trainClass<%=i%>" class="field-style field-split align-center" placeholder="Train Class">
    			<option value=""><--Select Class--></option>
    			<option value="SL" <%=tc.equals("SL") ? "SELECTED" : "" %>> Sleeper </option>
    			<option value="AC3" <%=tc.equals("AC3") ? "SELECTED" : "" %>> AC 3 Tier </option>
    			<option value="AC2" <%=tc.equals("AC2") ? "SELECTED" : "" %>> AC 2 Tier </option>
    			<option value="AC1" <%=tc.equals("AC1") ? "SELECTED" : "" %>> First AC </option>
    			</select>
	         </td>
	         
	        <td>

	        <ul>
	   		<li align="middle">
	   		<input type="button" value="Availability" onclick="onSeatAvailability('seatAvailability',<%=i%>,<%=tm.getTrain_master_id()%>);"/>
	   		</li>
	   		
	   		<% if (tc!="" && noOfSeats > 0) {  %>
	   		
	   		<li align="middle"> <label class="field-style field-split align-center"> Availability: <%=noOfSeats%> </label></li>
	   		
	   		<li align="middle">
			<input type="button" value="Book Now" onclick="onBookNow('BookingQuery',<%=i%>,<%=tm.getTrain_master_id()%>)"/> 
			</li>
			
			</ul>
	   		<% }
	   		else
	   		{
	   		%>
	   		<label class="field-style field-split align-center"> Not Available </label>
	   		<% } %>
	   		

	   		</td>      
	   		
	         <% } else {  %>
	         
	           <td>
	         	<select name="trainClass<%=j%>" id="trainClass<%=i%>" class="field-style field-split align-center" placeholder="Train Class">
    			<option value=""><--Select Class--></option>
    			<option value="SL"> Sleeper </option>
    			<option value="AC3"> AC 3 Tier </option>
    			<option value="AC2"> AC 2 Tier </option>
    			<option value="AC1"> First AC </option>
    			</select>
	         </td>
	         
	         <td>
	        <ul>
	   		<li align="middle">
	   		<input type="button" value="Availability" onclick="onSeatAvailability('seatAvailability',<%=i%>,<%=tm.getTrain_master_id()%>);"/>
	   		</li>
	   		</ul>
	   		</td>      
	         <% } %> 
	         
	   
	   		
	         
			</tr> 
			<%
			i++; j++;
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