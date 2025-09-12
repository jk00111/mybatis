package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;

public interface ProcessStep {

    void proceed();

    void reject();

    void waiting();

    StepStatus status();

}
