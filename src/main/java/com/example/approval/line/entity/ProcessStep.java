package com.example.approval.line.entity;

import com.example.approval.line.enums.StepStatus;
import com.example.approval.vo.ApprovalUser;

public interface ProcessStep {

    long id();

    void proceed(ApprovalUser user);

    void reject(ApprovalUser user);

    StepStatus status();

    boolean isUpdated();
}
