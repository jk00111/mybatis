package com.example.approval;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.service.ApprovalService;
import com.example.approval.draft.DraftService;
import com.example.approval.dto.ApprovalDto;
import com.example.approval.dto.DraftDto;
import com.example.approval.dto.ReviewAndApprovalDto;
import com.example.approval.dto.ReviewDto;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ReviewLine;
import com.example.approval.line.entity.ReviewStep;
import com.example.approval.line.service.LineService;
import com.example.approval.review.entity.Review;
import com.example.approval.review.service.ReviewService;
import com.example.approval.vo.ApprovalResult;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/** TODO
 * 결재 - 승인 용어 정리
 * 기안 << 개념이 필요한가 + 용어 변경 필요함
 *
 * */
@RequiredArgsConstructor
public class ApprovalFlowService {

    private final DraftService draftService;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;
    private final LineService lineService;

    public ApprovalResult escalate(ReviewAndApprovalDto dto, ApprovalUser requester) {
        long draftId = draft(new DraftDto(), requester);
        escalateReview(dto.forReview(), requester);
        escalateApproval(dto.forApproval(), requester);
        return ApprovalResult.escalated(draftId);
    }

    public ApprovalResult escalate(ApprovalDto approvalDto, ApprovalUser requester) {
        long draftId = draft(new DraftDto(), requester);
        Approval approval = Approval.escalate(approvalDto);
        approvalService.escalate(approval);

        long id = approval.id();
        ApprovalLine approvalLine = new ApprovalLine(id, approvalDto.steps());
        lineService.create(approvalLine);
        return ApprovalResult.escalated(draftId);
    }

    public ApprovalResult escalate(ReviewDto reviewDto, ApprovalUser requester) {
        long draftId = draft(new DraftDto(), requester);
        Review review = Review.escalate(reviewDto);
        reviewService.escalate(review);

        Set<ReviewStep> steps = reviewDto.steps();
        ReviewLine reviewLine = new ReviewLine(review.id(), steps);
        lineService.create(reviewLine);
        return ApprovalResult.escalated(draftId);
    }

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApproveEvent event = lineService.approve(line);

        approvalService.approve(id, event);
        return new ApprovalResult(id, event.isApproved());
    }

    public ApprovalResult rejectApproval(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        RejectEvent event = lineService.reject(line);

        approvalService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }

    public ApprovalResult review(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ReviewEvent event = lineService.review(reviewLine, user);

        reviewService.review(id, event);
        return new ApprovalResult(id, event.isReviewed());
    }

    public ApprovalResult rejectReview(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ApprovalEvent event = lineService.review(reviewLine, user);

        reviewService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }

    private long draft(DraftDto dto, ApprovalUser requester) {
        return draftService.escalate(dto);
    }

    private void escalateApproval(ApprovalDto approvalDto, ApprovalUser requester) {
        Approval approval = Approval.escalate(approvalDto);
        approvalService.escalate(approval);

        long id = approval.id();
        ApprovalLine approvalLine = new ApprovalLine(id, approvalDto.steps());
        lineService.create(approvalLine);
    }

    private void escalateReview(ReviewDto reviewDto, ApprovalUser requester) {
        Review review = Review.escalate(reviewDto);
        reviewService.escalate(review);

        Set<ReviewStep> steps = reviewDto.steps();
        ReviewLine reviewLine = new ReviewLine(review.id(), steps);
        lineService.create(reviewLine);
    }
}
