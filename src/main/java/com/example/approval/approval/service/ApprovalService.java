package com.example.approval.approval.service;

import com.example.approval.approval.entity.Approval;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;

public interface ApprovalService {

    Approval findOne(long id);

    void escalate(Approval approval);

    void approve(Approval approval, ApproveEvent event);

    void reject(Approval approval, RejectEvent event);

}
