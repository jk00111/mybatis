package com.example.approval.review.service;

import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.review.entity.Review;

public interface ReviewService {

    void escalate(Review review);

    Review findOne(long id);

    void review(long reviewId, ReviewEvent event);

    void reject(long reviewId, RejectEvent event);

}
