public interface HotelManager {
	void registerGuest();

	void createReservation();

	void checkRoomAvailability();

	void checkIn();

	void checkOut();

	void cancelReservation();

	void showGuestList();

	void showReservations();
}
