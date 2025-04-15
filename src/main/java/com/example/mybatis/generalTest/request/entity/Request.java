package com.example.mybatis.generalTest.request.entity;

import com.example.mybatis.generalTest.request.vo.RequestContents;
import com.example.mybatis.generalTest.request.enums.RequestStatus;
import com.example.mybatis.generalTest.request.enums.TestType;
import com.example.mybatis.generalTest.request.vo.RequestId;
import com.example.mybatis.generalTest.request.vo.Requester;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
public class Request {

    private Integer id;

    private String title;
    private String contents;
    private TestType testType;
    private LocalDate requestDate;
    private Integer requesterId;
    private RequestStatus status;

    public RequestId id() {
        return new RequestId(id);
    }

    public RequestContents contents() {
        return RequestContents.builder()
                .title(title)
                .requestDate(requestDate)
                .type(testType)
                .requester(Requester.ofId(requesterId))
                .contents(contents)
                .build();
    }

    public void submit() {
        status = RequestStatus.SUBMIT;
    }

    public void cancel() {
        status = RequestStatus.CANCEL;
    }

    public void updateContents(RequestContents contents) {
        this.title = contents.title();
        this.requestDate = contents.requestDate();
        this.testType = contents.type();
        this.requesterId = contents.requester().id();
        this.contents = contents.contentsDetail();
    }

    public static Request createFrom(RequestContents contents) {
        return Request.builder()
                .title(contents.title())
                .requesterId(contents.requester().id())
                .requestDate(contents.requestDate())
                .contents(contents.contentsDetail())
                .testType(contents.type())
                .status(RequestStatus.INIT)
                .build();
    }
}
