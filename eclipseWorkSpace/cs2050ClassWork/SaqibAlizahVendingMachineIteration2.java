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

import java.util.*;

public class SaqibAlizahVendingMachineIteration2 {

	static void main(String[] args) {

	}//end main

}//end public class

abstract class CarI2 {
	
	private int year;
	private double price;
	private String manufacturer;
	private String model;
	private int floor;
	private int space;
	
	public CarI2(int year, double price, String manufacturer, String model, int floor, int space) {
		
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

	public BasicCar(int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(year, Price, Manufacturer, Model, floor, space);
		
	}

	@Override
	public String toString() {
		return "Basic Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - " + " $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}//end basicCar class

class PremiumCar extends CarI2 {
	
	public PremiumCar(int year, double Price, String Manufacturer, String Model, int floor, int space) {
		super(year, Price, Manufacturer, Model, floor, space);
	}

	@Override
	public String toString() {
		return "Premium Car: " + getManufacturer() + " " + getModel() + " " + getYear() + 
				" - " + " $" + getPrice() + " (Floor: " + getFloor() + ", Space: " + getSpace() + ")";
	}
	
}// end PremiumCar class

class VendingMachineI2 {
	
	private LinkedList<Car> vendingMachine;
	private Queue<Car> washQueue;
	private Map<String, Car> locationMap;
	private int totalFloors;
	private int totalSpaces;
	
	public VendingMachineI2() {
		vendingMachine = new LinkedList<>();
		washQueue = new LinkedList<>();
		locationMap = new HashMap<>();
	}
	
	public static void addCar(Car currentCar, int Carfloor, int Carspace, int totalFloors, int totalSpaces, LinkedList<Car> vendingMachine, Map<String, Car> locationMap) {
		
		String key = Carfloor + ", " + Carspace;
		
		if(Carfloor >= 1 && Carfloor <= totalFloors && Carspace >= 1 && Carspace <= totalSpaces) {
			if(locationMap.containsKey(key)) {
				System.out.println("Could not add car, " + currentCar.toString() + " already a car in that spot.");
			} else {
				locationMap.put(key, currentCar);
				vendingMachine.add(currentCar);
			}
		} else {
			System.out.print("Could not add car, " + currentCar.toString() + ", invalid position (out of bounds).");
		}
	}
	
}// end VendingMachineI2 class
