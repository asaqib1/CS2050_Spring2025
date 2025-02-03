
public class SaqibTestSimpleRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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