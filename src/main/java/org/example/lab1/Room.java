package org.example.lab1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Room implements Comparable<Room> {
    private int roomNumber;
    private String type;
    private double pricePerNight;
    private boolean isAvailable;

    public Room() {}

    @JsonCreator
    public Room(
            @JsonProperty("roomNumber") int roomNumber,
            @JsonProperty("type") String type,
            @JsonProperty("pricePerNight") double pricePerNight,
            @JsonProperty("available") boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", type='" + type + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public int compareTo(Room otherRoom) {
        return Integer.compare(this.roomNumber, otherRoom.getRoomNumber());
    }

    public static class RoomBuilder {
        private int roomNumber;
        private String type;
        private double pricePerNight;
        private boolean isAvailable;

        public RoomBuilder setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public RoomBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public RoomBuilder setPricePerNight(double pricePerNight) {
            this.pricePerNight = pricePerNight;
            return this;
        }

        public RoomBuilder setAvailable(boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Room build() {
            List<String> validationErrors = new ArrayList<>();

            // Перевірка на від'ємний номер кімнати
            if (roomNumber <= 0) {
                validationErrors.add("roomNumber: " + roomNumber + " - номер кімнати повинен бути більше 0");
            }

            // Перевірка на порожній тип кімнати
            if (type == null || type.trim().isEmpty()) {
                validationErrors.add("type: \"" + type + "\" - тип не може бути порожнім");
            }

            // Перевірка на від'ємну ціну
            if (pricePerNight <= 0) {
                validationErrors.add("pricePerNight: " + pricePerNight + " - ціна за ніч повинна бути більше 0");
            }

            // Якщо є помилки, кидаємо IllegalArgumentException з детальним описом
            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Помилки валідації: " + String.join("; ", validationErrors));
            }

            return new Room(roomNumber, type, pricePerNight, isAvailable);
        }
    }
}
