/**
 * 
 */

/**
 * 
 */
public class FixSinglyLinkedOrderedList
{

	// Test the Singly Linked List
	public static void main(String[] args)
	{


		SinglyLinkedListFix list = new SinglyLinkedListFix();
		list.insertNode(4);
		list.insertNode(2);
		list.insertNode(8);
		list.insertNode(3);

		list.printList();
		list.deleteNode(0);
		list.deleteNode(3);
		list.printList();


	}

}


class NodeFix {
    int data;
    NodeFix next;

    public NodeFix(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedListFix {
	NodeFix head;

    public void insertNode(int number) {
    	
    	// Creates a new node with given value
    	// Set up pointers for traversal
    	NodeFix newNode = new NodeFix(number);
    	NodeFix current = head;
    	NodeFix previous = null;

    	// Traverse list until correct spot is found
        // (stop when current is null or current.data >= number)
        while (current != null && current.data < number) {
            previous = current;
            current = current.next;
        }
        
        // Inserts node at the beginning (as the head)
        if (previous == null) {
            newNode.next = head;
            head = newNode;
        } else {
        	
        	// Inserts between previous and current
            previous.next = newNode;
            newNode.next = current; // Connect newNode to rest of list

        }
    }

    public void deleteNode(int number) {
    	
    	// Sets the head of the list
    	NodeFix current = head;
    	NodeFix previous = null;

    	// Goes through the list until the matching node is found
    	// Or until the end of the list is reached
        while (current != null && current.data != number) { 
        	
        	// Keeps track of the previous node
        	// Moves to the next node
            previous = current;
            current = current.next;
        }
        
        // Check if the node was found (i.e., we didn't reach the end) 
        if (current != null) {
        	
        	// If the correct node is the head
        	// Redefine the head as the next node
        	if (previous == null) {
        		head = current.next;
        	} else {
        		
        		// Otherwise delete the node by bypassing it
        		previous.next = current.next;
        	}
        } 
    }

    public void printList() {
    	
    	// Starts at head
    	NodeFix current = head;
    	
    	// While not at the end of the list, print out it's data
    	// And move to the next node
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        
        // When end of the list is reached, print out null
        System.out.println("null");
    }
}