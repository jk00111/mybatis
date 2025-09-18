package com.example.approval.dto;

import com.example.approval.line.entity.ProcessStep;

import java.util.List;

public class ApprovalDto {

    private List<ProcessStep> steps;


    public List<ProcessStep> steps() {
        return this.steps;
    }
}
