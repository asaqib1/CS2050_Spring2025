
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//2D array in memory
		int[][] arrayOfArrays = new int[2][3];

	}//end main

}//end Test class


abstract class Danimal {
	
	String name;
	
	Danimal(String name) {
		this.name = name;
	}
	
	abstract void makeSound();
	
	void sleep() {
		System.out.println(name + " is sleeping.");
	}
	
}//end abstract class