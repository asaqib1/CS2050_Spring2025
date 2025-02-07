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
