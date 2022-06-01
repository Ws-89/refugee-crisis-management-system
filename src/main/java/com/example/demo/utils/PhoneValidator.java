package com.example.demo.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PhoneValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.length() == 9;
    }
}
