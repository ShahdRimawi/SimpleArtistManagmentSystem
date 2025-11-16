package Link;

import java.util.ArrayList;

import application.Passenger;


public class DoubleLinkedList { // flightList
	private NodeDouble head;
	private NodeDouble tail;
	private int canceled=0;
	private int bounded=0;
	public ArrayList<NodeDouble> all=new ArrayList<>(); //checkk
	
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	 public Passenger searchPassenger(int passengerId) {
	        NodeDouble current = head;

	        while (current != null) {
	            // Search in VIP and regular queues of the current flight
	            Passenger foundPassenger = current.search(passengerId);
	            if (foundPassenger != null) {
	                return foundPassenger;
	            }
	            current = current.getNext();
	        }

	        return null; // Passenger not found
	    }
	public void addFlight(String flightID, String destination, boolean isActive) {
        NodeDouble newFlight = new NodeDouble(flightID, destination, isActive);
        if (head == null) { // List is empty
            head = newFlight;
            tail = newFlight;
            all.add(newFlight);
        } else { // Append to the end
            tail.setNext(newFlight);
            newFlight.setPrevious(tail);
            all.add(newFlight);
            tail = newFlight;
        }
    }

	public void removeFlight(String flightID) {
        NodeDouble current = head;
        while (current != null) {
            if (current.getFlightID().equals(flightID)) {
                // Update links
                if (current.getPrevious() != null) {
                    current.getPrevious().setNext(current.getNext());
                } else {
                	 all.remove(head);
                    head = current.getNext(); // Removing head

                }
                if (current.getNext() != null) {
                    current.getNext().setPrevious(current.getPrevious());
                } else {
                	all.remove(tail);
                    tail = current.getPrevious(); // Removing tail
                }
                System.out.println("Flight " + flightID + " removed.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Flight " + flightID + " not found.");
    }
	
	 public NodeDouble searchFlight(String flightID) {
	        NodeDouble current = head;
	        while (current != null) {
	            if (current.getFlightID().equals(flightID)) {
	                return current;
	            }
	            current = current.getNext();
	        }
	        return null; // Not found
	    }
	 
	 public void updateFlight(String flightID, String newDestination, Boolean newStatus) {
	        NodeDouble flight = searchFlight(flightID);
	        all.remove(flight);
	        if (flight != null) {
	            if (newDestination != null) {
	                flight.setDestination(newDestination);
	            }
	            if (newStatus != null) {
	                flight.setStatus(newStatus);
	            }
	            all.add(flight);
	            System.out.println("Flight " + flightID + " updated.");
	        } else {
	            System.out.println("Flight " + flightID + " not found.");
	        }
	    }

	    // Display all flights
	    public void displayFlights() {
	        NodeDouble current = head;
	        while (current != null) {
	            System.out.println("Flight ID: " + current.getFlightID() + ", Destination: " + current.getDestination() +
	                               ", Status: " + (current.isStatus() ? "Active" : "Inactive"));
//	            all.add(current);
	            current = current.getNext();
	        }
	    }
	    

	    public void displaySpecificFlight(String flightID) {
	        NodeDouble flight = searchFlight(flightID);
	        if (flight != null) {
	            System.out.println("Flight ID: " + flight.getFlightID() + ", Destination: " + flight.getDestination() +
	                    ", Status: " + (flight.isStatus() ? "Active" : "Inactive"));
	        } else {
	            System.out.println("Flight " + flightID + " not found.");
	        }
	    }
	    public void displayActiveFlights() {
	        NodeDouble current = head;
	        System.out.println("Active Flights:");
	        while (current != null) {
	            if (current.isStatus()) {
	                System.out.println("Flight ID: " + current.getFlightID() + ", Destination: " + current.getDestination());
	            }
	            current = current.getNext();
	        }
	    }

	    public void displayInactiveFlights() {
	        NodeDouble current = head;
	        System.out.println("Inactive Flights:");
	        while (current != null) {
	            if (!current.isStatus()) {
	                System.out.println("Flight ID: " + current.getFlightID() + ", Destination: " + current.getDestination());
	            }
	            current = current.getNext();
	        }
	    }
	    
	    
	    
	    
	    
	    public void checkInPassenger( Passenger passenger) {
	    	
	        NodeDouble flight = searchFlight(passenger.getFlightID());
	        if (flight == null) {
	            System.out.println("Flight " + passenger.getFlightID() + " not found.");
	            return;
	        }
	        if (passenger.isVip) {
	            flight.vipQueue.enqueue(passenger);
	            System.out.println("VIP passenger " + passenger.getName() + " checked in.");
	        } else {
	            flight.regularQueue.enqueue(passenger);
	            System.out.println("Regular passenger " + passenger.getName() + " checked in.");
	        }
	    }

	    public void boardPassenger(String flightID) {
	        NodeDouble flight = searchFlight(flightID);
	        if (flight == null) {
	            System.out.println("Flight " + flightID + " not found.");
	            return;
	        }
	        Passenger passenger = null;
	        if (!flight.vipQueue.isEmpty()) {
	            passenger = flight.vipQueue.dequeue();
	        } else if (!flight.regularQueue.isEmpty()) {
	            passenger = flight.regularQueue.dequeue();
	        }
	        if (passenger != null) {
	            flight.boardedPassengers.insertAtStart(passenger);
	            System.out.println("Passenger " + passenger.getName() + " boarded.");
	            bounded++;
	        } else {
	            System.out.println("No passengers to board.");
	        }
	    }
	    
	    public void cancelPassenger( Passenger passenger) {
	    	
	        NodeDouble flight = searchFlight(passenger.getFlightID());
	        if (flight == null) {
	            System.out.println("Flight " + passenger.getFlightID() + " not found.");
	            return;
	        }
	        if (flight.vipQueue.remove(passenger) || flight.regularQueue.remove(passenger)) {
	            flight.canceledPassengers.insertAtStart(passenger);
	            System.out.println("Passenger " + passenger.getName() + " canceled.");
	            canceled++;
	        } else {
	            System.out.println("Passenger not found in queues.");
	        }
	    }

		public int getCanceled() {
			return canceled;
		}

		public void setCanceled(int canceled) {
			this.canceled = canceled;
		}
		
		public int getB() {
			return bounded;
		}

		public void setB(int bounded) {
			this.bounded = bounded;
		}
	    
}