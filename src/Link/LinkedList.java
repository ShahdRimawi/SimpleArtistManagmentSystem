package Link;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void printList() {
		Node<T> temp = head;
		while (temp != null) {
			System.out.print(temp + " ");
			temp = temp.getNext();
		}
		System.out.println("null");
	}

	public int getLength() {
		int length = 0;
		Node<T> start = head;
		while (start != null) {
			length++;
			start = start.getNext();
		}
		return length;
	}

	public void makeNull() {
		head = null;
	}

	public void insertAtStart(T data) {
		Node<T> newNode = new Node<>(data);

		if (head == null) {
			head = newNode;
		} else {
			newNode.setNext(head);
			head = newNode;
		}
	}

	public void insertAtEnd(T data) {
		Node<T> newNode = new Node<>(data);

		if (head == null) {
			head = newNode;
		} else {
			Node<T> temp = head;
			while (temp.getNext() != null) {
				temp = temp.getNext();
			}
			temp.setNext(newNode);

		}
	}

	public void insertSorted(T data) { // Insert sorted / between nodes

		Node<T> newNode = new Node<>(data);

		if (head == null || head.getData().compareTo(data) > 0) {
			newNode.setNext(head);
			head = newNode;
		} else {
			Node<T> prev = null;
			Node<T> current = head;

			while (current != null && data.compareTo(current.getData()) > 0) {
				prev = current;
				current = current.getNext();
			}

			newNode.setNext(current);
			if (prev != null) {
				prev.setNext(newNode);
			}
		}
	}

	public Node<T> find(T data) {
		Node<T> start = head;

		while (start != null) {
			if (start.getData().equals(data)) {
				return start;
			}
			start = start.getNext();
		}

		return null;
	}

	public void deleteHead() {
		if (head == null) {
			return; // If the list is empty, do nothing
		}
		head = head.getNext(); // Update the head to point to the next node
	}

	public void deleteAtEnd() {
		if (head == null || head.getNext() == null) {
			head = null;
			return;
		}

		Node<T> current = head;
		Node<T> prev = null;

		while (current.getNext() != null) {
			prev = current;
			current = current.getNext();
		}

		prev.setNext(null);
	}

	public void printListRecursive(Node<T> current) {
		if (current == null) {
			System.out.println("Null");
		} else {
			System.out.println(current);
			printListRecursive(current.getNext());
		}
	}

	public void printListBackwards(Node<T> current) {
		if (current == null) {
			System.out.println("Null");
		} else {
			printListBackwards(current.getNext());
			System.out.println(current);
		}
	}

	public void deleteNodeWithData(T data) {
		// Check if the list is empty
		if (head == null) {
			System.out.println("List is empty. Nothing to delete.");
			return;
		}

		// If the node to delete is the head
		if (head.data.equals(data)) {
			head = head.next;
			return;
		}

		// Use two pointers to traverse the list
		Node<T> current = head;
		Node<T> previous = null;

		// Traverse until we find the node to delete or reach the end
		while (current != null && !current.data.equals(data)) {
			previous = current;
			current = current.next;
		}

		// If the node was found, unlink it
		if (current != null) {
			previous.next = current.next;
		} else {
			System.out.println("Data not found in the list.");
		}
	}

}