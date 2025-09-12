package com.example.approval.event;

import com.example.approval.enums.EventType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalEvent implements ApproveEvent, RejectEvent, CancelEvent {

    private final EventType type;

    @Override
    public boolean isApproved() {
        return type.equals(EventType.APPROVED);
    }

    @Override
    public boolean isRejected() {
        return type.equals(EventType.REJECTED);
    }

    @Override
    public boolean isCanceled() {
        return type.equals(EventType.CANCELED);
    }

}
