/**
 * 
 */

/**
 * 
 */
public class GE02SampleCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	int[] array = {10, 7, 2, 1, 8};
	selectionSort(array);
	for (int i = 0; i < array.length; i ++) {
		System.out.println(" index " + i + " = " + array[i]);
	}
	
	int[] array2 = {2,5,7,2400};
	int result = binarySearch(array2, 2400);
	System.out.println(result);
	
	System.out.print(linearSearch(array2, 5));
	
	int[][] Array = {{2, 3, 1, 10}, {11, 50, 8, 4}};
	flattenArray(Array);
		
	}//end of main
	
	/**
	 * 
	 */
	public static void selectionSort(int[] array) {
        
        //Loops through array to put each element in correct position
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i; // Assume first element is the minimum
            
            //Finds smallest element in unsorted portion of array
            for(int j = minIndex + 1; j < array.length; j++) {
            	
            	if (array[j] < array[minIndex]) {
            		minIndex = j; //updates minimum
            	}
            }
            
            //Swaps smallest element with first element in unsorted portion
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
	
	public static void insertionSort(int[] array) {
		
		for (int i = 1; i < array.length; i++) {
	        // Store the element to be added into the sorted portion of the array
	        int key = array[i];

	        //store previous element in the sorted portion to compare to
	        int j = i - 1;

	        // shift elements larger than the key one to the right
	        // makes space for key element to be inserted
	        while (j >= 0 && key < array[j]) {
	            array[j + 1] = array[j]; // Shift element to the right
	            j--;
	        }
	        
	        //Place key in empty space
	        array[j + 1] = key;
	    }
	}
	
	public static int binarySearch(int[] array, int key) {
		// Sets index of the two pointers
		int low = 0;
		int high = array.length - 1;
		
		// Checks to see if all search options have been exhausted
		while (high >= low) {
			
			// Defines middle of array
			int mid = (high + low) / 2;
			
			// Returns key index if it is the same as array at index mid
			if (key == array[mid]) {
				return mid;
			}
			
			// If the key is greater than the element at index mid
			// Chops off the 1st part of the array 
			else if (key > array[mid]) {
				low = mid + 1;
			}
			
			// If they key is smaller than the element at index mid
			// Chops off the 2nd part of the array
			else if (key < array[mid]) {
				high = mid - 1;
			}
			
		}
		//returns -1 if key not found
		return -1;
	}
	
	public static int linearSearch(int[] array, int key) {
		
		// This for loop is iterating through the array
		for (int i = 0; i < array.length; i++) {
			
			// If the key is found then the index is returned
			if (array[i] == key) {
				return i;
			}
		}
		
		// If key is not found, -1 is returned
		return -1;
	}
	
	public static void flattenArray(int[][] array) {
	//flattening a 2D array
	//iterate through it and add each Car object to a new 1D array
		
		int arraySize = array.length * array[0].length;
		int newArray[] = new int[arraySize];
		int newArrayCount = 0;
		
		for (int row = 0; row < array.length; row++) {
			
			for (int column = 0; column < array[row].length; column++) {
				
				newArray[newArrayCount] = array[row][column];
				
				newArrayCount++;
			}
		}
		
	}
	
}//end public class
