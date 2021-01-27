package com.irctc.controller;

import java.io.IOException;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

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
 * Servlet implementation class BookingController
 */

public class BookingController extends HttpServlet {
	
	protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String masterType = new String();
		String action = new String();
		HttpSession session = request.getSession();
		
		
		// Connect to mysql and verify username password
		
		try {
			Connection c = IrctcUtilities.getConnection();
			action =   request.getParameter("act").toString();
			//int trainMasterSourceStation = Integer.parseInt(request.getAttribute("trainMasterSourceStation").toString());
			//int trainMasterDestinationStation = Integer.parseInt(request.getAttribute("trainMasterDestinationStation").toString());
				
				if (action!=null && action.equalsIgnoreCase("BookingQuery"))
				{

					int trainMasterSourceStation = 0;
					int trainMasterDestinationStation=0;
					String trainMasterSourceStationName = "";
					String trainMasterDestinationStationName = "";
					String dateOfJourney = "";
					String tc = "";
					int trainId = 0;
					String trainName = new String();
					
					trainMasterSourceStation = Integer.parseInt(request.getParameter("trainMasterSourceStation").toString());
					trainMasterDestinationStation = Integer.parseInt(request.getParameter("trainMasterDestinationStation").toString());
					dateOfJourney = request.getParameter("dateOfJourney").toString();
					
					tc = request.getParameter("tc").toString();
					trainId = Integer.parseInt(request.getParameter("trainId").toString());
					
					
					//To get train Name
					ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT concat(train_master_name , ' [', train_master_no, ']') FROM train_master where train_master_id = " + trainId);
					while (rs.next())
					{
						trainName = rs.getString(1);
					}
					
					//To get Source Station Name
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT station_master_name FROM station_master where station_master_id =" + trainMasterSourceStation);
					while (rs.next())
					{
						trainMasterSourceStationName = rs.getString(1);
					}
					
					
					//To get Destination Station Name
					smList = new ArrayList<StationMaster>();
					s = c.createStatement();
					rs = s.executeQuery("SELECT station_master_name FROM station_master where station_master_id =" + trainMasterDestinationStation);
					while (rs.next())
					{
						trainMasterDestinationStationName = rs.getString(1);
					}
					
					
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
					
					request.setAttribute("trainMasterSourceStationName",trainMasterSourceStationName);
					request.setAttribute("trainMasterDestinationStationName",trainMasterDestinationStationName);
					request.setAttribute("dateOfJourney",dateOfJourney);
					request.setAttribute("tc", tc);
					request.setAttribute("trainName", trainName);
					request.setAttribute("trainId", trainId);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				
				
				}
				
				
				else if (action!=null && action.equalsIgnoreCase("Search")) //if you click Search on the UserTypeForm 
				{
					
					int trainMasterSourceStation = 0;
					int trainMasterDestinationStation=0;
					String dateOfJourney = "";
					String trainClass = "";
					
					
					trainMasterSourceStation = Integer.parseInt(request.getParameter("trainMasterSourceStation").toString());
					trainMasterDestinationStation = Integer.parseInt(request.getParameter("trainMasterDestinationStation").toString());
					dateOfJourney = request.getParameter("dateOfJourney").toString();
					//trainClass = request.getParameter("trainClass");
					//System.out.println(trainMasterSourceStation + " "+ trainMasterDestinationStation + "  " +  dateOfJourney + " " +   trainClass );
					
					
					ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM train_master where train_master_source_station = " + trainMasterSourceStation + " and train_master_destination_station =  " + trainMasterDestinationStation + " and train_master_flag='Y'");
					
					TrainMaster tm = null;	
					
					while (rs.next())
					{
						tm = new TrainMaster();
						tm.setTrain_master_id(rs.getInt(1));
						tm.setTrain_master_no(rs.getString(2));
						tm.setTrain_master_name(rs.getString(3));
						tm.setTrain_master_departure_time(rs.getString(7));
						tm.setTrain_master_arrival_time(rs.getString(8));
						tm.setTrain_master_distance(rs.getDouble(6));
						tm.setTrain_master_travelling_time(rs.getString(9));
						tmList.add(tm);
					}
					
					
					//get station master details 
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					
					s = c.createStatement();
					rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
					HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
				 
					while (rs.next())
					{
						trainstationHashMap.put(rs.getInt(1), rs.getString(2));
						
					}
						
					RequestDispatcher dispatcher = request.getRequestDispatcher("bookingQuery.jsp");
					request.setAttribute("trainMasterSourceStation",trainMasterSourceStation);
					request.setAttribute("trainMasterDestinationStation",trainMasterDestinationStation);
					request.setAttribute("dateOfJourney",dateOfJourney);
					//request.setAttribute("trainClass",trainClass);
					request.setAttribute("tmList",tmList);
					request.setAttribute("trainstationHashMap",trainstationHashMap);
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				else if (action!=null && action.equalsIgnoreCase("seatAvailability"))
				{
					int trainMasterSourceStation = 0;
					int trainMasterDestinationStation=0;
					String dateOfJourney = "";
					String tc = "";
					int trainId = 0;
					String tcCombo = "";
					
					trainMasterSourceStation = Integer.parseInt(request.getParameter("trainMasterSourceStation").toString());
					trainMasterDestinationStation = Integer.parseInt(request.getParameter("trainMasterDestinationStation").toString());
					dateOfJourney = request.getParameter("dateOfJourney").toString();
					
					tc = request.getParameter("tc").toString();
					tcCombo = request.getParameter("tcCombo").toString();
					trainId = Integer.parseInt(request.getParameter("trainId").toString());
					
					//System.out.println("tc " + tc + " trainId " + trainId + " date of Journey " + dateOfJourney);
					ArrayList<TrainMaster> tmList = new ArrayList<TrainMaster>();
					Statement s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT * FROM train_master where train_master_source_station = " + trainMasterSourceStation + " and train_master_destination_station =  " + trainMasterDestinationStation + " and train_master_flag='Y'");
					
					TrainMaster tm = null;	
					
					while (rs.next())
					{
						tm = new TrainMaster();
						tm.setTrain_master_id(rs.getInt(1));
						tm.setTrain_master_no(rs.getString(2));
						tm.setTrain_master_name(rs.getString(3));
						tm.setTrain_master_departure_time(rs.getString(7));
						tm.setTrain_master_arrival_time(rs.getString(8));
						tm.setTrain_master_distance(rs.getDouble(6));
						tm.setTrain_master_travelling_time(rs.getString(9));
						tmList.add(tm);
					}
					
					
					//get station master details 
					ArrayList<StationMaster> smList = new ArrayList<StationMaster>();
					
					s = c.createStatement();
					rs = s.executeQuery("SELECT station_master_id, station_master_code FROM station_master where station_master_flag='Y'");
					HashMap<Integer,String> trainstationHashMap = new HashMap<Integer,String>();
				 
					while (rs.next())
					{
						trainstationHashMap.put(rs.getInt(1), rs.getString(2));
						
					}
					
					
					//get seat availability
					
					String aQuery =    " SELECT count(*) FROM seat_availability c where c.seat_availability_train_id = " + trainId ;
					aQuery = aQuery +  " and c.seat_availability_class='" + tc.trim()  +"' and  c.seat_availability_coachno!='XX' and c.seat_availability_id not in ";
					aQuery = aQuery +  " (select b.booking_transaction_seat_allotment_id from booking_master a, booking_transaction b ";
					aQuery = aQuery +  " where a.booking_master_id = b.booking_master_id ";
					aQuery = aQuery +  " and a.booking_master_date_of_journey = '" + dateOfJourney +"' ";
					aQuery = aQuery +  " and a.booking_master_train_id = " + trainId;
					aQuery = aQuery +  " and a.booking_master_class = '" + tc.trim() + "')";
					 
					aQuery = aQuery +  " ";
					
					System.out.println(aQuery);
					
					s = c.createStatement();
					rs = s.executeQuery(aQuery);
					
					while (rs.next())
					{
						
						request.setAttribute("noOfSeats", rs.getInt(1));
						//System.out.println("seats " + rs.getInt(1));
					}					
					RequestDispatcher dispatcher = request.getRequestDispatcher("bookingQuery.jsp");
					request.setAttribute("trainMasterSourceStation",trainMasterSourceStation);
					request.setAttribute("trainMasterDestinationStation",trainMasterDestinationStation);
					request.setAttribute("dateOfJourney",dateOfJourney);
					request.setAttribute("tmList",tmList);
					request.setAttribute("trainstationHashMap",trainstationHashMap);
					request.setAttribute("tc", tc);
					request.setAttribute("trainId", trainId);
					
					request.setAttribute("tcCombo", tcCombo);
					
					dispatcher.forward(request, response);
					c.close();
					return;
				}
				else if (action!=null && action.equalsIgnoreCase("Booking"))
				{
					String dateOfJourney = "";
					String tc = "";
					int trainId = 0;
					int bookingMasterId = 0;
					int seatAvailabilityId = 0;
					
					dateOfJourney = request.getParameter("dateOfJourney").toString();
					tc = request.getParameter("tc").toString();
					trainId = Integer.parseInt(request.getParameter("trainId").toString());
					
					Statement s = c.createStatement();

					//Use insert into query to add record into the table
					
					int i=s.executeUpdate("insert into booking_master (booking_master_user_id, booking_master_date_of_journey, booking_master_pnr,booking_master_class,booking_master_flag, booking_master_train_id) " 
														   + " values (" + session.getAttribute("userMasterId")  + ", '" + dateOfJourney  + "','" + Math.abs((IrctcUtilities.createRandomInteger())) + "','" + tc + "', 'Y'," + trainId + ")");
					
					s = c.createStatement();
					ResultSet rs = s.executeQuery("SELECT booking_master_id FROM booking_master where booking_master_user_id = " + session.getAttribute("userMasterId") + " order by booking_master_id desc");
					
					while (rs.next())
					{
						bookingMasterId = rs.getInt(1);
						break;
					}
					
					String nop = "nameOfPassenger";
					String age = "age";
					String fp = "foodPref";
					String ip = "idProof";

					
					for (int x=1;x<=6;x++)
					{
						if (request.getParameter(nop+x).toString()!="")
						{
							
							s = c.createStatement();
							
							String aQuery =    " SELECT c.seat_availability_id FROM seat_availability c where c.seat_availability_train_id = " + trainId ;
							aQuery = aQuery +  " and c.seat_availability_class='" + tc.trim()  +"' and c.seat_availability_coachno!='XX' and c.seat_availability_id not in ";
							aQuery = aQuery +  " (select b.booking_transaction_seat_allotment_id from booking_master a, booking_transaction b ";
							aQuery = aQuery +  " where a.booking_master_id = b.booking_master_id ";
							aQuery = aQuery +  " and a.booking_master_date_of_journey = '" + dateOfJourney +"' ";
							aQuery = aQuery +  " and a.booking_master_train_id = " + trainId;
							aQuery = aQuery +  " and a.booking_master_class = '" + tc.trim() + "') ";
							rs = s.executeQuery(aQuery);
							
							System.out.println(aQuery);
							
							while (rs.next())
							{
								seatAvailabilityId = rs.getInt(1);
								break;
							}
							
							String s1 = "insert into booking_transaction (booking_master_id, booking_transaction_pass_name, booking_transaction_age, booking_transaction_food_pref, booking_transaction_id_proof, booking_transaction_seat_allotment_id, booking_transaction_flag) values (" + bookingMasterId + ",'" + request.getParameter(nop+x).toString() + "'," + Integer.parseInt(request.getParameter(age+x).toString()) + ",'" + request.getParameter(fp+x).toString() + "','" + request.getParameter(ip+x).toString() +"'," + seatAvailabilityId + ",'Y'";
							System.out.println(s1);


							s = c.createStatement();
							int y = s.executeUpdate("insert into booking_transaction (booking_master_id, booking_transaction_pass_name, booking_transaction_age, booking_transaction_food_pref, booking_transaction_id_proof, booking_transaction_seat_allotment_id, booking_transaction_flag) values (" + bookingMasterId + ",'" + request.getParameter(nop+x).toString() + "'," + Integer.parseInt(request.getParameter(age+x).toString()) + ",'" + request.getParameter(fp+x).toString() + "','" + request.getParameter(ip+x).toString() +"'," + seatAvailabilityId + ",'Y')");  
						}
					}

					 HashMap<String,Object> hm= null;    
					
					ArrayList<HashMap> hmList = new ArrayList<HashMap>(); 
					
					s = c.createStatement();
					String sql = "SELECT a.booking_master_pnr, a.booking_master_date_of_journey, d.station_master_name src, e.station_master_name dest, a.booking_master_class, concat(c.train_master_name, ' [',c.train_master_no,']') tr_nm FROM booking_master a, booking_transaction b, train_master c, station_master d, station_master e "; 
					sql = sql +  "where a.booking_master_id = b.booking_master_id ";		
					sql = sql +  "and a.booking_master_train_id = c.train_master_id ";
					sql = sql +  "and c.train_master_source_station = d.station_master_id ";
					sql = sql +  "and c.train_master_destination_station = e.station_master_id ";
					sql = sql +  "and a.booking_master_user_id = " + Integer.parseInt(session.getAttribute("userMasterId").toString()) + " group by a.booking_master_pnr ";
							
					rs = s.executeQuery(sql);
					
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
				else if (action!=null && action.equalsIgnoreCase("bookingHistory"))
				{
					int pnr = Integer.parseInt(request.getParameter("pnr"));
					
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
					
					String passQuery = "SELECT b.booking_transaction_pass_name, b.booking_transaction_age, b.booking_transaction_food_pref,b.booking_transaction_id_proof, c.seat_availability_class, concat(c.seat_availability_coachno, '/',c.seat_availability_seatno,'/',c.seat_availability_berthtype) seat, b.booking_transaction_id  FROM booking_master a, booking_transaction b, seat_availability c ";
					passQuery = passQuery + "where a.booking_master_id = b.booking_master_id ";
					passQuery = passQuery + "and b.booking_transaction_seat_allotment_id = c.seat_availability_id ";
					passQuery = passQuery + "and a.booking_master_pnr  = " + pnr;
					
					ArrayList<HashMap> passList = new ArrayList<HashMap>();
					
					s = c.createStatement();
					rs = s.executeQuery(passQuery);
					
					while (rs.next())
					{
						hm = new HashMap<String,Object>();
						hm.put("name", rs.getString(1));
						hm.put("age", rs.getInt(2));
						hm.put("fp", rs.getString(3));
						hm.put("id", rs.getString(4));
						hm.put("class", rs.getString(5));
						hm.put("seat", rs.getString(6));
						hm.put("Id", rs.getInt(7));
						passList.add(hm);
					}
					
					request.setAttribute("passList", passList);
					request.setAttribute("pnr", pnr);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("bookingHistory.jsp");
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				else if (action!=null && action.equalsIgnoreCase("bookingCancellation"))
				{
					
					String ticketsToBeCancelled = request.getParameter("ticketsToBeCancelled"); //1,2,3,4,5

					Statement s = c.createStatement();
					String qry = " select * from booking_transaction where booking_transaction_id in (" +ticketsToBeCancelled + ")";  
					ResultSet rs = s.executeQuery(qry);
					
					while (rs.next())
					{
						s = c.createStatement();
						int y = s.executeUpdate("insert into cancellation_master (cancellation_master_date, booking_transaction_id, cancellation_master_old_status, cancellation_master_flag) values ('" + IrctcUtilities.javaDateToStringforDB(new Date()) +"'," + rs.getInt(1) + "," +rs.getInt(7) + ", 'Y')");
					}

					
					
					qry = " SELECT a.booking_master_train_id, a.booking_master_class FROM booking_master a, booking_transaction b "; 
					qry = qry + " where a.booking_master_id = b.booking_master_id ";  
					qry = qry + " and b.booking_transaction_id in (" +ticketsToBeCancelled + ")";
					System.out.println(qry);
					s = c.createStatement();
					rs = s.executeQuery(qry);
					
					int trainId = 0;
					String trainClass = "";
					
					while (rs.next())
					{
							trainId = rs.getInt(1);
							trainClass = rs.getString(2);
					}
					
					System.out.println("train Id " + trainId + " trainclass " + trainClass);
					
					
					int seatAvailabilityId = 0; //for cancellation 
					
					qry = " SELECT * FROM seat_availability where seat_availability_train_id =" +  trainId + " and seat_availability_class='" + trainClass +"'";
					qry = qry + " and seat_availability_remarks like '%CAN%'";
					
					System.out.println(qry);
					
					s = c.createStatement();
					rs = s.executeQuery(qry);
					
					while (rs.next())
					{
					seatAvailabilityId = rs.getInt(1);
					}
					
					System.out.println("seatavailabilityid " + seatAvailabilityId);
					
					
					qry = "update booking_transaction set booking_transaction_seat_allotment_id = " + seatAvailabilityId + " where booking_transaction_id in (" +ticketsToBeCancelled + ")";
					s = c.createStatement();
					int cnt = s.executeUpdate(qry);
					
					
					int pnr = Integer.parseInt(request.getParameter("pnr"));
					
				    HashMap<String,Object> hm= null;    
					
					ArrayList<HashMap> hmList = new ArrayList<HashMap>(); 
					
					s = c.createStatement();
					String sql = "SELECT a.booking_master_pnr, a.booking_master_date_of_journey, d.station_master_name src, e.station_master_name dest, a.booking_master_class, concat(c.train_master_name, ' [',c.train_master_no,']') tr_nm FROM booking_master a, booking_transaction b, train_master c, station_master d, station_master e "; 
					sql = sql +  "where a.booking_master_id = b.booking_master_id ";		
					sql = sql +  "and a.booking_master_train_id = c.train_master_id ";
					sql = sql +  "and c.train_master_source_station = d.station_master_id ";
					sql = sql +  "and c.train_master_destination_station = e.station_master_id ";
					sql = sql +  "and a.booking_master_user_id = " + Integer.parseInt(session.getAttribute("userMasterId").toString()) + " group by a.booking_master_pnr ";
							
					rs = s.executeQuery(sql);
					
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
					
					String passQuery = "SELECT b.booking_transaction_pass_name, b.booking_transaction_age, b.booking_transaction_food_pref,b.booking_transaction_id_proof, c.seat_availability_class, concat(c.seat_availability_coachno, '/',c.seat_availability_seatno,'/',c.seat_availability_berthtype) seat, b.booking_transaction_id  FROM booking_master a, booking_transaction b, seat_availability c ";
					passQuery = passQuery + "where a.booking_master_id = b.booking_master_id ";
					passQuery = passQuery + "and b.booking_transaction_seat_allotment_id = c.seat_availability_id ";
					passQuery = passQuery + "and a.booking_master_pnr  = " + pnr;
					
					ArrayList<HashMap> passList = new ArrayList<HashMap>();
					
					s = c.createStatement();
					rs = s.executeQuery(passQuery);
					
					while (rs.next())
					{
						hm = new HashMap<String,Object>();
						hm.put("name", rs.getString(1));
						hm.put("age", rs.getInt(2));
						hm.put("fp", rs.getString(3));
						hm.put("id", rs.getString(4));
						hm.put("class", rs.getString(5));
						hm.put("seat", rs.getString(6));
						hm.put("Id", rs.getInt(7));
						passList.add(hm);
					}
					
					request.setAttribute("passList", passList);
					request.setAttribute("pnr", pnr);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("bookingHistory.jsp");
					dispatcher.forward(request, response);
					c.close();
					return;
					
				}
				
				
				
				//Default 
			
				RequestDispatcher dispatcher = request.getRequestDispatcher("bookingQuery.jsp");
				dispatcher.forward(request, response);
				c.close();
				return;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}