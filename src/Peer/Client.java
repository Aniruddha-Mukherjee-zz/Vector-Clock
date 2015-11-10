/*
 * Client class of assignment 1
 * 
 */
package Peer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;


public class Client extends Thread{
	
	static final String serverName = "localhost";
	private int peerPosition;
        private static final int BASE_SOCKET = 20000;
	private VectorClock clock=null;
	// default number of terminals
	private static final int PEERS = 4;
	
	Client(VectorClock vc,int pos){
                peerPosition=pos;
                clock=vc;            
        }

	public  void put(int port){
	   
        //OutputStream outToServer = clientSocket.getOutputStream();
         try (Socket clientSocket = new Socket(serverName, port)) {
                        //OutputStream outToServer = clientSocket.getOutputStream();
         ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
                        
         DataOutputStream out = new DataOutputStream(outToServer);
                        
         outToServer.writeObject(clock);
                        //out.writeUTF("put"+v);                                  
         out.close();
          
         }
	      catch(IOException e)
	      {
	    	  System.out.println("Connection to port "+port+" was unsuccessful.Make sure the node is UP."+"\n");
	    	  e.printStackTrace();
	      }
		
	   }
	
	// utility function for get
	
	
	
	
        @Override
	public void run(){
		Scanner s = new Scanner(System.in);
		while(true){
			//System.out.println("looping");
			String input = s.nextLine();
		
                        if(input.equalsIgnoreCase("show")){
                            //System.out.println("show called");
                            clock.show();
			}
                        
                        else   if(input.equalsIgnoreCase("event")){
                            System.out.println("Event Successfully registered");
                            clock.update();
                        }
                        
                        else    if(input.contains("send")){                                                                                 
                   
                   try{
                        //System.out.println("Concatenated strig is "+input.substring(5,input.length()));
                        int process= Integer.parseInt(input.substring(5,input.length()));                                           
                        
                        if(process>=PEERS)
                        {
                            System.out.println("Invalid Process number.Try again");
                            continue;
                        }
                        if( process==peerPosition){
                            System.out.println("Cannot send message to self. Try again");
                            continue;
                        }
                        
                        System.out.println("send called to process "+process); 
                        clock.update();
                        put(process+BASE_SOCKET);
                      
                   }
                            catch(Exception e){
  System.out.println("Incorrect syntax. Correct syntax send <insert process number>"+"\n");
                                //e.printStackTrace();
                            }                                               
                        }       
                        
                        else
                            System.out.println("Invalid string input. Try again");
                      	
                
                }
	}
	      
}
