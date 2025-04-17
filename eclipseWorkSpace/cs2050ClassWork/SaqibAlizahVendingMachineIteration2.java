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

	static void main(String[] args) {

	}//end main
	
	public static void readFromFile(String filename, VendingMachineI2 newMachine) {

		// Initialize fileScanner to null so it can be correctly checked and closed in the finally block
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new File(filename));

			while (fileScanner.hasNextInt()) {
				String carType = fileScanner.next();
				int rowNumber = fileScanner.nextInt();
				int colNumber = fileScanner.nextInt();
				int year = fileScanner.nextInt();
				double price = fileScanner.nextDouble();
				String make = fileScanner.next();
				String model = fileScanner.next();

				if(carType.equalsIgnoreCase("B")) {
					CarI2 newCar = new BasicCar(carType, year, price, make, model, rowNumber, colNumber);
					newMachine.addCar(newCar, rowNumber, colNumber);
				}
				
				else if(carType.equalsIgnoreCase("P")) {
					CarI2 newCar = new PremiumCar(carType, year, price, make, model, rowNumber, colNumber);
					newMachine.addCar(newCar, rowNumber, colNumber);

				}
			
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
				" - " + " $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}//end basicCar class

class PremiumCar extends CarI2 {
	
	public PremiumCar(String type, int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(type, year, Price, Manufacturer, Model, floor, space);
	}

	@Override
	public String toString() {
		return "Premium Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - " + " $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}// end PremiumCar class

class VendingMachineI2 {
	
	private LinkedList<CarI2> vendingMachine;
	private Queue<CarI2> washQueue;
	private Map<String, CarI2> locationMap;
	private int totalFloors;
	private int totalSpaces;
	
	public VendingMachineI2() {
		vendingMachine = new LinkedList<>();
		washQueue = new LinkedList<>();
		locationMap = new HashMap<>();
	}
	
	public void addCar(CarI2 currentCar, int Carfloor, int Carspace) {
		
		String key = Carfloor + ", " + Carspace;
		
		if(Carfloor >= 1 && Carfloor <= totalFloors && Carspace >= 1 && Carspace <= totalSpaces) {
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
			cars.sort(Comparator.comparing(CarI2::getPrice));
		}

		else if (sortBy.equalsIgnoreCase("Year")) {
			cars.sort(Comparator.comparing(CarI2::getYear));
		}
		
		for (CarI2 currentCar : cars) {
			System.out.println(currentCar);
		}
	}
	
	public void retrieveCar(int floor, int space) {
		String key = floor + ", " + space;
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
			System.out.print("No cars available");
		} else {
			results.sort(Comparator.comparing(CarI2::getManufacturer));
			for(CarI2 car : results) {
				System.out.println(car);
			}
		}
	}
	
	public void addToWashQueue(CarI2 currentCar) {
		System.out.println("Car retrieved: " + currentCar.toString());
		washQueue.add(currentCar);
		System.out.println("Car added to wash queue.");
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
		String key = floor + ", " + space;
		CarI2 carSold = locationMap.get(key);
		if(carSold != null) {
			System.out.println("Car sold: " + carSold);
			locationMap.remove(key, carSold);
			vendingMachine.remove(carSold);
		} else {
			System.out.println("No car found at Floor " + floor + " Space " + space);
		}			
	}
	
}// end VendingMachineI2 class
