package com.example.approval.line.service;

import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApprovalEventFactory;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ApprovalStep;
import com.example.approval.line.entity.ProcessStep;
import com.example.approval.line.entity.ReviewLine;
import com.example.approval.line.repository.StepRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LineServiceImpl implements LineService {

    private final StepRepository repository;

    @Override
    public ProcessStep findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public List<ProcessStep> findByApproval(long approvalId) {
        return repository.findByApproval(approvalId);
    }

    @Override
    public void create(ApprovalLine line) {
        line.forEach(repository::create);
    }
    @Override
    public ApprovalEvent approve(ApprovalLine line) {
        ProcessStep current = line.getCurrent();
        current.proceed();
        repository.update(current);

        if (line.hasNext()) {
            activateNext(line);
        }

        return ApprovalEventFactory.ofApprove(line.isApproved());
    }

    @Override
    public ApprovalEvent reject(ApprovalLine line) {
        ProcessStep current = line.getCurrent();
        current.reject();
        repository.update(current);
        return ApprovalEventFactory.ofReject();
    }

    @Override
    public ApprovalEvent review(ReviewLine line) {

        return null;
    }

    @Override
    public void activateNext(ApprovalLine line) {
        ProcessStep next = line.next();
        next.waiting();
        repository.update(next);
    }
}
