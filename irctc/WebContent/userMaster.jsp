<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.irctc.controller.*" %>
<%@ page import="com.irctc.baseclasses.*" %>
<%@ page import="com.irctc.utilities.*" %>


<%
ArrayList umList = new ArrayList<UserMaster>();
umList = (ArrayList<UserMaster>)request.getAttribute("umList");
Date dt = new Date();

HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>();
userTypeHashMap = (HashMap<Integer, String>)request.getAttribute("userTypeHashMap");

String masterType = new String();

String userMasterLogin = new String();
String userMasterPassword = new String();
String userMasterName = new String();
String userMasterDob = new String();
String userMasterSex = new String();
String userMasterAddr1 = new String();
String userMasterAddr2 = new String();
String userMasterPincode = new String();
String userMasterEmailId = new String();
String userMasterDateOfReg = new String();
int userMasterUserType = 0;
String userMasterRemarks = new String();
String userMasterFlag = new String();

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
	userMasterLogin  = request.getAttribute("userMasterLogin").toString();
}
catch(Exception e)
{
	userMasterLogin ="";
	e.printStackTrace();
}



try
{
	userMasterPassword  = request.getAttribute("userMasterPassword").toString();
}
catch(Exception e)
{
	userMasterPassword ="";
	e.printStackTrace();
}



try
{
	userMasterName  = request.getAttribute("userMasterName").toString();
}
catch(Exception e)
{
	userMasterName ="";
	e.printStackTrace();
}



try
{
	userMasterDob  =  request.getAttribute("userMasterDob").toString();
}
catch(Exception e)
{
	userMasterDob ="";
	e.printStackTrace();
}


try
{
	userMasterSex  = request.getAttribute("userMasterSex").toString();
}
catch(Exception e)
{
	userMasterSex ="";
	e.printStackTrace();
}



try
{
	userMasterAddr1  = request.getAttribute("userMasterAddr1").toString();
}
catch(Exception e)
{
	userMasterAddr1 ="";
	e.printStackTrace();
}

try
{
	userMasterAddr2  = request.getAttribute("userMasterAddr2").toString();
}
catch(Exception e)
{
	userMasterAddr2 ="";
	e.printStackTrace();
}



try
{
	userMasterPincode  = request.getAttribute("userMasterPincode").toString();
}
catch(Exception e)
{
	userMasterPincode ="";
	e.printStackTrace();
}


try
{
	userMasterEmailId  = request.getAttribute("userMasterEmailId").toString();
}
catch(Exception e)
{
	userMasterEmailId ="";
	e.printStackTrace();
}



try
{
	userMasterDateOfReg  = request.getAttribute("userMasterDateOfReg").toString();
}
catch(Exception e)
{
	userMasterDateOfReg = "";
	e.printStackTrace();
}


try
{
	userMasterUserType  = Integer.parseInt(request.getAttribute("userMasterUserType").toString());
}
catch(Exception e)
{
	userMasterUserType = 0;
	e.printStackTrace();
}



try
{
	userMasterRemarks  = request.getAttribute("userMasterRemarks").toString();
}
catch(Exception e)
{
	userMasterRemarks = "";
	e.printStackTrace();
}


try
{
	userMasterFlag  = request.getAttribute("userMasterFlag").toString();
}
catch(Exception e)
{
	userMasterFlag = "";
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
	 if (userMasterForm.userMasterLogin.value!="" && userMasterForm.userMasterPassword.value!="" && userMasterForm.userMasterName.value!=""&& userMasterForm.userMasterDob.value!=""&& userMasterForm.userMasterSex.value!=""&& userMasterForm.userMasterAddr1.value!=""&& userMasterForm.userMasterPincode.value!=""&& userMasterForm.userMasterEmailId.value!=""&& userMasterForm.userMasterDateOfReg .value!=""&& userMasterForm.userMasterUserType .value!=""&& userMasterForm.userMasterFlag.value!="")
		return true;
	else 
		return false;
		
}


