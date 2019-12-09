package home;

public class Application {

	public static void main(String[] args) {
		
		System.out.println("—÷≈Õ¿–»… 1\n\n");
		
		Lift.printLiftMap();
		Lift.openDoors();
		Lift.enterPassenger(80);
		Lift.enterPassenger(100);
		Lift.closeDoors();
		Lift.moveToLevel(3);
		Lift.printLiftMap();
		
		System.out.println("\n—÷≈Õ¿–»… 2\n\n");
		
		Lift.printLiftMap();
		Lift.openDoors();
		for(int i = 0; i < 11; i++) {
			Lift.enterPassenger(100);
		}
		if(!Lift.closeDoors()) {
			System.out.println("PASSENGERS: The doors are not closing!");
		}
		else {
			System.out.println("PASSENGERS: The doors are closing");
		
			if(!Lift.moveToLevel(7)) {
				System.out.println("PASSENGERS: The lift is not moving!");
			}
			else {
				System.out.println("PASSENGERS: The lift started moving");
			}
		}
		Lift.printLiftMap();
	}

}
