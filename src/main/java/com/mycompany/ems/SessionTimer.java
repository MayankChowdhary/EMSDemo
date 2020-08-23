/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ems;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author THOR
 */
public class SessionTimer {
    
    public static class SessionStart{
                static int interval;
                static Timer timer;
                
        public  SessionStart(){
            
                 int delay = 1000;
                int period = 1000;
                timer = new Timer();
                interval = 180;
                timer.scheduleAtFixedRate(new TimerTask() {

                    @Override
                    public void run() {
                        try {
                           setInterval();
                        } catch (IOException ex) {
                            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AdminHome.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    }
                }, delay, period);
            }
        
         private static final int setInterval() throws IOException, ClassNotFoundException {
         if (interval == 1){
             
           timer.cancel();
           System.err.println("\n\n**** SESSION EXPIRED !! ****\n");
           System.exit(0);

             
         }
                  
         return --interval;
     }
         
         public static void resetTimer(){
             
             interval=180;
         }
         
         public static void cancelTimer(){
             timer.cancel();
         }
    }
    
}
