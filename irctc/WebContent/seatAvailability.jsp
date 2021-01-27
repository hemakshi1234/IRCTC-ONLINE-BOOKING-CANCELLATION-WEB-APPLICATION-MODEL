<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>
<%@ page import="com.irctc.utilities.*" %>


<%
ArrayList saList = new ArrayList<SeatAvailability>();
int trainMasterId = Integer.parseInt(request.getParameter("trainMasterId"));
saList = IrctcUtilities.getSeatAvailability(trainMasterId);
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
	 if (seatAvailabilityForm.userMasterLogin.value!="" && seatAvailabilityForm.userMasterPassword.value!="" && seatAvailabilityForm.userMasterName.value!=""&& seatAvailabilityForm.userMasterDob.value!=""&& seatAvailabilityForm.userMasterSex.value!=""&& seatAvailabilityForm.userMasterAddr1.value!=""&& seatAvailabilityForm.userMasterPincode.value!=""&& seatAvailabilityForm.userMasterEmailId.value!=""&& seatAvailabilityForm.userMasterDateOfReg .value!=""&& seatAvailabilityForm.userMasterUserType .value!=""&& seatAvailabilityForm.userMasterFlag.value!="")
		return true;
	else 
		return false;
		
}


function validateInputsForUpdateOrDelete()
{
	if (seatAvailabilityForm.userMasterId.value!="" && seatAvailabilityForm.userMasterLogin.value!="" && seatAvailabilityForm.userMasterPassword.value!="" && seatAvailabilityForm.userMasterName.value!=""&& seatAvailabilityForm.userMasterDob.value!=""&& seatAvailabilityForm.userMasterSex.value!=""&& seatAvailabilityForm.userMasterAddr1.value!=""&& seatAvailabilityForm.userMasterPincode.value!=""&& seatAvailabilityForm.userMasterEmailId.value!=""&& seatAvailabilityForm.userMasterDateOfReg .value!=""&& seatAvailabilityForm.userMasterUserType .value!=""&& seatAvailabilityForm.userMasterFlag.value!="")
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
	
	
	seatAvailabilityForm.act.value=x;
	seatAvailabilityForm.submit();
}

function populateValues(id,login,psw,uname,dob,sex,addr1,addr2,pin,email,dor,utype,rem,flag)
{
	//alert(id + ' ' + ut + ' ' + rm + ' ' + fl);
	seatAvailabilityForm.userMasterId.value = id;
	seatAvailabilityForm.userMasterLogin.value = login;
	seatAvailabilityForm.userMasterPassword.value = psw;
	seatAvailabilityForm.userMasterName.value = uname;
	seatAvailabilityForm.userMasterDob.value = dob;
	seatAvailabilityForm.userMasterSex.value = sex;
	seatAvailabilityForm.userMasterAddr1.value = addr1;
	seatAvailabilityForm.userMasterAddr2.value = addr2;
	seatAvailabilityForm.userMasterPincode.value = pin;
	seatAvailabilityForm.userMasterEmailId.value = email;
	seatAvailabilityForm.userMasterDateOfReg.value = dor;
	seatAvailabilityForm.userMasterUserType.value = utype;
	seatAvailabilityForm.userMasterRemarks.value = rem;
	seatAvailabilityForm.userMasterFlag.value = flag;
}

</script>


</head>

<br><br>

<form class="form-style-9" name="seatAvailabilityForm" method="post">
<h1 align="center"> <b> SEAT AVAILABILITY </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>

<br/><br/>

<body>

<% 

 %>

<table class="responstable">
<tr> 
<td> Sl.No </td>
<td> Class </td>
<td> Coach No </td>
<td> Seat No </td> 
<td> Berth</td>
</tr>

<% if (saList!=null && saList.size() > 0)
{
		int i=0;
		Iterator iterator = saList.iterator(); 
		SeatAvailability sa = new SeatAvailability();	
		while (iterator.hasNext()) 
			{
			%>
			<%
			sa = (SeatAvailability) iterator.next();
			%>
	         <tr>
	        <td> <%=++i%></td>
			<td> <%=sa.getSeat_availability_class() %></td>
			<td> <%=sa.getSeat_availability_coachno() %></td>
			<td> <%=sa.getSeat_availability_seatno() %></td> 
			<td> <%=sa.getSeat_availability_berthtype() %></td>
			</tr> 
			<%	
			}
}
else
{ %>
<tr> 
<td colspan=14> No Records Found  </td>

</tr>

	
<%
}
%>
</table>
</body>
</form>