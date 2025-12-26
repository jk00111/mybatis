package com.example.approval.line.policy;

import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.vo.ApprovalUser;

public interface ApprovalPolicy {

    ApprovalStep apply(ApprovalLine line, ApprovalUser user);

}
