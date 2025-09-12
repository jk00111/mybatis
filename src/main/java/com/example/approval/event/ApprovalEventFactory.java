package com.example.approval.event;

import com.example.approval.enums.EventType;

public class ApprovalEventFactory {

    public static ApprovalEvent ofApprove(boolean isApproved) {
        if (isApproved) {
            return new ApprovalEvent(EventType.APPROVED);
        }

        return new ApprovalEvent(EventType.NONE);
    }

    public static ApprovalEvent ofReject() {
        return new ApprovalEvent(EventType.REJECTED);
    }
}
