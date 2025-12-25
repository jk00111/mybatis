package com.example.approval.line.service;

import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.line.entity.*;
import com.example.approval.vo.ApprovalUser;

import java.util.List;
import java.util.Set;

public interface LineService {

    ProcessStep findOne(long id);

    List<ApprovalStep> findByApproval(long approvalId);

    Set<ReviewStep> findByReview(long reviewId);

    void create(ApprovalLine line);

    void create(ReviewLine line);

    ApprovalEvent approve(ApprovalLine line, ApprovalUser user);

    ApprovalEvent reject(ApprovalLine line, ApprovalUser user);

    ApprovalEvent review(ReviewLine line, ApprovalUser reviewer);

}
