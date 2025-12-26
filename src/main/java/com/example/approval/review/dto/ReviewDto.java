package com.example.approval.review.dto;

import com.example.approval.document.dto.DocumentDto;
import com.example.approval.line.entity.ReviewStep;
import lombok.Getter;

import java.util.Set;

public class ReviewDto {

    @Getter
    private DocumentDto documentDto;
    private Set<ReviewStep> steps;

    public Set<ReviewStep> steps(){
        return this.steps;
    }
}
