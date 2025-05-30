/*
 * Car Vending Machine Program - Iteration 01
 * ------------------------------------------
 * This program simulates a car vending machine where cars are stored
 * in a 2D array representing floors and spaces. It allows the user to:
 * - Display all cars in the vending machine
 * - Retrieve a car from a specific floor and space
 * - Sort and display cars by price or year
 * 
 * Cars are loaded from a text file which should contain
 * details about each car (make, model, year, price, and location).
 *
 * Author: Alizah Saqib
 * Course: CS 2050
 * Instructor: Deborah Harding
 * Date: 03/28/2025
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SaqibAlizahVendingMachineIteration1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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


		VendingMachine newMachine = new VendingMachine(floor, space);
		
		int choice;

		// Main menu loop: Repeatedly displays the vending machine menu
		// Switch: Performs actions based on user selection
		do {
			System.out.printf("%n=== Car Vending Machine Menu ===%n");
			System.out.println("1. Load Car Data");
			System.out.println("2. Display Vending Machine");
			System.out.println("3. Retrieve a Car");
			System.out.println("4. Print Sorted Inventory (Price)");
			System.out.println("5. Print Sorted Inventory (Year)");
			System.out.println("6. Exit");

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
				System.out.print("Enter floor to retrieve car: ");
				int floorNumber = input.nextInt();
				System.out.print("Enter location to retrieve car: ");
				int spaceNumber = input.nextInt();
				newMachine.retrieveCar(floorNumber, spaceNumber);
				break;
			case 4:
				newMachine.displayPriceSortedCars();
				break;
			case 5:
				newMachine.displayYearSortedCars();
				break;
			case 6:
				System.out.print("Exiting program. Goodbye!");
				break;

			}

		} while (choice != 6);

		input.close();

	}//end main


	/**
	 * Reads car information from the specified file.
	 * Each line in the file should include: row number, column number, year, price, make, and model.
	 * For each line, creates a Car object and adds it to the vending machine's car array.
	 * @param filename (the name of the file containing the car data)
	 * @param newMachine (vending machine object to which cars will be added)
	 */
	public static void readFromFile(String filename, VendingMachine newMachine) {

		// Initialize fileScanner to null so it can be correctly checked and closed in the finally block
		Scanner fileScanner = null;

		try {
			fileScanner = new Scanner(new File(filename));

			// Reads each line from the file to extract car details 
			// This includes location, year, price, make, and model
			// Then it creates a Car object with this data and adds it to the vending machine array
			while (fileScanner.hasNextInt()) {
				int rowNumber = fileScanner.nextInt();
				int colNumber = fileScanner.nextInt();
				int year = fileScanner.nextInt();
				double price = fileScanner.nextDouble();
				String make = fileScanner.next();
				String model = fileScanner.next();

				Car newCar = new Car(year, price, make, model);
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

}//end main class

class Car {

	private int year;
	private double price;
	private String manufacturer;
	private String model;

	/**
	 * Constructor which initializes a Car object with year, price, manufacturer, and model
	 * @param year (the year the car was manufactured)
	 * @param price (price of the car)
	 * @param manufacturer (the name of the car's manufacturer)
	 * @param model (model name of the car)
	 */
	public Car(int year, double price, String manufacturer, String model) {
		this.year = year;
		this.price = price;
		this.manufacturer = manufacturer;
		this.model = model;
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

	// Returns a formatted string representation of the car
	@Override
	public String toString() {
		return  manufacturer + " " + model + " " + year + " - $" + price;
	}

}//end Car class


class VendingMachine {

	private Car carsArray[][];
	private int rows;
	private int columns;

	/**
	 * Constructor which initializes the vending machine with a given number of floors and spaces
	 * @param floor (number of rows in the 2D array)
	 * @param space (number of columns in the 2D array)
	 */
	public VendingMachine(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.carsArray = new Car[rows][columns];
	}

	/**
	 * Attempts to add a Car object to the specified floor and space in the 2D array
	 * If the location is invalid or already occupied, an error message is printed
	 * @param currentCar (Car to be added)
	 * @param floor (the row in which the car will be added to)
	 * @param space (the column in which the car will be added to)
	 */
	public void addCar(Car currentCar, int floor, int space) {

		// Check if the given floor and space are in bounds
		if(floor >= 0 && floor < rows && space >= 0 && space < columns) {

			// Places the car at the specified location if it is empty
			if(carsArray[floor][space] == null) {
				carsArray[floor][space] = currentCar;

			} else {

				// If the spot is already occupied, prints an error and does not place the car
				System.out.println("Error: Slot at Floor: " 
						+ (floor + 1) + " Space: " 
						+ (space + 1) + " is already occupied.");
				System.out.println("Car " + currentCar + " cannot be placed.");
			}

			// Otherwise, prints out an error message
		} else {

			System.out.println("Error: Invalid position at Floor: " + (floor + 1) + " Space: " + (space + 1));
			System.out.println("Can not place car " + currentCar.toString());
		}		

	}

	/**
	 * Takes a 2D array and copies it's non-null elements to a new 1D array
	 * @param array (2D array to be flattened)
	 * @return a 1D array containing all non-null Car objects from original 2D array 
	 */
	private Car[] flattenArray(Car[][] array) {

		// Counts the number of non-null Car objects in the 2D array
		int notNullSpaces = 0;
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
				if (array[row][column] != null) {
					notNullSpaces++;
				}
			}
		}

		// Creates a new 1D array to hold only the non-null Car objects and tracks the current index
		Car[] newArray = new Car[notNullSpaces];
		int newArrayCount = 0;

		// Iterates through the 2D array and places its Car objects in the 1D array
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
				if (array[row][column] != null) {
					newArray[newArrayCount] = array[row][column];
					newArrayCount++;
				}
			}
		}

		return newArray;
	}

	/**
	 * Iterates through a 1D array and prints out its contents
	 * @param array
	 */
	private void printSortedArray(Car[] array) {

		for (int row = 0; row < array.length; row++) {
			if (array[row] != null) {
				System.out.println(array[row].toString());
			}
		}
	}

	/**
	 * Sorts the non-null Car objects in the array by price in ascending order using insertion sort
	 * Prints the sorted inventory by iterating through it
	 */
	public void displayPriceSortedCars() {

		// Returns the flattened 2D array
		Car flattenedArray[] = flattenArray(carsArray);

		// Sorts the flattened array by price using insertion sort
		for (int i = 1; i < flattenedArray.length; i++) {
			Car key = flattenedArray[i];
			int j = i - 1;

			while (j >= 0 && key.getPrice() < flattenedArray[j].getPrice()) {
				flattenedArray[j + 1] = flattenedArray[j];
				j--;
			}

			flattenedArray[j + 1] = key;
		}

		// Iterates through the flattened and sorted array and prints out it's contents
		System.out.printf("%nSorted Inventory by Price: %n");
		printSortedArray(flattenedArray);
	}

	/**
	 * Sorts the non-null Car objects in the array by year in ascending order using insertion sort
	 * Prints the sorted inventory by iterating through it
	 */
	public void displayYearSortedCars() {

		// Returns the flattened 2D array
		Car flattenedArray[] = flattenArray(carsArray);

		// Sorts the flattened array by year using insertion sort
		for (int i = 1; i < flattenedArray.length; i++) {
			Car key = flattenedArray[i];
			int j = i - 1;

			while (j >= 0 && key.getYear() < flattenedArray[j].getYear()) {
				flattenedArray[j + 1] = flattenedArray[j];
				j--;
			}

			flattenedArray[j + 1] = key;

		}

		// Iterates through the flattened and sorted array and prints out it's contents
		System.out.printf("Sorted Inventory by Year: %n");
		printSortedArray(flattenedArray);

	}

	/**
	 * Prints the Car at the specified floor and space if it exists
	 * Otherwise prints an error message if no car is found at the location
	 * @param floor (row number of car to retrieve from)
	 * @param space (column number of car to retrieve from)
	 */
	public void retrieveCar(int floor, int space) {

		// Checks if there is a Car object at the given location
		// If there is it prints the Car's information 
		// If there isn't, it prints out a different message

		int row = floor - 1;
		int col = space - 1;

		if (carsArray[row][col] != null) {
			System.out.printf("Car retrieved from floor " 
					+ floor + " location " 
					+ space + ": ");
			String car = carsArray[row][col].toString();
			System.out.printf(car + "%n");

		} else {
			System.out.println("No car located at Floor " + floor + " Location " + space);
		}
	}

	/**
	 * Prints each floor and space
	 * Shows the car details if present, or marks the space as EMPTY if no car is stored there	 
	 */
	public void displayVendingMachine() {

		// Iterates through the 2D arrays rows and columns and prints out the information for each spot

		System.out.println("Inventory Location");
		for (int row = 0; row < carsArray.length; row ++) {
			System.out.println("Floor " + (row + 1) + ": ");

			for (int col = 0; col < carsArray[0].length; col ++) {
				System.out.print("	Space " + (col + 1));

				if (carsArray[row][col] == null) {
					System.out.println(" EMPTY");

				} else {
					System.out.println(": " + carsArray[row][col].toString());
				}
			}
		}
	}

}//end vendingMachine class
