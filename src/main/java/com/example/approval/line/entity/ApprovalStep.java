package com.example.approval.line.entity;

public interface ApprovalStep extends ProcessStep {

    void waiting();

    void pass();
}
