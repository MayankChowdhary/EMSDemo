/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.IOException;
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
public class DeptRecords {
    
  public DeptRecords() throws IOException, ClassNotFoundException{
        
          int option;
        Scanner sc = new Scanner(System.in);
         int depid=0;
         String depname;
     do{
        do{
        SessionTimer.SessionStart.resetTimer();
        System.out.println("\n**** DEPARTMENT RECORDS ****\n");
        System.out.println("Please Select an option:");
         
         System.out.println("[1] Add Department");
         System.out.println("[2] Edit department");
         System.out.println("[3] Delete department");
         System.out.println("[4] View department");
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
               
                
                 System.out.println("\n*** Add Department ***\n");   
                 
                   System.out.println("Enter Department ID:");
                  if(sc.hasNextInt()){
                    depid=sc.nextInt();
                    sc.nextLine();
                  }
                  
                  System.out.println("Enter Department name:");
                  depname=sc.nextLine();
                  
                    try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection con = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                 
               String sql0 ="Insert into department VALUES ( ? , ?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql0);

                    preparedStatement.setInt(1, depid);
                    preparedStatement.setString(2, depname);
                    //step4 execute query
                
               
                preparedStatement.executeQuery();
                 
            }
             System.out.println("\n Record Successfully added.");
                    
            }catch(ClassNotFoundException | SQLException e){
                e.printStackTrace();
            } 
                 
              break;
              
          case 2:
            System.out.println("\n**** Edit Departments ****\n");
            
            System.out.println("Enter Existing Department ID: ");
            if(sc.hasNextInt()){
                depid= sc.nextInt();
                sc.nextLine();
            }
            
            System.out.println("Enter New Department Name:");
            depname=sc.nextLine();
           
             
             try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection con = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                //step3 create the statement object
                 String sql1="UPDATE Department SET department_nm = ? WHERE department_id = ? ";
                    PreparedStatement preparedStatement = con.prepareStatement(sql1);

                    preparedStatement.setString(1, depname);
                    preparedStatement.setInt(2, depid);
                   
                    
                //step4 execute query
                System.out.println(sql1);
                preparedStatement.executeUpdate();
                System.out.println("\nSuccessfully Updated.\n");
            }  

               }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
               }  
                        

              break;
          case 3:
            System.out.println("\n**** Delete Departments ****\n");
              System.out.println("Enter the Department ID: ");
              if(sc.hasNextInt()){
                depid= sc.nextInt();
                sc.nextLine();
            }
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                    String sql2 = "delete from department where department_id="+depid;
                    Statement stmt = conn.createStatement(); 

                  stmt.executeUpdate(sql2);
                  System.out.println("Record deleted successfully");
                     }
                } catch (SQLException e) {
                    System.err.println(e);
                }

              break;
          case 4:
                System.out.println("\n**** View Departments ****\n");
                
               try{  
               //step1 load the driver class  
               Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection conn = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                
                
                String sql5 =" Select * from department";
                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql5);
        
                  while(rs.next())  
                    System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
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

