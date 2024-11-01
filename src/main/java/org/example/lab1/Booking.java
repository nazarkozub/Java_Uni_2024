package org.example.lab1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {
    private String guestName;
    private Room room;
    private String startDate;
    private String endDate;
    private double totalPrice;

    // Конструктор за замовчуванням для Jackson
    public Booking() {}

    // Основний конструктор з анотаціями
    @JsonCreator
    public Booking(
            @JsonProperty("guestName") String guestName,
            @JsonProperty("room") Room room,
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate,
            @JsonProperty("totalPrice") double totalPrice) {
        this.guestName = guestName;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "guestName='" + guestName + '\'' +
                ", room=" + room +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class BookingBuilder {
        private String guestName;
        private Room room;
        private String startDate;
        private String endDate;
        private double totalPrice;

        public BookingBuilder setGuestName(String guestName) {
            this.guestName = guestName;
            return this;
        }

        public BookingBuilder setRoom(Room room) {
            this.room = room;
            return this;
        }

        public BookingBuilder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public BookingBuilder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public BookingBuilder calculateTotalPrice() {
            this.totalPrice = room.getPricePerNight() * 4; // Приклад: розрахунок на основі тривалості
            return this;
        }

        public Booking build() {
            return new Booking(guestName, room, startDate, endDate, totalPrice);
        }
    }
}
