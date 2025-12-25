package com.example.approval.line.service;

import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApprovalEventFactory;
import com.example.approval.line.entity.*;
import com.example.approval.line.repository.StepRepository;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final StepRepository repository;

    @Override
    public ProcessStep findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<ApprovalStep> findByApproval(long approvalId) {
        return repository.findByApproval(approvalId);
    }

    @Override
    public Set<ReviewStep> findByReview(long reviewId) {
        return repository.findByReview(reviewId);
    }

    @Override
    public void create(ApprovalLine line) {
        line.forEach(repository::create);
    }

    @Override
    public void create(ReviewLine line) {
        line.forEach(repository::create);
    }

    @Override
    public ApprovalEvent approve(ApprovalLine line, ApprovalUser user) {
        // 이러면 또 명렁-쿼리 분리가 안됌
        ApprovalStep approved = line.approve(user);
        repository.update(approved);

        if (line.hasNext()) {
            activateNext(line);
        }

        return ApprovalEventFactory.ofApprove(line.isApproved());
    }


    /**
    *  디미토 법칙 -> 라인 서비스가 step의 내부구현을 알아야 할 필요가 있는가??
    *  라인이 전달 역할만 한다면 라인 서비스의 역할은 머임?
    *  행위를 메시지로 요청하면.. 업데이트 과정떄문에 스탭자체 반환은 필요함
    *
    * */
    @Override
    public ApprovalEvent reject(ApprovalLine line, ApprovalUser user) {
        ProcessStep current = line.getCurrent();
        current.reject(user);
        repository.update(current);
        return ApprovalEventFactory.ofReject();
    }

    @Override
    public ApprovalEvent review(ReviewLine line, ApprovalUser reviewer) {
        ProcessStep reviewStep = line.get(reviewer);
        reviewStep.proceed(reviewer);
        repository.update(reviewStep);
        return ApprovalEventFactory.ofApprove(line.isReviewed());
    }

    private void activateNext(ApprovalLine line) {
        ApprovalStep next = line.next();
        next.waiting();
        repository.update(next);
    }
}
