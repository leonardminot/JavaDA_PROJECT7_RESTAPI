package com.nnk.springboot.service;

import java.sql.Timestamp;

public interface DateProvider {
    Timestamp getNow();
}
