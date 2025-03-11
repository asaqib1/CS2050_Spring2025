import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SaqibGE01Polymorphism {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner fileScanner = null;
		
		try {
			
			//set up scanner to read from file
			fileScanner = new Scanner(new File("Animals.txt"));

			//read 1st value for array size
			final int arraySize = fileScanner.nextInt();
			fileScanner.nextLine();
			Animal totalAnimals[] = new Animal[arraySize];

			//read animal information from file
			int index = 0;
			while (fileScanner.hasNext()) {
				String animalType = fileScanner.next().trim();
				String animalName = fileScanner.next().trim();
				String food = fileScanner.next().trim();
				int weight = fileScanner.nextInt();
				int sleep = fileScanner.nextInt();
				String location = fileScanner.nextLine().trim();

				//create animal object and store in array
				Animal newAnimal = null;
				if (animalType.equalsIgnoreCase("bear")) {
					newAnimal = new Bear(animalName, food, weight, sleep, location);
				}
				else if (animalType.equalsIgnoreCase("elephant")) {
					newAnimal = new Elephant(animalName, food, weight, sleep, location);
				}
				else if (animalType.equalsIgnoreCase("monkey")) {
					newAnimal = new Monkey(animalName, food, weight, sleep, location);
				}
				else if (animalType.equalsIgnoreCase("sloth")) {
					newAnimal = new Sloth(animalName, food, weight, sleep, location);
				}
				
				totalAnimals[index] = newAnimal;
				index++;

			}
			
			for (int i = 0; i < totalAnimals.length; i++) {
				System.out.print("Animal[" + i + "] is a");
				if (totalAnimals[i] instanceof Bear) {
					System.out.println(" Bear");
					System.out.println(totalAnimals[i]);
					totalAnimals[i].toString();
					totalAnimals[i].eat();
					totalAnimals[i].sleep();
					totalAnimals[i].swim();
				}
				else if (totalAnimals[i] instanceof Elephant) {
					System.out.println("n Elephant");
					System.out.println(totalAnimals[i]);
					totalAnimals[i].toString();
					totalAnimals[i].eat();
					totalAnimals[i].sleep();
					totalAnimals[i].swim();
				}
				else if (totalAnimals[i] instanceof Monkey) {
					System.out.println(" Monkey");
					System.out.println(totalAnimals[i]);
					totalAnimals[i].toString();
					totalAnimals[i].eat();
					totalAnimals[i].sleep();
					totalAnimals[i].swim();
				}
				else if (totalAnimals[i] instanceof Sloth) {
					System.out.println(" Sloth");
					System.out.println(totalAnimals[i]);
					totalAnimals[i].toString();
					totalAnimals[i].eat();
					totalAnimals[i].sleep();
					totalAnimals[i].swim();
				}
			}

		} catch (FileNotFoundException e) {
			System.out.print("File was not found");
			
		} finally {
			if (fileScanner != null){
				fileScanner.close();
			}		
		}
		
		
	}//end main

}//end main class

class AnimalP {
	
	//data fields
	private String name;
	private String food;
	private int weight;
	private int sleep;
	private String location;
	
	public AnimalP(String name, String food, int weight, int sleep, String location) {
		this.name = name;
		this.food = food;
		this.weight = weight;
		this.sleep = sleep;
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getFood() {
		return food;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getSleep() {
		return sleep;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void eat() {
		System.out.println("Animal is eating");
	}
	
	public void sleep() {
		System.out.println("Animal is sleeping");
	}
	
	public void swim() {
		System.out.println("Animal is swimming");
	}
	
}//end Animal class

class BearP extends AnimalP {
	
	public BearP(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat() {
		System.out.println("Bear is eating");
	}
	
	@Override
	public void sleep() {
		System.out.println("Bear is sleeping");
	}
	
	@Override
	public void swim() {
		System.out.println("Bear is swimming");
	}
	
	@Override
	public String toString() {
		return "Bear: Name: " + getName()
				+ " - Weighs: " + getWeight()
				+ " lbs Sleeps: " + getSleep() 
				+ " hours - Location: " + getLocation();
	}
	
}//end Bear class

class ElephantP extends AnimalP {
	public ElephantP(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void sleep() {
		System.out.println("Elephant is sleeping");
	}
	
	@Override 
	public String toString() {
		return "Elephant: Name: " + getName()
		+ " - Weighs: " + getWeight()
		+ " lbs Sleeps: " + getSleep() 
		+ " hours - Location: " + getLocation();
	}
	
}//end Elephant class

class MonkeyP extends AnimalP {
	
	public MonkeyP(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat() {
		System.out.println("Monkey is eating");
	}
	
	@Override
	public void swim() {
		System.out.println("Monkey is swimming");
	}
	
	@Override 
	public String toString() {
		return "Monkey: Name: " + getName()
		+ " - Weighs: " + getWeight()
		+ " lbs Sleeps: " + getSleep() 
		+ " hours - Location: " + getLocation();
	}
	
}//end Monkey class

class SlothP extends AnimalP {
	
	public SlothP(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override 
	public String toString() {
		return "Sloth: Name: " + getName()
		+ " - Weighs: " + getWeight()
		+ " lbs Sleeps: " + getSleep() 
		+ " hours - Location: " + getLocation();
	}
	
}//end Sloth class
