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
		
		
		//this section may not matter****** not sure yet
		if (direction == EAST_BOUND) {
			//populate an odd number list
		} else if (direction == WEST_BOUND) {
			//populate an even number list
		} else {
			//close code and run an error
		}
		
	}
	
	//runs automatically when road is started.
	public void run() {

		if (this.direction == EAST_BOUND) {
			
            System.out.println("Starting East Bound thread...");
            
            try {
                
                //Critical Section
                int arrayLength = this.carsOnRoad.size();
                
                for (int i = 0; i < arrayLength; i++) {
                	
                	int carNum = this.carsOnRoad.get(i);
                	if ((carNum % 2) == 1) {
                		
                		//Imagine this to be the green light that starts the West Bound's Red Light
                        s.acquire();
                        System.out.println("East bound light turns: GREEN");
                		System.out.println("East --> bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 2000) + 750);
                		Thread.sleep(sleepTime);
                		System.out.println("East --> bound vehicle: " + carNum + " has passed\n");
                		
                		//should let another thread get the chance to acquire but this never happens... the threads just run concurrently.
                		s.release();
                		//this.notifyAll();
                		//this.wait();
                		
                		
                		sleepTime = (int) ((Math.random() * 2000) + 750);
                		Thread.sleep(sleepTime);
                		
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
                
                //Critical Section
                int arrayLength = this.carsOnRoad.size();
                
                for (int i = 0; i < arrayLength; i++) {
                	
                	int carNum = this.carsOnRoad.get(i);
                	
                	if ((carNum % 2) == 0) {
                		//Imagine this to be the green light that starts the West Bound's Red Light
                		s.acquire();
                		System.out.println("West Bound light turns: GREEN");
                		System.out.println("West <-- bound vehicle: " + carNum + " is arriving");
                		
                		//does a sleep which is representative of the speed of the car
                		int sleepTime = (int) ((Math.random() * 2000) + 750);
                		Thread.sleep(sleepTime);
                		System.out.println("West <-- bound vehicle: " + carNum + " has passed\n");
                		s.release();

                		
                		//sleepTime = (int) ((Math.random() * 2000) + 750);
                		//Thread.sleep(sleepTime);
                		
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