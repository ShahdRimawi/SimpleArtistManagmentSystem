package Link;

public class Queue<T extends Comparable<T>> { // Queue linked list(have the same node of single linked list) < we don't
												// need insert at rear >

	private Node<T> front;
	private Node<T> rear;
//	private int size; //to know the size 

	public Queue() {
		// initial values
		front = rear = null;
		// size = 0;
	}

	public void clear() {
		while (!isEmpty()) {
			dequeue();
		}
	}

	public boolean isEmpty() {
		if (front == null && rear == null) {
			return true;
		}
		return false;

		// return size == 0;
	}

	public T getFront() {
		if (isEmpty()) {
			System.out.println("the queue is empty");

		}
		return front.data; // the data of the pointer front
	}

	public void enqueue(T data) { // last queue rear <<add to the last<<
		Node<T> newNode = new Node<>(data);
		if (isEmpty()) {
			front = rear = newNode; // the last and the begin will be it
		} else { // just rear not using front
			rear.next = newNode;
			rear = newNode;
		}
		// size++;
	}

	public T dequeue() { // first queue front ,want to<< delete from the first>>
		if (isEmpty()) {
			System.out.println("the queue is empty");

		}
		T data = front.data;

		front = front.next;

		if (front == null) {
			rear = null;
		}
		// size--;
		return data;
	}

//	public int size() {
//		return size;
//	}

	public void printList() {
		Node<T> current = front;

		while (current != null) {
			System.out.print(" <" + current.getData() + "> ");
			current = current.getNext();
		}

		System.out.println();
	}

	public boolean remove(T data) {
		if (isEmpty()) {
			return false; // Nothing to remove
		}

		// Special case: removing the front element
		if (front.data.equals(data)) {
			dequeue(); // Reuse existing dequeue method
			return true;
		}

		Node<T> current = front;
		Node<T> previous = null;

		// Traverse the queue to find the element
		while (current != null && !current.data.equals(data)) {
			previous = current;
			current = current.next;
		}

		// Element not found
		if (current == null) {
			return false;
		}

		// Remove the element
		if (previous != null) {
			previous.next = current.next;
		}

		// If we removed the rear element, update rear
		if (current == rear) {
			rear = previous;
		}

		return true;
	}

}