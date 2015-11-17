/*
Created by Aniruddha Mukherjee & Rahul Gautam
Server class of assignment 2
 */
package Peer;
import java.*;
//import Vclock.VectorClock;
import java.util.Scanner;

public class parser{
    
static   VectorClock data;

	public static void parse(String input){
				
			if(input.equalsIgnoreCase("show")){
                            //System.out.println("show called");
                            data.show();
			}
                        
                        else   if(input.equalsIgnoreCase("event")){
                            //System.out.println("event called");
                            data.update();
                        }
                        
                        else    if(input.equalsIgnoreCase("send")){
                                    //System.out.println("send called");
                        }       
                        
                        else
                            System.out.println("Invalid string input. Try again");
                       	
	}
        
        public static void main(String[] args){
            
            data=new VectorClock(0, 4);
            
            while(true){
            Scanner in = new Scanner(System.in);
            //System.out.println("Enter a string");
            String data = in.nextLine();
            parse(data);
            }

        }

}
