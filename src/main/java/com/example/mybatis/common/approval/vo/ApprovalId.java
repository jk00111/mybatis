package com.example.mybatis.common.approval.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalId {

    private final Integer id;

    public static ApprovalId of(Integer id) {
        return new ApprovalId(id);
    }
}
