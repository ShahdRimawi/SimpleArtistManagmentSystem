package application;

public class Passenger implements Comparable<Passenger> {

	private String name;
	private int id;
	private String flightID;
	public boolean isVip;

	public Passenger() {

	}

	public Passenger(String name, int id, String flightID, boolean isVip) {
		this.name = name;
		this.id = id;
		this.flightID = flightID;
		this.isVip = isVip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVip() {
		return isVip;
	}

	public void setVip(boolean isVip) {
		this.isVip = isVip;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightID() {
		return flightID;
	}

	public void setFlightID(String flightID) {
		this.flightID = flightID;
	}

	
	@Override
	public String toString() {
		return "Passenger [name=" + name + ", id=" + id + ", flightID=" + flightID + ", isVip=" + isVip + "]";
	}

	public int compareTo(Passenger other) {
		// Example: Compare by priority (higher priority first)
		return Integer.compare(other.getId(), this.getId());
	}
}
