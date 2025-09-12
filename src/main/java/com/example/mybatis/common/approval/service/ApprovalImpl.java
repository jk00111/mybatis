package com.example.mybatis.common.approval.service;

import com.example.mybatis.common.approval.entity.ApprovalEntity;
import com.example.mybatis.common.approval.repository.ApprovalRepository;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalResult;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.common.approval.vo.CancelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ApprovalImpl implements Approval {

    private final ApprovalRepository approvalRepository;

    @Override
    public ApprovalResult escalate(ApprovalEscalateInfo escalateInfo) {
        ApprovalEntity entity = ApprovalEntity.escalate(escalateInfo);
        approvalRepository.create(entity);

        return new ApprovalResult(entity);
    }

    @Override
    public ApprovalResult approve(ApprovalSubmit submit) {
        ApprovalEntity entity = approvalRepository.findOne(submit.approvalId());
        entity.approve(submit);
        approvalRepository.update(entity);
        return new ApprovalResult(entity);
    }

    @Override
    public ApprovalResult reject(ApprovalSubmit submit) {
        ApprovalEntity entity = approvalRepository.findOne(submit.approvalId());
        entity.reject(submit);
        approvalRepository.update(entity);

        return new ApprovalResult(entity);
    }

    @Override
    public ApprovalResult cancel(CancelRequest cancelRequest) {
        ApprovalEntity entity = approvalRepository.findOne(cancelRequest.getApprovalId());
        entity.cancel();
        approvalRepository.update(entity);

        return new ApprovalResult(entity);
    }
}
