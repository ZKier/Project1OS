import java.util.*;
import java.util.concurrent.Semaphore;

public class project1 {

	static int WEST_BOUND = 2;
	static int EAST_BOUND = 1;
	
	public static void main(String[] args) throws InterruptedException {		
		
		ArrayList<Integer> cars = new ArrayList<>();
		
		//allows for the randomization of the length of the car array (randomizes the amount of cars on the road)
		int randomLen = (int) ((Math.random() * 12) + 2);
		
		//makes a list of 2-12 cars, each car having a number between 0-100
		for (int i = 0; i < randomLen; i++) {
			int randomCarNum = (int) ((Math.random() * 100) + 0);
			while (cars.contains(randomCarNum)) {
				randomCarNum = (int) ((Math.random() * 100) + 0);
			}
			cars.add(i, randomCarNum);
		}
		
		//prints the list of cars for reference
		System.out.println(cars);
		
		//creates semaphore object
		Semaphore S = new Semaphore(1, true);
		
		//create the roads
		Road eastBound = new Road(EAST_BOUND, cars, S);
		Road westBound = new Road(WEST_BOUND, cars, S);
		
		//start and join the threads
		eastBound.start();		westBound.start();

		eastBound.join();		westBound.join();

		
	}  


}
