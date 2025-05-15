package com.example.mybatis.common.approval.service;

import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalResult;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.common.approval.vo.CancelRequest;

public interface Approval {

    ApprovalResult escalate(ApprovalEscalateInfo escalateInfo);

    ApprovalResult approve(ApprovalSubmit submit);

    ApprovalResult reject(ApprovalSubmit submit);

    ApprovalResult cancel(CancelRequest cancelRequest);

}
