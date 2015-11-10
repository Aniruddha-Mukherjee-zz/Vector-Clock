
package Peer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server // extends Thread
{
	private static final int BASE_SOCKET = 20000;
	private static final int PEERS = 4;
        private ServerSocket serverSocket;
	private VectorClock clock=null;
        public static Hashtable<Integer, Integer> table;

	// initialize server
	public Server(int port,VectorClock vc) throws IOException {
                clock=vc;
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(0);
		table = new Hashtable<Integer, Integer>();
	}

	public void isstart() {

		// SERVER FUNCTIONS
		while (true) {
                try {
		Socket server = serverSocket.accept();
				//int clientPort=server.getPort();
		//System.out.println("port of client is= "+server.getInetAddress().getHostAddress()+"\n");
				
		//DataInputStream in = new DataInputStream(server.getInputStream());

		ObjectInputStream inFromClient = new ObjectInputStream(server.getInputStream());
                                
                try {
                         VectorClock receivedclock=(VectorClock)inFromClient.readObject();
                         clock.update();
                         clock.update(receivedclock);
                    } 
                catch (   Exception ex) {
                    //Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                     ex.printStackTrace();
                }                         
		
                }
                 
                catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        
        }

	public static void main(String[] args) {
		
                int port = BASE_SOCKET + Integer.parseInt(args[0]);
		System.out.println("Started Server at port: " + port);
		System.out.println("Usage: show/event/send <insert process number>");
                VectorClock clock=new VectorClock(port-BASE_SOCKET, PEERS);
		// start client thread
		Client c = new Client(clock,port-BASE_SOCKET);
		c.start();

		try {
			Server s = new Server(port,clock);
			s.isstart();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
