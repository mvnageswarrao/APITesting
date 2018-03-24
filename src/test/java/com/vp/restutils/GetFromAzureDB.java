//package com.vp.restutils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class GetFromAzureDB {
//
// public static void main(String[] args) {
//
//     // Connect to database
//        String hostName = "villaplus.database.windows.net";
//        String dbName = "MyVillaPlus";
//        String user = "VPWeb";
//        String password = "wpMy@246!";
//        String url = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
//        Connection connection = null;
//
//        try {
//                connection = DriverManager.getConnection(url);
//                String schema = connection.getSchema();
//                System.out.println("Successful connection - Schema: " + schema);
//
//                System.out.println("Query data example:");
//                System.out.println("=========================================");
//
//                // Create and execute a SELECT SQL statement.
//                String selectSql = "SELECT top 10 * contact.Customers.fname,contact.Customers.lname,contact.Customers.CustID,Booking.Bookings.ref" + 
//                		"FROM contact.Customers INNER JOIN Booking.Bookings ON Bookings.CustID = contact.Customers.CustID";
//
//                try (Statement statement = connection.createStatement();
//                    ResultSet resultSet = statement.executeQuery(selectSql)) {
//
//                        // Print results from select statement
//                        System.out.println("Top 10 categories:");
//                        while (resultSet.next())
//                        {
//                            System.out.println(resultSet.getString(1) + " "
//                                + resultSet.getString(2));
//                        }
//                 connection.close();
//                }                   
//        }
//        catch (Exception e) {
//                e.printStackTrace();
//        }
//    }
//}