package com.nnk.springboot.service;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class DateProviderImpl implements DateProvider {
    @Override
    public Timestamp getNow() {
        return Timestamp.from(Instant.now());
    }
}
