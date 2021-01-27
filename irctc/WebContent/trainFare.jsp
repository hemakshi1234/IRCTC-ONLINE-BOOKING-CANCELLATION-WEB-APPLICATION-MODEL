<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>

<%

//to display the availabel train fare 

ArrayList tfList = new ArrayList<TrainFare>();
tfList = (ArrayList<TrainFare>)request.getAttribute("tfList");


// to populate train master combo box
HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
trainMasterHashMap = (HashMap<Integer,String>)request.getAttribute("trainMasterHashMap");

String masterType = new String();


int trainMasterId = 0;
double trainFareSleeper = 0;
double trainFareSleeperTotalseats = 0;
double trainFareAc1 = 0;
double trainFareAc1Totalseats = 0;
double trainFareAc2 = 0;
double trainFareAc2Totalseats = 0;
double trainFareAc3 = 0;
double trainFareAc3Totalseats = 0;
String trainFareRemarks = new String();
String trainFareFlag = new String();


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
	trainMasterId  = Integer.parseInt(request.getAttribute("trainMasterId").toString());
}
catch(Exception e)
{
	trainMasterId = 0;
	e.printStackTrace();
}

try
{
	trainFareSleeper   = Double.parseDouble(request.getAttribute("trainFareSleeper").toString());
}
catch(Exception e)
{
	trainFareSleeper  = 0;
	e.printStackTrace();
}


try
{
	trainFareSleeperTotalseats = Double.parseDouble(request.getAttribute("trainFareSleeperTotalseats").toString());
}
catch(Exception e)
{
	trainFareSleeperTotalseats  = 0;
	e.printStackTrace();
}




try
{
	trainFareAc1   = Double.parseDouble(request.getAttribute("trainFareAc1").toString());
}
catch(Exception e)
{
	trainFareAc1  = 0;
	e.printStackTrace();
}


try
{
	trainFareAc1Totalseats = Double.parseDouble(request.getAttribute("trainFareAc1Totalseats").toString());
}
catch(Exception e)
{
	trainFareAc1Totalseats  = 0;
	e.printStackTrace();
}




try
{
	trainFareAc2   = Double.parseDouble(request.getAttribute("trainFareAc2").toString());
}
catch(Exception e)
{
	trainFareAc2  = 0;
	e.printStackTrace();
}


try
{
	trainFareAc2Totalseats = Double.parseDouble(request.getAttribute("trainFareAc2Totalseats").toString());
}
catch(Exception e)
{
	trainFareAc2Totalseats  = 0;
	e.printStackTrace();
}




try
{
	trainFareAc3   = Double.parseDouble(request.getAttribute("trainFareAc3").toString());
}
catch(Exception e)
{
	trainFareAc3  = 0;
	e.printStackTrace();
}


try
{
	trainFareAc3Totalseats = Double.parseDouble(request.getAttribute("trainFareAc3Totalseats").toString());
}
catch(Exception e)
{
	trainFareAc3Totalseats  = 0;
	e.printStackTrace();
}



try
{
	trainFareRemarks   = request.getAttribute("trainFareRemarks").toString();
}
catch(Exception e)
{
	trainFareRemarks  ="";
	e.printStackTrace();
}




try
{
	trainFareFlag    = request.getAttribute("trainFareFlag").toString();
}
catch(Exception e)
{
	trainFareFlag ="";
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
	if (trainFareForm.trainMasterId.value!=0 && trainFareForm.trainFareSleeper.value!=0 && trainFareForm.trainFareSleeperTotalseats.value!=0 && trainFareForm.trainFareAc1.value!=0 && trainFareForm.trainFareAc1Totalseats.value!=0  && trainFareForm.trainFareAc2.value!= 0 && trainFareForm.trainFareAc2Totalseats.value!=0  && trainFareForm.trainFareAc3.value!= 0 && trainFareForm.trainFareAc3Totalseats.value != 0 &&trainFareForm.trainFareFlag.value!="")
		{
		return true;
		}
	else 
		return false;
		
}


