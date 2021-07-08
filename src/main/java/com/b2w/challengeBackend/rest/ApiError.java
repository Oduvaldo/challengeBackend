package com.b2w.challengeBackend.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {
    @Getter
    List<String> messages;

    public ApiError(String message) {
        this.messages = Arrays.asList(message);
    }
}
