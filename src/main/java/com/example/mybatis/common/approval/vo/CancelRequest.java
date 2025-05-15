package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.approval.user.ApprovalRequester;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CancelRequest {

    private final ApprovalId approvalId;
    private final ApprovalRequester requester;

}
