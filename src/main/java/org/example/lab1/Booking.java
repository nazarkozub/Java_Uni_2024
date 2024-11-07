package org.example.lab1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Booking {
    private String guestName;
    private Room room;
    private String startDate;
    private String endDate;
    private double totalPrice;

    public Booking() {}

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
        private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]+$");
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
            if (room != null) {
                this.totalPrice = room.getPricePerNight() * 4; // Приклад розрахунку
            }
            return this;
        }

        public Booking build() {
            List<String> validationErrors = new ArrayList<>();

            if (guestName == null || !NAME_PATTERN.matcher(guestName).matches()) {
                validationErrors.add("guestName: \"" + guestName + "\" - ім'я повинно містити тільки літери та пробіли");
            }

            if (room == null) {
                validationErrors.add("room: null - номер кімнати не може бути порожнім");
            }

            if (startDate == null || startDate.trim().isEmpty()) {
                validationErrors.add("startDate: \"" + startDate + "\" - дата початку не може бути порожньою");
            }

            if (endDate == null || endDate.trim().isEmpty()) {
                validationErrors.add("endDate: \"" + endDate + "\" - дата закінчення не може бути порожньою");
            }

            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException("Помилки валідації: " + String.join("; ", validationErrors));
            }

            return new Booking(guestName, room, startDate, endDate, totalPrice);
        }
    }
}
