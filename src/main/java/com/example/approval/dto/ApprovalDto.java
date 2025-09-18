package com.example.approval.dto;

import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.line.entity.ProcessStep;

import java.util.List;

public class ApprovalDto {

    private List<ApprovalStep> steps;


    public List<ApprovalStep> steps() {
        return this.steps;
    }
}
