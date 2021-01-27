package com.irctc.controller;

import java.io.IOException;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.irctc.baseclasses.UserType;
import com.irctc.utilities.IrctcUtilities;




/**
 * Servlet implementation class LoginController
 */

public class LoginController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		
		
		// Connect to mysql and verify username password
		
		try {
			Connection c = IrctcUtilities.getConnection();
			PreparedStatement ps = c.prepareStatement("select user_master_login, user_master_password, user_master_id  from user_master where user_master_login  = ? and user_master_password =?");
			ps.setString(1, username);
			ps.setString(2, password);
 
			ResultSet rs = ps.executeQuery();
			
			ArrayList<String> al = new ArrayList<String>();
			ArrayList utList = new ArrayList<UserType>();
			UserType ut = null;
			
		while (rs.next())
		{
			session.setAttribute("loginId", username);
			session.setAttribute("userMasterId", rs.getInt(3));
			Statement s = c.createStatement();
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			request.setAttribute("utList",utList);
			dispatcher.forward(request, response);
			c.close();
			return;
		}
		c.close();
		RequestDispatcher dispatcher = request.getRequestDispatcher("failure.jsp");
		dispatcher.forward(request, response);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}