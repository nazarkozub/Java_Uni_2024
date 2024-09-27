package org.example.lab1;

public class HotelApp {
    public static void main(String[] args) {
        Room room = new Room(101, "Standard", 100.0, true);

        Booking booking = new Booking.BookingBuilder()
                .setGuestName("Kozub Nazar")
                .setRoom(room)
                .setStartDate("2024-10-01")
                .setEndDate("2024-10-05")
                .setTotalPrice(400.0)
                .build();

        System.out.println(booking);
    }
}

