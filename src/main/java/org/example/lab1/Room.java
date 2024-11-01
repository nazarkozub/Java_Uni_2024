package org.example.lab1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Room implements Comparable<Room> {
    private int roomNumber;
    private String type;
    private double pricePerNight;

    @JsonProperty("available")
    private boolean isAvailable;

    // Конструктор за замовчуванням (необхідний для Jackson)
    public Room() {}

    // Основний конструктор з анотаціями для Jackson
    @JsonCreator
    public Room(
            @JsonProperty("roomNumber") int roomNumber,
            @JsonProperty("type") String type,
            @JsonProperty("pricePerNight") double pricePerNight,
            @JsonProperty("available") boolean isAvailable
    ) {
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
}
