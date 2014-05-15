package com.springapp.mvc.service;

public class IncrementCommand {
    private Integer value;

    public IncrementCommand(Integer value) {
        this.value = value;
    }

    public void execute() {
        value++;
    }
}
