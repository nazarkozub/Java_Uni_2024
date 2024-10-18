package org.example.lab1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String guestName;
    private Room room;
    private String startDate;
    private String endDate;
    private double totalPrice;

    private Booking(BookingBuilder builder) {
        this.guestName = builder.guestName;
        this.room = builder.room;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalPrice = builder.totalPrice;
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

        // Метод для підрахунку кількості днів між двома датами
        private long calculateNights(String startDate, String endDate) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            return ChronoUnit.DAYS.between(start, end);
        }

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

        // Метод для автоматичного підрахунку ціни
        public BookingBuilder calculateTotalPrice() {
            long nights = calculateNights(this.startDate, this.endDate);
            this.totalPrice = nights * this.room.getPricePerNight();
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
