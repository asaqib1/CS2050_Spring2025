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
		
		// Makes sure user is giving correct input
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
		
		// Main menu loop: Repeatedly displays the vending machine menu
		// Switch: Performs actions based on user selection
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
	
	/**
	 * Reads car information from the specified file.
	 * Each line in the file should include: car type, row number, column number, year, price, make, and model.
	 * For each line, creates a Car object and adds it to the vending machine's arrayList and hashMap.
	 * @param filename (the name of the file containing the car data)
	 * @param newMachine (vending machine object to which cars will be added)
	 */
	public static void readFromFile(String filename, VendingMachineI2 newMachine) {
		
		// Initialize fileScanner to null so it can be correctly checked and closed in the finally block
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new File(filename));

			// Reads each line from the file to extract car details 
			// This includes type, location, year, price, make, and model
			// Then it creates a Car object with this data and adds it to the vending machine 
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

			// Prints out an error message if the file the user inputed is not found
		} catch (FileNotFoundException e) {
			System.out.printf("File was not found %n");
			
			// Closes scanner at the end if it was used
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
	
	/**
	 * Constructor which initializes a Car object with type, year, price, manufacturer, and model
	 * @param year (the year the car was manufactured)
	 * @param price (price of the car)
	 * @param manufacturer (the name of the car's manufacturer)
	 * @param model (model name of the car)
	 * @param floor (size of the vending machine)
	 * @param space (size of the vending machine)
	 */
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
	
	// Returns a formatted string representation of the car
	@Override
	public abstract String toString();

}// end CarI2 abstract class

class BasicCar extends CarI2{

	
	/**
	 * BasicCar constructor which uses the super keyword to call the Superclass's constructor
	 * @param type
	 * @param year
	 * @param Price
	 * @param Manufacturer
	 * @param Model
	 * @param floor
	 * @param space
	 */
	public BasicCar(String type, int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(type, year, Price, Manufacturer, Model, floor, space);
		
	}

	// Returns a formatted string representation of the car and is overridden from the superclass
	@Override
	public String toString() {
		return "Basic Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}//end basicCar class

class PremiumCar extends CarI2 {
	
	/**
	 * PremiumCar constructor which uses the super keyword to call the Superclass's constructor
	 * @param type
	 * @param year
	 * @param Price
	 * @param Manufacturer
	 * @param Model
	 * @param floor
	 * @param space
	 */
	public PremiumCar(String type, int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(type, year, Price, Manufacturer, Model, floor, space);
	}

	// Returns a formatted string representation of the car and is overridden from the superclass
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
	
	/**
	 * Constructor for the VendingMachineI2 class
	 * Initializes the vending machine, wash queue, and location map
	 * Sets the total number of floors and spaces
	 * @param totalFloors
	 * @param totalSpaces
	 */
	public VendingMachineI2(int totalFloors, int totalSpaces) {
		vendingMachine = new LinkedList<>();
		washQueue = new LinkedList<>();
		locationMap = new HashMap<>();
		this.totalFloors = totalFloors;
		this.totalSpaces = totalSpaces;
	}
	
	/**
	 * Adds a car to the vending machine at a specific floor and space
	 * Checks for valid position and if the spot is already occupied
	 * @param currentCar
	 * @param carFloor
	 * @param carSpace
	 */
	public void addCar(CarI2 currentCar, int carFloor, int carSpace) {
		
		String key = makeKey(carFloor, carSpace);
		
		// Check if position is valid
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
	
	/**
	 * Displays the list of cars sorted by price or year
	 * @param sortBy
	 */
	public void displaySortedCars(String sortBy) {
		List<CarI2> cars = new ArrayList<>(vendingMachine);
		
		// Sort by price
		if(sortBy.equalsIgnoreCase("Price")){
			System.out.println("Sorted Inventory by Price: ");
			cars.sort(Comparator.comparing(CarI2::getPrice));
		}

		// Sort by year
		else if (sortBy.equalsIgnoreCase("Year")) {
			System.out.println("Sorted Inventory by Year: ");
			cars.sort(Comparator.comparing(CarI2::getYear));
		}
		
		// Print out the sorted list
		for (CarI2 currentCar : cars) {
			System.out.println(currentCar);
		}
	}
	
	/**
	 * Retrieves car based on floor and space location
	 * It does not remove the car, just prints it out
	 * @param floor
	 * @param space
	 */
	public void retrieveCar(int floor, int space) {
		String key = makeKey(floor, space);
		CarI2 car = locationMap.get(key);
		
		if(car != null) {
			System.out.println("Car retrieved: " + car);
		} else {
			System.out.println("Car not found at this location");
		}
	}
	
	/**
	 * Displays all cars currently stored in the vending machine
	 */
	public void displayVendingMachine() {
		if (vendingMachine.isEmpty()) {
			System.out.println("Currently no cars in the vending machine.");
		} else {
			for(CarI2 currentCar : vendingMachine) {
				System.out.println(currentCar);
			}
		}
	}
	
	/**
	 * Searches for cars by manufacturer and type (Basic or Premium)
	 * Displays all matching cars sorted by manufacturer name
	 * @param manufacturer
	 * @param type
	 */
	public void searchForCars(String manufacturer, String type) {
		List<CarI2> results = new ArrayList<>();
		for(CarI2 currentCar : vendingMachine) {
			
			// Check car type and manufacturer
			if(type.equalsIgnoreCase("Basic") && currentCar instanceof BasicCar || type.equalsIgnoreCase("Premium") && currentCar instanceof PremiumCar) {
				if(currentCar.getManufacturer().equalsIgnoreCase(manufacturer)) {
					results.add(currentCar);			
				}
			}
		}
		
		// Checks if no matching cars are found
		// Prints out error message if so
		if(results.isEmpty()) {
			System.out.println("No cars available");
			
		// Else prints out the list with the matching cars
		} else {
			results.sort(Comparator.comparing(CarI2::getManufacturer));
			for(CarI2 car : results) {
				System.out.println(car);
			}
		}
	}
	
	/**
	 * Adds a car to the wash queue based on its location
	 * @param floor
	 * @param space
	 */
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
	
	/**
	 * Processes the wash queue by "washing" (printing and removing) each car
	 */
	public void processWashQueue() {
		if(washQueue.isEmpty()) {
			System.out.println("No cars in the wash queue.");
		} else {
			while(!washQueue.isEmpty()) {
				
				// Remove from queue
				CarI2 washedCar = washQueue.remove();
				System.out.println("Washing: " + washedCar);
			}
		}
	}
	
	/**
	 * Removes a sold car from both the vending machine and location map
	 * @param floor
	 * @param space
	 */
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
	
	/**
	 * Helper method used to uniquely identify car locations
	 * Creates a unique key based on floor and space numbers
	 * @param floor
	 * @param space
	 * @return
	 */
	private String makeKey(int floor, int space) {
		return floor + "," + space;
	}
	
}// end VendingMachineI2 class
