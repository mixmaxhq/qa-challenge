package com.API_Task.POJO;

public enum StatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
