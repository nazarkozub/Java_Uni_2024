package org.example.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotelApp {
    public static void main(String[] args) throws IOException { // Додано throws IOException
        List<Room> rooms = new ArrayList<>();
        try {
            rooms.add(new Room.RoomBuilder()
                    .setRoomNumber(101)
                    .setType("")
                    .setPricePerNight(100.0)
                    .setAvailable(true)
                    .build());

            rooms.add(new Room.RoomBuilder()
                    .setRoomNumber(102)
                    .setType("Suite")
                    .setPricePerNight(200.0)
                    .setAvailable(false)
                    .build());

            rooms.add(new Room.RoomBuilder()
                    .setRoomNumber(103)
                    .setType("Standard")
                    .setPricePerNight(150.0)
                    .setAvailable(true)
                    .build());
        } catch (IllegalArgumentException e) {
            System.out.println("Error while creating rooms: " + e.getMessage());
            return; // Зупиняємо виконання програми, якщо є помилка
        }

        List<Booking> bookings = new ArrayList<>();
        try {
            bookings.add(new Booking.BookingBuilder()
                    .setGuestName("Kozub Nazar")
                    .setRoom(rooms.get(0))
                    .setStartDate("2024-10-01")
                    .setEndDate("2024-10-05")
                    .calculateTotalPrice()
                    .build());

            bookings.add(new Booking.BookingBuilder()
                    .setGuestName("Lionel Messi")
                    .setRoom(rooms.get(1))
                    .setStartDate("2024-10-10")
                    .setEndDate("2024-10-15")
                    .calculateTotalPrice()
                    .build());

            bookings.add(new Booking.BookingBuilder()
                    .setGuestName("Cristiano Ronaldo")
                    .setRoom(rooms.get(2))
                    .setStartDate("2024-12-24")
                    .setEndDate("2024-12-31")
                    .calculateTotalPrice()
                    .build());
        } catch (IllegalArgumentException e) {
            System.out.println("Error while creating booking: " + e.getMessage());
            return; // Зупиняємо виконання програми, якщо є помилка
        }

        // Серіалізація Booking у JSON, XML та YAML
        Serializer<Booking> jsonSerializer = new JsonSerializer<>();
        Serializer<Booking> xmlSerializer = new XmlSerializer<>();
        Serializer<Booking> yamlSerializer = new YamlSerializer<>();

        // Серіалізуємо перше бронювання у файли
        Booking bookingToSerialize = bookings.get(0);
        jsonSerializer.serialize(bookingToSerialize, "booking.json");
        xmlSerializer.serialize(bookingToSerialize, "booking.xml");
        yamlSerializer.serialize(bookingToSerialize, "booking.yaml");

        // Десеріалізація з JSON
        Booking deserializedJsonBooking = jsonSerializer.deserialize("booking.json", Booking.class);
        System.out.println("Deserialized JSON: " + deserializedJsonBooking);

        // Десеріалізація з XML
        Booking deserializedXmlBooking = xmlSerializer.deserialize("booking.xml", Booking.class);
        System.out.println("Deserialized XML: " + deserializedXmlBooking);

        // Десеріалізація з YAML
        Booking deserializedYamlBooking = yamlSerializer.deserialize("booking.yaml", Booking.class);
        System.out.println("Deserialized YAML: " + deserializedYamlBooking);
    }
}
