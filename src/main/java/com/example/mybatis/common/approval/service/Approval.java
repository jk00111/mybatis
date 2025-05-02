package com.example.mybatis.common.approval.service;

import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalResult;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;

public interface Approval {

    ApprovalResult escalate(ApprovalEscalateInfo escalateInfo);

    ApprovalResult approve(ApprovalSubmit submit);

    ApprovalResult reject(ApprovalSubmit submit);

}
