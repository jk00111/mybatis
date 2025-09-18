package com.example.approval.approval.service;

import com.example.approval.approval.entity.Approval;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;

public interface ApprovalService {

    Approval findOne(long id);

    void escalate(Approval approval);

    void approve(long approvalId, ApproveEvent event);

    void reject(long approvalId, RejectEvent event);

}
