package com.example.approval.line.repository;

import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.line.entity.ProcessStep;
import com.example.approval.line.entity.ReviewStep;

import java.util.List;
import java.util.Set;

public interface StepRepository {

    void create(ProcessStep step);
    void update(ProcessStep step);
    void delete(ProcessStep step);
    ProcessStep findOne(long id);
    List<ApprovalStep> findByApproval(long approvalId);
    Set<ReviewStep> findByReview(long reviewId);
}
