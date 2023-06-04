package com.example.bookerfx;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenBounds {

    public static double getWindowWidth(Double ratio) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        return screenBounds.getWidth() * ratio;
    }

    public static double getWindowHeight(Double ratio) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        return screenBounds.getHeight() * ratio;
    }
}
