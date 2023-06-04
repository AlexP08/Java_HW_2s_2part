package com.example.bookerfx.controller;

import com.example.bookerfx.http.HttpRequestHandler;
import com.example.bookerfx.model.Booking;
import javafx.scene.control.TableView;

import java.util.List;

public class BookerFXController {
    private final TableView<Booking> bookingTableView;
    private final HttpRequestHandler httpRequestHandler;

    public BookerFXController(TableView<Booking> bookingTableView) {
        this.bookingTableView = bookingTableView;
        httpRequestHandler = new HttpRequestHandler();
    }

    public void initialize() {
        fetchBookings();
    }

    private void fetchBookings() {
        List<Booking> bookings = httpRequestHandler.getAllBookings();
        updateTableView(bookings);
    }

    private void updateTableView(List<Booking> bookings) {
        bookingTableView.getItems().clear();
        bookingTableView.getItems().addAll(bookings);
    }
}