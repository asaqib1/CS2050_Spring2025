import java.util.ArrayList;

public class BirdLab {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Bird> birdList = new ArrayList<>();
		
	}//end main

}//end BirdLab class

abstract class Bird {
	
	String birdName;
	String swimSpeed;
	
	public abstract void printInterestingFact(ArrayList<Bird> bird);
	
}//end abstract Bird class

interface Swim {
	
	
	
}//end interface Swim

class penguin extends Bird implements Swim{
	
	private int swim;
	
	@Override
	public void printInterestingFact(ArrayList<Bird> bird) {
		for(Bird birds : bird) {
			System.out.print("I can't fly but I'm the fastest swimmer and deepest diver.");
		}
	}
	
	
}//end penguin class
