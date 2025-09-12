package com.example.mybatis.common.approval.service;

import com.example.mybatis.common.approval.lock.Lock;
import com.example.mybatis.common.approval.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Primary
public class SpinLockApproval implements Approval {

    private final ApprovalImpl approval;
    private final Lock<ApprovalId> lock;

    @Override
    public ApprovalResult escalate(ApprovalEscalateInfo escalateInfo) {
        return approval.escalate(escalateInfo);
    }

    @Override
    public ApprovalResult approve(ApprovalSubmit submit) {
        ApprovalId id = submit.approvalId();
        lock.lock(id);
        try{
            Thread.sleep(3000);
            return approval.approve(submit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock(id);
        }
    }

    @Override
    public ApprovalResult reject(ApprovalSubmit submit) {
        return approval.reject(submit);
    }

    @Override
    public ApprovalResult cancel(CancelRequest cancelRequest) {
        return approval.cancel(cancelRequest);
    }
}
