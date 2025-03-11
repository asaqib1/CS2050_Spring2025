
/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

//Main class demonstrating file reading and exception handling

public class GE01AnimalPolyMorphism
{

	public static void main(String[] args)
	{
		try
		{
			Animal[] animals = readAnimalsFromFile("Animals.txt");

			// Now demonstrate the power of polymorphism!
			for (int i = 0; i < animals.length; i++)
			{
				System.out.println();
				if (animals[i] instanceof Bear)
				{
					System.out.println("Animal[" + i + "] is a Bear");
				} else if (animals[i] instanceof Elephant)
				{
					System.out.println("Animal[" + i + "] is an Elephant");
				} else if (animals[i] instanceof Monkey)
				{
					System.out.println("Animal[" + i + "] is a Monkey");
				} else if (animals[i] instanceof Sloth)
				{
					System.out.println("Animal[" + i + "] is a Sloth");
				}

				// Student Note: You can call the toString method explicitly, but if you leave
				// the call off,
				// the toString method is called implicitly as shown in the code below.
				// Explicit call: System.out.println(animals[i].toString());
				// Or: System.out.println(animals[i]);
				System.out.println(animals[i].toString());

				animals[i].eat();
				animals[i].sleep();
				animals[i].swim();
			} // End for loop

		} catch (FileNotFoundException e)
		{
			System.out.println("Error: File not found! Please check the file path.");
		} catch (InputMismatchException e)
		{
			System.out.println("Error: Incorrect file format! Please check the contents of the file.");
		} catch (Exception e)
		{
			System.out.println("Unexpected error occurred: " + e.getMessage());
		}
	}

	/**
	 * Reads animal data from a file and returns an array of Animal objects.
	 */
	public static Animal[] readAnimalsFromFile(String filename) throws FileNotFoundException
	{
		File inputFileName = new File(filename);
		Scanner inputFile = null;

		try
		{
			inputFile = new Scanner(inputFileName);

			// Ensure valid input for number of animals
			// I DIDN"T EXPECT YOU TO IMPLEMENT
			if (!inputFile.hasNextInt())
			{
				throw new InputMismatchException("First line must be an integer representing the number of animals.");
			}
			int numAnimals = inputFile.nextInt();
			inputFile.nextLine(); // Move to next line

			// Handle invalid number of animals
			if (numAnimals <= 0)
			{
				throw new IllegalArgumentException("Invalid number of animals. It must be greater than zero.");
			}

			Animal[] animals = new Animal[numAnimals];

			for (int i = 0; i < animals.length; i++)
			{
				if (!inputFile.hasNext())
				{
					throw new InputMismatchException("Unexpected end of file while reading animal data.");
				}

				String type = inputFile.next();
				String name = inputFile.next();
				String food = inputFile.next();
				int weight = inputFile.nextInt();
				int sleep = inputFile.nextInt();
				String location = inputFile.nextLine().trim();

				switch (type)
				{
				case "Bear":
					animals[i] = new Bear(name, food, weight, sleep, location);
					break;
				case "Elephant":
					animals[i] = new Elephant(name, food, weight, sleep, location);
					break;
				case "Monkey":
					animals[i] = new Monkey(name, food, weight, sleep, location);
					break;
				case "Sloth":
					animals[i] = new Sloth(name, food, weight, sleep, location);
					break;
				default:
					System.out.println("Warning: Unknown animal type '" + type + "'. Skipping entry.");
					i--; // Retry the same index
					break;
				}
			}

			return animals;

		} finally // Always runs to clean up such as closing scanner no matter if exception or not
		{
			if (inputFile != null)
			{
				inputFile.close(); // Ensuring Scanner is closed in case of an exception
			}
		}
	}

}// end public class GE01AnimalPolyMorphism

// Superclass Animal demonstrating Inheritance and Polymorphism
class Animal
{

	// Instance variables for an Animal
	private String name;
	private String food;
	private int weight;
	private int sleep;
	private String location;

	// Student Note: There is NO NEED for a default constructor
	// In this assignment, it is never used and could be left off without hurting
	// anything.
	// Developers many times include the default constructor because some Java
	// libraries
	// require the existence of a default constructor, such as libraries for
	// serialization.
	// In any case, for this assignment, I have left the default constructor off.

	// Constructor that initializes the instance variables
	public Animal(String name, String food, int weight, int sleep, String location)
	{
		this.name = name;
		this.food = food;
		this.weight = weight;
		this.sleep = sleep;
		this.location = location;
	}

	// Return animal's name
	public String getName()
	{
		return name;
	}

	// Return animal's food
	public String getFood()
	{
		return food;
	}

	// Return animal's weight
	public int getWeight()
	{
		return weight;
	}

	// Return animal's sleep time
	public int getSleep()
	{
		return sleep;
	}

	// Return animal's location
	public String getLocation()
	{
		return location;
	}

	// Make animal eat
	public void eat()
	{
		System.out.println("Animal is eating");
	}

	// Make animal sleep
	public void sleep()
	{
		System.out.println("Animal is sleeping - do not disturb");
	}

	// Make animal swim
	public void swim()
	{
		System.out.println("Animal is swimming");
	}

}

// Subclasses inheriting from Animal
class Bear extends Animal
{
	public Bear(String name, String food, int weight, int sleep, String location)
	{
		// You could create setters in Animal and call them to set the values, but
		// calling
		// super is the way this assignment is handling setting the instance variables.
		// Both approaches are used in industry.
		super(name, food, weight, sleep, location);
	}

	@Override
	public void eat()
	{
		System.out.println("Bear is eating " + getFood());
	}

	@Override
	public void sleep()
	{
		System.out.println("Bear is sleeping " + getSleep() + " hours");
	}

	@Override
	public void swim()
	{
		System.out.println("Bear is swimming");
	}

	@Override
	public String toString()
	{
		return "Bear: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep()
				+ " hours - Location: " + getLocation();
	}

}// bear

class Elephant extends Animal
{
	public Elephant(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}

	@Override
	public void sleep()
	{
		System.out.println("Elephant is sleeping " + getSleep() + " hours");
	}

	@Override
	public String toString()
	{
		return "Elephant: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep()
				+ " hours - Location: " + getLocation();
	}
}// Elephant

class Monkey extends Animal
{
	public Monkey(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}

	@Override
	public void eat()
	{
		System.out.println("Monkey is eating " + getFood());
	}

	@Override
	public void swim()
	{
		System.out.println("Monkey is swimming");
	}

	@Override
	public String toString()
	{
		return "Monkey: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep()
				+ " hours - Location: " + getLocation();
	}
}// Monkey

class Sloth extends Animal
{
	public Sloth(String name, String food, int weight, int sleep, String location)
	{
		super(name, food, weight, sleep, location);
	}

	@Override
	public String toString()
	{
		return "Sloth: Name: " + getName() + " - Weighs: " + getWeight() + " lbs - Sleeps: " + getSleep()
				+ " hours - Location: " + getLocation();
	}
}