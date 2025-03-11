import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SaqibAlizahVendingMachineIteration1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the number of floors for the car vending machine: ");
		int floor = input.nextInt();
		System.out.print("Enter the number of spaces for the car vending machine: ");
		int space = input.nextInt();
		
		Car[][] array = new Car[floor][space];
		vendingMachine newMachine = new vendingMachine(floor, space, array);
		
		boolean repeat = true;

		do {
		System.out.printf("%n=== Car Vending Machine Menu ===%n");
		System.out.println("1. Load Car Data");
		System.out.println("2. Display Vending Machine");
		System.out.println("3. Retrieve a Car");
		System.out.println("4. Print Sorted Inventory (Price)");
		System.out.println("5. Print Sorted Inventory (Year)");
		System.out.println("6. Exit");
		
		System.out.printf("%nEnter your choice: ");
		int choice = input.nextInt();
		
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
				repeat = false;
				break;
			
		}
		
		} while (repeat == true);
		
		input.close();
		
	}//end main
	
	public static void readFromFile(String filename, vendingMachine newMachine) throws FileNotFoundException {
		
		//set up scanner to read from file
		Scanner fileScanner = new Scanner(new File(filename));
		
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
		
		fileScanner.close();
	}

}//end main class

class Car {
	
	private int year;
	private double price;
	private String manufacturer;
	private String model;
	
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
	
	public String getMaufacturer() {
		return manufacturer;
	}
	
	public String getModel() {
		return model;
	}
	
	@Override
	public String toString() {
		return  manufacturer + " " + model + " " + year + " - $" + price;
	}
	
}//end Car class


class vendingMachine {
	
	private Car carsArray[][];
	
	public vendingMachine(int floor, int space, Car carsArray[][]) {
		this.carsArray = new Car[floor][space];
	}
	
	public void addCar(Car currentCar, int floor, int space) {
		if (floor < 0 || floor >= carsArray.length || space < 0 || space >= carsArray[0].length) {
			System.out.println("Error: Invalid position at Floor: " + (floor + 1) + " Space: " + (space + 1));
			System.out.println("Can not place car " + currentCar.toString());
		}
		
		else if(carsArray[floor][space] == null) {
			carsArray[floor][space] = currentCar;
		}
		
		else {
			System.out.println("Error: Slot at Floor: " 
								+ (floor + 1) + " Space: " 
								+ (space + 1) + " is already occupied.");
			System.out.println("Car " + currentCar + " cannot be placed.");
		}
	}
	
	private Car[] flattenArray(Car[][] array) {
		
		int nullCount = 0;
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
				if (array[row][column] != null) {
					nullCount++;
				}
			}
		}
		
		Car[] newArray = new Car[nullCount];
		int newArrayCount = 0;
		
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
	
	public void displayPriceSortedCars() {
		
		Car flattenedArray[] = flattenArray(carsArray);
						
		//sort it by price
		for (int i = 1; i < flattenedArray.length; i++) {
			Car key = flattenedArray[i];
			int j = i - 1;
			
			while (j >= 0 && key.getPrice() < flattenedArray[j].getPrice()) {
				flattenedArray[j + 1] = flattenedArray[j];
				j--;
			}
			
			flattenedArray[j + 1] = key;
			
		}
		
		//print array out
		System.out.printf("%nSorted Inventory by Price: %n");
		for (int row = 0; row < flattenedArray.length; row++) {
			if (flattenedArray[row] != null) {
				System.out.println(flattenedArray[row].toString());
			}
		}
	}
	
	public void displayYearSortedCars() {
		
		Car flattenedArray[] = flattenArray(carsArray);
		
		//sort it by year
		for (int i = 1; i < flattenedArray.length; i++) {
			Car key = flattenedArray[i];
			int j = i - 1;
			
			while (j >= 0 && key.getYear() < flattenedArray[j].getYear()) {
				flattenedArray[j + 1] = flattenedArray[j];
				j--;
			}
			
			flattenedArray[j + 1] = key;
			
		}
		
		System.out.printf("Sorted Inventory by Year: %n");
		//print array out
		for (int row = 0; row < flattenedArray.length; row++) {
			if (flattenedArray[row] != null) {
				System.out.println(flattenedArray[row].toString());
			}
		}
		
	}
		
	public void retrieveCar(int floor, int space) {
		if (carsArray[floor][space] != null) {
		System.out.printf("Car retrieved from floor " 
							+ floor + " location " 
							+ space + ": ");
		String car = carsArray[floor - 1][space - 1].toString();
		System.out.printf(car + "%n");
		} else {
			System.out.println("No car located at Floor " + floor + " Location " + space);
		}
	}
	
	public void displayVendingMachine() {
		System.out.println("Inventory Location");
		for (int row = 0; row < carsArray.length; row ++) {
			System.out.println("Floor " + (row + 1) + ": ");
			for (int col = 0; col < carsArray[0].length; col ++) {
				if (carsArray[row][col] == null) {
					System.out.println("	Space " + (col + 1) + " EMPTY");
				} else {
					System.out.println("	Space " + (col + 1) + ": " + carsArray[row][col].toString());
				}
			}
		}
	}
	
}//end vendingMachine class
