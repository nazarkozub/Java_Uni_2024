package org.example.lab1;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Booking booking = (Booking) obj;
        return guestName.equals(booking.guestName) && room.equals(booking.room) &&
                startDate.equals(booking.startDate) && endDate.equals(booking.endDate);
    }

    @Override
    public int hashCode() {
        int result = guestName.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

    /**
     * Патерн Builder для класу Booking.
     */
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

        public BookingBuilder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}

