/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author THOR
 */
public class EMSLogout {
    static Logger logger = Logger.getLogger(EMSLogout.class.getName());
    public EMSLogout() throws IOException, ClassNotFoundException{
        
         int option=-1;
        java.io.Console console;
      // ClassLoader c1 = EMSLogin.class.getClassLoader();
       // System.out.println(c1.getResource("resources/log4j.properties").getPath());

        // creates pattern layout
        PatternLayout layout = new PatternLayout();
        String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
        layout.setConversionPattern(conversionPattern);
 
        // creates console appender
       ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(layout);
        consoleAppender.activateOptions();
 
        // creates file appender
        FileAppender fileAppender = new FileAppender();
        File file = new File( "D:\\working\\emslog\\emslog.txt" );
        fileAppender.setFile(file.getCanonicalPath());
        fileAppender.setLayout(layout);
        fileAppender.activateOptions();
 
        // configures the root logger
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.DEBUG);
        //rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(fileAppender);
        
        Scanner sc = new Scanner(System.in);
        do{
        System.out.println("\n***EMPLOYEE MANAGEMENT SYSTEM LOGIN***\n") ; 
        System.out.println("Select an option:\n");
        System.out.println("[1] Admin Login");
        System.out.println("[2] User Login");
        System.out.println("[3] Exit Application\n");
        
        
                System.out.print("Enter your option: ");
                if(sc.hasNextInt())
                {
                    option= sc.nextInt();
                }else{
                    
                    option=-1;
                }   
            sc.nextLine();         
        
            
        if(option <1 || option >3){
            
             System.out.print("\nInvalid Option!\n"); 
        }
      }while(option <1 || option >3);
        
         console = System.console();
       
     if((option==1 || option==2) && console==null){
                System.err.println("Failed to initilize Console please use Command line interface!");
                System.exit(0);
     }
                         
            switch (option) {
                case 1: 
                   
                    System.out.println("\n***Admins Login***\n");
                    String auser = console.readLine("Enter Username: ");
                    String apass = new String(console.readPassword("Enter Password: "));
                    
                    if(userSearch(Integer.parseInt(auser),apass,"ADMIN")){
                        
                        logger.info("AdminLogin Successful!");
                            new AdminHome();
                    }else{
                        logger.info("AdminLogin Failed!");
                        System.err.println("Wrong credentials!");
                    }
                    
                    break;
                
                case 2:
                    System.out.println("\n***Users Login***\n");
                    String uuser = console.readLine("Enter Username: ");
                    String upass = new String(console.readPassword("Enter Password: "));
                   if(userSearch(Integer.parseInt(uuser),upass,"EMPLOYEE")){
                        
                        logger.info("UserLogin Successful!");
                    }else{
                        logger.info("UserLogin Failed!");
                        System.err.println("Wrong credentials!");
                    }
                    break;
                
                case 3:
                    System.out.println("Application Terminated.\n");
                    System.exit(0);
    
                default:
                    System.err.println("Invalid Option!\n");
               
            }
           
      
    }
    
    protected static boolean userSearch(int userid,String pass, String role){
        boolean search=false;
        try{  
                //step1 load the driver class  
                Class.forName("oracle.jdbc.driver.OracleDriver");  

            //step3 create the statement object
            try ( //step2 create  the connection object
                    Connection con = DriverManager.getConnection(  
                            "jdbc:oracle:thin:@localhost:1521:xe","empdb","4201977")) {
                //step3 create the statement object
                Statement stmt=con.createStatement();
                //step4 execute query
                ResultSet rs=stmt.executeQuery("select * from login_master");
                while (rs.next()) {
                    int useridx= rs.getInt(1);
                    String passx=rs.getString(2);
                    String rolex=rs.getString(3);
                // System.out.println(useridx+"  "+passx+"  "+rolex);
                 
      
                    if(userid==useridx && pass.equals(passx) && role.equals(rolex)){
                        search=true;
                     logger.info("Search Successful!");
                    }
                        
                  }
                
                //step5 close the connection object
            }  

                }catch(ClassNotFoundException | SQLException e){ System.out.println(e);
                logger.info(e);
                }  


        return search;
    }
    
    
}
