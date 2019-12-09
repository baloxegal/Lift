package home;

public class Lift {
	final static double MAX_WEIGHT = 1000.0;
	final static byte MAX_LEVEL = 11;
	final static byte MIN_LEVEL = 0;
	private static boolean isOperational = true;
	private static boolean isMoving = false;
	private static boolean isClosed = true;
	private static byte level = 0;
	private static double weight = 0;
	
	public static byte getLevel() {
		return level;
	}
	public static boolean isOperational() {
		return isOperational;
	}
	public static boolean isMoving() {
		return isMoving;
	}
	public static boolean isClosed() {
		return isClosed;
	}
//	private static void setLevel(int level) {					Не используется!
//		if(level < MIN_LEVEL || level > MAX_LEVEL) {			Этот метод может использоваться только при первоначальной настройке лифта или после ремонта - поэтому закомментировал.
//			System.err.println("Level value out of bounds");	По той же причине сеттер для isOerational даже и не писал.
//			return;
//		}
//		Lift.level = (byte)level;
//	}
	private static void setIsMoving() {
		if(weight > MAX_WEIGHT) {
			System.err.println("Excess weight");
			isMoving = false;
			return;
		}
		isMoving = true;
	}
	public static void printLiftMap() {
		for(int i = MAX_LEVEL; i >= 0; i--) {
			if(i == MAX_LEVEL) {
				System.out.print(" T| ");
			}
			else if(i == MIN_LEVEL) {
				System.out.print(" P| ");
			}
			else {
				System.out.printf("%2d| ", i);
			}
			if(i == getLevel()) {
				if(!isOperational()) {
					System.out.println("[X] |");
				}
				else if(isClosed() && isOperational()) {
					System.out.println("] [ |");
				}
				else {
					System.out.println("[ ] |");
				}
			}
			else {
				System.out.println("    |");
			}
		}
		System.out.println();
	}
	public static void printLiftMap(int level) {
		for(int i = MAX_LEVEL; i >= 0; i--) {
			if(i == MAX_LEVEL) {
				System.out.print(" T| ");
			}
			else if(i == MIN_LEVEL) {
				System.out.print(" P| ");
			}
			else {
				System.out.printf("%2d| ", i);
			}
			if(i == getLevel()) {
				if(!isOperational()) {
					System.out.println("[X] |");
				}
				else if(isClosed() && isOperational()) {
					System.out.println("] [ |");
				}
				else {
					System.out.println("[ ] |");
				}
			}
			else if(i == getLevel() + 1 && compareLevel(level) == -1) {
				System.out.println(" ^  |");
			}
			else if(i == getLevel() - 1 && compareLevel(level) == 1) {
				System.out.println(" v  |");
			}
			else {
				System.out.println("    |");
			}
		}
		System.out.println();
	}
	public static boolean openDoors() {
		if (isOperational() && !isMoving()) {
			isClosed = false;
			return true;
		}
		//System.err.println("Lift is not operational or it is moving");
		return false;
	}
	public static boolean closeDoors() {
		if (isOperational() && !isMoving() && weight <= MAX_WEIGHT) {
			isClosed = true;
			return true;
		}
		//System.err.println("Lift is not operational, the weight is exceeded or it is moving");
		return false;
	}
	public static void enterPassenger(double passengerWeight) {
		weight += passengerWeight;
	}
	public static void exitPassenger(double passengerWeight) {
		weight -= passengerWeight;
	}
	private static boolean moveOneLevelUp() {
		if (level + 1 > MAX_LEVEL) {
			return false;
		}
		level++;
		setIsMoving();
		return true;
	}
	private static boolean moveOneLevelDown() {
		if (level - 1 < MIN_LEVEL) {
			return false;
		}
		level--;
		setIsMoving();
		return true;
	}
	private static int compareLevel(int level) {
		if (getLevel() == level){
			return 0;
		}
		else if (getLevel() > level){
			return 1;
		}
		return -1;
	}
	public static boolean moveToLevel(int level) {
		if(!isOperational() || !isClosed() || weight > MAX_WEIGHT) {
			//System.err.println("Lift is not operational, the weight is exceeded or the doors are not closed");
			return false;
		}
		else if(compareLevel(level) == 0) {
			//System.out.println("Lift is located on the selected floor.");
			return false;
		}
		else if(compareLevel(level) == 1) {
			for(int i = getLevel(); i > level; i--) {
				moveOneLevelDown();
				if(i == level + 1) {
					isMoving = false;
					continue;
				}
				printLiftMap(level);
				isMoving = false;
			}
		}
		else {
			for(int i = getLevel(); i < level; i++) {
				moveOneLevelUp();
				if(i == level - 1) {
					isMoving = false;
					continue;
				}
				printLiftMap(level);
				isMoving = false;
			}
		}
		return true;
	}
}
