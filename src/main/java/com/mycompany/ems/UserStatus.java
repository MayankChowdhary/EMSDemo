/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author THOR
 */
public class UserStatus {
    
    public UserStatus(int userid) throws ClassNotFoundException{
        
        System.out.println("\n**** Compliance Status ****\n");
        
        try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
               
                 String sql5 = "SELECT * FROM statusreport where empid = '"+userid+"'";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql5);
                System.out.println("Compliance ID   StatusRpt ID  Employee ID  Comment  Create Date  Dept. ID");

                  while(rs.next())
                    System.out.println(rs.getInt(1)+"\t   "+rs.getInt(2)+"\t   "+rs.getInt(3)+"\t   "+rs.getString(4)+"\t   "+rs.getDate(5)+"\t   "+rs.getInt(6));  
                     }
                              
                   
                } catch (SQLException e) {
                    System.err.println(e);
                }
        
    }
}
 