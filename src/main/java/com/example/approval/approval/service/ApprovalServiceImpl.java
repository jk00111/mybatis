package com.example.approval.approval.service;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.repository.ApprovalRepository;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository repository;

    @Override
    public Approval findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public void escalate(Approval approval) {
        repository.create(approval);
    }

    @Override
    public void approve(long approvalId, ApproveEvent event) {
        if (!event.isApproved()) {
            return;
        }
        Approval approval = findOne(approvalId);
        approval.approve(event);
        repository.update(approval);
    }

    @Override
    public void reject(long approvalId, RejectEvent event) {
        Approval approval = findOne(approvalId);

        approval.reject(event);
        repository.update(approval);
    }
}
