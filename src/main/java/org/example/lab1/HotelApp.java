package org.example.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotelApp {
    public static void main(String[] args) throws IOException {
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
