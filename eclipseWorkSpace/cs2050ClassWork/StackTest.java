/*
 * Stack Example
 * Add comments to explain the code
 */

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class StackTest {
    public static void main(String[] args) {

    	Stack<String> strings = new Stack<>();
    	
    	// Push adds Hi, Hello, and Goodbye to stack
    	strings.push("Hi");
    	System.out.println(strings);
    	
    	strings.push("Hello");
    	System.out.println(strings);
    	
    	strings.push("Goodbye");
    	System.out.println(strings);
    	
    	// Retrieves and returns the last element inserted on the stack
    	String firstElement = strings.peek();
    	System.out.println(firstElement);
    	
    	// Deletes the last element inserted into the stack
    	strings.pop();
    	System.out.println(strings);
    	
    	// Retrieves and returns the first element on the stack
    	String firstElement2 = strings.peek();
    	System.out.println(firstElement2);
    	
    	// Gets the size of the list and returns it
    	int listSize = strings.size();
    	System.out.println("The size of the array is: " + listSize);
    	
    	// Check whether the array is empty or not and return true/false
    	boolean empty = strings.isEmpty();
    	System.out.println("Is the array empty? " + empty);
    	
    	
        Stack<Integer> stack = new Stack<>();


        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        

        System.out.println("Stack after pushing: " + stack);


        int popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Stack after popping: " + stack);


        int topElement = stack.peek();
        System.out.println("Top element: " + topElement);


        System.out.println("Is stack empty? " + stack.isEmpty());
        
        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Stack after popping: " + stack);
        
        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Stack after popping: " + stack);
        
        popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Stack after popping: " + stack);
    }
}



class Stack<T> {
	

    private ArrayList<T> items; 


    public Stack() {
        this.items = new ArrayList<>();
    }


    public boolean isEmpty() {
        return this.items.isEmpty();
    }


    public void push(T item) {
        this.items.add(item);
    }


    public T pop() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return this.items.remove(items.size() - 1);
    }


    public T peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return this.items.get(items.size() - 1);
    }


    public int size() {
        return this.items.size();
    }


    @Override
    public String toString() {
        if (!this.items.isEmpty()) {
            return "bottom ->" + this.items.toString() + "<- top";
        } else {
            return "<<empty stack>>";
        }
    }
}

