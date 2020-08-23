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
public class UserHome {
    
    public UserHome( int userid) throws IOException, ClassNotFoundException{
        
          int option=-1;
        Scanner sc = new Scanner(System.in);
        new SessionTimer.SessionStart();
     
        do{
         SessionTimer.SessionStart.resetTimer();
        System.out.println("\n**** EMS HOME USER ****\n");
        System.out.println("Please Select an option:");
         
         System.out.println("[1] My Regulation");
         System.out.println("[2] View/Edit Comment");
         System.out.println("[3] Compliance Status");
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
        
        
      switch(option){
          
          case 1:
                 new MyRegulation(userid);
              break;
              
          case 2:
              new UserComment(userid);
              break;
          case 3:
              new UserStatus(userid);
              break;
          case 4:
              SessionTimer.SessionStart.cancelTimer();
              new EMSLogout();
              break;
              
          default:
               System.err.println("Invalid Option!\n");
               
      }
            }while(option!=4); 
    }
}
