package com.example.approval.review.service;

import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.review.entity.Review;

public interface ReviewService {

    void escalate();

    Review findOne(long id);

    void review(Review review, ReviewEvent event);

    void reject(Review review, RejectEvent event);

}
