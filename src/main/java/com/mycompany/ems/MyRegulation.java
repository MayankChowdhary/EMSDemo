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
public class MyRegulation {
    

    public MyRegulation(int userid) throws ClassNotFoundException{
        

        System.out.println("\n**** MyRegulation ****\n");
         try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
               
                 String sql5 =" SELECT * FROM compliance where department_id = (select department_id from employees  where empid ="+userid+")";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql5);
                
                  while(rs.next())  
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getDate(4)+"  "+rs.getInt(5));  
                     }
                              
                   
                } catch (SQLException e) {
                    System.err.println(e);
                }
        
    
  } 
    
}
