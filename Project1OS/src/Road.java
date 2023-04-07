import java.util.*;
import java.util.concurrent.*;

public class Road implements Runnable {
		
	int WEST_BOUND = 2;
	int EAST_BOUND = 1;
	int direction;
	Semaphore s;
	ArrayList<Integer> carsOnRoad;

	
	public Road(int bound, ArrayList<Integer> list, Semaphore s) {
		
		this.direction = bound;
		this.carsOnRoad = list;
		this.s = s;
		
		
		//this section may not matter****** not sure yet
		if (direction == EAST_BOUND) {
			//populate an odd number list
		} else if (direction == WEST_BOUND) {
			//populate an even number list
		} else {
			//close code and run an error
		}
		
	}
	
	//runs automatically when road is created.
	public void run() {
		//maybe start it off running
		//then make a pause
		
		//not sure frfr going to sleep

		if (this.direction == EAST_BOUND) {
			
            System.out.println("Starting East Bound thread...");
            
            try {
            	System.out.println("East Bound thread trying to acquire");
            	
                //Imagine this to be the green light that starts the West Bound's Red Light
                s.acquire();
                
                //Critical Section
                int arrayLength = this.carsOnRoad.size();
                
                for (int i = 0; i < arrayLength; i++) {
                	int carNum = this.carsOnRoad.get(i);
                	if ((carNum % 2) == 1/*index value odd, print arrival, sleep, print passed, release */) {
                		System.out.println("East bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 1000) + 200);
                		Thread.sleep(sleepTime);
                		System.out.println("East bound vehicle: " + carNum + " has passed");
                		s.release();
                	} else {
                		continue;
                	}
                    //sharedCounter.counter++;
                    //System.out.println(threadName + " current value: " + sharedCounter.counter); 
                	
                    // Now incrementer thread will sleep so that the decrementer thread 
                    //(if a context switch happen) get a chance to update
                    //Thread.sleep(SLEEP_TIME); //change to 100, and then 1000
                }
            } catch (InterruptedException e) {
                    System.out.println(e);
            }
            
            //prof added this in class
            s.release();

        } else {
        	System.out.println("Starting West Bound thread...");
            
            try {
            	System.out.println("West Bound thread trying to acquire");
            	
                //Imagine this to be the green light that starts the West Bound's Red Light
                s.acquire();
                
                //Critical Section
                int arrayLength = this.carsOnRoad.size();
                
                for (int i = 0; i < arrayLength; i++) {
                	int carNum = this.carsOnRoad.get(i);
                	if ((carNum % 2) == 0/*index value odd, print arrival, sleep, print passed, release */) {
                		System.out.println("East bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 1000) + 200);
                		Thread.sleep(sleepTime);
                		System.out.println("East bound vehicle: " + carNum + " has passed");
                		s.release();
                	} else {
                		continue;
                	}
                    //sharedCounter.counter++;
                    //System.out.println(threadName + " current value: " + sharedCounter.counter); 
                	
                    // Now incrementer thread will sleep so that the decrementer thread 
                    //(if a context switch happen) get a chance to update
                    //Thread.sleep(SLEEP_TIME); //change to 100, and then 1000
                }
            } catch (InterruptedException e) {
                    System.out.println(e);
            }
            
            //prof added this in class
            s.release();
        }
		
	}
	   
}