package com.example.approval.line.service;

import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.line.entity.ProcessStep;
import com.example.approval.line.entity.ReviewLine;

import java.util.List;

public interface LineService {

    ProcessStep findOne(long id);

    List<ProcessStep> findByApproval(long approvalId);

    void create(ApprovalLine line);

    ApprovalEvent approve(ApprovalLine line);

    ApprovalEvent reject(ApprovalLine line);

    ApprovalEvent review(ReviewLine line);

    void activateNext(ApprovalLine line);

}
