import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Add comments
 */

public class TestShapesInheritance
{

	public static void main(String[] args) {
		CircleFromSimpleGeometricShape circle = 
				new CircleFromSimpleGeometricShape(1);
		System.out.println("circle toString: " + circle.toString());
		System.out.println("The color is " + circle.getColor());
		System.out.printf("The radius is  %.2f \n", circle.getRadius());
		System.out.printf("The area is  %.2f \n", circle.getArea());
		System.out.printf("The diameter is  %.2f \n", circle.getDiameter());
		
		RectangleFromSimpleGeometricShape rectangle1 = new RectangleFromSimpleGeometricShape();
		System.out.printf("\nRectangle 1 information: ");
		System.out.print("\nThe width is " + rectangle1.getWidth());
		System.out.print("\nThe height is " + rectangle1.getHeight());
		System.out.print("\nThe area is " + rectangle1.getArea());
		System.out.println("\nThe perimeter is " + rectangle1.getPerimeter());
		RectangleFromSimpleGeometricShape rectangle2 = new RectangleFromSimpleGeometricShape(2, 4);
		System.out.printf("\nRectangle 2 information: ");
		System.out.print("\nThe width is " + rectangle2.getWidth());
		System.out.print("\nThe height is " + rectangle2.getHeight());
		System.out.print("\nThe area is " + rectangle2.getArea());
		System.out.println("\nThe perimeter is " + rectangle2.getPerimeter());
		RectangleFromSimpleGeometricShape rectangle3 = new RectangleFromSimpleGeometricShape(10, 5, "Yellow", true);
		System.out.printf("\nRectangle 3 information: ");
		System.out.print("\nThe width is " + rectangle3.getWidth());
		System.out.print("\nThe height is " + rectangle3.getHeight());
		System.out.printf("\nThe color is " + rectangle3.getColor());
		System.out.print("\nThe area is " + rectangle3.getArea());
		System.out.println("\nThe perimeter is " + rectangle3.getPerimeter());
		
				
	}//end main

}//end TestShapeInheritance Class



/**
 * Simple Geometric Shape Superclass
 */
class SimpleGeometricShape {
	private String color = "white";
	private boolean filled;
	private java.util.Date dateCreated;


	/**
	 * Construct a default geometric object
	 */
	public SimpleGeometricShape() {
		dateCreated = new java.util.Date();
	}


	/**
	 * Construct a geometric object with the specified color and filled 
	 * @param String color
	 * @param boolean filled
	 */
	public SimpleGeometricShape(String color, boolean filled) {
		dateCreated = new java.util.Date();
		this.color = color;
		this.filled = filled;
	}

	/**
	 * get current color
	 * @return String color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Set new color
	 * @param String color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Since filled is boolean, 
	 *    its get method is named isFilled
	 * get current filled value 
	 * @return boolean filled 
	 */
	public boolean isFilled() {
		return filled;
	}

	/**
	 * Set a new filled
	 * @param boolean filled
	 */
	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	/**
	 * Get dateCreated
	 * @return dateCreated
	 */
	public java.util.Date getDateCreated() {
		return dateCreated;
	}
	
	@Override
	public String toString() {
		System.out.println("In SimpleGeometricShape toString method ");
		return "created on " + dateCreated + "\ncolor: " + color +
							" and filled: " + filled;
	}

}//end SimpleGeometricShape class


/**
 * Add comments
 */
class CircleFromSimpleGeometricShape 
extends SimpleGeometricShape {
	private double radius;

	/**
	 * Construct a default circle object
	 */
	public CircleFromSimpleGeometricShape() {
	}

	/**
	 * 
	 * @param radius
	 */
	public CircleFromSimpleGeometricShape(double radius) {
		this.radius = radius;
	}

	public CircleFromSimpleGeometricShape(double radius, 
			String color, boolean filled) {
		this.radius = radius;
		setColor(color);
		setFilled(filled);
	}


	/**
	 * Get radius
	 * @return double radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 *  Set a new radius
	 * @param double radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Get Area 
	 * @return double 
	 */
	public double getArea() {
		return radius * radius * Math.PI;
	}

	/**
	 * Get Diameter 
	 * @return double 
	 */
	public double getDiameter() {
		return 2 * radius;
	}

	/**
	 * Get Perimeter 
	 * @return double 
	 */
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}

}//end CircleFromSimpleGeometricShape class

class RectangleFromSimpleGeometricShape
extends SimpleGeometricShape {
	
	private double width;
	private double height;
	private static int rectangleCount = 0; 

	public RectangleFromSimpleGeometricShape() {
		rectangleCount ++;
	}
	
	public RectangleFromSimpleGeometricShape(double width, double height) {
		this.width = width;
		this.height = height;
		rectangleCount ++;
	}
	
	public RectangleFromSimpleGeometricShape(double width, double height, String color, boolean filled) {
		this.width = width;
		this.height = height;
		setColor(color);
		setFilled(filled);
		rectangleCount ++;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight( ) {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	
	public double getArea() {
		return width * height;
	}
	
	public double getPerimeter() {
		return 2 * (width * height);
	}
	
	public static int getNumberOfRectangles() {
		return rectangleCount;
	}
	
	public static void writeRectangleInfoToFile(RectangleFromSimpleGeometricShape rectangle) throws IOException {
		File rectangleFile = new File("rectangles.txt");
		
		PrintWriter resultsFile = new PrintWriter(rectangleFile);
		
		resultsFile.println("Rectangle information: ");
		resultsFile.print("\nThe area is " + rectangle.getArea());
		resultsFile.println("\nThe perimeter is " + rectangle.getPerimeter());
		
		resultsFile.close();
		
		System.out.print("The file is located at: " + rectangleFile.getAbsolutePath());
	}
	
}//end RectangleFromSimpleGeometricShape class



