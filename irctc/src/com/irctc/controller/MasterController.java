package com.irctc.controller;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.irctc.baseclasses.StationMaster;
import com.irctc.baseclasses.TrainFare;
import com.irctc.baseclasses.TrainMaster;
import com.irctc.baseclasses.UserMaster;
import com.irctc.baseclasses.UserType;
import com.irctc.utilities.IrctcUtilities;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;




/**
 * Servlet implementation class MasterController
 */

public class MasterController extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String masterType = new String();
		String action = new String();
		HttpSession session = request.getSession();
		
		try
		{
		masterType = request.getParameter("masterType");
		}
		catch (Exception e)
		{
			masterType="";
			e.printStackTrace();
		}
		
		try
		{
		action = request.getParameter("act");
		}
		catch (Exception e)
		{
			action="";
			e.printStackTrace();
		}

		
		// Connect to mysql and verify username password
		
		try {
			Connection c = IrctcUtilities.getConnection();
			
			if (masterType.equalsIgnoreCase("UserTypeMaster"))
			{

				if (action!=null && action.equalsIgnoreCase("Add"))
				{
					/*
					 * Code for Adding New Record 
					 */
					
					// Get Parameter Values
					String userType = request.getParameter("userType");
					String remarks = request.getParameter("remarks");
					String flag = request.getParameter("flag");
					
					ArrayList utList = new ArrayList<UserType>();
					Statement s = c.createStatement();
					
					
					
					// Use insert into query to add record into the table
					
					int i=s.executeUpdate("insert into usertype_master(usertype_master_usertype, usertype_master_remarks, usertype_master_flag) values ('"+userType+"','"+remarks+"','"+flag+"')");

					
                   
					
					// default code 
					
					String query="select * from usertype_master where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					UserType ut = new UserType();
					while (rs.next())
					{
						ut = new UserType();
						ut.setUserId(rs.getInt(1));
						ut.setType(rs.getString(2));
						ut.setRemark(rs.getString(3));
						ut.setFlag(rs.getString(4));
						utList.add(ut);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("userTypeMaster.jsp");
					request.setAttribute("utList",utList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userType",userType);
					request.setAttribute("remarks", remarks);
					request.setAttribute("flag", flag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				
				
				else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
				{
					String userType = request.getParameter("userType");
					String remarks = request.getParameter("remarks");
					String flag = request.getParameter("flag");
					
					String query="select * from usertype_master where 1 = 1 ";
					
					if (userType!=null && userType!="")
					{
						query = query + " and usertype_master_usertype like '%" + userType + "%'";
					}
					
					if (remarks!=null && remarks!="")
					{
						query = query + " and usertype_master_remarks like '%" + remarks + "%'";
					}
					
					if (flag !=null && flag !="")
					{
						query = query + " and usertype_master_flag like '%" + flag + "%'";
					}
					
					ArrayList utList = new ArrayList<UserType>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery(query);
					UserType ut = new UserType();
					while (rs.next())
					{
						ut = new UserType();
						ut.setUserId(rs.getInt(1));
						ut.setType(rs.getString(2));
						ut.setRemark(rs.getString(3));
						ut.setFlag(rs.getString(4));
						utList.add(ut);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("userTypeMaster.jsp");
					request.setAttribute("utList",utList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userType",userType);
					request.setAttribute("remarks", remarks);
					request.setAttribute("flag", flag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				
				
				else if (action!=null && action.equalsIgnoreCase("Update"))
				{

					/*
					 * Code for Update a record 
					 */
					
					// Get Parameter Values
					int userTypeId = Integer.parseInt(request.getParameter("userTypeId"));
					String userType = request.getParameter("userType");
					String remarks = request.getParameter("remarks");
					String flag = request.getParameter("flag");
					
					ArrayList utList = new ArrayList<UserType>();
					Statement s = c.createStatement();
					
					
					
					// Use update query to update a record 
					
					int i=s.executeUpdate("update usertype_master set usertype_master_usertype='"+ userType + "', usertype_master_remarks='" + remarks + "',usertype_master_flag='"+ flag +"' where usertype_master_id = " + userTypeId );
					
					// default code 
					
					String query="select * from usertype_master where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					UserType ut = new UserType();
					while (rs.next())
					{
						ut = new UserType();
						ut.setUserId(rs.getInt(1));
						ut.setType(rs.getString(2));
						ut.setRemark(rs.getString(3));
						ut.setFlag(rs.getString(4));
						utList.add(ut);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("userTypeMaster.jsp");
					request.setAttribute("utList",utList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userType",userType);
					request.setAttribute("remarks", remarks);
					request.setAttribute("flag", flag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				else if (action!=null && action.equalsIgnoreCase("Delete"))
				{
					/*
					 * Code for Delete a record 
					 */
					
					// Get Parameter Values
					int userTypeId = Integer.parseInt(request.getParameter("userTypeId"));
					
					
					ArrayList utList = new ArrayList<UserType>();
					Statement s = c.createStatement();
					
					
					
					// Use delete  query to delete a record 
					
					int i=s.executeUpdate("delete from usertype_master where usertype_master_id = " + userTypeId );
					
					// default code 
					
					String query="select * from usertype_master where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					UserType ut = new UserType();
					while (rs.next())
					{
						ut = new UserType();
						ut.setUserId(rs.getInt(1));
						ut.setType(rs.getString(2));
						ut.setRemark(rs.getString(3));
						ut.setFlag(rs.getString(4));
						utList.add(ut);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("userTypeMaster.jsp");
					request.setAttribute("utList",utList);
					request.setAttribute("masterType",masterType);
					//request.setAttribute("action", action);
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				
				
				/*
				 * Default page: When you click from the Menu. 
				 */
				ArrayList utList = new ArrayList<UserType>();
				UserType ut = new UserType();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT * FROM usertype_master");
				
				while (rs.next())
				{
					ut = new UserType();
					ut.setUserId(rs.getInt(1));
					ut.setType(rs.getString(2));
					ut.setRemark(rs.getString(3));
					ut.setFlag(rs.getString(4));
					utList.add(ut);
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("userTypeMaster.jsp");
				request.setAttribute("utList",utList);
				request.setAttribute("masterType",masterType);
				
				dispatcher.forward(request, response);
				c.close();
				return;
			}
			
			
			
			else if (masterType.equalsIgnoreCase("UserMaster"))
			{
				/*
				 * Code for UserMaster Updation 
				 */


				if (action!=null && action.equalsIgnoreCase("Add"))
				{
					/*
					 * Code for Adding New Record 
					 */
					
					// Get Parameter Values
					String userMasterLogin= request.getParameter("userMasterLogin");
					String userMasterPassword = request.getParameter("userMasterPassword");
					String userMasterName = request.getParameter("userMasterName");
					String userMasterDob = request.getParameter("userMasterDob");
					String userMasterSex = request.getParameter("userMasterSex");
					String  userMasterAddr1= request.getParameter("userMasterAddr1");
					String  userMasterAddr2= request.getParameter("userMasterAddr2");
					String userMasterPincode = request.getParameter("userMasterPincode");
					String  userMasterEmailId= request.getParameter("userMasterEmailId");
					String userMasterDateOfReg = request.getParameter("userMasterDateOfReg");
					String userMasterUserType = request.getParameter("userMasterUserType");
					String  userMasterRemarks= request.getParameter("userMasterRemarks");
					String  userMasterFlag= request.getParameter("userMasterFlag");
					
				// date reference
				//	String dob = request.getParameter("userMasterDob");
				//	System.out.println("dob" + dob );
					
					ArrayList umList = new ArrayList<UserMaster>();
					Statement s = c.createStatement();
					String error = "";
					
					
					
					// Use insert into query to add record into the table
					// Using Try Catch as server side validation to avoid duplicate entry (login name) in the User Master 
					try
					{
					int i=s.executeUpdate("insert into user_master(user_master_login, user_master_password, user_master_name, user_master_dob, user_master_sex, user_master_addr1, user_master_addr2, user_master_pincode, user_master_email_id, user_master_date_of_reg, user_master_usertype, user_master_remarks, user_master_flag) values ('"+userMasterLogin+"','"+userMasterPassword+"','"+userMasterName+"','"+userMasterDob+"','"+userMasterSex+"','"+userMasterAddr1+"','"+userMasterAddr2+"','"+userMasterPincode+"','"+userMasterEmailId+"','"+userMasterDateOfReg+"','"+userMasterUserType+"','"+userMasterRemarks+"','"+userMasterFlag+"')");
					}
					catch(MySQLIntegrityConstraintViolationException e)
					{
						error = "Login Name is Unique.  There cannot be two records in the same Login Name : " + userMasterLogin;
						e.printStackTrace();
					}
					catch(Exception e)
					{
						error = "Error Occurred. " + e.getMessage();
						e.printStackTrace();
					}

					
					// default code 
					
					ArrayList<UserType> utList = new ArrayList<UserType>();
					s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT usertype_master_id, usertype_master_usertype FROM usertype_master where usertype_master_flag='Y'");
					HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>();
					while (rs.next())
					{
						userTypeHashMap.put(rs.getInt(1), rs.getString(2));
					}
					
					
					
					
					String query="select * from user_master where 1 = 1 ";
					
					s = c.createStatement();
					rs = s.executeQuery(query);
					UserMaster um = new UserMaster();
					
					
					while (rs.next())
					{
						um = new UserMaster();
						um.setUser_master_id(rs.getInt(1));
						um.setUser_master_login(rs.getString(2));
						um.setUser_master_password(rs.getString(3));
						um.setUser_master_name(rs.getString(4));
						um.setUser_master_dob(rs.getDate(5));
						um.setUser_master_sex(rs.getString(6));
						um.setUser_master_addr1(rs.getString(7));
						um.setUser_master_addr2(rs.getString(8));
						um.setUser_master_pincode(rs.getString(9));
						um.setUser_master_email_id(rs.getString(10));
						um.setUser_master_date_of_reg(rs.getDate(11));
						um.setUser_master_usertype(rs.getInt(12));
						um.setUser_master_remarks(rs.getString(13));
						um.setUser_master_flag(rs.getString(14));
						umList.add(um);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("userMaster.jsp");
					request.setAttribute("umList",umList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userTypeHashMap", userTypeHashMap);
					request.setAttribute("error", error);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				
				
				
				
				
				else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
				{
					String userMasterLogin= request.getParameter("userMasterLogin");
					String userMasterPassword = request.getParameter("userMasterPassword");
					String userMasterName = request.getParameter("userMasterName");
					String userMasterDob = request.getParameter("userMasterDob");
					String userMasterSex = request.getParameter("userMasterSex");
					String  userMasterAddr1= request.getParameter("userMasterAddr1");
					String  userMasterAddr2= request.getParameter("userMasterAddr2");
					String userMasterPincode = request.getParameter("userMasterPincode");
					String  userMasterEmailId= request.getParameter("userMasterEmailId");
					String userMasterDateOfReg = request.getParameter("userMasterDateOfReg");
					String userMasterUserType = request.getParameter("userMasterUserType");
					String  userMasterRemarks= request.getParameter("userMasterRemarks");
					String  userMasterFlag= request.getParameter("userMasterFlag");
					
					String query="select * from user_master where 1 = 1 ";
					
					if (userMasterLogin!=null && userMasterLogin!="")
					{
						query = query + " and user_master_login like '%" + userMasterLogin + "%'";
					}
					
					if (userMasterPassword!=null && userMasterPassword!="")
					{
						query = query + " and user_master_password like '%" + userMasterPassword + "%'";
					}
					
					if (userMasterName!=null && userMasterName!="")
					{
						query = query + " and user_master_name like '%" +userMasterName + "%'";
					}
					
					if (userMasterDob!=null && userMasterDob!="")
					{
						query = query + " and user_master_dob like '%" + userMasterDob + "%'";
					}
					
					if (userMasterSex!=null && userMasterSex!="")
					{
						query = query + " and user_master_sex like '%" + userMasterSex + "%'";
					}
					
					
					if (userMasterAddr1!=null && userMasterAddr1!="")
					{
						query = query + " and user_master_addr1 like '%" + userMasterAddr1 + "%'";
					}
					
					if (userMasterAddr2!=null && userMasterAddr2!="")
					{
						query = query + " and user_master_addr2 like '%" + userMasterAddr2 + "%'";
					}
					
					
					if (userMasterPincode!=null && userMasterPincode!="")
					{
						query = query + " and user_master_pincode like '%" +userMasterPincode + "%'";
					}
					if (userMasterEmailId!=null && userMasterEmailId!="")
					{
						query = query + " and user_master_email_id like '%" +userMasterEmailId + "%'";
					}
					if (userMasterDateOfReg!=null && userMasterDateOfReg!="")
					{
						query = query + " and user_master_date_of_reg like '%" + userMasterDateOfReg + "%'";
					}
					if (userMasterUserType!=null &&userMasterUserType!="")
					{
						query = query + " and user_master_usertype like '%" + userMasterUserType + "%'";
					}
					if (userMasterRemarks!=null && userMasterRemarks!="")
					{
						query = query + " and user_master_remarks like '%" + userMasterRemarks + "%'";
					}
					if (userMasterFlag!=null && userMasterFlag!="")
					{
						query = query + " and user_master_flag like '%" + userMasterFlag + "%'";
					}
					
					ArrayList umList = new ArrayList<UserMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery(query);
					UserMaster um = new UserMaster();
					

					while (rs.next())
					{
						um = new UserMaster();
						um.setUser_master_id(rs.getInt(1));
						um.setUser_master_login(rs.getString(2));
						um.setUser_master_password(rs.getString(3));
						um.setUser_master_name(rs.getString(4));
						um.setUser_master_dob(rs.getDate(5));
						um.setUser_master_sex(rs.getString(6));
						um.setUser_master_addr1(rs.getString(7));
						um.setUser_master_addr2(rs.getString(8));
						um.setUser_master_pincode(rs.getString(9));
						um.setUser_master_email_id(rs.getString(10));
						um.setUser_master_date_of_reg(rs.getDate(11));
						um.setUser_master_usertype(rs.getInt(12));
						um.setUser_master_remarks(rs.getString(13));
						um.setUser_master_flag(rs.getString(14));
						umList.add(um);
					}
					
					
					
					
					ArrayList<UserType> utList = new ArrayList<UserType>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT usertype_master_id, usertype_master_usertype FROM usertype_master where usertype_master_flag='Y'");
					HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>();
					while (rs.next())
					{
						userTypeHashMap.put(rs.getInt(1), rs.getString(2));
					}
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("userMaster.jsp");
					request.setAttribute("umList",umList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userTypeHashMap", userTypeHashMap);

					//Set input values for the placeholder
					request.setAttribute("userMasterLogin",userMasterLogin);
					request.setAttribute("userMasterPassword",userMasterPassword);
					request.setAttribute("userMasterName",userMasterName);
					request.setAttribute("userMasterDob",userMasterDob);
					request.setAttribute("userMasterSex",userMasterSex);
					request.setAttribute("userMasterAddr1",userMasterAddr1);
					request.setAttribute("userMasterAddr2",userMasterAddr2);
					request.setAttribute("userMasterPincode",userMasterPincode);
					request.setAttribute("userMasterEmailId",userMasterEmailId);
					request.setAttribute("userMasterDateOfReg",userMasterDateOfReg);
					request.setAttribute("userMasterUserType",userMasterUserType);
					request.setAttribute("userMasterRemarks",userMasterRemarks);
					request.setAttribute("userMasterFlag",userMasterFlag);
						
					
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
									
					
				}
				
				else if (action!=null && action.equalsIgnoreCase("Update"))
				{

					/*
					 * Code for Update a record 
					 */
					

					
					// Get Parameter Values
					int userMasterId = Integer.parseInt(request.getParameter("userMasterId"));
					String userMasterLogin= request.getParameter("userMasterLogin");
					String userMasterPassword = request.getParameter("userMasterPassword");
					String userMasterName = request.getParameter("userMasterName");
					String userMasterDob = request.getParameter("userMasterDob");
					String userMasterSex = request.getParameter("userMasterSex");
					String  userMasterAddr1= request.getParameter("userMasterAddr1");
					String  userMasterAddr2= request.getParameter("userMasterAddr2");
					String userMasterPincode = request.getParameter("userMasterPincode");
					String  userMasterEmailId= request.getParameter("userMasterEmailId");
					String userMasterDateOfReg = request.getParameter("userMasterDateOfReg");
					String userMasterUserType = request.getParameter("userMasterUserType");
					String  userMasterRemarks= request.getParameter("userMasterRemarks");
					String  userMasterFlag= request.getParameter("userMasterFlag");
					
					Statement s = c.createStatement();
					
					// Use update query to update a record 
					
					int i=s.executeUpdate("update user_master set user_master_login='"+ userMasterLogin+ "', user_master_password='" + userMasterPassword + "',user_master_name='"+ userMasterName +"' , user_master_dob='" + userMasterDob +"', user_master_sex='" + userMasterSex +"', user_master_addr1='" + userMasterAddr1 +"', user_master_addr2='" + userMasterAddr2 +"', user_master_pincode='" + userMasterPincode +"', user_master_email_id='" + userMasterEmailId +"', user_master_date_of_reg='" + userMasterDateOfReg +"', user_master_usertype='" + userMasterUserType +"', user_master_remarks='" + userMasterRemarks+"', user_master_flag='" + userMasterFlag+"' where user_master_id = " + userMasterId);
					
					// default code 
					
					ArrayList umList = new ArrayList<UserMaster>();
					s = c.createStatement();
					ResultSet rs = s.executeQuery(" select * from user_master ");
					UserMaster um = new UserMaster();
					

					while (rs.next())
					{
						um = new UserMaster();
						um.setUser_master_id(rs.getInt(1));
						um.setUser_master_login(rs.getString(2));
						um.setUser_master_password(rs.getString(3));
						um.setUser_master_name(rs.getString(4));
						um.setUser_master_dob(rs.getDate(5));
						um.setUser_master_sex(rs.getString(6));
						um.setUser_master_addr1(rs.getString(7));
						um.setUser_master_addr2(rs.getString(8));
						um.setUser_master_pincode(rs.getString(9));
						um.setUser_master_email_id(rs.getString(10));
						um.setUser_master_date_of_reg(rs.getDate(11));
						um.setUser_master_usertype(rs.getInt(12));
						um.setUser_master_remarks(rs.getString(13));
						um.setUser_master_flag(rs.getString(14));
						umList.add(um);
					}
					
					
					
					
					ArrayList<UserType> utList = new ArrayList<UserType>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT usertype_master_id, usertype_master_usertype FROM usertype_master where usertype_master_flag='Y'");
					HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>();
					while (rs.next())
					{
						userTypeHashMap.put(rs.getInt(1), rs.getString(2));
					}
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("userMaster.jsp");
					request.setAttribute("umList",umList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userTypeHashMap", userTypeHashMap);
					
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				
				
				
				else if (action!=null && action.equalsIgnoreCase("Delete"))
				{
					/*
					 * Code for Delete a record 
					 */
					
					// Get Parameter Values
					int userMasterId = Integer.parseInt(request.getParameter("userMasterId"));
					
					
					Statement s = c.createStatement();
					
					
					// Use delete  query to delete a record 
					
					int i=s.executeUpdate("delete from user_master where user_master_id = " + userMasterId );
					
					// default code 
					
					
					ArrayList umList = new ArrayList<UserMaster>();
					s = c.createStatement();
					ResultSet rs = s.executeQuery(" select * from user_master ");
					UserMaster um = new UserMaster();
					

					while (rs.next())
					{
						um = new UserMaster();
						um.setUser_master_id(rs.getInt(1));
						um.setUser_master_login(rs.getString(2));
						um.setUser_master_password(rs.getString(3));
						um.setUser_master_name(rs.getString(4));
						um.setUser_master_dob(rs.getDate(5));
						um.setUser_master_sex(rs.getString(6));
						um.setUser_master_addr1(rs.getString(7));
						um.setUser_master_addr2(rs.getString(8));
						um.setUser_master_pincode(rs.getString(9));
						um.setUser_master_email_id(rs.getString(10));
						um.setUser_master_date_of_reg(rs.getDate(11));
						um.setUser_master_usertype(rs.getInt(12));
						um.setUser_master_remarks(rs.getString(13));
						um.setUser_master_flag(rs.getString(14));
						umList.add(um);
					}
					
					
					
					
					ArrayList<UserType> utList = new ArrayList<UserType>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT usertype_master_id, usertype_master_usertype FROM usertype_master where usertype_master_flag='Y'");
					HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>();
					while (rs.next())
					{
						userTypeHashMap.put(rs.getInt(1), rs.getString(2));
					}
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("userMaster.jsp");
					request.setAttribute("umList",umList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("userTypeHashMap", userTypeHashMap);
					
					//request.setAttribute("action", action);
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				
				
				/*
				 * Default page: When you click from the Menu. 
				 */
				
				// Get UserTypes to populate the Combo Box
				
				ArrayList<UserType> utList = new ArrayList<UserType>();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT usertype_master_id, usertype_master_usertype FROM usertype_master where usertype_master_flag='Y'");
				
				HashMap<Integer, String> userTypeHashMap = new HashMap<Integer, String>(); 
				
				while (rs.next())
				{
					userTypeHashMap.put(rs.getInt(1), rs.getString(2));
				}
				
				
				
				// Get User Master record to Display (Default)
				
				ArrayList<UserMaster> umList = new ArrayList<UserMaster>();
				UserMaster um = new UserMaster();
				s = c.createStatement();
				rs = s.executeQuery("SELECT * FROM user_master");
				
				while (rs.next())
				{
					um = new UserMaster();
					um.setUser_master_id(rs.getInt(1));
					um.setUser_master_login(rs.getString(2));
					um.setUser_master_password(rs.getString(3));
					um.setUser_master_name(rs.getString(4));
					um.setUser_master_dob(rs.getDate(5));
					um.setUser_master_sex(rs.getString(6));
					um.setUser_master_addr1(rs.getString(7));
					um.setUser_master_addr2(rs.getString(8));
					um.setUser_master_pincode(rs.getString(9));
					um.setUser_master_email_id(rs.getString(10));
					um.setUser_master_date_of_reg(rs.getDate(11));
					um.setUser_master_usertype(rs.getInt(12));
					um.setUser_master_remarks(rs.getString(13));
					um.setUser_master_flag(rs.getString(14));
					
					umList.add(um);
				}
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("userMaster.jsp");
				request.setAttribute("umList",umList);
				request.setAttribute("masterType",masterType);
				request.setAttribute("userTypeHashMap", userTypeHashMap);
				
				dispatcher.forward(request, response);
				c.close();
				return;
			
				
			}
			
			
			
			else if (masterType.equalsIgnoreCase("StationMaster"))
			{
			
				
				if (action!=null && action.equalsIgnoreCase("Add"))
				{
					
// Get Parameter Values
					
					String stationCode=request.getParameter("stationCode");
				    String stationName=request.getParameter("stationName");
					String stationRemarks=request.getParameter("stationRemark");
				    String stationFlag=request.getParameter("stationFlag");

			
					
					ArrayList smList = new ArrayList<StationMaster>();
					Statement s = c.createStatement();
					
					
					
					// Use insert into query to add record into the table
					
					int i=s.executeUpdate("insert into station_master(station_master_code, station_master_name, station_master_remarks, station_master_flag) values ('"+stationCode+"','"+stationName+"','"+ stationRemarks+"','"+stationFlag+"')");

					
                   
					
					// default code 
					
					String query="select * from station_master where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					StationMaster sm = new StationMaster();
					
					while (rs.next())
					{
						sm = new StationMaster();
						sm.setStationMasterId(rs.getInt(1));
						sm.setStationMasterCode(rs.getString(2));
						sm.setStationMasterName(rs.getString(3));
						sm.setStationMasterRemarks(rs.getString(4));
						sm.setStationMasterFlag(rs.getString(5));
						smList.add(sm);
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("StationMaster.jsp");
					request.setAttribute("smList",smList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("stationCode",  stationCode);
					request.setAttribute("stationName", stationName);
					request.setAttribute("stationRemark", stationRemarks);
					request.setAttribute("stationFlag",stationFlag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				
				
				
				else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
				{
					String stationCode=request.getParameter("stationCode");
				    String stationName=request.getParameter("stationName");
					String stationRemarks=request.getParameter("stationRemark");
				    String stationFlag=request.getParameter("stationFlag");

					
					String query="select * from station_master where 1 = 1 ";
					
					if (stationCode!=null && stationCode!="")
					{
						query = query + " and station_master_code like '%" +stationCode + "%' ";
					}
					
					if (stationName!=null && stationName!="")
					{
						query = query + " and station_master_name like '%" +stationName + "%' ";
					}
					
					if (stationRemarks !=null && stationRemarks !="")
					{
						query = query + " and station_master_remarks like '%" + stationRemarks + "%' ";
					}
					
					if (stationFlag !=null && stationFlag!="")
					{
						query = query + " and station_master_flag like '%" + stationFlag + "%' ";
					}
					

					ArrayList smList = new ArrayList<StationMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery(query);
					StationMaster sm = new StationMaster();
					while (rs.next())
					{
						sm = new StationMaster();
						sm.setStationMasterId(rs.getInt(1));
						sm.setStationMasterCode(rs.getString(2));
						sm.setStationMasterName(rs.getString(3));
						sm.setStationMasterRemarks(rs.getString(4));
						sm.setStationMasterFlag(rs.getString(5));
						smList.add(sm);
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("StationMaster.jsp");
					request.setAttribute("smList",smList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("stationCode",  stationCode);
					request.setAttribute("stationName", stationName);
					request.setAttribute("stationRemark", stationRemarks);
					request.setAttribute("stationFlag",stationFlag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				
			
				
			
				
				else if (action!=null && action.equalsIgnoreCase("Update"))
				{
					

					
					// Get Parameter Values
					int stationId = Integer.parseInt(request.getParameter("stationId"));
					String stationCode=request.getParameter("stationCode");
				    String stationName=request.getParameter("stationName");
					String stationRemarks=request.getParameter("stationRemark");
				    String stationFlag=request.getParameter("stationFlag");
					
				    ArrayList smList = new ArrayList<StationMaster>();
					Statement s = c.createStatement();
					
					
					// Use update query to update a record 
					
					int i=s.executeUpdate("update station_master set station_master_code='"+stationCode + "', station_master_name='" + stationName  + "', station_master_remarks ='" + stationRemarks  + "',station_master_flag='"+ stationFlag +"' where station_master_id= " + stationId);
					
					// default code 
					
					String query="select * from station_master  where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					StationMaster sm = new StationMaster();
					while (rs.next())
					{
						sm = new StationMaster();
						sm.setStationMasterId(rs.getInt(1));
						sm.setStationMasterCode(rs.getString(2));
						sm.setStationMasterName(rs.getString(3));
						sm.setStationMasterRemarks(rs.getString(4));
						sm.setStationMasterFlag(rs.getString(5));
						smList.add(sm);
					}
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("StationMaster.jsp");
					request.setAttribute("smList",smList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("stationCode",  stationCode);
					request.setAttribute("stationName", stationName);
					request.setAttribute("stationRemark", stationRemarks);
					request.setAttribute("stationFlag",stationFlag);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
					
					
				
				}
			
			
				
				
			
				else if (action!=null && action.equalsIgnoreCase("Delete"))
				{
					

					// Get Parameter Values
					int stationId = Integer.parseInt(request.getParameter("stationId"));
					
					
					 ArrayList smList = new ArrayList<StationMaster>();
						Statement s = c.createStatement();
					
					
					// Use delete  query to delete a record 
					
					int i=s.executeUpdate("delete from station_master where station_master_id = "+ stationId);
					
					// default code 
					
					String query="select * from station_master  where 1 = 1 ";
					
					
					ResultSet rs = s.executeQuery(query);
					StationMaster sm = new StationMaster();
					while (rs.next())
					{
						sm = new StationMaster();
						sm.setStationMasterId(rs.getInt(1));
						sm.setStationMasterCode(rs.getString(2));
						sm.setStationMasterName(rs.getString(3));
						sm.setStationMasterRemarks(rs.getString(4));
						sm.setStationMasterFlag(rs.getString(5));
						smList.add(sm);;
					}
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("StationMaster.jsp");
					request.setAttribute("smList",smList);
					request.setAttribute("masterType",masterType);
					//request.setAttribute("action", action);
					dispatcher.forward(request, response);
					c.close();
					return;
				
					
			
					
				}
				
			
				
				 ArrayList smList = new ArrayList<StationMaster>();
				 StationMaster sm = new StationMaster();
					Statement s = c.createStatement();
			
					ResultSet rs = s.executeQuery("SELECT * FROM station_master ");
					
					while (rs.next())
					{
						sm = new StationMaster();
						sm.setStationMasterId(rs.getInt(1));
						sm.setStationMasterCode(rs.getString(2));
						sm.setStationMasterName(rs.getString(3));
						sm.setStationMasterRemarks(rs.getString(4));
						sm.setStationMasterFlag(rs.getString(5));
						smList.add(sm);;
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("StationMaster.jsp");
					request.setAttribute("smList",smList);
					request.setAttribute("masterType",masterType);
					//request.setAttribute("action", action);
					dispatcher.forward(request, response);
					c.close();
					return;		
					
					
				
			}
			
			
			else if (masterType.equalsIgnoreCase("TrainMaster"))
			{
						if (action!=null && action.equalsIgnoreCase("Add"))
						{
							try
							{
									/*
									 * Code for Adding New Record 
									 */
									
									// Get Parameter Values
									
									//	int userMasterId = Integer.parseInt(request.getParameter("userMasterId"));
									
									String trainMasterNo= request.getParameter("trainMasterNo");
									String trainMasterName= request.getParameter("trainMasterName");
									int trainMasterSourceStation= Integer.parseInt(request.getParameter("trainMasterSourceStation"));
									int trainMasterDestinationStation=Integer.parseInt( request.getParameter("trainMasterDestinationStation"));
									double trainMasterDistance= Double.parseDouble(request.getParameter("trainMasterDistance"));
									String trainMasterDepartureTime= request.getParameter("trainMasterDepartureTime");
									String  trainMasterArrivalTime= request.getParameter("trainMasterArrivalTime");
									String trainMasterTravellingTime = request.getParameter("trainMasterTravellingTime");
									String  trainMasterRemarks= request.getParameter("trainMasterRemarks");
									String trainMasterFlag = request.getParameter("trainMasterFlag");
				
					
									
									ArrayList tmList = new ArrayList<TrainMaster>();
									Statement s = c.createStatement();
									String error = "";
									
									
									
									// Use insert into query to add record into the table
									// Using Try Catch as server side validation to avoid duplicate entry (login name) in the User Master 
									
								
									int i=s.executeUpdate("insert into train_master(train_master_no,train_master_name, train_master_source_station, train_master_destination_station, train_master_distance, train_master_departure_time, train_master_arrival_time,train_master_travelling_time,train_master_remarks,train_master_flag) values ('"+trainMasterNo+"','"+trainMasterName+"','"+trainMasterSourceStation+"','"+trainMasterDestinationStation+"','"+trainMasterDistance+"','"+trainMasterDepartureTime+"','"+trainMasterArrivalTime+"','"+trainMasterTravellingTime+"','"+trainMasterRemarks+"','"+trainMasterFlag+"')");
									
									
									
									// default code 
									
									ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
									
									s = c.createStatement();
									ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
									HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
									
									
								 
									
									while (rs.next())
									{
										trainstationHashMap.put(rs.getInt(1), rs.getString(2));
										
									}
									
									
									
									String query="select * from train_master where 1 = 1 ";
									
									s = c.createStatement();
									rs = s.executeQuery(query);
									TrainMaster tm = new TrainMaster();
									
									
									while (rs.next())
									{
										tm = new TrainMaster();
										tm.setTrain_master_id(rs.getInt(1));
										tm.setTrain_master_no(rs.getString(2));
										tm.setTrain_master_name(rs.getString(3));
										tm.setTrain_master_source_station(rs.getInt(4));
										tm.setTrain_master_destination_station(rs.getInt(5));
										tm.setTrain_master_distance(rs.getDouble(6));
										tm.setTrain_master_departure_time(rs.getString(7));
										tm.setTrain_master_arrival_time(rs.getString(8));
										tm.setTrain_master_travelling_time(rs.getString(9));
										tm.setTrain_master_remarks(rs.getString(10));
										tm.setTrain_master_flag(rs.getString(11));
										
										tmList.add(tm);
									}
									RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
									request.setAttribute("tmList",tmList);
									request.setAttribute("masterType",masterType);
									request.setAttribute("trainstationHashMap", trainstationHashMap);
									
									request.setAttribute("error", error);
									//request.setAttribute("action", action);
									
									dispatcher.forward(request, response);
									c.close();
									return;
							} // end of try  block 
							catch(Exception e)
							{
								e.printStackTrace();
								// default code 
								
								ArrayList tmList = new ArrayList<TrainMaster>();
								ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
								
								Statement s = c.createStatement();
								ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
								HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
								
								while (rs.next())
								{
									trainstationHashMap.put(rs.getInt(1), rs.getString(2));
									
								}
								
								String query="select * from train_master where 1 = 1 ";
								
								s = c.createStatement();
								rs = s.executeQuery(query);
								TrainMaster tm = new TrainMaster();
								
								
								while (rs.next())
								{
									tm = new TrainMaster();
									tm.setTrain_master_id(rs.getInt(1));
									tm.setTrain_master_no(rs.getString(2));
									tm.setTrain_master_name(rs.getString(3));
									tm.setTrain_master_source_station(rs.getInt(4));
									tm.setTrain_master_destination_station(rs.getInt(5));
									tm.setTrain_master_distance(rs.getDouble(6));
									tm.setTrain_master_departure_time(rs.getString(7));
									tm.setTrain_master_arrival_time(rs.getString(8));
									tm.setTrain_master_travelling_time(rs.getString(9));
									tm.setTrain_master_remarks(rs.getString(10));
									tm.setTrain_master_flag(rs.getString(11));
									
									tmList.add(tm);
								}
								RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
								request.setAttribute("tmList",tmList);
								request.setAttribute("masterType",masterType);
								request.setAttribute("trainstationHashMap", trainstationHashMap);
								request.setAttribute("error", e.getMessage());
								//request.setAttribute("action", action);
								
								dispatcher.forward(request, response);
								c.close();
								return;
							}
						} // end of if (action!=null && action.equalsIgnoreCase("Add"))
			
			
				
				else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
				{
					

					String trainMasterNo= request.getParameter("trainMasterNo");
					String trainMasterName= request.getParameter("trainMasterName");
					int trainMasterSourceStation= Integer.parseInt(request.getParameter("trainMasterSourceStation"));
					int trainMasterDestinationStation=Integer.parseInt( request.getParameter("trainMasterDestinationStation"));
					double trainMasterDistance = 0;
					try
					{
						trainMasterDistance= Double.parseDouble(request.getParameter("trainMasterDistance").toString());
					}
					catch(Exception e)
					{
						trainMasterDistance=0;
					}
					String trainMasterDepartureTime= request.getParameter("trainMasterDepartureTime");
					String  trainMasterArrivalTime= request.getParameter("trainMasterArrivalTime");
					String trainMasterTravellingTime = request.getParameter("trainMasterTravellingTime");
					String  trainMasterRemarks= request.getParameter("trainMasterRemarks");
					String trainMasterFlag = request.getParameter("trainMasterFlag");
					
					String query="select * from train_master where 1 = 1 ";
					
					if (trainMasterNo!=null && trainMasterNo!="")
					{
						query = query + " and train_master_no like '%"+trainMasterNo+"%'";
					}
					
					if (trainMasterName!=null && trainMasterName!="")
					{
						query = query + " and train_master_name like '%" + trainMasterName + "%'";
					}
					
					if (trainMasterSourceStation!=0)
					{
						query = query + " and train_master_source_station = " +trainMasterSourceStation; 
					}
					
					if (trainMasterDestinationStation!=0)
					{
						query = query + " and train_master_destination_station = " + trainMasterDestinationStation;
					}
					
					if (trainMasterDistance!=0)
					{
						query = query + " and train_master_distance  =  " + trainMasterDistance ;
					}
					
					
					if (trainMasterDepartureTime!=null && trainMasterDepartureTime!="")
					{
						query = query + " and train_master_departure_time like '" + trainMasterDepartureTime + "%'";
					}
					
					if (trainMasterArrivalTime!=null && trainMasterArrivalTime!="")
					{
						query = query + " and train_master_arrival_time like '" + trainMasterArrivalTime + "%'";
					}
					
					
					if (trainMasterTravellingTime!=null && trainMasterTravellingTime!="")
					{
						query = query + " and train_master_travelling_time like '" +trainMasterTravellingTime + "%'";
					}
					if (trainMasterRemarks!=null && trainMasterRemarks!="")
					{
						query = query + " and train_master_remarks like '%" +trainMasterRemarks + "%'";
					}
					if (trainMasterFlag!=null && trainMasterFlag!="")
					{
						query = query + " and train_master_flag like '%" + trainMasterFlag + "%'";
					}
					
					
					ArrayList tmList = new ArrayList<TrainMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery(query);
					TrainMaster tm = new TrainMaster();
					

					while (rs.next())
					{
						tm = new TrainMaster();
						tm.setTrain_master_id(rs.getInt(1));
						tm.setTrain_master_no(rs.getString(2));
						tm.setTrain_master_name(rs.getString(3));
						tm.setTrain_master_source_station(rs.getInt(4));
						tm.setTrain_master_destination_station(rs.getInt(5));
						tm.setTrain_master_distance(rs.getDouble(6));
						tm.setTrain_master_departure_time(rs.getString(7));
						tm.setTrain_master_arrival_time(rs.getString(8));
						tm.setTrain_master_travelling_time(rs.getString(9));
						tm.setTrain_master_remarks(rs.getString(10));
						tm.setTrain_master_flag(rs.getString(11));
						
						tmList.add(tm);
					}
					
					
					
					
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
                    HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
					
					while (rs.next())
					{
						trainstationHashMap.put(rs.getInt(1), rs.getString(2));
						
					}
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
					request.setAttribute("tmList",tmList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("trainstationHashMap",trainstationHashMap);

					//Set input values for the placeholder
					request.setAttribute("trainMasterNo",trainMasterNo);
					request.setAttribute("trainMasterName",trainMasterName);
					request.setAttribute("trainMasterSourceStation",trainMasterSourceStation);
					request.setAttribute("trainMasterDestinationStation",trainMasterDestinationStation);
					request.setAttribute("trainMasterDistance",trainMasterDistance);
					request.setAttribute("trainMasterDepartureTime",trainMasterDepartureTime);
					request.setAttribute("trainMasterArrivalTime",trainMasterArrivalTime);
					request.setAttribute("trainMasterTravellingTime",trainMasterTravellingTime);
					request.setAttribute("trainMasterRemarks",trainMasterRemarks);
					request.setAttribute("trainMasterFlag",trainMasterFlag);
										
					
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
									
					
				}
				
				
				else if (action!=null && action.equalsIgnoreCase("Update"))
				{


					/*
					 * Code for Update a record 
					 */
					

					
					// Get Parameter Values
					int trainMasterId = Integer.parseInt(request.getParameter("trainMasterId").toString());
					String trainMasterNo= request.getParameter("trainMasterNo");
					String trainMasterName= request.getParameter("trainMasterName");
					int trainMasterSourceStation= Integer.parseInt(request.getParameter("trainMasterSourceStation"));
					int trainMasterDestinationStation=Integer.parseInt( request.getParameter("trainMasterDestinationStation"));
					double trainMasterDistance = 0;
					try
					{
						trainMasterDistance= Double.parseDouble(request.getParameter("trainMasterDistance").toString());
					}
					catch(Exception e)
					{
						trainMasterDistance=0;
					}
					String trainMasterDepartureTime= request.getParameter("trainMasterDepartureTime");
					String  trainMasterArrivalTime= request.getParameter("trainMasterArrivalTime");
					String trainMasterTravellingTime = request.getParameter("trainMasterTravellingTime");
					String  trainMasterRemarks= request.getParameter("trainMasterRemarks");
					String trainMasterFlag = request.getParameter("trainMasterFlag");
					
					
					Statement s = c.createStatement();
					
					// Use update query to update a record

					int i=s.executeUpdate("update train_master set train_master_no= " + trainMasterNo + ", train_master_name= '" + trainMasterName + "', train_master_source_station= " + trainMasterSourceStation + ", train_master_destination_station = " + trainMasterDestinationStation +
							", train_master_distance = " + trainMasterDistance + ", train_master_departure_time = '" + trainMasterDepartureTime + "' , train_master_arrival_time ='" + trainMasterArrivalTime +  
							"', train_master_travelling_time = '" + trainMasterTravellingTime + "', train_master_remarks = '" + trainMasterRemarks +
							"', train_master_flag ='" + trainMasterFlag +"' where train_master_id = " + trainMasterId);
				
					
					
					// default code 
					
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
					
					s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");

					HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
					
					while (rs.next())
					{
						trainstationHashMap.put(rs.getInt(1), rs.getString(2));
						
					}
					
					
					String query="select * from train_master where 1 = 1 ";
					
					s = c.createStatement();
					rs = s.executeQuery(query);
					TrainMaster tm = new TrainMaster();
					
					
					while (rs.next())
					{
						tm = new TrainMaster();
						tm.setTrain_master_id(rs.getInt(1));
						tm.setTrain_master_no(rs.getString(2));
						tm.setTrain_master_name(rs.getString(3));
						tm.setTrain_master_source_station(rs.getInt(4));
						tm.setTrain_master_destination_station(rs.getInt(5));
						tm.setTrain_master_distance(rs.getDouble(6));
						tm.setTrain_master_departure_time(rs.getString(7));
						tm.setTrain_master_arrival_time(rs.getString(8));
						tm.setTrain_master_travelling_time(rs.getString(9));
						tm.setTrain_master_remarks(rs.getString(10));
						tm.setTrain_master_flag(rs.getString(11));
						
						tmList.add(tm);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
					request.setAttribute("tmList",tmList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("trainstationHashMap", trainstationHashMap);
					
					//request.setAttribute("error", error);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				
					
				}
				else if (action!=null && action.equalsIgnoreCase("Delete"))
				{
					
					/*
					 * Code for Delete a record 
					 */
					
					// Get Parameter Values
					int trainMasterId = Integer.parseInt(request.getParameter("trainMasterId").toString());
					
					
					Statement s = c.createStatement();
					
					
					// Use delete  query to delete a record 
					
					int i=s.executeUpdate("delete from train_master where train_master_id = " + trainMasterId);
					
					
					// default code 
					
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
					
					s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");

					HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
					
					while (rs.next())
					{
						trainstationHashMap.put(rs.getInt(1), rs.getString(2));
						
					}
					
					
					String query="select * from train_master where 1 = 1 ";
					
					s = c.createStatement();
					rs = s.executeQuery(query);
					TrainMaster tm = new TrainMaster();
					
					
					while (rs.next())
					{
						tm = new TrainMaster();
						tm.setTrain_master_id(rs.getInt(1));
						tm.setTrain_master_no(rs.getString(2));
						tm.setTrain_master_name(rs.getString(3));
						tm.setTrain_master_source_station(rs.getInt(4));
						tm.setTrain_master_destination_station(rs.getInt(5));
						tm.setTrain_master_distance(rs.getDouble(6));
						tm.setTrain_master_departure_time(rs.getString(7));
						tm.setTrain_master_arrival_time(rs.getString(8));
						tm.setTrain_master_travelling_time(rs.getString(9));
						tm.setTrain_master_remarks(rs.getString(10));
						tm.setTrain_master_flag(rs.getString(11));
						
						tmList.add(tm);
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
					request.setAttribute("tmList",tmList);
					request.setAttribute("masterType",masterType);
					request.setAttribute("trainstationHashMap", trainstationHashMap);
					
					//request.setAttribute("error", error);
					//request.setAttribute("action", action);
					
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				
				
				/*
				 * Default page: When you click from the Menu. 
				 */

				ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
				Statement s = c.createStatement();
				ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
				
				HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
								 
				
				while (rs.next())
				{
					trainstationHashMap.put(rs.getInt(1), rs.getString(2));
					
				}
				
				
				
				// Get User Master record to Display (Default)
				
				TrainMaster tm = new TrainMaster();
				s = c.createStatement();
				rs = s.executeQuery("SELECT * FROM train_master");
				
				while (rs.next())
				{
					tm = new TrainMaster();
					tm.setTrain_master_id(rs.getInt(1));
					tm.setTrain_master_no(rs.getString(2));
					tm.setTrain_master_name(rs.getString(3));
					tm.setTrain_master_source_station(rs.getInt(4));
					tm.setTrain_master_destination_station(rs.getInt(5));
					tm.setTrain_master_distance(rs.getDouble(6));
					tm.setTrain_master_departure_time(rs.getString(7));
					tm.setTrain_master_arrival_time(rs.getString(8));
					tm.setTrain_master_travelling_time(rs.getString(9));
					tm.setTrain_master_remarks(rs.getString(10));
					tm.setTrain_master_flag(rs.getString(11));
					
					
					tmList.add(tm);
				}
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("trainMaster.jsp");
				request.setAttribute("tmList",tmList);
				request.setAttribute("masterType",masterType);
				request.setAttribute("trainstationHashMap", trainstationHashMap);
				
				
				dispatcher.forward(request, response);
				c.close();
				return;
				
			}
			
			
			
			else if (masterType.equalsIgnoreCase("TrainFare"))
			{

				if (action!=null && action.equalsIgnoreCase("Add"))
				{
					try
					{
							/*
							 * Code for Adding records into Train_fare & seat_availability tables 
							 */
							
							// Get Parameter Values
							
							//int trainFareId = Integer.parseInt(request.getParameter("trainFareId"));
							int trainMasterId = Integer.parseInt(request.getParameter("trainMasterId"));
							
							double trainFareSleeper = Double.parseDouble(request.getParameter("trainFareSleeper"));
							double trainFareSleeperTotalseats = Double.parseDouble(request.getParameter("trainFareSleeperTotalseats"));
							
							double trainFareAc1 = Double.parseDouble(request.getParameter("trainFareAc1"));
							double trainFareAc1Totalseats = Double.parseDouble(request.getParameter("trainFareAc1Totalseats"));
							
							double trainFareAc2 = Double.parseDouble(request.getParameter("trainFareAc2"));
							double trainFareAc2Totalseats = Double.parseDouble(request.getParameter("trainFareAc2Totalseats"));
							
							double trainFareAc3 = Double.parseDouble(request.getParameter("trainFareAc3"));
							double trainFareAc3Totalseats = Double.parseDouble(request.getParameter("trainFareAc3Totalseats"));
							
							String  trainFareRemarks = request.getParameter("trainFareRemarks").toString();
							String trainFareFlag = request.getParameter("trainFareFlag").toString();
		
			
							
							
							Statement s = c.createStatement();
							String error = "";
							
							
							try
							{
								c.setAutoCommit(false);
								
							// Use insert into query to add record into the train_fare table
							
  					
							int i=s.executeUpdate("insert into train_fare(train_master_id,train_fare_sleeper,train_fare_sleeper_totalseats, train_fare_ac1, train_fare_ac1_totalseats,train_fare_ac2, train_fare_ac2_totalseats,train_fare_ac3, train_fare_ac3_totalseats,train_fare_remarks,train_fare_flag) values ("+trainMasterId+","+trainFareSleeper+","+trainFareSleeperTotalseats+","+trainFareAc1 +","+trainFareAc1Totalseats +","+trainFareAc2 +","+trainFareAc2Totalseats +","+trainFareAc3 +","+trainFareAc3Totalseats+",'"+trainFareRemarks +"','" + trainFareFlag + "')");
							
							
							/*
							 * 
							 * Code to add data into seat_availability begins here
							 */
							
							// Array using mod logic to allot berth type automatically
							
							String[] SLAC3 = new String[] {"SU","LB","MB","UB","LB","MB","UB","SL"};
							String[] AC2 = new String[] {"SU","LB","UB","LB","UB","SL"};
							String[] AC1 = new String[] {"UB","LB"};
							
							/*
							 *Code to add record into seat_availability for Sleeper Classes begin Here 
							 *  
							 */
							
							
							int sl_coaches = (int) trainFareSleeperTotalseats / 72;
							
							String coachName = new String();
							String coach = "SL";
							String berthType = new String();
							
							s = c.createStatement();
							int r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_remarks, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', 'XX',0,'XX','CAN','Y')");
							
							for (int x=1;x<=sl_coaches;x++)
							{
								 
								coachName = "S".concat(Integer.toString(x));
								
								s = c.createStatement();
								for(int y=1;y<=72;y++)
								{
									s = c.createStatement();
									berthType = SLAC3[y%8];
									r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', '" +  coachName +"'," + y + ",'"+ berthType +"','Y')");
								}
								
							}
							
							/*
							 *Code to add record into seat_availability for AC3 Class begin Here 
							 *  
							 */
							

							int AC3_coaches = (int) trainFareSleeperTotalseats / 64;
							
							
							 coach = "AC3";
							
								s = c.createStatement();
								r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_remarks, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', 'XX',0,'XX','CAN','Y')");
						
								
							for (int x=1;x<=sl_coaches;x++)
							{
								 
								coachName = "B".concat(Integer.toString(x));
								
								s = c.createStatement();
								for(int y=1;y<=64;y++)
								{
									s = c.createStatement();
									berthType = SLAC3[y%8];
									r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', '" +  coachName +"'," + y + ",'"+ berthType +"','Y')");
								}
								
							}
							
							/*
							 *Code to add record into seat_availability for AC2 Class begin Here 
							 *  
							 */
							

							int AC2_coaches = (int) trainFareSleeperTotalseats / 54;
							
							
							 coach = "AC2";
							
								s = c.createStatement();
								r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_remarks, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', 'XX',0,'XX','CAN','Y')");
						
								
							for (int x=1;x<=sl_coaches;x++)
							{
								 
								coachName = "A".concat(Integer.toString(x));
								
								s = c.createStatement();
								for(int y=1;y<=54;y++)
								{
									s = c.createStatement();
									berthType = AC2[y%6];
									r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', '" +  coachName +"'," + y + ",'"+ berthType +"','Y')");
								}
								
							}
							
							/*
							 *Code to add record into seat_availability for AC1 Class begin Here 
							 *  
							 */
							

							int AC1_coaches = (int) trainFareSleeperTotalseats / 22;
							
							
							 coach = "AC1";
							
								s = c.createStatement();
								r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_remarks, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', 'XX',0,'XX','CAN','Y')");
						
								
							for (int x=1;x<=sl_coaches;x++)
							{
								 
								coachName = "H".concat(Integer.toString(x));
								
								s = c.createStatement();
								for(int y=1;y<=22;y++)
								{
									s = c.createStatement();
									berthType = AC2[y%2];
									r = s.executeUpdate("insert into seat_availability(seat_availability_train_id,seat_availability_class,seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype, seat_availability_flag) values ("+trainMasterId+",'"+ coach +"', '" +  coachName +"'," + y + ",'"+ berthType +"','Y')");
								}
								
							}
							
							c.commit(); // if no error occurs till this line, then commit 
							}
							catch(Exception e)
							{
								e.printStackTrace();
								c.rollback();
								error = "Records could not be added due to " + e.getMessage();
							}
							
							
							/*
							 * 
							 * Code to add data into seat_availability ends here
							 */

							// default code 
							ArrayList<TrainFare> tfList = new ArrayList<TrainFare>();
							s = c.createStatement();
							ResultSet rs = s.executeQuery("SELECT train_master_id, train_master_name, train_master_no FROM train_master where train_master_flag='Y'");
							
							HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
											 
							
							while (rs.next())
							{
								trainMasterHashMap.put(rs.getInt(1), rs.getString(2).concat(" [").concat(rs.getString(3)).concat(" ]"));
							}
							
							
							
							// Get Train Fare record to Display (Default)
							
							TrainFare tf = new TrainFare();
							s = c.createStatement();
							rs = s.executeQuery("SELECT * FROM train_fare");
							
							while (rs.next())
							{
								tf = new TrainFare();
								tf.setTrain_fare_id(rs.getInt(1));
								tf.setTrain_master_id(rs.getInt(2));
								tf.setTrain_fare_sleeper(rs.getDouble(3));
								tf.setTrain_fare_sleeper_totalseats(rs.getDouble(4));
								tf.setTrain_fare_ac1(rs.getDouble(5));
								tf.setTrain_fare_ac1_totalseats(rs.getDouble(6));
								tf.setTrain_fare_ac2(rs.getDouble(7));
								tf.setTrain_fare_ac2_totalseats(rs.getDouble(8));
								tf.setTrain_fare_ac3(rs.getDouble(9));
								tf.setTrain_fare_ac3_totalseats(rs.getDouble(10));
								tf.setTrain_fare_remarks(rs.getString(11));
								tf.setTrain_fare_flag(rs.getString(12));
								tfList.add(tf);
							}
							
							
							RequestDispatcher dispatcher = request.getRequestDispatcher("trainFare.jsp");
							request.setAttribute("tfList",tfList);
							request.setAttribute("masterType",masterType);
							request.setAttribute("trainMasterHashMap", trainMasterHashMap);
							request.setAttribute("error", error);
							
							
							dispatcher.forward(request, response);
							c.close();
							return;
							
					} // end of try  block 
					catch(Exception e)
					{
						
						e.printStackTrace();
						// default code 
						ArrayList<TrainFare> tfList = new ArrayList<TrainFare>();
						Statement s = c.createStatement();
						ResultSet rs = s.executeQuery("SELECT train_master_id, train_master_name, train_master_no FROM train_master where train_master_flag='Y'");
						
						HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
										 
						
						while (rs.next())
						{
							trainMasterHashMap.put(rs.getInt(1), rs.getString(2).concat(" [").concat(rs.getString(3)).concat(" ]"));
						}
						
						
						
						// Get Train Fare record to Display (Default)
						
						TrainFare tf = new TrainFare();
						s = c.createStatement();
						rs = s.executeQuery("SELECT * FROM train_fare");
						
						while (rs.next())
						{
							tf = new TrainFare();
							tf.setTrain_fare_id(rs.getInt(1));
							tf.setTrain_master_id(rs.getInt(2));
							tf.setTrain_fare_sleeper(rs.getDouble(3));
							tf.setTrain_fare_sleeper_totalseats(rs.getDouble(4));
							tf.setTrain_fare_ac1(rs.getDouble(5));
							tf.setTrain_fare_ac1_totalseats(rs.getDouble(6));
							tf.setTrain_fare_ac2(rs.getDouble(7));
							tf.setTrain_fare_ac2_totalseats(rs.getDouble(8));
							tf.setTrain_fare_ac3(rs.getDouble(9));
							tf.setTrain_fare_ac3_totalseats(rs.getDouble(10));
							tf.setTrain_fare_remarks(rs.getString(11));
							tf.setTrain_fare_flag(rs.getString(12));
							tfList.add(tf);
						}
						
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("trainFare.jsp");
						request.setAttribute("tfList",tfList);
						request.setAttribute("masterType",masterType);
						request.setAttribute("trainMasterHashMap", trainMasterHashMap);
						
						
						dispatcher.forward(request, response);
						c.close();
						return;
					}
				} // end of if (action!=null && action.equalsIgnoreCase("Add"))
	
	
		
		else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
		{
			


			/*
			 * Code for Adding records into Train_fare & seat_availability tables 
			 */
			
			// Get Parameter Values
			
			//int trainFareId = Integer.parseInt(request.getParameter("trainFareId"));
			int trainMasterId = 0;
			double trainFareSleeper =0;
			double trainFareSleeperTotalseats = 0;
			double trainFareAc1 = 0;
			double trainFareAc1Totalseats =0;
			double trainFareAc2 =0;
			double trainFareAc2Totalseats = 0;
			double trainFareAc3 =0;
			double trainFareAc3Totalseats = 0;
			
			try
			{
			trainMasterId = Integer.parseInt(request.getParameter("trainMasterId"));
			}
			catch(Exception e)
			{
				trainMasterId = 0;
			}
			
			try
			{
			trainFareSleeper = Double.parseDouble(request.getParameter("trainFareSleeper"));
			}
			catch(Exception e)
			{
				trainFareSleeper = 0;
				e.printStackTrace();
			}
			

			try
			{
				trainFareSleeperTotalseats = Double.parseDouble(request.getParameter("trainFareSleeperTotalseats"));
			}
			catch(Exception e)
			{
				trainFareSleeperTotalseats = 0;
				e.printStackTrace();
			}
			
			try
			{
				trainFareAc1 = Double.parseDouble(request.getParameter("trainFareAc1"));
			}
			catch(Exception e)
			{
				trainFareAc1 = 0;
				e.printStackTrace();
			}

			try
			{
				trainFareAc1Totalseats = Double.parseDouble(request.getParameter("trainFareAc1Totalseats"));
			}
			catch(Exception e)
			{
				trainFareAc1Totalseats = 0;
				e.printStackTrace();
			}
			
			try
			{
				trainFareAc2 = Double.parseDouble(request.getParameter("trainFareAc2"));
			}
			catch(Exception e)
			{
				trainFareAc2 = 0;
				e.printStackTrace();
			}
			
			
			try
			{
				trainFareAc2Totalseats = Double.parseDouble(request.getParameter("trainFareAc2Totalseats"));
			}
			catch(Exception e)
			{
				trainFareAc2Totalseats = 0;
				e.printStackTrace();
			}
			
			try
			{
				trainFareAc3 = Double.parseDouble(request.getParameter("trainFareAc3"));
			}
			catch(Exception e)
			{
				trainFareAc3 = 0;
				e.printStackTrace();
			}
			
			
			try
			{
				trainFareAc3Totalseats = Double.parseDouble(request.getParameter("trainFareAc3Totalseats"));
			}
			catch(Exception e)
			{
				trainFareAc3Totalseats = 0;
				e.printStackTrace();
			}
			
			
			String  trainFareRemarks = request.getParameter("trainFareRemarks").toString();
			String trainFareFlag = request.getParameter("trainFareFlag").toString();


			String query="select * from train_fare where 1 = 1 ";
			
			if (trainMasterId!=0 )
			{
				query = query + " and train_master_id = "+trainMasterId;
			}
			
			if (trainFareSleeper!=0)
			{
				query = query + " and train_fare_sleeper  = "+ trainFareSleeper;
			}
			
			if (trainFareSleeperTotalseats!=0)
			{
				query = query + " and train_fare_sleeper_totalseats = " +trainFareSleeperTotalseats; 
			}
			
			if (trainFareAc1!=0)
			{
				query = query + " and train_fare_ac1 = " + trainFareAc1;
			}
			
			if (trainFareAc1Totalseats!=0)
			{
				query = query + " and train_fare_ac1_totalseats = " +trainFareAc1Totalseats; 
			}
			
			if (trainFareAc2!=0)
			{
				query = query + " and train_fare_ac2 = " + trainFareAc2;
			}
			
			if (trainFareAc2Totalseats!=0)
			{
				query = query + " and train_fare_ac2_totalseats = " +trainFareAc2Totalseats; 
			}
			
			if (trainFareAc3!=0)
			{
				query = query + " and train_fare_ac3 = " + trainFareAc3;
			}
			
			if (trainFareAc3Totalseats!=0)
			{
				query = query + " and train_fare_ac3_totalseats = " +trainFareAc3Totalseats; 
			}
			
			
			if (trainFareRemarks!=null && trainFareRemarks!="")
			{
				query = query + " and train_fare_remarks like '%" +trainFareRemarks + "%'";
			}
			if (trainFareFlag!=null && trainFareFlag!="")
			{
				query = query + " and train_fare_flag like '%" + trainFareFlag + "%'";
			}
			
			ArrayList tfList = new ArrayList<TrainFare>();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);
			TrainFare tf = new TrainFare();
           
			while (rs.next())
			{
				tf = new TrainFare();
				tf.setTrain_fare_id(rs.getInt(1));
				tf.setTrain_master_id(rs.getInt(2));
				tf.setTrain_fare_sleeper(rs.getDouble(3));
				tf.setTrain_fare_sleeper_totalseats(rs.getDouble(4));
				tf.setTrain_fare_ac1(rs.getDouble(5));
				tf.setTrain_fare_ac1_totalseats(rs.getDouble(6));
				tf.setTrain_fare_ac2(rs.getDouble(7));
				tf.setTrain_fare_ac2_totalseats(rs.getDouble(8));
				tf.setTrain_fare_ac3(rs.getDouble(9));
				tf.setTrain_fare_ac3_totalseats(rs.getDouble(10));
				tf.setTrain_fare_remarks(rs.getString(11));
				tf.setTrain_fare_flag(rs.getString(12));
				tfList.add(tf);
			}
			
			
			
			ArrayList<TrainMaster> smList = new ArrayList<TrainMaster>();
			s = c.createStatement();
			rs = s.executeQuery("SELECT train_master_id, train_master_name, train_master_no FROM train_master where train_master_flag='Y'");
            HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
			
			while (rs.next())
			{
				trainMasterHashMap.put(rs.getInt(1), rs.getString(2).concat(" [").concat(rs.getString(3)).concat(" ]"));
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("trainFare.jsp");
			request.setAttribute("tfList",tfList);
			request.setAttribute("masterType",masterType);
			request.setAttribute("trainMasterHashMap", trainMasterHashMap);
			
			//Set input values for the placeholder
			request.setAttribute("trainMasterId",trainMasterId);
			request.setAttribute("trainFareSleeper",trainFareSleeper);
			request.setAttribute("trainFareSleeperTotalseats",trainFareSleeperTotalseats);
			request.setAttribute("trainFareAc1",trainFareAc1);
			request.setAttribute("trainFareAc1Totalseats",trainFareAc1Totalseats);
			request.setAttribute("trainFareAc2",trainFareAc2);
			request.setAttribute("trainFareAc2Totalseats",trainFareAc2Totalseats);
			request.setAttribute("trainFareAc3",trainFareAc3);
			request.setAttribute("trainFareAc3Totalseats",trainFareAc3Totalseats);
			request.setAttribute("trainFareRemarks",trainFareRemarks);
			request.setAttribute("trainFareFlag",trainFareFlag);
			
			//request.setAttribute("action", action);
			dispatcher.forward(request, response);
			c.close();
		}
				
			
				
		else if (action!=null && action.equalsIgnoreCase("Delete"))
		{
			
			
			/*
			 * Code for Delete a record  Starts here
			 * 
			 *  Step 1: Delete train_fare table based on train_fare_id (only 1 record will be deleted)
			 *  Step 2: Delete seat_availability table based on the train_master_id 
			 */
			
			// Get Parameter Values
			int trainFareId = Integer.parseInt(request.getParameter("trainFareId"));
			int trainMasterId = Integer.parseInt(request.getParameter("trainMasterId"));
			
			
			Statement s = c.createStatement();
			
			// Use delete  query to delete a record 
			
			int i=s.executeUpdate("delete from train_fare where train_fare_id = " +  trainFareId );
			int j=s.executeUpdate("delete from seat_availability where seat_availability_train_id = " + trainMasterId);
			

			/*
			 * Code for Delete a record Ends here  
			 */
			

			
			//default code 
			

			ArrayList<TrainFare> tfList = new ArrayList<TrainFare>();
			s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT train_master_id, train_master_name, train_master_no FROM train_master where train_master_flag='Y'");
			
			HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
							 
			
			while (rs.next())
			{
				trainMasterHashMap.put(rs.getInt(1), rs.getString(2).concat(" [").concat(rs.getString(3)).concat(" ]"));
			}
			
			
			
			// Get Train Fare record to Display (Default)
			
			TrainFare tf = new TrainFare();
			s = c.createStatement();
			rs = s.executeQuery("SELECT * FROM train_fare");
			
			while (rs.next())
			{
				tf = new TrainFare();
				tf.setTrain_fare_id(rs.getInt(1));
				tf.setTrain_master_id(rs.getInt(2));
				tf.setTrain_fare_sleeper(rs.getDouble(3));
				tf.setTrain_fare_sleeper_totalseats(rs.getDouble(4));
				tf.setTrain_fare_ac1(rs.getDouble(5));
				tf.setTrain_fare_ac1_totalseats(rs.getDouble(6));
				tf.setTrain_fare_ac2(rs.getDouble(7));
				tf.setTrain_fare_ac2_totalseats(rs.getDouble(8));
				tf.setTrain_fare_ac3(rs.getDouble(9));
				tf.setTrain_fare_ac3_totalseats(rs.getDouble(10));
				tf.setTrain_fare_remarks(rs.getString(11));
				tf.setTrain_fare_flag(rs.getString(12));
				tfList.add(tf);
			}
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("trainFare.jsp");
			request.setAttribute("tfList",tfList);
			request.setAttribute("masterType",masterType);
			request.setAttribute("trainMasterHashMap", trainMasterHashMap);
			
			
			dispatcher.forward(request, response);
			c.close();
			return;
			
		}
				
				
		
		
		/*
		 * Default page: When you click from the Menu. 
		 */

		ArrayList<TrainFare> tfList = new ArrayList<TrainFare>();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT train_master_id, train_master_name, train_master_no FROM train_master where train_master_flag='Y'");
		
		HashMap<Integer,String> trainMasterHashMap = new HashMap<Integer,String>();
						 
		
		while (rs.next())
		{
			trainMasterHashMap.put(rs.getInt(1), rs.getString(2).concat(" [").concat(rs.getString(3)).concat(" ]"));
		}
		
		
		
		// Get Train Fare record to Display (Default)
		
		TrainFare tf = new TrainFare();
		s = c.createStatement();
		rs = s.executeQuery("SELECT * FROM train_fare");
		
		while (rs.next())
		{
			tf = new TrainFare();
			tf.setTrain_fare_id(rs.getInt(1));
			tf.setTrain_master_id(rs.getInt(2));
			tf.setTrain_fare_sleeper(rs.getDouble(3));
			tf.setTrain_fare_sleeper_totalseats(rs.getDouble(4));
			tf.setTrain_fare_ac1(rs.getDouble(5));
			tf.setTrain_fare_ac1_totalseats(rs.getDouble(6));
			tf.setTrain_fare_ac2(rs.getDouble(7));
			tf.setTrain_fare_ac2_totalseats(rs.getDouble(8));
			tf.setTrain_fare_ac3(rs.getDouble(9));
			tf.setTrain_fare_ac3_totalseats(rs.getDouble(10));
			tf.setTrain_fare_remarks(rs.getString(11));
			tf.setTrain_fare_flag(rs.getString(12));
			tfList.add(tf);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("trainFare.jsp");
		request.setAttribute("tfList",tfList);
		request.setAttribute("masterType",masterType);
		request.setAttribute("trainMasterHashMap", trainMasterHashMap);
		
		
		dispatcher.forward(request, response);
		c.close();
		return;
		}
		
		else if (masterType.equalsIgnoreCase("booking"))
		{

			
			ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
            HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
			
			while (rs.next())
			{
				trainstationHashMap.put(rs.getInt(1), rs.getString(2));
			}
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("bookingQuery.jsp");
			request.setAttribute("trainstationHashMap",trainstationHashMap);

			dispatcher.forward(request, response);
			c.close();
			return;
							
		}
		else if (masterType.equalsIgnoreCase("bookingHistory"))
		{
				
				HashMap<String,Object> hm= null;    
				
				ArrayList<HashMap> hmList = new ArrayList<HashMap>(); 
				
				Statement s = c.createStatement();
				String sql = "SELECT a.booking_master_pnr, a.booking_master_date_of_journey, d.station_master_name src, e.station_master_name dest, a.booking_master_class, concat(c.train_master_name, ' [',c.train_master_no,']') tr_nm FROM booking_master a, booking_transaction b, train_master c, station_master d, station_master e "; 
				sql = sql +  "where a.booking_master_id = b.booking_master_id ";		
				sql = sql +  "and a.booking_master_train_id = c.train_master_id ";
				sql = sql +  "and c.train_master_source_station = d.station_master_id ";
				sql = sql +  "and c.train_master_destination_station = e.station_master_id ";
				sql = sql +  "and a.booking_master_user_id = " + Integer.parseInt(session.getAttribute("userMasterId").toString()) + " group by a.booking_master_pnr ";
						
				ResultSet rs = s.executeQuery(sql);
				
				while (rs.next())
				{
					hm = new HashMap<String,Object>();
					hm.put("pnr", rs.getInt(1));
					hm.put("doj", rs.getString(2));
					hm.put("src", rs.getString(3));
					hm.put("dest", rs.getString(4));
					hm.put("class", rs.getString(5));
					hm.put("train", rs.getString(6));
					hmList.add(hm);
				}
				
				request.setAttribute("hmList", hmList);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("bookingHistory.jsp");
				dispatcher.forward(request, response);
				c.close();
				return;
							
		}
		else if (masterType.equalsIgnoreCase("userMasterReport"))
		{
			   Map<String, Object> map = new HashMap<String, Object>();
			   map.put("conn", IrctcUtilities.getConnection());
			   String fileName = "userMaster";
			   IrctcUtilities.generateReport(fileName, map, (Connection)map.get("conn"), response, getServletContext());
			   return;
		}
		else if (masterType.equalsIgnoreCase("showTrainMasterReport"))
		{
			   Map<String, Object> map = new HashMap<String, Object>();
			   map.put("conn", IrctcUtilities.getConnection());
			   String fileName = "trainMaster";
			   IrctcUtilities.generateReport(fileName, map, (Connection)map.get("conn"), response, getServletContext());
			   return;
		}
			
			
			
			
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("failure.jsp");
		dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}