/*
 * Car Vending Machine Program - Iteration 02
 * ------------------------------------------
 * This is an updated version of the Car Vending Machine project. 
 * In this iteration, we moved from using a 2D array to a LinkedList 
 * for storing car objects, making the system more flexible and dynamic. 
 * We also added a HashMap to keep track of car locations, and used 
 * an abstract Car class with two types of cars: Basic and Premium.
 *
 * New features include searching cars by manufacturer and type, 
 * sorting the inventory by price or year, adding cars to a car wash queue, 
 * and selling (removing) cars from the machine.
 *
 * Everything runs through a menu system, and cars are still loaded 
 * from a text file at the start.
 *
 * Author: Alizah Saqib
 * Course: CS 2050
 * Instructor: Deborah Harding
 * Date: 04/28/2025
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SaqibAlizahVendingMachineIteration2 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the number of floors for the car vending machine: ");
		while (!input.hasNextInt()) {
			System.out.println("Enter a valid number.");
			input.next();
		}
		int floor = input.nextInt();

		System.out.printf("Enter the number of spaces for the car vending machine: %n");
		while(!input.hasNextInt()) {
			System.out.println("Enter a valid number.");
			input.next();
		}
		int space = input.nextInt();

		VendingMachineI2 newMachine = new VendingMachineI2(floor, space);
		
		int choice;
		
		do {
			System.out.printf("%n=== Car Vending Machine Menu ===%n");
			System.out.println("1. Load Car Data from File");
			System.out.println("2. Display Vending Machine");
			System.out.println("3. Retrieve a Car by Location (Floor & Space)");
			System.out.println("4. Print Sorted Inventory (Price)");
			System.out.println("5. Print Sorted Inventory (Year)");
			System.out.println("6. Search for Cars (Manufacturer & Type)");
			System.out.println("7. Add Car to Wash Queue");
			System.out.println("8. Process Car Wash Queue");
			System.out.println("9. Sell a Car");
			System.out.println("10. Exit");

			System.out.printf("%nEnter your choice: ");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter the file name: ");
				String fileName = input.next();
				readFromFile(fileName, newMachine);
				break;
			case 2:
				newMachine.displayVendingMachine();
				break;
			case 3:
				System.out.print("Enter floor: ");
				int floorNumber = input.nextInt();
				System.out.print("Enter space: ");
				int spaceNumber = input.nextInt();
				newMachine.retrieveCar(floorNumber, spaceNumber);
				break;
			case 4:
				newMachine.displaySortedCars("Price");
				break;
			case 5:
				newMachine.displaySortedCars("Year");
				break;
			case 6: 
				System.out.println("Enter manufacturer: ");
				String manufacturer = input.next();
				System.out.println("Enter car type (Basic/Premium): ");
				String type = input.next();
				newMachine.searchForCars(manufacturer, type);
				break;
			case 7: 
				System.out.print("Enter floor: ");
				int floorNum = input.nextInt();
				System.out.print("Enter space: ");
				int spaceNum = input.nextInt();
				newMachine.addToWashQueue(floorNum, spaceNum);
				break;
			case 8: 
				newMachine.processWashQueue();
				break;
			case 9: 
				System.out.print("Enter floor of the car to sell: ");
				int sellFloor = input.nextInt();
				System.out.print("Enter space of the car to sell: ");
				int sellSpace = input.nextInt();
				newMachine.removeSoldCar(sellFloor, sellSpace);
				break;
			case 10:
				System.out.print("Exiting program. Goodbye!");
				break;

			}

		} while (choice != 10);

		input.close();

	}//end main
	
	public static void readFromFile(String filename, VendingMachineI2 newMachine) {

		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new File(filename));

			while (fileScanner.hasNext()) {
				String carType = fileScanner.next();
				int rowNumber = fileScanner.nextInt();
				int colNumber = fileScanner.nextInt();
				int year = fileScanner.nextInt();
				double price = fileScanner.nextDouble();
				String make = fileScanner.next();
				String model = fileScanner.nextLine().trim();
				
				CarI2 newCar = null;

				if(carType.equalsIgnoreCase("B")) {
					newCar = new BasicCar(carType, year, price, make, model, rowNumber, colNumber);
				}
				
				else if(carType.equalsIgnoreCase("P")) {
					newCar = new PremiumCar(carType, year, price, make, model, rowNumber, colNumber);
				}
				
				newMachine.addCar(newCar, rowNumber, colNumber);
			
			}

		} catch (FileNotFoundException e) {
			System.out.printf("File was not found %n");

		} finally {
			if (fileScanner != null) {
				fileScanner.close();
			}
		}

	}

}//end public class

abstract class CarI2 {
	
	private String type;
	private int year;
	private double price;
	private String manufacturer;
	private String model;
	private int floor;
	private int space;
	
	public CarI2(String type, int year, double price, String manufacturer, String model, int floor, int space) {
		
		this.type = type;
		this.year = year;
		this.price = price;
		this.manufacturer = manufacturer;
		this.model = model;
		this.floor = floor;
	    this.space = space;
	}
	
	public int getYear() {
		return year;
	}

	public double getPrice() {
		return price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getSpace() {
		return space;
	}
	
	@Override
	public abstract String toString();

}// end CarI2 abstract class

class BasicCar extends CarI2{

	public BasicCar(String type, int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(type, year, Price, Manufacturer, Model, floor, space);
		
	}

	@Override
	public String toString() {
		return "Basic Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}//end basicCar class

class PremiumCar extends CarI2 {
	
	public PremiumCar(String type, int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(type, year, Price, Manufacturer, Model, floor, space);
	}

	@Override
	public String toString() {
		return "Premium Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}// end PremiumCar class

class VendingMachineI2 {
	
	private LinkedList<CarI2> vendingMachine;
	private Queue<CarI2> washQueue;
	private Map<String, CarI2> locationMap;
	private int totalFloors;
	private int totalSpaces;
	
	public VendingMachineI2(int totalFloors, int totalSpaces) {
		vendingMachine = new LinkedList<>();
		washQueue = new LinkedList<>();
		locationMap = new HashMap<>();
		this.totalFloors = totalFloors;
		this.totalSpaces = totalSpaces;
	}
	
	public void addCar(CarI2 currentCar, int carFloor, int carSpace) {
		
		String key = makeKey(carFloor, carSpace);
		
		if(carFloor >= 1 && carFloor <= totalFloors && carSpace >= 1 && carSpace <= totalSpaces) {
			if(locationMap.containsKey(key)) {
				System.out.println("Could not add car, " + currentCar + " already a car in that spot.");
			} else {
				locationMap.put(key, currentCar);
				vendingMachine.add(currentCar);
			}
		} else {
			System.out.println("Could not add car, " + currentCar.toString() + ", invalid position (out of bounds).");
		}
	}
	
	public void displaySortedCars(String sortBy) {
		List<CarI2> cars = new ArrayList<>(vendingMachine);
		if(sortBy.equalsIgnoreCase("Price")){
			System.out.println("Sorted Inventory by Price: ");
			cars.sort(Comparator.comparing(CarI2::getPrice));
		}

		else if (sortBy.equalsIgnoreCase("Year")) {
			System.out.println("Sorted Inventory by Year: ");
			cars.sort(Comparator.comparing(CarI2::getYear));
		}
		
		for (CarI2 currentCar : cars) {
			System.out.println(currentCar);
		}
	}
	
	public void retrieveCar(int floor, int space) {
		String key = makeKey(floor, space);
		CarI2 car = locationMap.get(key);
		
		if(car != null) {
			System.out.println("Car retrieved: " + car);
		} else {
			System.out.println("Car not found at this location");
		}
	}
	
	public void displayVendingMachine() {
		for(CarI2 currentCar : vendingMachine) {
			System.out.println(currentCar);
		}
	}
	
	public void searchForCars(String manufacturer, String type) {
		List<CarI2> results = new ArrayList<>();
		for(CarI2 currentCar : vendingMachine) {
			if(type.equalsIgnoreCase("Basic") && currentCar instanceof BasicCar || type.equalsIgnoreCase("Premium") && currentCar instanceof PremiumCar) {
				if(currentCar.getManufacturer().equalsIgnoreCase(manufacturer)) {
					results.add(currentCar);			
				}
			}
		}
		
		if(results.isEmpty()) {
			System.out.println("No cars available");
		} else {
			results.sort(Comparator.comparing(CarI2::getManufacturer));
			for(CarI2 car : results) {
				System.out.println(car);
			}
		}
	}
	
	public void addToWashQueue(int floor, int space) {
		String key = makeKey(floor, space);
		CarI2 currentCar = locationMap.get(key);
		
		if(currentCar != null) {
			System.out.println("Car retrieved: " + currentCar);
			washQueue.add(currentCar);
			System.out.println("Car added to wash queue.");
		} else {
			System.out.println("No car at that position, could not be added to wash queue.");
		}
	}
	
	public void processWashQueue() {
		if(washQueue.isEmpty()) {
			System.out.println("No cars in the wash queue.");
		} else {
			while(!washQueue.isEmpty()) {
				CarI2 washedCar = washQueue.remove();
				System.out.println("Washing: " + washedCar);
			}
		}
	}
	
	public void removeSoldCar(int floor, int space) {
		String key = makeKey(floor, space);
		CarI2 carSold = locationMap.get(key);
		
		if(carSold != null) {
			System.out.println("Car sold: " + carSold);
			locationMap.remove(key, carSold);
			vendingMachine.remove(carSold);
		} else {
			System.out.println("No car found at Floor " + floor + " Space " + space);
		}			
	}
	
	private String makeKey(int floor, int space) {
		return floor + "," + space;
	}
	
}// end VendingMachineI2 class
