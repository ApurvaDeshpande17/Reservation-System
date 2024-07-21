public class Reservation {
	private int reservationId;
	private Guest guest;
	private Room room;
	private String startDate;
	private String endDate;
	private boolean userCheckedIn;

	public Reservation(int reservationId, Guest guest, Room room, String startDate, String endDate) {
		this.reservationId = reservationId;
		this.guest = guest;
		this.room = room;
		this.startDate = startDate;
		this.endDate = endDate;
		userCheckedIn = false;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isCheckedIn() {
		return userCheckedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		userCheckedIn = checkedIn;
	}

	@Override
	public String toString() {
		return "Reservation ID = " + reservationId + ", " + guest + ", " + room + ", startDate='" + startDate + '\''
				+ ", endDate='" + endDate + '\'';
	}

}
