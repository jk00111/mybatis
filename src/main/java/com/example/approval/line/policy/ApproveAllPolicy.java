package com.example.approval.line.policy;

import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.vo.ApprovalUser;

public class ApproveAllPolicy implements ApprovalPolicy {

    @Override
    public ApprovalStep apply(ApprovalLine line, ApprovalUser user) {
        if (line.iterator().hasNext()) {
            ApprovalStep next = line.iterator().next();
        }
        line.forEach(ApprovalStep::pass);

        ApprovalStep current = line.getCurrent();
        current.proceed(user);
        return current;
    }
}
