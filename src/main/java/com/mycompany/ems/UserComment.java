/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author THOR
 */
public class UserComment {
     
   public  UserComment(int userid) throws ClassNotFoundException{
        int option=-1;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n**** MY Comment ****\n");
        
         try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
               
                 String sql5 = "SELECT comments FROM statusreport where empid = '"+userid+"'";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql5);
                
                  while(rs.next())  
                    System.out.println(rs.getString(1));  
                     }
                              
                   
                } catch (SQLException e) {
                    System.err.println(e);
                }
        
         do{
            SessionTimer.SessionStart.resetTimer();
         System.out.println("Please Select an option:");
         System.out.println("[1] Edit Comment");
         System.out.println("[2] Go Back");
          System.out.print("\nEnter your option: ");
         
         if(sc.hasNextInt())
                {
                    option= sc.nextInt();
                }else{
                    
                    option=-1;
                }   
            sc.nextLine();         
                 
            
             switch(option){
                 
                 case 1: 
                     
                     System.out.println("\nEnter New Comment:");
                     String coment= sc.nextLine();
                     
                      try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection con = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                //step3 create the statement object
                 String sql1="UPDATE statusreport SET comments = ?  WHERE empid = ? ";
                    
                 PreparedStatement preparedStatement = con.prepareStatement(sql1);

                 
                    preparedStatement.setString(1, coment);
                    preparedStatement.setInt(2, userid);
                   
                //step4 execute query
                preparedStatement.executeUpdate();
                System.out.println("\nSuccessfully Updated.\n");
            }  

               }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
               }  
                     
                     break;
                 case 2:
                     
                     break;
                 default: 
                       System.out.print("\nInvalid Option!\n"); 
                     break;
                      
                     
                  
             }
        
         }while(option!=2);
         
    }
    
}
