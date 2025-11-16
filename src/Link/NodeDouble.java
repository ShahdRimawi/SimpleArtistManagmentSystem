package Link;

import application.Passenger;

public class NodeDouble { // Node flight
	private String flightID;
	private String destination;
	private boolean status;
	public Queue<Passenger> regularQueue;
	public Queue<Passenger> vipQueue;
	public LinkedList<Passenger> boardedPassengers;
	public LinkedList<Passenger> canceledPassengers;
	private LinkedListStack<Runnable> undoStack;
	private LinkedListStack<Runnable> redoStack;
//    private T data;
	private NodeDouble next;
	private NodeDouble previous;

	public NodeDouble(String flightID, String destination, boolean status) {
		this.flightID = flightID;
		this.destination = destination;
		this.status = status;
		this.regularQueue = new Queue<>();
		this.vipQueue = new Queue<>();
		this.boardedPassengers = new LinkedList<>();
		this.canceledPassengers = new LinkedList<>();
		this.undoStack = new LinkedListStack<>();
		this.redoStack = new LinkedListStack<>();
	}

	public NodeDouble getNext() {
		return next;
	}

	public void setNext(NodeDouble next) {
		this.next = next;
	}

	public NodeDouble getPrevious() {
		return previous;
	}

	public void setPrevious(NodeDouble previous) {
		this.previous = previous;
	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Passenger search(int id) { // search about passenger
		// Search in regularQueue
		Passenger foundPassenger = searchQueue(regularQueue, id); // search in queue
		if (foundPassenger != null) {
			return foundPassenger;
		}

		return searchQueue(vipQueue, id);
	}

	private Passenger searchQueue(Queue<Passenger> queue, int id) {
		Queue<Passenger> tempQueue = new Queue<>();
		Passenger foundPassenger = null;

		while (!queue.isEmpty()) {
			Passenger passenger = queue.dequeue();
			if (passenger.getId() == id) { // Assuming Passenger has a getId method
				foundPassenger = passenger; // Found the passenger
			}
			tempQueue.enqueue(passenger); // Re add to tempQueue
		}

		while (!tempQueue.isEmpty()) {
			queue.enqueue(tempQueue.dequeue());
		}

		return foundPassenger;
	}

	public boolean removePassenger(int id) {
		if (removeFromQueue(regularQueue, id)) {
			return true;
		}
		return removeFromQueue(vipQueue, id);
	}

	private boolean removeFromQueue(Queue<Passenger> queue, int id) {
		Queue<Passenger> tempQueue = new Queue<>();
		boolean removed = false;

		while (!queue.isEmpty()) {
			Passenger currentPassenger = queue.dequeue();
			if (currentPassenger.getId() == id) {
				removed = true;
			} else {
				tempQueue.enqueue(currentPassenger);
			}
		}

		while (!tempQueue.isEmpty()) {
			queue.enqueue(tempQueue.dequeue());
		}

		return removed;
	}

	public boolean updatePassenger(int id, Passenger updatedPassenger) {
		if (updateQueue(regularQueue, id, updatedPassenger)) {
			return true;
		}
		return updateQueue(vipQueue, id, updatedPassenger);
	}

	private boolean updateQueue(Queue<Passenger> queue, int id, Passenger updatedPassenger) {
		Queue<Passenger> tempQueue = new Queue<>();
		boolean updated = false;

		while (!queue.isEmpty()) {
			Passenger currentPassenger = queue.dequeue();
			if (currentPassenger.getId() == id) {
				tempQueue.enqueue(updatedPassenger);
				updated = true;
			} else {
				tempQueue.enqueue(currentPassenger);
			}
		}

		while (!tempQueue.isEmpty()) {
			queue.enqueue(tempQueue.dequeue());
		}

		return updated;
	}

	public void printAllPassengers() {
		System.out.println("Regular Passengers:");
		regularQueue.printList();
		System.out.println("VIP Passengers:");
		vipQueue.printList();
	}

	public void printPassengerInfo(int id) {
		Passenger passenger = search(id);
		if (passenger != null) {
			System.out.println(passenger);
		} else {
			System.out.println("Passenger not found.");
		}
	}

	@Override
	public String toString() {
		return "flightID=" + flightID + ", destination=" + destination + ", status=" + status
//				+ ", regularQueue=" + regularQueue + ", vipQueue=" + vipQueue + ", boardedPassengers="
//				+ boardedPassengers + ", canceledPassengers=" + canceledPassengers + ", undoStack=" + undoStack
//				+ ", redoStack=" + redoStack + ", next=" + next + ", previous=" + previous + 
				+ "";
	}

	public void undo() {
		if (!undoStack.isEmpty()) {
			Runnable undoAction = undoStack.pop();
			redoStack.push(undoAction);
			undoAction.run();
			System.out.println("Undo performed for flight " + flightID);
		} else {
			System.out.println("No actions to undo for flight " + flightID);
		}
	}

	public void redo() {
		if (!redoStack.isEmpty()) {
			Runnable redoAction = redoStack.pop();
			undoStack.push(redoAction);
			redoAction.run();
			System.out.println("Redo performed for flight " + flightID);
		} else {
			System.out.println("No actions to redo for flight " + flightID);
		}
	}

	public void addUndoAction(Runnable undoAction) {
		undoStack.push(undoAction);
		redoStack.clear(); // Clear redo stack whenever a new action is performed
	}

	public int countQueueReg() {

		Queue<Passenger> tempQueue = new Queue<>(); // Temporary queue
		int count = 0;

		while (!regularQueue.isEmpty()) {
			Passenger passenger = regularQueue.dequeue();
			tempQueue.enqueue(passenger);
			count++;
		}

		while (!tempQueue.isEmpty()) {
			regularQueue.enqueue(tempQueue.dequeue());
		}

		return count;
	}

	public int countQueueVip() {

		Queue<Passenger> tempQueue = new Queue<>(); // Temporary queue
		int count = 0;

		while (!vipQueue.isEmpty()) {
			Passenger passenger = vipQueue.dequeue();
			tempQueue.enqueue(passenger);
			count++;
		}

		while (!tempQueue.isEmpty()) {
			vipQueue.enqueue(tempQueue.dequeue());
		}

		return count;
	}

}