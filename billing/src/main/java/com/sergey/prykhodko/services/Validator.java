package com.sergey.prykhodko.services;

public interface Validator<T> {
    boolean validate(T unitToValidate);
}