function validateInputsForDelete()
{
	if (trainFareForm.trainFareId.value!=0 && trainFareForm.trainMasterId.value!=0 && trainFareForm.trainFareSleeper.value!=0 && trainFareForm.trainFareSleeperTotalseats.value!=0 && trainFareForm.trainFareAc1.value!=0 && trainFareForm.trainFareAc1Totalseats.value!=0  && trainFareForm.trainFareAc2.value!= 0 && trainFareForm.trainFareAc2Totalseats.value!=0  && trainFareForm.trainFareAc3.value!= 0 && trainFareForm.trainFareAc3Totalseats.value != 0 &&trainFareForm.trainFareFlag.value!="")
		return true;
	else 
		return false;
}


function onSubmit(x)
{
	

	/* if (isNaN(trainFareForm.trainFareSleeper.value)) 
	{
	alert('Sleeper Fare is not Numeric!.. Kindly check your input!...');
	return false;
	}
	 */
	
	
	if (x=='Add' && !validateInputsForAdd())
	{
			alert('Please check your Input Values!...');
			return; 
	}
	
	
	if (x=='Delete' && !validateInputsForDelete())
	{
			alert('Please click on the row in the Table to Update / Delete !...');
			return; 
	}
	
	
	trainFareForm.act.value=x;
	trainFareForm.submit();
}


function populateValues(fareid, trainid, slfare,slseat,ac1fare,ac1seat,ac2fare,ac2seat,ac3fare,ac3seat,remarks,flag)
{
	trainFareForm.trainFareId.value=fareid;
	trainFareForm.trainMasterId.value=trainid;
	trainFareForm.trainFareSleeper.value=slfare;
	trainFareForm.trainFareSleeperTotalseats.value=slseat;
	trainFareForm.trainFareAc1.value=ac1fare;
	trainFareForm.trainFareAc1Totalseats.value=ac1seat;
	trainFareForm.trainFareAc2.value=ac2fare;
	trainFareForm.trainFareAc2Totalseats.value=ac2seat;
	trainFareForm.trainFareAc3.value=ac3fare;
	trainFareForm.trainFareAc3Totalseats.value=ac3seat;
	trainFareForm.trainFareRemarks.value=remarks;
	trainFareForm.trainFareFlag.value=flag;
}

