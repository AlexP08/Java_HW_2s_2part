module com.example.hotelbookingfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    exports com.example.bookerfx;
    opens com.example.bookerfx to com.google.gson, javafx.fxml;

    exports com.example.bookerfx.model;
    opens com.example.bookerfx.model to com.google.gson;
}