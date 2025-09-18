package com.example.approval.vo;

import com.example.approval.event.ApprovalEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalResult {

    private final long id;
    private final boolean isFinish;


    public static ApprovalResult escalated(long id) {
        return new ApprovalResult(id, false);
    }
}
