package com.example.approval.approval.dto;

import com.example.approval.document.dto.DocumentDto;
import com.example.approval.line.entity.ApprovalStep;
import lombok.Getter;

import java.util.List;

public class ApprovalDto {

    @Getter
    private DocumentDto documentDto;
    private List<ApprovalStep> steps;

    public List<ApprovalStep> steps() {
        return this.steps;
    }
}
