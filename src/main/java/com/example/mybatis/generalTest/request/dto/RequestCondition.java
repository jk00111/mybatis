package com.example.mybatis.generalTest.request.dto;

import com.example.mybatis.generalTest.request.enums.RequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestCondition {

    private final RequestStatus INIT_STATUS = RequestStatus.INIT;
    private final RequestStatus SUBMIT_STATUS = RequestStatus.SUBMIT;
    private final RequestStatus CANCEL_STATUS = RequestStatus.CANCEL;

    private String title;
    private LocalDateTime requestDate;
    private String requesterName;


}