function validateInputsForUpdateOrDelete()
{
	if (userMasterForm.userMasterId.value!="" && userMasterForm.userMasterLogin.value!="" && userMasterForm.userMasterPassword.value!="" && userMasterForm.userMasterName.value!=""&& userMasterForm.userMasterDob.value!=""&& userMasterForm.userMasterSex.value!=""&& userMasterForm.userMasterAddr1.value!=""&& userMasterForm.userMasterPincode.value!=""&& userMasterForm.userMasterEmailId.value!=""&& userMasterForm.userMasterDateOfReg .value!=""&& userMasterForm.userMasterUserType .value!=""&& userMasterForm.userMasterFlag.value!="")
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
	
	
	userMasterForm.act.value=x;
	userMasterForm.submit();
}

function populateValues(id,login,psw,uname,dob,sex,addr1,addr2,pin,email,dor,utype,rem,flag)
{
	//alert(id + ' ' + ut + ' ' + rm + ' ' + fl);
	userMasterForm.userMasterId.value = id;
	userMasterForm.userMasterLogin.value = login;
	userMasterForm.userMasterPassword.value = psw;
	userMasterForm.userMasterName.value = uname;
	userMasterForm.userMasterDob.value = dob;
	userMasterForm.userMasterSex.value = sex;
	userMasterForm.userMasterAddr1.value = addr1;
	userMasterForm.userMasterAddr2.value = addr2;
	userMasterForm.userMasterPincode.value = pin;
	userMasterForm.userMasterEmailId.value = email;
	userMasterForm.userMasterDateOfReg.value = dor;
	userMasterForm.userMasterUserType.value = utype;
	userMasterForm.userMasterRemarks.value = rem;
	userMasterForm.userMasterFlag.value = flag;
}

</script>


</head>

<jsp:include page="header.jsp" />
<br><br>

<form class="form-style-9" name="userMasterForm" action="MasterController" method="post">
<h1 align="center"> <b> USER MASTER </b></h1>
<% if (request.getAttribute("error")!=null && request.getAttribute("error")!="")
{ %>
<br>
<h1> <p color="red">  <%=request.getAttribute("error").toString() %> </p></h1>
<%
}
%>

<br/><br/>
<input type="hidden" name="act">
<input type="hidden" name="userMasterId">
<input type="hidden" name="masterType" value="<%=masterType%>">

<body>
<ul>
<li>
	
    <input type="text" name="userMasterLogin" class="field-style field-split align-center" placeholder="Login" value="<%=userMasterLogin%>" maxlength="10">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterPassword" class="field-style field-split align-center" placeholder="Password" value="<%=userMasterPassword%>" maxlength="10">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <input type="text" name="userMasterName" class="field-style field-split align-center" placeholder="User Name" value="<%=userMasterName%>" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <input type="date" name="userMasterDob" class="field-style field-split align-center" placeholder="Date of Birth" value="<%=userMasterDob%>" title="Date of Birth">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <select name="userMasterSex" class="field-style field-split align-center" placeholder="Sex">
    <option value=""><--Select Sex--></option>
    <option value="M" <%=userMasterSex.equals("M") ? "SELECTED" : "" %>> Male </option>
    <option value="F" <%=userMasterSex.equals("F") ? "SELECTED" : "" %>> Female </option>
    <option value="T" <%=userMasterSex.equals("T") ? "SELECTED" : "" %>> Transgender </option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterAddr1" class="field-style field-split align-center" placeholder="Address" value="<%=userMasterAddr1%>" maxlength="250">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterAddr2" class="field-style field-split align-center" placeholder="Address" value="<%=userMasterAddr2%>" maxlength="250">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterPincode" class="field-style field-split align-center" placeholder="Pin Code" value="<%=userMasterPincode%>" maxlength="6">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterEmailId" class="field-style field-split align-center" placeholder="E-mail Id" value="<%=userMasterEmailId%>" maxlength="100">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="date" name="userMasterDateOfReg" class="field-style field-split align-center" placeholder="Date of Reg" value="<%=userMasterDateOfReg%>" title="Date of Registration">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

 	<select name="userMasterUserType" class="field-style field-split align-center" placeholder="User Type">
 	<option value=""><--Select UserType--></option>
 	<%
 	Iterator userTypeHashMapIterator = userTypeHashMap.entrySet().iterator(); 
  
        while (userTypeHashMapIterator.hasNext()) 
        { 
            Map.Entry mapElement = (Map.Entry)userTypeHashMapIterator.next(); %> 
        
        <option value="<%=Integer.parseInt(mapElement.getKey().toString())%>" <%=userMasterUserType==Integer.parseInt(mapElement.getKey().toString()) ? "SELECTED" : "" %>> <%=mapElement.getValue().toString() %> </option>
        <%
        }
        %>
        
    
    </select>
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" name="userMasterRemarks" class="field-style field-split align-center" placeholder="Remarks" value="<%=userMasterRemarks%>" maxlength="1000">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <select name="userMasterFlag" class="field-style field-split align-center" placeholder="Flag">
    <option value="Y" <%=userMasterFlag.equals("Y") ? "SELECTED" : "" %>> Active </option>
    <option value="N" <%=userMasterFlag.equals("N") ? "SELECTED" : "" %>> In-Active </option>
    </select>
    
