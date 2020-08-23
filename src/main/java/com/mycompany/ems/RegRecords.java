/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
public class RegRecords {
    
    public RegRecords() throws IOException, ClassNotFoundException{
        
          int option;
        Scanner sc = new Scanner(System.in);
         int compid=0,depid=0;
                String rltype,details;
                Date crdate;
     do{
        do{
        SessionTimer.SessionStart.resetTimer();
        System.out.println("\n**** REGULATION RECORDS ****\n");
        System.out.println("Please Select an option:");
         System.out.println("[1] Add Regulation");
         System.out.println("[2] Edit Regulation");
         System.out.println("[3] Delete Regulation");
         System.out.println("[4] View Regulation");
         System.out.println("[5] Go Back");
        
         System.out.print("Enter your option: ");
                if(sc.hasNextInt())
                {
                    option= sc.nextInt();
                }else{
                    
                    option=-1;
                }   
            sc.nextLine();         
        
            
        if(option <1 || option >5){
            
             System.out.print("\nInvalid Option!\n"); 
        }
      }while(option <1 || option >5);
        
        
      switch(option){
          
          case 1:
               
                
                 System.out.println("\n*** Add Regulation ***\n");                                
                  
                  System.out.println("Enter Compliance id:");
                  if(sc.hasNextInt())
                  {
                    compid=sc.nextInt();
                    sc.nextLine();
                  }
                 
                  System.out.println("Enter RL Type:");
                  rltype=sc.nextLine();
                  System.out.println("Enter Details:");
                  details=sc.nextLine();
                  
                  System.out.println("Enter Creation date (format: YYYY-MM-DD):");
                  crdate =Date.valueOf(sc.nextLine());
                  
                  System.out.println("Enter Department ID:");
                  if(sc.hasNextInt())
                  depid=sc.nextInt();
                 sc.nextLine();
                 
                   try{  
                //step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  

             try ( //step3 create the statement object
             //step2 create  the connection object
                     Connection conn = DriverManager.getConnection(  
                             "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                    String sql1="INSERT INTO compliance VALUES (  ? ,  ?, ? , ? , ?)";
                    PreparedStatement cstmt = conn.prepareStatement(sql1);
                
                 cstmt.setInt(1, compid);
                 cstmt.setString(2, rltype);
                 cstmt.setString(3, details);
                 cstmt.setDate(4, crdate );
                 cstmt.setInt(5, depid);
                 cstmt.executeUpdate();
             }
             
             System.out.println("\n Record Successfully added.");
                    
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            } 
                 
              break;
              
          case 2:
            System.out.println("\n**** Edit Regulation ****\n");
            
            System.out.println("Enter Existing Compliance ID: ");
            if(sc.hasNextInt()){
                compid= sc.nextInt();
                sc.nextLine();
            }
            
            System.out.println("Enter New RL Type:");
            rltype=sc.nextLine();
            
            System.out.println("Enter New Details:");
            details=sc.nextLine();
            
            System.out.println("Enter new Creation Date (format: YYYY-MM-DD):");
             crdate =Date.valueOf(sc.nextLine());
             
            System.out.println("Enter new Department ID:");
             if(sc.hasNextInt())
             {depid=sc.nextInt();
                 sc.nextLine();
             }
             
             
             try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection con = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                //step3 create the statement object
                 String sql1="UPDATE compliance SET rltype = ? , details = ?, createdate= ?  , department_id= ?  WHERE complianceid = ? ";
                    PreparedStatement preparedStatement = con.prepareStatement(sql1);

                    preparedStatement.setString(1, rltype);
                    preparedStatement.setString(2, details);
                    preparedStatement.setDate  (3, crdate);
                    preparedStatement.setInt(4,depid);
                    preparedStatement.setInt(5,compid);
                    
                //step4 execute query
                preparedStatement.executeUpdate();
                System.out.println("\nSuccessfully Updated.\n");
            }  

               }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
               }  
                        

              break;
          case 3:
            System.out.println("\n**** Delete Regulation ****\n");
              System.out.println("Enter the Compliance ID: ");
              if(sc.hasNextInt()){
                compid= sc.nextInt();
                sc.nextLine();
            }
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                    String sql2 = "DELETE from compliance where complianceid="+compid;
                    Statement stmt = conn.createStatement(); 

                  stmt.executeUpdate(sql2);
                  System.out.println("Record deleted successfully");
                     }
                } catch (SQLException e) {
                    System.err.println(e);
                }

              break;
          case 4:
                System.out.println("\n**** View Regulation ****\n");
                
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
                
               // CallableStatement pstmt = conn.prepareCall("{? = call getAllRL_func()}");
               // pstmt.registerOutParameter(1, OracleTypes.CURSOR);
               // pstmt.executeQuery();
                //ResultSet rs =((OracleCallableStatement) pstmt).getCursor(1);
                 String sql5 =" Select * from compliance";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql5);
                
                  while(rs.next())  
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getDate(4)+"  "+rs.getInt(5));  
                     }
                } catch (SQLException e) {
                    System.err.println(e);
                }
              
              break;
          case 5:
              new AdminHome();
              break;
          default:
               System.err.println("Invalid Option!\n");
               
      }
   
  }while(option!=5);
  } 
    
}
