package com.example.approval.dto;

import com.example.approval.line.entity.ProcessStep;

import java.util.Set;

public class ReviewDto {

    private Set<ProcessStep> steps;

    public Set<ProcessStep> steps(){
        return this.steps;
    }
}
