/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author THOR
 */
public class EMPRecords {
    
    public  EMPRecords() throws IOException, ClassNotFoundException{
        
         int option;
        Scanner sc = new Scanner(System.in);
         int depid=0,empid=0;
                String fname,lname,email;
                Date dob;
     do{
        do{
        SessionTimer.SessionStart.resetTimer();
        System.out.println("\n**** EMPLOYEE RECORDS ****\n");
        System.out.println("Please Select an option:");
         
         System.out.println("[1] Add Employees");
         System.out.println("[2] Edit Employees");
         System.out.println("[3] Delete Employees");
         System.out.println("[4] View Employees");
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
               
                
                 System.out.println("\n*** Add Employees ***\n");                                
                  
                  System.out.println("Enter First name:");
                  fname=sc.nextLine();
                 
                  System.out.println("Enter Last name:");
                  lname=sc.nextLine();
                  
                  System.out.println("Enter DOB (format: YYYY-MM-DD):");
                  dob =Date.valueOf(sc.nextLine());
                  
                  System.out.println("Enter Email:");
                  email=sc.nextLine();
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
                 CallableStatement cstmt = conn.prepareCall("{call addEmp_sp(?,?,?,?,?)}");
                
                 cstmt.setString(1, fname);
                 cstmt.setString(2, lname);
                 cstmt.setDate(3, dob);
                 cstmt.setString(4, email );
                 cstmt.setInt(5, depid);
                 cstmt.executeUpdate();
             }
             
             System.out.println("\n Record Successfully added.");
                    
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            } 
                 
              break;
              
          case 2:
            System.out.println("\n**** Edit Employees ****\n");
            
            System.out.println("Enter Existing Employee ID: ");
            if(sc.hasNextInt()){
                empid= sc.nextInt();
                sc.nextLine();
            }
            
            System.out.println("Enter New Firstname:");
            fname=sc.nextLine();
            
            System.out.println("Enter New Lastname:");
            lname=sc.nextLine();
            
            System.out.println("Enter new DOB (format: YYYY-MM-DD):");
             dob =Date.valueOf(sc.nextLine());
             
            System.out.println("Enter new Email:");
             email=sc.nextLine();
             
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
                 String sql1="UPDATE Employees SET firstname = ? , lastname = ?, dob= ? , email= ? , department_id= ?  WHERE empid = ? ";
                    PreparedStatement preparedStatement = con.prepareStatement(sql1);

                    preparedStatement.setString(1, fname);
                    preparedStatement.setString(2, lname);
                    preparedStatement.setDate  (3, dob);
                    preparedStatement.setString(4,email);
                    preparedStatement.setInt(5,depid);
                    preparedStatement.setInt(6,empid);
                    
                //step4 execute query
                System.out.println(sql1);
                preparedStatement.executeUpdate();
                System.out.println("\nSuccessfully Updated.\n");
            }  

               }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
               }  
                        

              break;
          case 3:
            System.out.println("\n**** Delete Employees ****\n");
              System.out.println("Enter the Employee ID: ");
              if(sc.hasNextInt()){
                empid= sc.nextInt();
                sc.nextLine();
            }
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                    String sql2 = "delete from employees where empid="+empid;
                    Statement stmt = conn.createStatement(); 

                  stmt.executeUpdate(sql2);
                  System.out.println("Record deleted successfully");
                     }
                } catch (SQLException e) {
                    System.err.println(e);
                }

              break;
          case 4:
                System.out.println("\n**** View Employees ****\n");
                
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
                
                CallableStatement pstmt = conn.prepareCall("{call getAllEmp_sp(?)}");
                pstmt.registerOutParameter(1, OracleTypes.CURSOR);
                pstmt.executeQuery();
                ResultSet rs =((OracleCallableStatement) pstmt).getCursor(1);
                  while(rs.next())  
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6));  
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
