package com.example.approval;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.service.ApprovalService;
import com.example.approval.dto.EscalateDto;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ReviewLine;
import com.example.approval.line.service.LineService;
import com.example.approval.review.entity.Review;
import com.example.approval.review.service.ReviewService;
import com.example.approval.vo.ApprovalResult;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalFlowService {

    private final ApprovalService approvalService;
    private final ReviewService reviewService;
    private final LineService lineService;

    public ApprovalResult escalate(EscalateDto dto, ApprovalUser requester) {
        return null;
    }

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApproveEvent event = lineService.approve(line);
        Approval approval = approvalService.findOne(id);
        approvalService.approve(approval, event);

        return new ApprovalResult(id, event.isApproved());
    }

    public ApprovalResult reject(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        RejectEvent event = lineService.reject(line);

        Approval approval = approvalService.findOne(id);
        approvalService.reject(approval, event);

        return new ApprovalResult(id, event.isRejected());
    }

    public ApprovalResult review(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByApproval(id));
        ReviewEvent event = lineService.review(reviewLine, user);
        Review review = reviewService.findOne(id);
        reviewService.review(review, event);

        return new ApprovalResult(id, event.isReviewed());
    }
}
