package com.sergey.prykhodko.exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
