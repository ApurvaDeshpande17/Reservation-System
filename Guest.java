public class Guest {
	private int guestId;
	private String name;
	private String address;
	private String phoneNumber;
	// private Reservation reservation;

	public Guest(int guestId, String name, String address, String phoneNumber) {
		this.guestId = guestId;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	public int getGuestId() {
		return guestId;
	}

	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Guest Id=" + guestId + ", Guest Name='" + name + '\'' + ", Address='" + address + '\''
				+ ", Contact Number='" + phoneNumber + '\'';
	}
}
