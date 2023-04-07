import java.util.*;
import java.util.concurrent.*;

public class Road extends Thread {
		
	int WEST_BOUND = 2;
	int EAST_BOUND = 1;
	int direction;
	Semaphore s;
	ArrayList<Integer> carsOnRoad;

	
	public Road(int bound, ArrayList<Integer> list, Semaphore s) {
		
		this.direction = bound;
		this.carsOnRoad = list;
		this.s = s;
	
	}
	
	//runs automatically when road is started.
	public void run() {

		if (this.direction == EAST_BOUND) {
			
            System.out.println("Starting East Bound thread...");
            
            try {
                
                int arrayLength = this.carsOnRoad.size();
                //works its way down the list of cars on the road
                for (int i = 0; i < arrayLength; i++) {
                	
                	int carNum = this.carsOnRoad.get(i);
                	if ((carNum % 2) == 1) {
                		
                		//Imagine this to be the green light that starts the West Bound's Red Light
                        s.acquire();
                        
                        //Critical Section
                        System.out.println("East bound light turns: GREEN");
                		System.out.println("East --> bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 2000) + 750);
                		Thread.sleep(sleepTime);
                		System.out.println("East --> bound vehicle: " + carNum + " has passed\n");
                		
                		//gives other threads the chance to make their lights green
                		s.release();
                		
                	} else {
                		continue;
                	}
                	
                }
                
            } catch (InterruptedException e) {
            	
            	System.out.println(e);
                    
            }
            
            //releases the lock so that other threads can work and finish
            s.release();

        } else {
        	
        	System.out.println("Starting West Bound thread...");
            
            try {
                
                int arrayLength = this.carsOnRoad.size();
                
                for (int i = 0; i < arrayLength; i++) {
                	
                	int carNum = this.carsOnRoad.get(i);
                	
                	if ((carNum % 2) == 0) {
                		//Imagine this to be the green light that starts the East Bound's Red Light
                		s.acquire();
                		//Critical Section
                		System.out.println("West Bound light turns: GREEN");
                		System.out.println("West <-- bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 2000) + 750);
                		Thread.sleep(sleepTime);
                		System.out.println("West <-- bound vehicle: " + carNum + " has passed\n");
                		s.release();
                		
                	} else {
                		
                		continue;
                		
                	}

                }
                
            } catch (InterruptedException e) {
                   
            	System.out.println(e);
            	
            }
            
            //to make sure the other threads can finish
            s.release();
        }
		
	}
	   
}