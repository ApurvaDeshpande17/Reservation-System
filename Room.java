public class Room {

	private int roomId;
	private String roomNumber;
	private String type;
	private boolean availability;

	private Reservation reservation;

	public Room(int roomId, String roomNumber, String type) {
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.type = type;
		this.availability = true;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Room ID=" + roomId + ", Room Number='" + roomNumber + '\'' + ", Room Type='" + type + '\''
				+ ", Room Availability=" + availability;
	}
}
