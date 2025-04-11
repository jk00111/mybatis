package com.example.mybatis.generalTest.request.vo;

import com.example.mybatis.common.entity.User;
import org.springframework.util.StringUtils;

public class Requester {

    private final Integer id;
    private final String name;

    private Requester(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        if (!StringUtils.hasText(name)) {
            throw new UnsupportedOperationException("empty name");
        }

        return name;
    }


    public static Requester from(User user) {
        return new Requester(user.getId(), user.getName());
    }

    public static Requester ofId(Integer id) {
        return new Requester(id, null);
    }
}
