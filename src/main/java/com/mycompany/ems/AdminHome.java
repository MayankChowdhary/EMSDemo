/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author THOR
 */
public class AdminHome {
    

    public  AdminHome() throws IOException, ClassNotFoundException{
          new SessionTimer.SessionStart();
        int option;
        Scanner sc = new Scanner(System.in);
        do{
        
        System.out.println("\n**** EMS HOME ADMIN ****\n");
        System.out.println("Please Select an option:");
         
         System.out.println("[1] Employees Records");
         System.out.println("[2] Department Records");
         System.out.println("[3] Regulation Records");
         System.out.println("[4] Log out");
        
         System.out.print("Enter your option: ");
                if(sc.hasNextInt())
                {
                    option= sc.nextInt();
                }else{
                    
                    option=-1;
                }   
            sc.nextLine();         
        
            
        if(option <1 || option >4){
            
             System.out.print("\nInvalid Option!\n"); 
        }
      }while(option <1 || option >4);
        
        
      switch(option){
          
          case 1:
               new EMPRecords();
              break;
              
          case 2:
              new DeptRecords();
              break;
          case 3:
              
                    new RegRecords();
              break;
          case 4:
               SessionTimer.SessionStart.cancelTimer();
              new EMSLogout();
              break;
          default:
              SessionTimer.SessionStart.resetTimer();
               System.err.println("Invalid Option!\n");
               
      }
         
         
         
        
    }
  
    
}
