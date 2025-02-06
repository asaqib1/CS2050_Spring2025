
public class SaqibTestSimpleRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleRectangle rectangle1 = new SimpleRectangle();
		SimpleRectangle rectangle2 = new SimpleRectangle(1, 1);
		SimpleRectangle rectangle3 = new SimpleRectangle(3, 4);
		
		System.out.println("Rectangle 1 information: \narea - " 
							+ rectangle1.getArea() + " \nperimeter - " 
							+ rectangle1.getPerimeter());
		
		System.out.println("\nRectangle 2 information: \narea - " 
				+ rectangle2.getArea() + " \nperimeter - " 
				+ rectangle2.getPerimeter());
		
		System.out.println("\nRectangle 3 information: \narea - " 
				+ rectangle3.getArea() + " \nperimeter - " 
				+ rectangle3.getPerimeter());
		
	}//end main

}//end SaqibTestSimpleRectangle class

class SimpleRectangle {
	
	private double length;
	private double width;
	
	public SimpleRectangle() {}
	
	public SimpleRectangle(double newLength, double newWidth) {
		this.length = newLength;
		this.width = newWidth;
	}
	
	public double getArea() {
		return length * width;
	}
	
	public double getPerimeter() {
		return 2 * (length + width);
	}
	
	public void setLength(double newLength) {
		this.length = newLength;
	}
	
	public void setWidth(double newWidth) {
		this.width = newWidth;
	}
	
}//end SimpleRectangle class