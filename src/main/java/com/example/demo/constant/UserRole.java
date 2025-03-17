package com.example.demo.constant;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum UserRole implements IEnum<String> {
    ADMIN("admin"),
    USER("user");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}