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
        return repository.findOne(id);
    }

    @Override
    public void escalate(Review review) {

    }

    @Override
    public void review(long reviewId, ReviewEvent event) {
        if (!event.isReviewed()) {
            return;
        }

        Review review = findOne(reviewId);
        review.review(event);
        repository.update(review);
    }

    @Override
    public void reject(long reviewId, RejectEvent event) {
        Review review = findOne(reviewId);
        review.reject(event);
        repository.update(review);
    }
}
