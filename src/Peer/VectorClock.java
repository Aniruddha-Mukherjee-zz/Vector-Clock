
package Peer;

import java.*;
import java.io.Serializable;

public class VectorClock implements Serializable{
	
	 private int peerPosition;
	 private int[] timestamp;
	 
	 	public VectorClock(int pos,int size) {
			peerPosition=pos;
			timestamp=new int[size];
			
			for (int i=0;i<size;i++)
				timestamp[i]=0;
		}
	 	
	 public void show(){
		 for (int item : timestamp)
			 System.out.print(item+" ");
                 System.out.print("\n");
                
	 }
	 
	 public void update(){
		//System.out.println("peerposition is "+peerPosition+"\n");
             timestamp[peerPosition]++;
		 	
	 }
	 
	 public void update(VectorClock vc){
		 
		 for(int i=0;i<timestamp.length;i++)
			 	timestamp[i]=Math.max(timestamp[i],vc.timestamp[i]);		 
	 }

	
	
}



