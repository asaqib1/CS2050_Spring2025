
public class SaqibGE012DArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Declaring an array of type CarP objects
		CarP[][] CarsArray = new CarP[2][3];
		CarsArray[0][0] = new CarP("Ford");
		CarsArray[0][1] = new CarP("Dodge");
		CarsArray[0][2] = new CarP("Toyota");
		CarsArray[1][0] = new CarP("Hyundai");
		CarsArray[1][1] = new CarP("Chevrolet");
		CarsArray[1][2] = new CarP("Subaru");
		
		//prints out the column header
		for (int col = 0; col < CarsArray[0].length; col++) {
			System.out.printf("	col[" + col + "] ");
		}
		
		System.out.println("");
		
		//prints out the row headers plus the object
		for (int row = 0; row < CarsArray.length; row++) {
			System.out.print("row[" + row + "] ");
				for (int column = 0; column < CarsArray[row].length; column++) {
					System.out.printf(CarsArray[row][column].toString() + " ");
				}
			
				System.out.println();
			}	

	}//end main
	


}//end public class

class CarP{
	private String make;

	public CarP() { 
		this.make = "Unknown"; 
	}
	
	public CarP(String make) {
 	   this.make = make;
	}

	public void printMake() {
		System.out.print(this.make + " ");
	}
	
	@Override
	public String toString() {
		return make;
	}
} // end Car