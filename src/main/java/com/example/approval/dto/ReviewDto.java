package com.example.approval.dto;

import com.example.approval.line.entity.ProcessStep;
import com.example.approval.line.entity.ReviewStep;

import java.util.Set;

public class ReviewDto {

    private Set<ReviewStep> steps;

    public Set<ReviewStep> steps(){
        return this.steps;
    }
}
