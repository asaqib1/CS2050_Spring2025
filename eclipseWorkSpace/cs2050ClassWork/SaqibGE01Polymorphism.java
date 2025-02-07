/**
 * 
 */

/**
 * 
 */
public class SaqibGE01Polymorphism {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}//end main

}//end main class

class Animal {
	
	//data fields
	private String name;
	private String food;
	private int weight;
	private int sleep;
	private String location;
	
	public Animal(String name, String food, int weight, int sleep, String location) {
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
		System.out.print("Animal is eating");
	}
	
	public void sleep() {
		System.out.print("Animal is sleeping");
	}
	
	public void swim() {
		System.out.print("Animal is swimming");
	}
	
}//end Animal class

class Bear extends Animal {
	
	public Bear(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat() {
		System.out.print("Bear is eating");
	}
	
	@Override
	public void sleep() {
		System.out.print("Bear is sleeping");
	}
	
	@Override
	public void swim() {
		System.out.print("Bear is swimming");
	}
	
	@Override
	public String toString() {
		return "Bear: Name: " + getName()
				+ " - Weighs: " + getWeight()
				+ " lbs Sleeps: " + getSleep() 
				+ " hours - Location: " + getLocation();
	}
	
}//end Bear class

class Elephant extends Animal {
	public Elephant(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void sleep() {
		System.out.print("Elephant is sleeping");
	}
	
	@Override 
	public String toString() {
		return "Elephant: Name: " + getName()
		+ " - Weighs: " + getWeight()
		+ " lbs Sleeps: " + getSleep() 
		+ " hours - Location: " + getLocation();
	}
	
}//end Elephant class

class Monkey extends Animal {
	
	public Monkey(String name, String food, int weight, int sleep, String location) {
		super(name, food, weight, sleep, location);
	}
	
	@Override
	public void eat() {
		System.out.print("Monkey is eating");
	}
	
	@Override
	public void swim() {
		System.out.print("Monkey is swimming");
	}
	
	@Override 
	public String toString() {
		return "Monkey: Name: " + getName()
		+ " - Weighs: " + getWeight()
		+ " lbs Sleeps: " + getSleep() 
		+ " hours - Location: " + getLocation();
	}
	
}//end Monkey class

class Sloth extends Animal {
	
	public Sloth(String name, String food, int weight, int sleep, String location) {
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
