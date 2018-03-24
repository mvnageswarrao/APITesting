package com.vp.restutils;

import java.sql.Connection;
import java.sql.DriverManager;
import  java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import  java.sql.ResultSet;		
import java.sql.SQLException;

public class GetFromDB 
{

	public GetFromDB()
	{
		
	}
	
	//"databasename=VP_Web_Live_BookingPages;integratedSecurity=true";
//	String DBUrl = "jdbc:sqlserver://172.16.0.5:1433;databasename=VP_Web_Live_BookingPages";
//	String username = "sa";
//	String password = "ash@vp8";

//	// For Azure Sql DB connection
//	String DBUrl = "jdbc:sqlserver:sqlvillaplus-test.database.windows.net;databasename=MyVillaPlus";
//	String username = "VPWebTest";
//	String password = "'TxpS157!";
	
	String DBUrl = "jdbc:sqlserver://46.37.176.33:1334;databasename=VP_Web_Live_B";
	String username = "ashtest";
	String password = "VdTmTg8W2";

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String SQLquery = null;
		
	public ResultSet getdata(String SQLquery) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		try
		{
			con = DriverManager.getConnection(DBUrl,username,password);
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQLquery);
			return rs;
		}
		catch(Exception e)
		{
			System.out.println("Error in connection :"+e.getMessage());
			
			return null;
		}
	}
	public void closeDBConn() throws SQLException
	{
		con.close();
	}
	public String getSelectableDest() throws SQLException, ClassNotFoundException
	{
		ArrayList<String> CentreLst = new ArrayList<String>();
		SQLquery = "SELECT contact.Customers.fname,contact.Customers.lname,contact.Customers.CustID , Booking.Bookings.ref" + 
				"FROM contact.Customers INNER JOIN Booking.Bookings ON Bookings.CustID = contact.Customers.CustID" + 
				"Where  Booking.Bookings.Ref";
		rs = getdata(SQLquery);
		while (rs.next())
		{			
			CentreLst.add(rs.getString("CentreName"));
		}
		closeDBConn();
		Random ran = new Random();
		int index = ran.nextInt(CentreLst.size());
		return CentreLst.get(index);
	}
	public String getCenterCode(String DesttoSelect) throws ClassNotFoundException, SQLException
	{
		String centerCode = null;
		SQLquery = "SELECT Centre  from  dbo.TblCentre where CentreName = '" + DesttoSelect + "'";
        rs = getdata(SQLquery);
		while (rs.next())
		{	
			centerCode = rs.getString("Centre");
		}
		return centerCode;	
	}
	public String getSelectableDate(String centerCode, String EndDate) throws ClassNotFoundException, SQLException
	{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String StartDate = dateFormat.format(date);
		ArrayList<String> DateLst = new ArrayList<String>();
		SQLquery = "exec dbo.QAsp_GetAvailableDates @strCentre='" + centerCode + "', @StartDate='" + StartDate + "',@EndDate='" + EndDate + "'";
		rs = getdata(SQLquery);
		while (rs.next())
		{			
			DateLst.add(rs.getString("WeekStart"));
		}
		closeDBConn();
		Random ran = new Random();
		int index = ran.nextInt(DateLst.size());
		String DatetoSelect = DateLst.get(index-1);
		return DatetoSelect;
		
	}
	public ArrayList<String> getVOPrices(String VillaName, String DepDate, String NumofNights) throws ClassNotFoundException, SQLException
	{
		
		ArrayList<String> VOPriceValuesLst = new ArrayList<String>();
 
		
		SQLquery  = "EXEC dbo.QAsp_CalculateVillaPrice @villaName = '"+VillaName+"', @departureDate = '"+ DepDate+ "', @weeks = '" +NumofNights+ "'";
		System.out.println(SQLquery);
		rs = getdata(SQLquery);
		rs.relative(2);
		while (rs.next())
		{		
				rs.getInt("VillaId");

			//VOPriceValuesLst.add(rs.getString("WeekStart"));
		}
		while (rs.next())
		{		rs.getInt("VillaId");
				rs.getString(1);		
				rs.getString(2);		
				rs.getString(3);
			//VOPriceValuesLst.add(rs.getString("WeekStart"));
		}
		closeDBConn();
		return VOPriceValuesLst;
	}
	@SuppressWarnings("null")
	public List<String> getCustData() throws SQLException, ClassNotFoundException
	{
		List<String> CustLst = null ;
//		SQLquery = "SELECT top 10 contact.Customers.fname,contact.Customers.lname,contact.Customers.CustID , Booking.Bookings.ref" + 
//				"FROM contact.Customers INNER JOIN Booking.Bookings ON Bookings.CustID = contact.Customers.CustID";
		SQLquery = "SELECT top 10 * Booking.Bookings";
		rs = getdata(SQLquery);
		while (rs.next())
		{			
			CustLst.add(rs.getString("BookID"));
			CustLst.add(rs.getString("Ref"));
			CustLst.add(rs.getString("centre"));
			CustLst.add(rs.getString("TotalPrice"));
		}
		closeDBConn();
		Random ran = new Random();
		int index = ran.nextInt(CustLst.size());
//		return CustLst.get(index);
		return CustLst;
		
	}
}


