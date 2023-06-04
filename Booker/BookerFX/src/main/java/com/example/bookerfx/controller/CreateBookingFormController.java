package com.example.bookerfx.controller;

import com.example.bookerfx.http.HttpRequestHandler;
import com.example.bookerfx.model.Booking;
import com.example.bookerfx.model.Client;
import com.example.bookerfx.model.Room;
import com.example.bookerfx.view.CreateBookingFormView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class CreateBookingFormController {

    private CreateBookingFormView view;
    private final HttpRequestHandler httpRequestHandler;

    public CreateBookingFormController() {
        httpRequestHandler = new HttpRequestHandler();
    }

    public void setView(CreateBookingFormView view) {
        this.view = view;
    }

    public void createBooking() {
        try {
            validateFormFields();

            Room room = new Room();
            room.setId(view.getRoomId());

            Client client = new Client();
            client.setId(view.getClientId());

            Booking newBooking = new Booking(
                    room,
                    client,
                    LocalDateTime.parse(view.getCheckInDate(), httpRequestHandler.localDateTimeFormatter),
                    LocalDateTime.parse(view.getCheckOutDate(), httpRequestHandler.localDateTimeFormatter),
                    view.getTotalCost());
            httpRequestHandler.addBooking(newBooking);

            view.close();
        } catch (IllegalArgumentException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void validateFormFields() {
        Integer roomId = view.getRoomId();
        Integer clientId = view.getClientId();
        String checkInDateString = view.getCheckInDate();
        String checkOutDateString = view.getCheckOutDate();
        Double totalCost = view.getTotalCost();

        LocalDateTime checkInDate;
        LocalDateTime checkOutDate;

        try {
            checkInDate = LocalDateTime.parse(checkInDateString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            LocalDateTime currentDateTime = LocalDateTime.now();

            if (checkInDate.isBefore(currentDateTime)) {
                throw new IllegalArgumentException("This date must be later than the current day.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid format for check-in date. Expected format: dd.MM.yyyy HH:mm");
        }

        try {
            checkOutDate = LocalDateTime.parse(checkOutDateString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

            if (checkOutDate.isBefore(checkInDate)) {
                throw new IllegalArgumentException("Check-out date must be later than check-in date.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid format for check-out date. Expected format: dd.MM.yyyy HH:mm");
        }

        if (!checkRoomAvailability(roomId, checkInDate, checkOutDate)) {
            throw new IllegalArgumentException("The room is occupied for the selected dates.");
        }
    }

    private boolean checkRoomAvailability(int roomId, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        List<Booking> existingBookings = httpRequestHandler.getBookingsByRoomId(roomId);

        for (Booking booking : existingBookings) {
            LocalDateTime existingCheckIn = booking.getCheckInDate();
            LocalDateTime existingCheckOut = booking.getCheckOutDate();

            if (checkInDate.isBefore(existingCheckOut) && existingCheckIn.isBefore(checkOutDate)) {
                return false;
            }
        }

        return true;
    }
}