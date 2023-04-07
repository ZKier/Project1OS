import java.util.*;
import java.util.concurrent.Semaphore;

public class project1 {

	static int WEST_BOUND = 2;
	static int EAST_BOUND = 1;
	
	public static void main(String[] args) throws InterruptedException {		
		
		ArrayList<Integer> cars = new ArrayList<>();
		
		int randomLen = (int) ((Math.random() * 12) + 2);
		
		//makes a list of 2-12 cars, each car having a number between 0-100
		for (int i = 0; i < randomLen; i++) {
			int randomCarNum = (int) ((Math.random() * 100) + 0);
			while (cars.contains(randomCarNum)) {
				randomCarNum = (int) ((Math.random() * 100) + 0);
			}
			cars.add(i, randomCarNum);
		}
		
		System.out.println(cars);
		
		Semaphore S = new Semaphore(1, true);
		
		Road eastBound = new Road(EAST_BOUND, cars, S);
		Road westBound = new Road(WEST_BOUND, cars, S);
		
		
		eastBound.start();		westBound.start();

		eastBound.join();		westBound.join();

		
	}  


}