</li>

<br><br>
<li align="middle">
<input type="submit" value="Submit" onclick="onSubmit('Add')"/> 
<input type="submit" value="Search" onclick="onSubmit('Search')"/>
<input type="submit" value="Update" onclick="onSubmit('Update')"/>
<input type="submit" value="Delete" onclick="onSubmit('Delete')"/>
<input type="submit" value="Clear" onclick="userMasterForm.reset()"/>
</li>
</ul>

<br><br>



<% 

 %>

<table class="responstable">
<tr> 
<td> User Master Id </td>
<td> Login </td>
<td> Password </td>
<td> Name</td> 
<td> DOB </td>
<td> Sex </td>
<td> Address 1 </td>
<td> Address 2 </td>
<td> Pincode </td>
<td> Email Id</td>
<td> Date of Reg.</td>
<td> User Type </td>
<td> Remarks </td>
<td> Flag </td>
</tr>

<% if (umList!=null && umList.size() > 0)
{
		Iterator iterator = umList.iterator(); 
		UserMaster um = new UserMaster();	
		while (iterator.hasNext()) 
			{
			%>
			<%
			um = (UserMaster) iterator.next();
			%>
	         <tr onClick="populateValues(<%=um.getUser_master_id()%>,'<%=um.getUser_master_login()%>','<%=um.getUser_master_password()%>','<%=um.getUser_master_name()%>','<%=um.getUser_master_dob()%>','<%=um.getUser_master_sex()%>','<%=um.getUser_master_addr1()%>','<%=um.getUser_master_addr2()%>','<%=um.getUser_master_pincode()%>','<%=um.getUser_master_email_id()%>','<%=um.getUser_master_date_of_reg()%>','<%=um.getUser_master_usertype()%>','<%=um.getUser_master_remarks()%>','<%=um.getUser_master_flag()%>')">
			<td> <%=um.getUser_master_id()%></td>
			<td> <%=um.getUser_master_login()%></td>
			<td> <%=um.getUser_master_password()%></td>
			<td> <%=um.getUser_master_name()%></td> 
			<td> <%=IrctcUtilities.javaDateToString(um.getUser_master_dob())%></td>
			<td> <%=um.getUser_master_sex()%></td>
			<td> <%=um.getUser_master_addr1()%></td>
			<td> <%=um.getUser_master_addr2()%></td>
			<td> <%=um.getUser_master_pincode()%></td>
			<td> <%=um.getUser_master_email_id()%></td>
			<td> <%=IrctcUtilities.javaDateToString(um.getUser_master_date_of_reg())%></td>
			<td> <%=userTypeHashMap.get(um.getUser_master_usertype())%></td>
			<td> <%=um.getUser_master_remarks()%></td>
			<td> <%=um.getUser_master_flag()%></td>
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