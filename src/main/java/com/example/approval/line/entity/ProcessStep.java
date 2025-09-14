package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;

public interface ProcessStep {

    long id();

    void proceed();

    void reject();

    void waiting();

    StepStatus status();

}
