/**
 * 
 */

/**
 * 
 */
public class DoubleLinkedListLab
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		DoublyLinkedListL18 list = new DoublyLinkedListL18();

		System.out.println("Inserting at the End:");
		list.insertAtEnd(10);
		list.insertAtEnd(20);
		list.insertAtEnd(30);
		list.printForward();
		list.printBackward();

		System.out.println("\nInserting at the Head:");
		list.insertAtHead(5);
		list.insertAtHead(1);
		list.printForward();
		list.printBackward();

		System.out.println("\nDeleting Node 20:");
		list.deleteNode(20);
		list.printForward();
		list.printBackward();

		System.out.println("\nReversing the List:");
		list.reverseList();
		list.printForward();
		list.printBackward();

	}

}

class NodeL18
{
	int data;
	NodeL18 next;
	NodeL18 prev;

	public NodeL18(int data)
	{
		this.data = data;
		this.next = null;
		this.prev = null;
	}
}

class DoublyLinkedListL18
{
	NodeL18 head;

	// Insert at the end
	public void insertAtEnd(int data)
	{
		// Create a new node with given data 
		NodeL18 newNode = new NodeL18(data);

		if (head == null)
		{
			// If list empty, newNode becomes head
			head = newNode;
		} else
		{
			// Traverse to end of list
			NodeL18 temp = head;
			while (temp.next != null)
			{
				temp = temp.next;
			}

			// Link new node to end of list
			temp.next = newNode;
			newNode.prev = temp; // Backward link
		}
	}

	// Print the list forward
	public void printForward()
	{
		// Sets head
		NodeL18 current = head;
		System.out.print("Forward: ");

		// Traverse to end of list
		while (current != null)
		{
			// Print out each nodes data and move on to next node
			System.out.print(current.data + " ⇄ ");
			current = current.next;
		}

		// When we reach the end of the list, print out null
		System.out.println("null");
	}

	public void insertAtHead(int data)
	{
		System.out.println("Insert at the Head:");

		NodeL18 newNode = new NodeL18(data);
		if (head != null) {
			newNode.next = head; 
			head.prev = newNode;
		}
		head = newNode; 
	}

	public void deleteNode(int data)
	{
		System.out.println("Delete node:");

		// Start from head
		// Keep track of previous node
		NodeL18 current = head;
		NodeL18 previous = null;
		boolean found = false;

		// Traverse the list until the node is found or the end is reached
		while (current != null && !found) {
			if (current.data == data) {
				found = true; // Node found
			} else {

				// Move onto next node
				previous = current;
				current = current.next;
			}
		}

		if(found) {
			if (previous == null) {

				// Delete head node by redefining head as next node
				head = current.next;
				if (head != null) {
					head.prev = null; // Disconnect backward link
				}
				
			} else {
				
				// Node to delete is in the middle or end
				previous.next = current.next;
				if (current.next != null) {
					current.next.prev = previous; // Fix backward link
				}
			}
		}
	}

	public void reverseList()
	{
		System.out.println("Reverse list:");
		NodeL18 current = head;
		NodeL18 temp = null;
		
		// Traverse and swap next and prev for each node
		while (current != null) {
			temp = current.prev;
			current.prev = current.next;
			current.next = temp;
			current = current.prev; // move to the next node (which was prev before swap)
		}
		
		// After loop, temp will be at the new head (old tail)
		if (temp != null) {
			head = temp.prev;
		}
		
	}

	// Print the list backward
	public void printBackward()
	{
		System.out.println("Print backward:");
		
	    NodeL18 current = head;
	    
		if (head == null) {
	        System.out.println("List is empty.");
	    }
		
		// Traverse to the last node
	    while (current.next != null) {
	        current = current.next;
	    }
	    
	    // Traverse backward from the last node
	    while (current != null) {
	        System.out.print(current.data + " ⇄ ");
	        current = current.prev;
	    }
	    
	    System.out.println("null");
	}
}
