package com.example.mybatis.generalTest.request.vo;

import com.example.mybatis.generalTest.request.enums.TestType;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class RequestContents {

    private final String title;
    private final LocalDate requestDate;
    private final String contents;
    private final TestType type;
    private final Requester requester;


    public String title() {
        return title;
    }

    public LocalDate requestDate() {
        return requestDate;
    }

    public TestType type() {
        return type;
    }

    public Requester requester() {
        return requester;
    }

    public String contentsDetail() {return contents; }

}
