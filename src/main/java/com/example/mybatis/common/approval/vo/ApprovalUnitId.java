package com.example.mybatis.common.approval.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApprovalUnitId {

    private final Integer approvalId;

    private final Integer stage;

    private final Integer order;
}
