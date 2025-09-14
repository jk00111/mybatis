package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ReviewLine implements Iterable<ProcessStep> {

    private final Set<ProcessStep> steps;

    public ReviewLine(long id, List<ProcessStep> steps) {
        this.steps = new HashSet<>(steps);
    }

    @Override
    public Iterator<ProcessStep> iterator() {
        return steps.iterator();
    }

    public ProcessStep get(ApprovalUser user) {
        return steps.stream()
                .filter(step -> step.id() == user.getId())
                .findFirst().orElseThrow();
    }

    public boolean isReviewed() {
        return steps.stream()
                .allMatch(step -> StepStatus.REVIEWED.equals(step.status()));
    }
}
