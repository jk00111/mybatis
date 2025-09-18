package com.example.approval.review.service;

import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.review.entity.Review;
import com.example.approval.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;


    @Override
    public Review findOne(long id) {
        return null;
    }

    @Override
    public void escalate(Review review) {

    }

    @Override
    public void review(Review review, ReviewEvent event) {
        review.review(event);
        repository.update(review);
    }

    @Override
    public void reject(Review review, RejectEvent event) {
        review.reject(event);
        repository.update(review);
    }
}
