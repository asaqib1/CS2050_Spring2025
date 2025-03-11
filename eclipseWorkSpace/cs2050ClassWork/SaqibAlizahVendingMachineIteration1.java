
public class SaqibAlizahVendingMachineIteration1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}//end main
	
	public static void readFromFile() {
		
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
		return  manufacturer + model + year + price;
	}
	
}//end Car class


class vendingMachine {
	
	private Car carsArray[][];
	
	public vendingMachine(int floor, int space, Car carsArray[][]) {
		carsArray = new Car[floor][space];
	}
	
	public void addCar(Car currentCar, int floor, int space) {
		if(carsArray[floor][space] == null) {
			carsArray[floor][space] = currentCar;
		}
		
		else {
			System.out.println("Error: Slot at Floor: " 
								+ floor + " Space: " 
								+ space + " is already occupied");
			System.out.println("Car " + currentCar + " cannot be placed.");
		}
	}
	
	private Car[] flattenArray(Car[][] array) {
		
		int arraySize = array.length * array[0].length;
		Car newArray[] = new Car[arraySize];
		int newArrayCount = 0;
		
		for (int row = 0; row < array.length; row++) {
			for (int column = 0; column < array[row].length; column++) {
				newArray[newArrayCount] = array[row][column];
				newArrayCount++;
			}
		}
		
		return newArray;
	}
	
	private Car[][] unflattenArray(Car[] array, int rows, int cols) {
		
		Car newArray[][] = new Car[rows][cols];
		
		int index = 0;
		
		for (int row = 0; row < rows; row++) {
			
			for (int column = 0; column < cols; column ++) {
				newArray[row][column] = array[index];
				index++;
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
		
		Car unflattenedArray[][] = unflattenArray(flattenedArray, carsArray.length, carsArray[0].length);
		
		//print array out
		for (int row = 0; row < unflattenedArray.length; row++) {
			for (int column = 0; column < unflattenedArray[0].length; column++) {
				System.out.println(unflattenedArray[row][column].getPrice());
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
		
		Car unflattenedArray[][] = unflattenArray(flattenedArray, carsArray.length, carsArray[0].length);
		
		//print array out
		for (int row = 0; row < unflattenedArray.length; row++) {
			for (int column = 0; column < unflattenedArray[0].length; column++) {
				System.out.println(unflattenedArray[row][column].getYear());
			}
		}
		
	}
		
	public void retrieveCar(int floor, int space) {
		System.out.print("Car retrieved from floor " 
							+ floor + " location " 
							+ space + ": ");
		String car = carsArray[floor][space].toString();
		System.out.print(car);
	}
	
	public void displayVendingMachine() {
		for (int row = 0; row < carsArray.length; row ++) {
			System.out.println("Floor " + (row + 1));
			for (int col = 0; col < carsArray[0].length; col ++) {
				if (carsArray[row][col] == null) {
					System.out.println("Space " + (col + 1) + " EMPTY");
				} else {
					System.out.println("Space " + (col + 1) + ": " + carsArray[row][col].toString());
				}
			}
		}
	}
	
}//end vendingMachine class
