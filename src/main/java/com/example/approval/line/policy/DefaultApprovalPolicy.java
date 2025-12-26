package com.example.approval.line.policy;

import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.vo.ApprovalUser;

public class DefaultApprovalPolicy implements ApprovalPolicy {

    @Override
    public ApprovalStep apply(ApprovalLine line, ApprovalUser user) {
        ApprovalStep current = line.getCurrent();
        current.proceed(user);

        if (line.hasNext()) {
            ApprovalStep next = line.next();
            next.waiting();
        }

        return current;
    }
}
