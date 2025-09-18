package com.example.approval.line.repository;

import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.line.entity.ProcessStep;

import java.util.List;
import java.util.Set;

public interface StepRepository {

    void create(ProcessStep step);
    void update(ProcessStep step);
    void delete(ProcessStep step);
    ProcessStep findOne(long id);
    List<ProcessStep> findByApproval(long approvalId);
    Set<ProcessStep> findByReview(long reviewId);
}