function openPopup(trainMasterId)
{
	//trainFareForm.submit();
	window.open('seatAvailability.jsp?trainMasterId='+trainMasterId,'popUpWindow','height=500,width=900,left=100,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
}

</script>


</head>

<jsp:include page="header.jsp" />
<br><br>

<form class="form-style-9" name="trainFareForm" action="MasterController" method="post">
<h1 align="center"> <b> TRAIN FARE </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>



<br/><br/>
<input type="hidden" name="act">
<input type="hidden" name="trainFareId">
<input type="hidden" name="masterType" value="<%=masterType%>">

<body>
<ul>
<li>

    <select name="trainMasterId" class="field-style field-split align-center" placeholder="Train Name" title="Select Train">
 	<option value="0"><--Select Train--></option>
 	<%
 	Iterator trainmaster =trainMasterHashMap.entrySet().iterator(); 
  
        while (trainmaster.hasNext()) 
        { 
            Map.Entry mapElement = (Map.Entry)trainmaster.next(); %> 
        
        <option value="<%=Integer.parseInt(mapElement.getKey().toString())%>" <%=trainMasterId==Integer.parseInt(mapElement.getKey().toString()) ? "SELECTED" : "" %>> <%=mapElement.getValue().toString() %> </option>
        <%
        }
        %>
    </select>
    
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainFareSleeper" class="field-style field-split align-center" placeholder="Sleeper Class Fare" value="<%=trainFareSleeper%>" maxlength="5" title="Sleeper Class Fare">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainFareSleeperTotalseats" class="field-style field-split align-center" placeholder="Sleeper Total Seats" value="<%=trainFareSleeperTotalseats%>" maxlength="3" title=" Sleeper Class Total Seats">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainFareAc1" class="field-style field-split align-center" placeholder="AC1 Class Fare" value="<%=trainFareAc1%>" maxlength="5" title="AC1 Fare">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainFareAc1Totalseats" class="field-style field-split align-center" placeholder="AC1 Total Seats" value="<%=trainFareAc1Totalseats%>" maxlength="3"  title="AC1 Total Seats">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="text" name="trainFareAc2" class="field-style field-split align-center" placeholder="AC2 Class Fare" value="<%=trainFareAc2%>" maxlength="5"  title="AC2 Fare">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainFareAc2Totalseats" class="field-style field-split align-center" placeholder="AC2 Total Seats" value="<%=trainFareAc2Totalseats%>" maxlength="3"  title="AC2 Total Seats">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


    <input type="text" name="trainFareAc3" class="field-style field-split align-center" placeholder="AC3 Class Fare" value="<%=trainFareAc3%>" maxlength="5"  title="AC3 Fare">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="trainFareAc3Totalseats" class="field-style field-split align-center" placeholder="AC3 Total Seats" value="<%=trainFareAc3Totalseats%>" maxlength="3"  title="AC3 Total Seats">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

    
    <input type="text" name="trainFareRemarks" class="field-style field-split align-center" placeholder="Remarks" value="<%=trainFareRemarks%>" maxlength="500"  title="Remarks">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
   <select name="trainFareFlag" class="field-style field-split align-center" placeholder="Flag"  title="Flag">
    <option value="Y" <%=trainFareFlag.equals("Y") ? "SELECTED" : "" %>> Active </option>
    <option value="N" <%=trainFareFlag.equals("N") ? "SELECTED" : "" %>> In-Active </option>
    </select>
    
</li>

<br><br>
<li align="middle">
<input type="submit" value="Submit" onclick="onSubmit('Add')"/> 
<input type="submit" value="Search" onclick="onSubmit('Search')"/>
<input type="submit" value="Delete" onclick="onSubmit('Delete')"/>
<input type="submit" value="Clear" onclick="trainFareForm.reset()"/>
</li>
</ul>

<br><br>



<% 

 %>

<table class="responstable">
<tr> 
<td> Select </td>
<td> Train Name</td>
<td> Sleeper Class Fare </td>
<td> Sleeper Class Total Seats  </td> 
<td> AC1 Class Fare </td>
<td> AC1 Class Total Seats  </td>
<td> AC2 Class Fare </td>
<td> AC2 Class Total Seats  </td>
<td> AC3 Class Fare </td>
<td> AC3 Class Total Seats  </td>
<td> Remarks </td>
<td> Flag </td>
</tr>

<% if (tfList!=null && tfList.size() > 0)
{
		Iterator iterator = tfList.iterator(); 
		TrainFare tf = new TrainFare();	
		while (iterator.hasNext()) 
			{
			%>
			<%
			tf = (TrainFare) iterator.next();
			%>
	         <tr onClick="populateValues(<%=tf.getTrain_fare_id()%>,<%=tf.getTrain_master_id()%>,'<%=tf.getTrain_fare_sleeper()%>','<%=tf.getTrain_fare_sleeper_totalseats()%>',<%=tf.getTrain_fare_ac1()%>,<%=tf.getTrain_fare_ac1_totalseats()%>,<%=tf.getTrain_fare_ac2()%>,<%=tf.getTrain_fare_ac2_totalseats()%>,<%=tf.getTrain_fare_ac3()%>,<%=tf.getTrain_fare_ac3_totalseats()%>,'<%=tf.getTrain_fare_remarks()%>','<%=tf.getTrain_fare_flag()%>')">
	         <td>  <input type="radio" name="radio" onClick="openPopup(<%=tf.getTrain_master_id()%>);"/> </td>
	         <td> <%=trainMasterHashMap.get(tf.getTrain_master_id())%></td>
	         <td> <%=tf.getTrain_fare_sleeper()%></td>
	         <td> <%=tf.getTrain_fare_sleeper_totalseats()%></td>
	         <td> <%=tf.getTrain_fare_ac1()%></td>
	         <td> <%=tf.getTrain_fare_ac1_totalseats()%></td>
	         <td> <%=tf.getTrain_fare_ac2()%></td>
	         <td> <%=tf.getTrain_fare_ac2_totalseats()%></td>
	         <td> <%=tf.getTrain_fare_ac3()%></td>
	         <td> <%=tf.getTrain_fare_ac3_totalseats()%></td>
	         <td> <%=tf.getTrain_fare_remarks()%></td>
	         <td> <%=tf.getTrain_fare_flag()%></td>
	   
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