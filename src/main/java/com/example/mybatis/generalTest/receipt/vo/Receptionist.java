package com.example.mybatis.generalTest.receipt.vo;

import com.example.mybatis.common.entity.User;
import org.springframework.util.StringUtils;

public class Receptionist {

    private final Integer id;
    private final String name;

    private Receptionist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        if (!StringUtils.hasText(name)) {
            throw new NullPointerException("empty name");
        }

        return name;
    }


    public static Receptionist from(User user) {
        return new Receptionist(user.getId(), user.getName());
    }

    public static Receptionist ofId(Integer id) {
        return new Receptionist(id, null);
    }

    public static Receptionist of(Integer id, String name) {
        return new Receptionist(id, name);
    }
}
