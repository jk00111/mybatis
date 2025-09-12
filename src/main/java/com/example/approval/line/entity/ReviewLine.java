package com.example.approval.line.entity;

import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.Set;

@RequiredArgsConstructor
public class ReviewLine implements Iterable<ReviewStepImpl> {

    private final Set<ReviewStepImpl> steps;
    @Override
    public Iterator<ReviewStepImpl> iterator() {
        return steps.iterator();
    }

    public ReviewStepImpl get(ApprovalUser user) {
        return steps.stream().filter(step -> step.getReviewerId() == user.getId()).findFirst().orElseThrow();
    }
}
