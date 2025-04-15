package com.example.mybatis.generalTest.request.dto;

import com.example.mybatis.generalTest.request.enums.TestType;
import com.example.mybatis.generalTest.request.vo.RequestContents;
import com.example.mybatis.generalTest.request.vo.Requester;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RequestContentsDto {

    private String title;
    private LocalDate requestDate;
    private TestType type;
    private Requester requester;
    private String contents;

    public RequestContents toContents() {
        return RequestContents.builder()
                .title(title)
                .requestDate(requestDate)
                .type(type)
                .requester(requester)
                .contents(contents)
                .build();
    }
}
