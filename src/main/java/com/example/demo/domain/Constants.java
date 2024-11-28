package com.example.demo.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A constants class to manage application-wide constants and state.
 */
public class Constants {
    public static final String SECRET_KEY = "01234567890123456789012345678901";
    public static final List<String> VALID_TOKENS = new CopyOnWriteArrayList<>();
}
