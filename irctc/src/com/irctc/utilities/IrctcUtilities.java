package com.irctc.utilities;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
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
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.irctc.baseclasses.SeatAvailability;
import com.irctc.baseclasses.UserType;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class IrctcUtilities {
	
	public static Connection getConnection() throws IOException
	{
		Connection conn = null;
		
		try {
			 	ServletContext c = MyListener.context;
			    String path = c.getRealPath("/WEB-INF/connection.properties");
		        Properties p = new Properties();
		        p.load(new FileInputStream(path));
		        //p.load(fis);
		        String dname= (String) p.getProperty("Dname"); 
		        String url= (String) p.getProperty ("URL"); 
		        String user = (String) p.getProperty ("Uname"); 
		        String psw = (String) p.getProperty ("password"); 
		        Class.forName(dname); 
		        conn = DriverManager.getConnection(url, user, psw);
		        return conn;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
			return conn;
		}
		catch(SQLException e1)
		{
			e1.printStackTrace();
			return conn;
		}
		catch(FileNotFoundException e2)
		{
			e2.printStackTrace();
			return conn;
		}
	}
	
		
	public static String javaDateToString(Date date)
	{
		try
		{
			SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");
			// 		myDate is the java.util.Date in yyyy-mm-dd format
			// 	Converting it into String using formatter
			String strDate = sm.format(date);
			//	Converting the String back to java.util.Date
			//Date dt = sm.parse(strDate);
			return strDate;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	public static String javaDateToStringforDB(Date date)
	{
		try
		{
			SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
			// 		myDate is the java.util.Date in yyyy-mm-dd format
			// 	Converting it into String using formatter
			String strDate = sm.format(date);
			//	Converting the String back to java.util.Date
			//Date dt = sm.parse(strDate);
			return strDate;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
	
	
	public static Date javaStringToDateForPlaceholder(String dt)
	{
		try
		{
			
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
			return date;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new Date();
		}
	}
	
	
	
	public static ArrayList<SeatAvailability> getSeatAvailability(int trainMasterId) throws SQLException, IOException
	{

		Connection c = getConnection();
		ArrayList saList = new ArrayList<SeatAvailability>();
		
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("select seat_availability_class, seat_availability_coachno, seat_availability_seatno, seat_availability_berthtype  from seat_availability where seat_availability_train_id=" + trainMasterId);
		SeatAvailability sa = new SeatAvailability();
		while (rs.next())
		{
			sa = new SeatAvailability();
			sa.setSeat_availability_class(rs.getString(1));
			sa.setSeat_availability_coachno(rs.getString(2));
			sa.setSeat_availability_seatno(rs.getDouble(3));
			sa.setSeat_availability_berthtype(rs.getString(4));
			saList.add(sa);
		}
		return saList;
	}
	
	
	public static int createRandomInteger(){
		int aStart = 1000000000;
		long aEnd = 9999999999L;
	    Random aRandom = new Random();
	    if ( aStart > aEnd ) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);
	    return randomNumber;
	  }
	
	public static void generateReport(String jasper_filename, Map<String,Object> parameters, Connection conn, HttpServletResponse response, ServletContext context)
	{
		byte bytes[] = null;
		try
		{
		bytes =	JasperRunManager.runReportToPdf(getCompiledReport(context,jasper_filename),parameters,conn);
		}
		catch(JRException e)
		{
			e.printStackTrace();
		}
		
		response.setHeader("Content-Disposition", "attachment; filename="+jasper_filename+".pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream;
		try {
			ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
	}

	
	
	
	public static JasperReport getCompiledReport(ServletContext context, String fileName)
	throws JRException {
		File reportFile =
			new File(
					context.getRealPath(
							"/reports/" + fileName + ".jasper"));
		JasperReport jasperReport =
			(JasperReport) JRLoader.loadObject(reportFile.getPath());
		return jasperReport;
	}
	
	
	
	

}
