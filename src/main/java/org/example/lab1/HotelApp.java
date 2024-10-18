package org.example.lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HotelApp {
    public static void main(String[] args) {
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(101, "Standard", 100.0, true));
        rooms.add(new Room(102, "Suite", 200.0, false));
        rooms.add(new Room(103, "Standard", 150.0, true));

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking.BookingBuilder()
                .setGuestName("Kozub Nazar")
                .setRoom(rooms.get(0))
                .setStartDate("2024-10-01")
                .setEndDate("2024-10-05")
                .calculateTotalPrice()  // Автоматичний підрахунок ціни
                .build());

        bookings.add(new Booking.BookingBuilder()
                .setGuestName("Lionel Messi")
                .setRoom(rooms.get(1))
                .setStartDate("2024-10-10")
                .setEndDate("2024-10-15")
                .calculateTotalPrice()  // Автоматичний підрахунок ціни
                .build());

        bookings.add(new Booking.BookingBuilder()
                .setGuestName("Cristiano Ronaldo")
                .setRoom(rooms.get(2))
                .setStartDate("2024-12-24")
                .setEndDate("2024-12-31")
                .calculateTotalPrice()  // Автоматичний підрахунок ціни
                .build());

        // 1. Знайти всі доступні кімнати
        List<Room> availableRooms = findAvailableRooms(rooms);
        System.out.println("Available rooms: " + availableRooms);

        // 2. Відсортувати кімнати за номером (Comparable)
        List<Room> sortedRooms = rooms.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Rooms sorted by number: " + sortedRooms);

        // 3. Відсортувати бронювання за ціною (Comparator)
        List<Booking> sortedBookingsByPrice = bookings.stream()
                .sorted(Comparator.comparingDouble(Booking::getTotalPrice))
                .collect(Collectors.toList());
        System.out.println("Bookings sorted by total price: " + sortedBookingsByPrice);

        // 4. Знайти бронювання за іменем гостя
        List<Booking> bookingsForGuest = findBookingsByGuest("Kozub Nazar", bookings);
        System.out.println("Bookings for Kozub Nazar: " + bookingsForGuest);
    }

    // Метод для пошуку доступних кімнат
    public static List<Room> findAvailableRooms(List<Room> rooms) {
        return rooms.stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }

    // Метод для пошуку бронювань за гостем
    public static List<Booking> findBookingsByGuest(String guestName, List<Booking> bookings) {
        return bookings.stream()
                .filter(booking -> booking.getGuestName().equals(guestName))
                .collect(Collectors.toList());
    }
}
