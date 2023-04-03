/*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	Zacchaeus Kier &  Sofiat Okanlawon
	
	
	
	Purpose: Write a Java program to solve the following synchronization & deadlock problem.
	
	
	
 	Problem: 
 		* Suppose a two-way (east-west), two-lane road contains a long one-lane bridge.
 	 
 		* A westbound (or eastbound) car can only use the bridge if there are no oncoming cars
 	  	  on the bridge.
 	  
 		* Because of accidents, a signaling system has been installed at each end of the bridge.
 	
 		* When a car approaches the bridge, a sensor notifies the controller computer by calling
 	      a function (*arrive*) with the car’s travel direction (east or west).
 	  
		* When a car leaves the bridge, the sensor notifies the controller computer by calling
	      (*passed*) with the car’s travel direction.
	  
		* The traffic controller sets the signal lights to allow or prevent cars wanting to pass
	      the bridge.
	  
		* Construct and implement an algorithm for controlling the lights such that they
	      synchronize the traffic to pass the bridge correctly. 
	      

	You may also use Thread.sleep() with different sleep times for eastbound and westbound cars.    //so maybe Thread.sleep(random_time_between_1_to_4_secs) 
	This would allow simulating different time taken to pass the bridge.

*//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;

public class project1 {

	public static void main(String[] args) {
		
		ArrayList<Integer> northBoundCars;
		ArrayList<Integer> eastBoundCars;
		
		ArrayList<Integer> carOnBridge; //size should never be greater than one.
		
		ArrayList<Integer> carsPastBridge;
	}  

	
	static class stats implements Runnable {
      
		int function;
   
		public stats(int num) {
			function = num;
      
		}
   
		public void run() {
            
		}
   
	}

}