package com.sergey.prykhodko.managers;

public interface Validator<T> {
    boolean validate(T unitToValidate);
}
