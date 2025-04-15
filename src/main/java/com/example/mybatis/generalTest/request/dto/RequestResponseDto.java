package com.example.mybatis.generalTest.request.dto;

import com.example.mybatis.generalTest.request.enums.RequestStatus;
import com.example.mybatis.generalTest.request.enums.TestType;
import com.example.mybatis.generalTest.request.vo.Requester;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class RequestResponseDto {

    private Integer id;
    private String title;
    private String contents;
    private LocalDate requestDate;
    private TestType type;
    private String requesterId;
    private RequestStatus status;

}
