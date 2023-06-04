package com.TrainFX.exception;


/**
 * Класс исключения, для обработки ошибок соединения с сервером
 */
public class NoConnectionException extends  Exception{
    /**
     * инициализатор класса, использующий метод
     * @param message сообщение, которое необходимо отобразить в ошибке
     */
    public NoConnectionException(String message) {
        super(message);
    }
}

